<html id="thepage">
<title>JP's prototype/script.aculo.us Toy</title>
<head>
<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'boxes.css')}" />
<g:javascript library="prototype"/>
<g:javascript library="scriptaculous"/>
<g:javascript library="demo"/>
</head>
<body id='body'>
	<div id='title' class="whiteBox">
		<lu><g:link controller="jpslab" action="creations" >Back to JP's Lab</g:link></lu>
		<lu><h4>Box Creation</h4></lu>
		<li><a id="opener" href="#">Create 25 Boxes</a>&nbsp;<a id="circleCreator" href="#">Create a new Newman</a></li>
		<li>Stack Boxes on Create<input type="checkbox" id="stacker"/></li>
		<lu><h4>Box Movement</h4></lu>
		<li>Move Objects To Mouse When Clicked<br/>(Right click circles)<input type="checkbox" id="mover"/></li>
		<li><a id="circler" href="#">Circle the Screen</a>&nbsp;&nbsp;<a id="stackem" href="#">Center 'em</a></li>
		<li><a id="alldragger" href="#">Make All Boxes Draggable</a></li>
		<li><a id="cutEm" href="#">Cut the Deck</a>&nbsp;<a id="shuffleEm" href="#">Shuffle</a></li>
		<lu><h4>Effects</h4><lu>
		<li>
			On Boxes
			<input type="radio" id="onBox" name="effectSwitch" value="demoBox"/>
			On Newmans
			<input type="radio" id="onNewman"  name="effectSwitch" value="circle"/>
			On Both
			<input type="radio" checked="checked" id="onBox"  name="effectSwitch" value="both"/>
		</li>
		<li>
			<a id="shinker" href="#">Shrink</a>
			<a id="fader" href="#">Fade</a>
			<a id="dropper" href="#">DropOut</a>
			<a id="folder" href="#">Fold</a>
			<a id="puffer" href="#">Puff</a>
			<a id="squisher" href="#">Squish</a>
			<a id="switcher" href="#">SwitchOff</a>
			<a id="morpher" href="#">MorphAll</a>
		</li>
		<li>
			<input type="button" id="randomBoom"  value="Random Boom"/>
		</li>
		<br/>
		Effect Speed Slider
		<div id="slideTrack" class="track" style="width: 20em;" >
					<div id="slideHandle" class="handle" ></div>
					<p id="speed"></p>
		</div>
	</div>


</body>
</html>