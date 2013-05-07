import java.text.SimpleDateFormat

class CrudTagLib {
	
	//REQUIRED ATTRS:
	//editUrl
	//deleteUrl
	//itemId 
	//objectName (ie Comment, Blog, etc)
	def editDeleteBox = { attrs, body ->
		//spit out body wrapped with crudDiv	
		def boxName = attrs['objectName'] + attrs['itemId']
		def output = "&nbsp;"
		if(session.user!=null){
			 output =  """
				<div class="editDeleteBox" id="${boxName}">
			"""
		
			if(attrs['hideEdit']!='true'){
				output += """<img onclick="openEditor('${attrs['editUrl']}',event);" id="edit${boxName}" src="${createLinkTo(dir:'images', file:'edit.gif')}" class="editLink" />"""
			}
		
			output += """
					<img onclick="deleteObject('${attrs['deleteUrl']}','${attrs['objectName']}');" id="delete${boxName}" src="${createLinkTo(dir:'images', file:'delete.gif')}" class="deleteLink" />
				</div>
			"""
		}
		out << output
	}

	def closeButton = {
		out <<	"""<button type="input" class="closeButton" onclick="closeEditBox();"><b>X</b></button>"""
	}

	def addBox = { attrs ->
		//spit out body wrapped with crudDiv	
		def boxName = attrs['objectName']
		if(session.user!=null){
			out <<  """
			<div class="editDeleteBox" id="${boxName}">
				<img onclick="openCreator('${attrs['addUrl']}',event);" id="create${boxName}" src="${createLinkTo(dir:'images', file:'add.gif')}" class="addLink" />
			</div>
			"""
		}
	}

	def commentListing = { attrs ->

		def objectInstance
		//this is nasty, but whateva
		if("news".equalsIgnoreCase(attrs['objectName']))
			objectInstance = News.findById(attrs['objectId'])
		else if("creation".equalsIgnoreCase(attrs['objectName']))
			objectInstance = Creation.findById(attrs['objectId'])
		

		def comments = []
	
		if(objectInstance)
			comments = objectInstance.getComments()
	
		def output ="""
			<a class="commentsLink" href="#" id="viewComments${attrs['objectId']}"
			onclick="growBox('comments${objectInstance.getId()}','hideComments${attrs['objectId']}','viewComments${attrs['objectId']}',event)">View ${comments.size()} Comments</a>
			<a class="commentsLink" href="#" style="display:none;" id="hideComments${attrs['objectId']}"
			onclick="hideBox('comments${objectInstance.getId()}','hideComments${attrs['objectId']}','viewComments${attrs['objectId']}',event)">Hide ${comments.size()} Comments</a>
			<a class="commentsLink" href="#" id="addComments${attrs['objectId']}" 
			onclick="openCreator('${createLink(controller:'comment',action:'add')+'?objectName='+attrs['objectName']+'&objectId='+attrs['objectId']}',event)">Comment on this</a>
			"""
	
		def dateFormat = new SimpleDateFormat('MM/dd/yy hh:mm a')
		if(!comments.isEmpty()){
			comments = comments.sort{a,b -> a.getDate().compareTo(b.getDate())}
		
			output += """<div class="commentList" id="comments${objectInstance.getId()}" style="display:none;"><div>"""
			
			comments.each{ cmnt ->
				if(session.user!=null){
					def divId = attrs['objectName'] + cmnt.getId()
					output += """
					<div class="editDeleteBox" id="${divId}">
						<img onclick="deleteObject('${createLink(controller:'comment', action:'delete')+'?className='+cmnt.class.getName()+'&id='+cmnt.getId()}','comment');" id="delete${divId}" src="${createLinkTo(dir:'images', file:'delete.gif')}" class="deleteLink" />
					</div>
					"""
				}
				output += """
				Comment By:<span class="commenter">${cmnt.getCommentor()}</span>
				<br/>
				${dateFormat.format(cmnt.getDate())}
				<br/>
				${cmnt.getText()}
				<br/><br/>
				"""
			}
			output += "</div></div>"
		}
	
		out << output
	}

	def crudJavascript = { attrs ->
	
		def output = """
			<script language="javascript">
				
				function deleteObject(deleteUrl,objectName){
					var deleteIt = confirm('Are you sure you want to delete this '+objectName+'?');
					if(deleteIt){
						new Ajax.Request(deleteUrl,{
							method:'get',
							onComplete:function(transport){
								document.location.href="${attrs['curLocation']}"+'?sess='+new Date().valueOf();
							}
							});
						
					}
				}

				function openEditor(editUrl,event){
					Event.stop(event);
					if(\$('editBox')){
						\$('editBox').remove();
					}

					var editBox = Builder.node('div', {id: 'editBox',
						'class':'editBox',
						style:"position:absolute; display:none;"});
					document.body.appendChild(editBox);
					var yLoc = event.clientY + \$('body').scrollTop;
					new Ajax.Updater(editBox,editUrl,{
						method:'get',
						parameters:{time:new Date()},
						onComplete:function(){
							new Effect.Appear(editBox,{duration:0.5, queue:'editBox'});
							new Effect.Move(editBox,{x:'350',y:yLoc,mode:'absolute',speed:1.5, queue:'editBox'});
						}
					});
					new Draggable(editBox);
					return true;
				}
				
				function closeEditBox(){
					\$('editBox').remove();
				}


				function openCreator(addUrl,event){
					Event.stop(event);
					if(\$('editBox')){
						\$('editBox').remove();
					}
					
					var addBox = Builder.node('div', {id: 'editBox',
						'class':'editBox',
						style:"position:absolute; display:none;"});
					document.body.appendChild(addBox);

					var yLoc = event.clientY + \$('body').scrollTop;
					new Ajax.Updater(addBox,addUrl,{
						method:'get',
						parameters:{time:new Date()},
						onComplete:function(){
							new Effect.Appear(addBox,{duration:0.5, queue:'editBox'});
							new Effect.Move(addBox,{x:'350',y:yLoc,mode:'absolute',speed:1.5, queue:'editBox'});
						}
					});
					new Draggable(addBox);
					return true;
				}
		
				function refreshPage(){
						\$('editBox').remove();
						document.location.href="${attrs['curLocation']}"+'?sess='+new Date().valueOf();
				}

				function growBox(divName,hideLink,showLink,event){
					Event.stop(event);
					\$(showLink).setStyle('display:none;');
					
					\$(hideLink).setStyle('display:inline;');
					new Effect.SlideDown(divName);
				}

				function hideBox(divName,hideLink,showLink,event){
					Event.stop(event);
					\$(showLink).setStyle('display:inline;');

					\$(hideLink).setStyle('display:none;');		
					new Effect.SlideUp(divName);
				}

			</script>
		"""
		
		out << output
			
	}

	
	
}
