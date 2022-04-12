<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ownTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
	<title>Demo</title>
	<%-- version agnostic thanks to webjars-locator package --%>
	<link href="webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="webjars/jquery/jquery.min.js"></script>
	<script src="webjars/popper.js/umd/popper.min.js"></script> 
	<script src="webjars/bootstrap/js/bootstrap.min.js"></script> 
<!-- 		 <link href="/css/custom.css" rel="stylesheet">  -->

	<script type="text/javascript">
	
	$(document).ready(function(){

		$("#suggest").hide();
		
		$(function () {
			  $("#suggest").popover()
			})
		
		$(function () {
			  $('[data-toggle="tooltip"]').tooltip()
			})

		$('input').removeClass('active');

		var previousgame="";

		$('label').click(function(){
			gameid=$(this).attr("id");
			$("#in-img").attr("src","/games/in/img/"+gameid+".png");
			$.ajax({
		           url : "/games/in/pl/"+gameid+".pl",
		           dataType: "text",
		           success : function (data) { $("#in-pl").val(data);}
		       });
		       
			$("#in-eng").load("/games/in/eng/"+gameid+".txt");
			
			$("#solve").removeClass("disabled");
			$("#out-img").attr("src","/games/out/noimage.png");
			$("#out-pl").text("");
			$("#out-exeinfo").text("...");
			$("#loading").attr("src","");

      	  $('[data-toggle="popover"]').popover('hide')
			$.ajax({
	              type: "POST",
	              url: "/games/demo/suggest",
	              data: "gameid="+gameid,
	              dataType: "html",
	              success: function(suggestion){
	            	  $("#suggest").attr("data-content",suggestion);
	            	  $("#suggest").show();
	            	  $('[data-toggle="popover"]').popover('show')
	              }
	         });

			
			
		});

		$('#solve').click(function(){
			gametosolve=$('label.active').attr("id")+"-"+ Math.floor((Math.random() * 100) + 1);
			if (previousgame!="" && previousgame!=gametosolve){
				$.ajax({
	 	              type: "POST",
	 	              url: "/games/demo/aftersolving",
	 	              data: "imagename="+previousgame,
	 	              dataType: "html",
	 	         });
				}
				 
			$("#solve").html("<span class=\"spinner-border spinner-border-sm\" role=\"status\" aria-hidden=\"true\"></span>Solve");
			$("#solve").addClass("disabled");
			var ajaxTime= new Date();
			$.ajax({
	              type: "POST",
	              url: "/games/demo/solve",
	              data: "gameid="+gametosolve+"&inpl="+$('#in-pl').val(),
	              dataType: "html",	              
	              success: function(msg){
	                $("#out-pl").html(msg);
	                $.ajax({
	     			    url:"/games/out/img/"+gametosolve+".png",
	     			    type:'HEAD',
	     			    error: function() {$("#out-img").attr("src","/games/out/noimage.png");},
	     			    success: function(){
	     			    	if (msg.includes("false.") || msg.includes("ERROR"))
		     			    	$("#out-img").attr("src","/games/out/noimage.png");
	     			    	else{
		     			    	$("#out-img").attr("src","/games/out/img/"+gametosolve+".png");//.trigger('imgout-changed');
	     			    	}
	     			    }
	     			}).done(function(){
     			    	previousgame=gametosolve;
     			    });	
	                
	              },
	              error: function(){alert("Error...");}
	          }).done(function () {
	        	  var totalTime = new Date().getTime()-ajaxTime.getTime();
	        	  $('#out-exeinfo').text("Swipl called at "+ajaxTime.toUTCString()+". Total time: "+totalTime +"ms");
	        	  $('#out-pl').scrollTop($('#out-pl')[0].scrollHeight);    
	        	  $("#solve").html("Solve");
	  			  $("#solve").removeClass("disabled");
	  			});
				
		});

		$('img').on("error", function () {
		    this.src = ResolveUrl("/games/out/noimage.png");
		});
	});

	
	</script>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>${title}</h1>
			<p>
				This is a demo application for our Prolog puzzle reasoning library.
			</p>
		</div>
	</div>
	
	

