<button type="input" class="closeButton" onclick="closePhotoEditor();"><b>X</b></button>


<g:each var="photo" in="${photoList}">
		<li class="photoListing">
			<g:if test="${photo.name != null}">
			<img style="max-width:50;" src="${createLinkTo(dir:'images', file:photo.name)}" />
			<g:set var="link" value="${photo.name}" />
			</g:if>
			<g:if test="${photo.url != null}">
			<img style="max-width:50;" src="${photo.url}" />
			<g:set var="link" value="${photo.url}" />
			</g:if>
			<br/>
			&lt;PHOTO&gt;${link}&lt;PHOTO&gt; <br/> <br/>
			&lt;PHOTORIGHT&gt;${link}&lt;PHOTORIGHT&gt; <br/> <br/>
			&lt;PHOTOLEFT&gt;${link}&lt;PHOTOLEFT&gt; <br/> <br/>
				<g:formRemote name="uploadForm${photo.id}" url="[controller:'photo',action:'save']" onComplete="alert('Caption Saved!');">
					<g:editDeleteBox 
							hideEdit="true"
							deleteUrl="${createLink(controller:'photo', action:'delete')+'?id='+photo.id}"
							itemId="${photo.id}"
							objectName="photo" />
					<input type="hidden" name="id" value="${photo.id}"/>
					<g:textField name="caption" value="${photo.caption}" />
					<input type="submit" value="Save Caption"/>
				</g:formRemote>
		</li>
</g:each>
<br/>
<br/>
<div id="uploadFile">
	<h5>Add a new photo:</h5>
	<g:form action="add" controller="photo" method="post">
		<ul>Add a photo from another site</ul>
		<input type="hidden" name="curAction" value="${curAction}"/>
		<input type="hidden" name="curController" value="${curController}"/>
		<p>Image Url:<g:textField id="url" name="url"/></p>
		<p>Caption:<g:textField name="caption" value="" /></p>
		<g:hiddenField name="id" value="newPhoto.id" />
		<input type="submit" value="Add File"/>
	</g:form>	
	<g:form action="upload" controller="photo" method="post" enctype="multipart/form-data">
		<ul>Upload a photo</ul>
		<input type="hidden" name="curAction" value="${curAction}"/>
		<input type="hidden" name="curController" value="${curController}"/>
		<p>Image: <input type="file" id="fileUpload" name="fileUpload" /></p>
		<p>Caption:<g:textField name="caption" value="" /></p>
		<input type="submit" value="Upload File"/>
	</g:form>
</div>



