var Demo = {
 	boxes : [],
 	count : -1,
 	colorLetters : ['0','1','2','3','4','5','6','A','B','C','D','E','F'],
 	dragData : $H(),
 	mouseMoving : false,
 	circles : [],
 	colorsDropped : [],
 	duration:5/4,
 	xValues : [],
 	yValues : [],

 	init : function(){
		Demo.loadObservers();
		Demo.initSlider();
		Demo.initCircleCoords();
		new Draggable($('title'));
		Demo.showDiv();
	},
	
	initCircleCoords : function(){
		var radius = 200;
		var steps = 25;
		for (var i = 0; i < steps; i++) {
		    Demo.xValues[i] = (radius * Math.cos(2 * Math.PI * i / steps));
		    Demo.yValues[i] = (radius * Math.sin(2 * Math.PI * i / steps));
		}
	},

	initSlider : function(){
			new Control.Slider('slideHandle','slideTrack',{
				sliderValue:0.25,
				onSlide:function(value,slider){
					Demo.duration = value * 5;
					$('slideHandle').setStyle({cursor:'-moz-grabbing'});
					$('speed').update(Demo.duration.toFixed(2)+'seconds');
					
				},
				onChange:function(){
					$('slideHandle').setStyle({cursor:'-moz-grab'});
				}
			});
		$('speed').update(Demo.duration.toFixed(2)+'seconds');
	},

	loadObservers : function(){
		Event.observe($('opener'), 'click', function(e){Event.stop(e); Demo.showDiv()});
		Event.observe($('shinker'), 'click', function(e){Event.stop(e); Demo.shrinkDiv()});
		Event.observe($('fader'), 'click', function(e){Event.stop(e); Demo.fadeDiv()});
		Event.observe($('dropper'), 'click', function(e){Event.stop(e); Demo.dropDiv()});
		Event.observe($('folder'), 'click', function(e){Event.stop(e); Demo.foldDiv()});
		Event.observe($('puffer'), 'click', function(e){Event.stop(e); Demo.puffDiv()});
		Event.observe($('squisher'), 'click', function(e){Event.stop(e); Demo.squishDiv()});
		Event.observe($('switcher'), 'click', function(e){Event.stop(e); Demo.switchDiv()});	
		Event.observe($('alldragger'), 'click', function(e){Event.stop(e); Demo.allDraggable()});
		Event.observe($('circler'), 'click', function(e){Event.stop(e); Demo.circleEm()});
		Event.observe($('mover'), 'change', function(){ Demo.toggleMove()});
		Event.observe($('stackem'), 'click', function(e){Event.stop(e); Demo.stackEm()});
		Event.observe($('circleCreator'),'click',function(e){Event.stop(e); Demo.showCircle()});
		Event.observe($('cutEm'),'click',function(e){Event.stop(e); Demo.cutTheDeck()});
		Event.observe($('shuffleEm'),'click',function(e){Event.stop(e); Demo.shuffleTheDeck()});
		Event.observe($('morpher'),'click',function(e){Event.stop(e); Demo.morphAll()});
		Event.observe($('randomBoom'),'click',function(e){Demo.exlpodeRandom()});
		

	},
	
	showCircle : function(){
		var circleId = 'circle'+Demo.circles.size();
		Demo.circles.push(circleId);
		var circle = Builder.node('img',{id:circleId,src:"../images/newman.jpeg",'class':'circle',style:'top:0px; left:350px; display:none; position:absolute;'});
		document.body.appendChild(circle);
		Demo.createDraggable(circle,false);
	 			
		Effect.Appear(circle,{duration:Demo.duration,
			beforeFinish:function(){
				new Effect.Move(circle,{x:Math.round(Math.random()*600),
				y:Math.round(Math.random()*300), 
				duration:Demo.duration, 
				mode:'relative',
				transition:Effect.Transitions.spring});	
			}
			});
		Event.observe(circle,'dblclick',function(e){Demo.naAhAh(circle, e)});
	},
	
	naAhAh : function(newman, event){
		Effect.Shake(newman,{duration:3.5});
		if(!$('nahahah')){
			var nah = Builder.node('span',{id:'nahahah', 'class':'nahAh'});
			$('body').appendChild(nah);
			$('nahahah').update('Nah ah aaah! you didnt say the magic word!');
			Effect.tagifyText('nahahah');
			
		}
		else{
			Effect.multiple('nahahah',Effect.Appear,{speed:0.001});
		}
		Sound.play('../sounds/magicword.mp3');
		Effect.multiple('nahahah',Effect.Move,{x:event.clientX-200, y:event.clientY-200, mode:'absolute',speed:0.025});
		Effect.multiple('nahahah',Effect.Fade,{speed:0.05});
	},
	
	showDiv : function(){
		var loop = 1;
		var boxes = [];
		while(loop <=25){
			loop=loop+1;
			var boxCount = $$('.demoBox').size();
			var boxId = 'demoBox' + boxCount;
			var theBox = null;
			if($(boxId)){
				boxCount++;
				boxId = 'demoBox' + boxCount;
			}
		
			if($F('stacker')!='on'){
				theBox = Builder.node('div', {id: boxId,
					'class':'demoBox',
					style: "top:100px; left:350px; background-color:"+Demo.generateColor()+";"});
			}
			else{
				theBox = Builder.node('div', {id: boxId,
					'class':'demoBox',
					style: "top:350px; left:350px;background-color:"+Demo.generateColor()+";"});
			
			}
			
			document.body.appendChild(theBox);  
			$(theBox.getAttribute('id')).update('<input type="button" id="explode'+boxId+'" onclick="Demo.explodeIt('+boxId+');" value="BOOM"/>');
			
			boxes.push(theBox);
		}


		if($F('stacker')!='on'){
		boxes.each(function(newbox){
			new Effect.Move(newbox,{x:Math.round(10+Math.random()*600),
					y:Math.round(Math.random()*300), 
					duration:Demo.duration, 
					mode:'relative',
					transition:Effect.Transitions.spring});
			});
		}
		
	},
	
	exlpodeRandom : function(){
		var allBoxes = $$('.demoBox');
		if(allBoxes.size()==0){
			alert('Please generate some boxes to see this effect!');
		}
		else{
			for(var i=0;i<6;i++){
				var box = allBoxes[Math.floor(Math.random()*allBoxes.size())];
				if(box){
					Demo.explodeIt(box.getAttribute('id'));		
				}
			}	
		}
	
	},
	
	explodeIt : function(boxId){
		Sound.play('../sounds/Boom'+Math.ceil(Math.random()*4)+'.mp3');
		var box = $(boxId);
		var top = new Number(box.getStyle('top').replace('px',''));
		var left = new Number(box.getStyle('left').replace('px',''));
		var origSize = new Number(box.getStyle('width').replace('px',''));
		var newSize = Math.floor(origSize/4);
		var color = box.getStyle('background-color');
		
		var x=left;
		var y=top;
		
		var pieces = [];
		
		for(var i=0;i<5;i++){
			for(var j=0;j<5;j++){
				var newPiece = Builder.node('div', {id: i,
					'class':'demoBox',
					style: "top:"+y+"; left:"+x+"; background-color:"+color+"; height:"+newSize+"; width:"+newSize+""
				});	
				document.body.appendChild(newPiece); 
				pieces.push(newPiece);
				x += newSize;			
			}
			x = left;
			y += newSize;
		}
		box.remove();

		var xPos = Math.floor((left+(origSize/2)));
		var yPos = Math.floor((top+(origSize/2)));
		var count = 0;
		var singleCount = 0;


		pieces.each(function(box){
			x = xPos + Demo.xValues[count];
			y = yPos + Demo.yValues[count];
			
			new Effect.Move(box, {
				x: x, y: y, mode: 'absolute',
				duration :0.3,
				queue:"blowdUp",
				afterFinish:function(){
					new Effect.DropOut(box,{duration:3.0,
						queue:"blowdUp",
						afterFinish:function(){
							box.remove();
						}
						});
				}
			});
			count++;
			
		});	

	
		return true;
	},
	
	dropOutCollection : function(pieces){
		
	},

	stackEm : function(){
		var allBoxes = $$('.demoBox');
		$$('.circle').each(function(circle){allBoxes.push(circle)});
		
		var windowHeight = window.outerHeight;
		if(isNaN(windowHeight)){
			windowHeight = 600;
		}
		var windowWidth = window.outerWidth;
		if(isNaN(windowWidth)){
			windowWidth = 1000;
		}	
		
		Effect.multiple(allBoxes,Effect.Move,{
				x:Math.floor(windowWidth/2), y:Math.floor(windowHeight/2), mode: 'absolute',
				transition: Effect.Transitions.spring,
				duration : Demo.duration});
				
	},
	
	circleEm : function(){
		var allBoxes = $$('.demoBox');
		$$('.circle').each(function(circle){allBoxes.push(circle)});
		var xPos = 0;
		var yPos = 0
		var count = 0;
		
		var windowHeight = window.outerHeight;
		if(isNaN(windowHeight)){
			windowHeight = 600;
		}
		var windowWidth = window.outerWidth;
		if(isNaN(windowWidth)){
			windowWidth = 900;
		}
		
		allBoxes.each(function(box,fadeOut){
			count++;
			new Effect.Move(box, {
				x: xPos, y: yPos, mode: 'absolute',
				transition: Effect.Transitions.spring,
				duration : Demo.duration,
				afterEffect : function(){if(fadeOut){Effect.DropOut(piece,{duration:3.0});}}
				});
			
			if(count<(allBoxes.size()/4)){
				xPos = 0
				yPos = Math.floor(((windowHeight-200)/(allBoxes.size()/4))*count);
			}
			else if((count>(allBoxes.size()/4)) && (count<(allBoxes.size()/2))){
				xPos = Math.floor((windowWidth/(allBoxes.size()/4))*(count-(allBoxes.size()/4)));
				yPos = windowHeight-200;
			}
			else if((count>(allBoxes.size()/2)) && (count<((allBoxes.size()*3)/4))){
				xPos = (windowWidth-100);
				yPos = Math.floor(((windowHeight-200)/(allBoxes.size()/4))*(count-(allBoxes.size()/2)));
			}
			else{
				xPos = Math.floor((windowWidth/(allBoxes.size()/4))*(count-(allBoxes.size()*3/4)));
				yPos = 0;
			}
			
		});	
	
	},
	
	circleObjs : function(allBoxes){
		
	
	},
	
	cutTheDeck:function(){
		var allBoxes = $$('.demoBox');
		$$('.circle').each(function(circle){allBoxes.push(circle)});
		var left = [];
		var right = [];
		for(var i=0; i<allBoxes.size();i++){
			if((i % 2) == 0){
				left.push(allBoxes[i]);
				allBoxes[i].addClassName('left');
			}
			else{
				right.push(allBoxes[i]);
				allBoxes[i].addClassName('right');
			}
		}
		
		Effect.multiple(left,Effect.Move,{
			x: 450, y: 350, mode: 'absolute',
			transition: Effect.Transitions.linear,
			duration : Demo.duration});
		Effect.multiple(right,Effect.Move,{
			x: 800, y: 350, mode: 'absolute',
			transition: Effect.Transitions.linear,
			duration : Demo.duration});

	},
	
	shuffleTheDeck : function(){
		var allBoxes = $$('.circle');
		$$('.demoBox').each(function(circle){allBoxes.push(circle)});
		var allOfThem = []
		var lCount = 0;
		var rCount = 0;
		var left = $$('.left');
		var right = $$('.right');
		
		for(i=0; i<allBoxes.size();i++){
			if((i % 2) == 0){
				if(lCount<left.size()){
					allOfThem.push(left[lCount]);
					left[lCount].removeClassName('left');
					left[lCount].setStyle({zIndex:i});
					lCount++;
				}
				else if(rCount<right.size()){
					allOfThem.push(right[rCount]);
					right[rCount].removeClassName('right');
					right[rCount].setStyle({zIndex:i});
					rCount++;
				}
			}
			else{
				if(rCount<right.size()){
					allOfThem.push(right[rCount]);
					right[rCount].removeClassName('right');
					right[rCount].setStyle({zIndex:i});
					rCount++;
				}
				else if(lCount<left.size()){
					allOfThem.push(left[lCount]);
					left[lCount].removeClassName('left');
					left[lCount].setStyle({zIndex:i});
					lCount++;
				}
			}
					
		}
				
		Effect.multiple(allOfThem,Effect.Move,{
			x: 600, y: 350, mode: 'absolute',
			transition: Effect.Transitions.linear,
			duration : Demo.duration});
	
	
	},
	
	moveAllToMouse : function(event){
		if($F('mover')=='on'&& !Demo.mouseMoving){
			Demo.mouseMoving = true;
			var allBoxes = $$('.demoBox');
			$$('.circle').each(function(circle){allBoxes.push(circle)});
			
			Effect.multiple(allBoxes,Effect.Move,{
				x: event.clientX, y: event.clientY, mode: 'absolute',
				transition: Effect.Transitions.spring,
				duration : Demo.duration});
				
			Demo.mouseMoving = false;
		}
		
		

	
	
	},
	
	toggleMove : function() {
		if($F('mover')=='on'){
			Event.observe('thepage','click',function(e){Demo.moveAllToMouse(e);});
			Event.observe('thepage','contextmenu',function(e){Event.stop(e);Demo.circleEm();});
		}
		else{
			Event.stopObserving('thepage','click');
			Event.stopObserving('thepage','contextmenu');
		}
	},
		
	switchDiv : function(){
		
		if(Demo.divExists()){
			var box = Demo.popTopBox();
			new Effect.SwitchOff(box, {duration : Demo.duration,
				afterFinish : function() {new Effect.Appear(box,{duration:Demo.duration})}});
			
		}
		
	},
	
	shrinkDiv : function(){
	
		if(Demo.divExists()){
			var box = Demo.popTopBox();
			new Effect.Shrink(box, {duration : Demo.duration,
				afterFinish : function() {new Effect.Grow(box,{duration:Demo.duration})}});
			
		}
	
	},
	
	fadeDiv : function(){
		
		if(Demo.divExists()){
			var box = Demo.popTopBox();
			new Effect.Fade(box, {duration : Demo.duration,
				afterFinish : function() {new Effect.Appear(box,{duration:Demo.duration})}});
			
		}
		
	},
		
	dropDiv : function(){
			
		if(Demo.divExists()){
			var box = Demo.popTopBox();
			new Effect.DropOut(box, {duration : Demo.duration,
				afterFinish : function() {new Effect.Appear(box,{duration:Demo.duration})}});	
			
		}
			
	},
	
	foldDiv : function(){
	
		if(Demo.divExists()){
			var box = Demo.popTopBox();
			new Effect.Fold(box, {duration : Demo.duration,
				afterFinish : function() {new Effect.Appear(box,{duration:Demo.duration})}});
			
		}
	
	},
	
	puffDiv : function(){
		
		if(Demo.divExists()){
			var box = Demo.popTopBox();
			new Effect.Puff(box, {duration : Demo.duration,
				afterFinish : function() {new Effect.Appear(box,{duration:Demo.duration})}});
			
		}
		
	},
		
	squishDiv : function(){
			
		if(Demo.divExists()){	
			var box = Demo.popTopBox();
			new Effect.Squish(box, {duration : Demo.duration,
				afterFinish : function() {new Effect.Appear(box,{duration:Demo.duration})}});	
			
		}
		
	},
	
	divExists : function(){
		var objects = [];
		if($F('onBox')=='demoBox'){
			$$('.demoBox').each(function(box){objects.push(box);});
		}
		else if($F('onNewman')=='circle'){
			$$('.circle').each(function(circle){objects.push(circle);});
		}
		else{
			$$('.demoBox').each(function(box){objects.push(box);});
			$$('.circle').each(function(circle){objects.push(circle);});	 	
	 	}
	 	
		if(objects.any()){
			return true;
		}
		else{
			alert('There are no objects on the stack');
			return false;
		}
	},
	
	toggleDrag : function(anchorId) {
		var boxNum = anchorId.replace(/track/,'');
		if(Demo.divExists()){
	 		var theBox = $('demoBox'+boxNum);
	 		Demo.createDraggable(theBox,true);
		}
		
		
	 },
	 
	 morphAll : function(){
	 	var allObjects = [];
	 	$$('.demoBox').each(function(box){allObjects.push(box);});;
	 	$$('.circle').each(function(circle){allObjects.push(circle);});
	 	
	 	
	 	allObjects.each(function(obj){
	 		
	 		new Effect.Morph(obj,{
				style:{backgroundColor:'#'+Demo.generateColor(),
					height:(Math.random()*350)+'px',
					width:(Math.random()*350)+'px'},
				duration:Demo.duration
			});
			
			new Effect.Move(obj,{x:Math.round(Math.random()*Math.floor(window.outerWidth-100)),
				y:Math.round(Math.random()*Math.floor(window.outerHeight-100)), 
				duration:Demo.duration, 
				mode:'absolute',
				transition:Effect.Transitions.linear});	
	 	
	 	});
	 	
	 },
	 
	 changeColor : function(anchorId){
	 	var boxNum = anchorId.replace(/color/,'');
		var theBox = $('demoBox'+boxNum);
		new Effect.Morph(theBox,{
			style:{backgroundColor:'#'+Demo.generateColor()},
			duration:Demo.duration
			}); 
	 	
	 },
	 
	 createDraggable : function(theBox, addHandle){
	 	var handle;
	 	if(addHandle){
	 		var inner = theBox.innerHTML
	 		
	 		theBox.update(inner+'<h4>Click and Drag Here</h4>');
	 		
			handle = theBox.down('h4');
		}
		else{
			handle = theBox;
		}
		
	 	new Draggable(theBox,{handle:handle,
	 	 	onStart:function(){
	 	 		var boxId = theBox.getAttribute('id');
	 	 		var x = theBox.getStyle('left').replace(/px/,'');
	 	 		var y = theBox.getStyle('top').replace(/px/,'');
	 	 		Demo.dragData.set(boxId+'x', x);
	 	 		Demo.dragData.set(boxId+'y', y);
	 	 		Demo.dragData.set(boxId+'time', new Date().getTime()/1000);
	 	 	},
	 	 	onEnd:function(){
	 	 		var boxId = theBox.getAttribute('id');
	 	 		var endX = theBox.getStyle('left').replace(/px/,'');
	 			var endY = theBox.getStyle('top').replace(/px/,'');
	 			var startTime= Demo.dragData.get(boxId+'time');
	 			var endTime= new Date().getTime()/1000;

	 	 		new Effect.Move(theBox, {
	 				x: (endX-Demo.dragData.get(boxId+'x')), y: (endY-Demo.dragData.get(boxId+'y')), mode: 'relative',
	 				duration : (endTime-startTime)*2,
	 				transition: Effect.Transitions.linear});
	 			Demo.dragData.unset(boxId+'x');	
	 			Demo.dragData.unset(boxId+'y');	
	 			Demo.dragData.unset(boxId+'time');
	 	 	},
	 	 	onDropped:function(){
	 	 		if(theBox.getAttribute('class')=='dropped'){
	 	 			this.onEnd=function(){};
	 	 			handle.remove();
	 	 		}
	 	}
	 	});
	 },
	 
	 removeBox : function(anchorId){
	 
	 	var boxNum = anchorId.replace(/remove/,''); 
	 	if(Demo.divExists()){
			 var theBox = $('demoBox'+boxNum);
			 theBox.remove();
			
		}
		
	 
	 },
	 
	 allDraggable : function(){
	 	var allBoxes = $$('.demoBox');
	 	allBoxes.each(function(box){
	 		Demo.createDraggable(box,true);
	 	});
	 	
	 },
	 
	 popTopBox : function(){
	 	var objects = [];
	 	if($F('onBox')=='demoBox'){
	 		$$('.demoBox').each(function(box){objects.push(box);});
	 	}
	 	else if($F('onNewman')=='circle'){
	 		$$('.circle').each(function(circle){objects.push(circle);});
	 	}
	 	else{
	 		$$('.demoBox').each(function(box){objects.push(box);});
	 		$$('.circle').each(function(circle){objects.push(circle);});	 	
	 	}
	 
	 	if(objects.any()){
	 		if(Demo.count<0 || Demo.count>=objects.size()){
				Demo.count = objects.size()-1;
			}
			var box = objects[Demo.count];
			Demo.count--;
			
			return box;
		}
	 },
	 
	 generateColor : function(){
	 	var color = '';
	 	for(var i = 0; i < 6; i++){
	 		var index = Math.round(Math.random()*(Demo.colorLetters.size()-1));
	 		color = color + Demo.colorLetters[index];
	 	}
	 	return color;
	 
	 },
	 
	 biggerBox : function(anchorId){
	 	var boxNum = anchorId.replace(/big/,''); 
	 	new Effect.Scale('demoBox'+boxNum , 150, { scaleFromCenter: true });
	 },
	 
	 smallerBox : function(anchorId){
	 	 	var boxNum = anchorId.replace(/small/,''); 
	 	 	new Effect.Scale('demoBox'+boxNum , 50, { scaleFromCenter: true });
	 }

};

document.observe('dom:loaded', function(){Demo.init();});