class BoxTagLib {
	def customBox = { attrs, body ->
		def name = attrs['boxname']
		def box = Box.findByBoxName(name)
		if(box==null){
			box = new Box(boxName:name,backColor:'#ffffff',fontColor:'#000000',text:'*',fontRedNum:'1.0',fontBlueNum:'1.0',fontGreenNum:'1.0',backRedNum:'1.0',backBlueNum:'1.0',backGreenNum:'1.0')
			box.save()
		}
		def output = ''
		if(session.user || (attrs['public']=='true')){	
			output += """
			<span><a href="#" onclick="editBox(event,'${name}')">Edit this box</a></span>
			"""
		}
		output +=
			"""
			<div id="${name}" height="${attrs['height']}" width="${attrs['width']}" style="background-color:${box.getBackColor()};">
				<span id="${name}Text" style="color:${box.getFontColor()};">
						${box.getText()}
				</span>
				${body}
			</div>
		"""
		out << output
	}
	
	def boxjs = {
			def colorVals = 18446744073709551616
			def editorHTML = ""
			def output = """
				<script language="javascript">
					function editBox(event,boxId){
						Event.stop(event);
						
						if(\$('editBox')){
							\$('editBox').remove();
						}
						
						var editor = Builder.node('div', {id: 'editBox',
							'class':'editBox',
							style:"position:absolute; display:none;"});
						document.body.appendChild(editor);
					    
						new Ajax.Updater(editor,"${createLink(controller:'box', action:'edit')}",{
							method:'get',
							parameters:{name:boxId,time:new Date()},
							onComplete:function(){
								new Effect.Appear(editor,{duration:0.5, queue:'editor'});
								new Effect.Move(editor,{x:event.clientX,y:event.clientY,mode:'absolute',speed:1.5, queue:'editor'});
								\$('fontDisplay').setStyle({backgroundColor:\$F('fontColor')});
								\$('backDisplay').setStyle({backgroundColor:\$F('backColor')});
								setTimeout("initSlider()",1000);
								new Draggable(\$('boxeditor'));
							}
						});
						
					}

					function initSlider(){
						
						new Control.Slider(['fontRedHandle','fontBlueHandle','fontGreenHandle'],\$('fontColorTrack'),{
									sliderValue:[\$F('fontRedNum'),\$F('fontBlueNum'),\$F('fontGreenNum')], 
									onSlide:function(value,slider){
										\$('fontRedNum').value = Math.floor(value[0]);
										\$('fontBlueNum').value = Math.floor(value[1]);
										\$('fontGreenNum').value = Math.floor(value[2]);
										var color = createColor('font');								
										\$('fontDisplay').update(color);
										\$('fontColor').value = color;
										\$('fontDisplay').setStyle({backgroundColor:color});
										\$('fontRedHandle').setStyle({cursor:'-moz-grabbing'});				
									},	
									range: \$R(0, 255),
									onChange:function(){ 
										\$('fontRedHandle').setStyle({cursor:'-moz-grab'});
								}
						});

						new Control.Slider(['backRedHandle','backBlueHandle','backGreenHandle'],\$('backColorTrack'),{
									sliderValue:[\$F('backRedNum'),\$F('backBlueNum'),\$F('backGreenNum')], 
									onSlide:function(value,slider){
										\$('backRedNum').value = Math.floor(value[0]);
										\$('backBlueNum').value = Math.floor(value[1]);
										\$('backGreenNum').value = Math.floor(value[2]);
										var color = createColor('back');								
										\$('backDisplay').update(color);
										\$('backColor').value = color;
										\$('backDisplay').setStyle({backgroundColor:color});
										\$('backRedHandle').setStyle({cursor:'-moz-grabbing'});				
									},	
									range: \$R(0, 255),
									onChange:function(){ 
										\$('backRedHandle').setStyle({cursor:'-moz-grab'});
								}
						});

						
					Event.observe(\$('backSlideRadio'), 'click', function(e){ toggleBackSlide(true);});
					Event.observe(\$('backManualRadio'), 'click', function(e){ toggleBackSlide(false);});
					Event.observe(\$('boxEditorExpander'), 'click', function(e){ toggleBoxSize(\$('boxEditorExpander'));});
					}

					function toggleBoxSize(expand){
						if(expand.checked){
							new Effect.Morph(\$('boxeditor'),{
								style:{height:'900px',
								width:'850px'},
								duration:0.5
							});

							new Effect.Move(\$('boxeditor'),{
								x:0,y:0,
								mode:'absolute',
								speed:0.5
							});
							new Effect.Morph(\$('text'),{
								style:{height:'720px',
								width:'700px'},
								duration:0.75
							});
						}
						else{
							new Effect.Morph(\$('boxeditor'),{
								style:{height:'230px',
								width:'600px'},
								duration:1.5
							});
							new Effect.Morph(\$('text'),{
								style:{height:'50px',
								width:'400px'},
								duration:1.5
							});
						}

					}

					function toggleBackSlide(slideFlag){
						if(slideFlag){
							\$('backColorTrack').setStyle({display:'block'});
							\$('backRedHandle').setStyle({display:'block'});
							\$('backBlueHandle').setStyle({display:'block'});
							\$('backGreenHandle').setStyle({display:'block'});
							\$('backDisplay').setStyle({display:'block'});
							\$('backColor').setStyle({display:'none'});
							
						}
						else{
							\$('backColorTrack').setStyle({display:'none'});
							\$('backRedHandle').setStyle({display:'none'});
							\$('backBlueHandle').setStyle({display:'none'});
							\$('backGreenHandle').setStyle({display:'none'});
							\$('backDisplay').setStyle({display:'none'});
							\$('backColor').setStyle({display:'block'});
						}

					}
					function createColor(name){
							return 'rgb('+\$F(name+'RedNum')+','+\$F(name+'GreenNum')+','+\$F(name+'BlueNum')+')';
					}

					function closeAndRefreshEditor(transport){
						\$('editBox').remove();
						var boxJSON = transport.responseText.evalJSON();
						var box = \$(boxJSON.boxName);
						box.setStyle({backgroundColor: boxJSON.backColor});
						var boxText = \$(boxJSON.boxName + 'Text');
						box.setStyle({color: boxJSON.fontColor});
						box.update(boxJSON.text);
						
					}

				</script>
			"""
			out << output
	}
}