<!-- 	GLOBAL LAYOUT -->
	<div class="container">
	
	<div class="row"> 
		<div class="col-md-12">
			<ownTag:navBar />
			
<!-- 			<div class="row"> -->
<!-- 				<div class="col-sm-11 alert alert-primary" role="alert"> -->
<!-- 				 Select a game and press Solve -->
<!-- 				</div> -->
<!-- 			</div> -->
			<br>
			<p>Select a game and press Solve</p>
		
			<div class="row">
		      <div class="col-sm-1">
		      
				<div class="btn-group-toggle btn-group-vertical btn-group-sm" data-toggle="buttons" id="game-buttons">
				<c:set var="gameCount" value="0" scope="page" />
		      	<c:forEach var="anActiveGame" items="${activeGameList}">
		      		<c:set var="gameCount" value="${gameCount + 1}" scope="page"/>
		      		<label class="btn btn-outline-secondary" id="${anActiveGame.getName()}" data-toggle="tooltip" data-placement="left" title="${anActiveGame.getType().human_readable_name()}">
					    <input type="radio" >${gameCount}
<%-- 					    ${pageContext.getAttribute("gameCount")} --%>
					</label>
		      	</c:forEach>
		      	</div>
		      </div>
		      
		      <div class="col-sm-11"  >
		      		
			      <div class="row">
			      	<div class="col-sm-6">
			      	  <div class="row">
			      	  	<div class="col-sm-6" style="text-align: left;margin-top: 10px;margin-bottom: 5px;">
			      			Human readable:
			      		</div>
			      	  </div>
			      	  	<div class="row">
			      	  	<div class="col-sm-12"> 
			      	  <div class="border border-secondary" >
				      	<div class=container style="text-align: center;">
				      		<img  id="in-img" width="200" src="/games/out/noimage.png" >
				      	</div>
<!-- 				      	<br> -->
				      	<div class=container id="in-eng" style="height: 100px; font-size: 13px;"></div>
				      </div>
				      </div>
				      </div>
			      	</div>
			      	<div class="col-sm-6">
			      	<div class="row">
			      		<div class="col-sm-6" style="text-align: left;margin-top: 10px;margin-bottom: 5px;">
			      			Prolog translation:
			      		</div>
			      		<div class="col-sm-6" style="text-align: right;">
			      			<button id=suggest type="button" class="btn btn-link" 
			      			data-container="body" data-placement="top" data-toggle="popover"
			      			title="Try to..." data-content="" >...</button> 
			      
			      		</div>
			      	</div>
			      	<div class="row">
			      		<div class="col-sm-12" >
			      		<textarea id="in-pl" class="form-control form-control-sm" rows="14" ></textarea>
			      		</div>
			      	</div>
			      	 <!-- <p>Prolog translation: 
			      	 </p>
			      	  <textarea id="in-pl" class="form-control form-control-sm" rows="14" >
				      </textarea> -->
				      
			      	</div>			      
			      </div>
			      
			      <div class="row">
				    <div class="col-sm-12"  >
				      <div style="text-align: right;margin-top: 5px;">
				      
			      	   
					     <button id=solve type="button" class="btn btn-primary disabled">Solve</button>
					  </div>
					</div>
				  </div>
				  
				  <div class="row">
			      		<div class="col-sm-6">
			      			<p>Output image:</p>
					      	<div class="border border-secondary" >
						      	<div class=container style="text-align: center;height: 218px;">
						      		<img  id="out-img" width="200" src="/games/out/noimage.png">
						      	</div>
					      	<div class=container id="out-eng"></div>
			      			</div>
			      		</div>
			      		<div class="col-sm-6">
			      			<p>Swipl console:</p>
					      	<textarea id="out-pl" class="form-control form-control-sm" rows="9" ></textarea>
					      	<div id="out-exeinfo" class=small>...</div>
					      	
						</div>
				  	  </div>
		        </div>
		      
		    </div> 
		</div>
	</div>
	
	


	<hr>
	<footer>
		<p>University of Bologna - ASIA-GiM Project</p>
	</footer>
	
	</div> <!--  End GLOBAL LAYOUT -->
</body>
</html>
