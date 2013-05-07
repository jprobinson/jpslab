class PhotoTagLib {
	def photoEditor = { attrs,body ->
		if(session.user!=null){		
			out << """
				<img onclick="openPhotoEditor();" id="photoIcon" src="${createLinkTo(dir:'images', file:'photoEditor.png')}" class="photoIcon" />
				"""
		}
	}
	
	def photoJS = { attrs, body ->
	
		def output = """
		<script language="javascript">
			function openPhotoEditor(){
					if(\$('photoEditorBox')){
						\$('photoEditorBox').remove();
					}

					var photoBox = Builder.node('div', {id: 'photoEditorBox',
						'class':'photoEditor',
						style:"position:absolute; "});
					\$('body').appendChild(photoBox);
			
					new Ajax.Updater(photoBox,'${createLink(controller:'photo',action:'view')}',{
						method:'get',
						parameters:{time:new Date(),curController:'${attrs['curController']}',curAction:'${attrs['curAction']}'},
						onComplete:function(){
							
							new Effect.Appear(photoBox,{duration:0.5});
						}
					});
					return true;
					
			}

			
			function closePhotoEditor(){
				\$('photoEditorBox').remove();
			}
		</script>
	"""
	out << output
	}
}
