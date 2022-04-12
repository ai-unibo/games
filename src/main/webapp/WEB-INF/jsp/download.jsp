<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ownTag" tagdir="/WEB-INF/tags" %>



<!DOCTYPE html>
<html>
<head>
	<title>Welcome</title>	
	<%-- version agnostic thanks to webjars-locator package --%>
	<link href="webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="webjars/jquery/jquery.min.js"></script>
	<script src="webjars/bootstrap/js/bootstrap.min.js"></script> 
<!-- 		 <link href="css/custom.css" rel="stylesheet">  -->
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>${title}</h1>
			<p>
				How to get our Prolog puzzle reasoning library.
			</p>
		</div>
	</div>
	
	

<!-- 	GLOBAL LAYOUT -->
	<div class="container">
	
	<div class="row"> 
	<div class="col-md-12">
	<ownTag:navBar />
	<br>
	The puzzle reasoning library depends on the following:
	<ul>
	<li> OpenCV 4.3.0
	<li> swipl 8.0.3
	<li> Tesseract OCR 4.00
	</ul>
	<p>
	The source code and installation instructions are available on 
	<a href="https://github.com/ai-unibo/spatial-reasoning">github</a>
	
	</p>
	</div>
	</div>
	
<!-- 	<div class="row"> -->
	

<!-- <!-- 	Left Margin --> 
<!-- 	<div class="col-md-1"> -->
<!-- 	</div> -->


	
<!-- <!-- 	Main Content --> 
<!-- 	<div class="col-md-10"> -->
		
<!-- 		<p> -->
<!-- 		Descriptive text... -->
<!-- 		</p> -->
<!-- 		<div class="alert alert-success">  -->
<!-- 		    <a href="#" class="close" data-dismiss="alert" -->
<!--                   aria-label="close">×</a>         -->
<!-- 		        <strong>Success!</strong> It is working as we expected. -->
<!-- 		</div> -->
		
<!-- 	</div> -->
	
<!-- <!-- 	Right Margin --> 
<!-- 	<div class="col-md-1"> -->
<!-- 	</div> -->

<!-- 	</div> End Row -->


	<hr>
	<footer>
		<p>University of Bologna - ASIA-GiM Project</p>
	</footer>
	
	</div> <!--  End GLOBAL LAYOUT -->
</body>
</html>
