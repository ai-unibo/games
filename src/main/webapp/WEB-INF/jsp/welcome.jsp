<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ownTag" tagdir="/WEB-INF/tags" %>



<!DOCTYPE html>
<html>
<head>
	<title>Welcome</title>	
	<%-- version agnostic thanks to webjars-locator package  --%>
	<link href="webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="webjars/jquery/jquery.min.js"></script>
	<script src="webjars/bootstrap/js/bootstrap.min.js"></script> 
	<%-- version explicit
	<link href="/games/webjars/bootstrap/4.3.0/css/bootstrap.min.css" rel="stylesheet">
	<script src="/games/webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="/games/webjars/bootstrap/4.3.0/js/bootstrap.min.js"></script> --%>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1>${title}</h1>
			<p>
				Welcome to the demo site of Prolog puzzle reasoning library.
			</p>
		</div>
	</div>
	
	

<!-- 	GLOBAL LAYOUT -->
	<div class="container">
	
	<div class="row"> 
	<div class="col-md-12">
	<ownTag:navBar />
	<br>
	<p>
	Despite the indisputable progresses made by Artificial Intelligence in many research fields, some tasks that are rather easy for a human being, continue to be challenging for a machine. An emblematic example of such tasks is the resolution of mathematical puzzles with diagrams given their textual and graphical description. Even when considering puzzles that can be solved by a 4th grade student, the multimodal interpretation of both natural language and diagram, and the integration of the derived knowledge, possibly involving common-sense concepts, makes the resolution extremely challenging for a machine. Whereas sub-symbolical approaches have proven successful in fields like image recognition and Natural Language Processing, the combination of these techniques towards the identification of the correct answer appears to be a matter of reasoning, more suitable for the application of a symbolic technique.
We employ logic programming to perform spatial reasoning on the puzzle's diagram and integrate the deriving knowledge into the solving process.<br>
<br>
This site proposes a working demo of a Prolog puzzle reasoning library<br>
<!--  This site proposes a working demo of a Prolog spatial reasoning described in<br>
<br>
R. Buscaroli, F. Chesani, G. Giuliani, D. Loreti and P. Mello, <span class="font-italic">"A Prolog application for reasoning on math puzzles with diagrams"</span>. Under consideration for publication in Theory and Practice of Logic Programming<br>
-->
<br>
All the games used in this site are translated from  <a href="http://matematica.unibocconi.it/articoli/archivio-giochi">PRISTEM Research Center catalogue</a> of Bocconi Univesity of Milan, Italy.

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
