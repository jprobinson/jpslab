<g:closeButton/>
<div class="linkEditor">
<g:formRemote name="editorForm" url="[controller:'wedding',action:'saveLink']" onComplete="refreshPage();">
	<input type="hidden" id="id" name="id" value="${link.id}"/>
	<input type="hidden" id="objectName" name="objectName" value="${objectName}"/>
	<table>
	<tr>
		<td><label for="url">URL</label></td>
		<td><g:textField name="url" value="${link.url}" /></td>
	</tr>
	<tr>
		<td><label for="text">Link Text</label></td>
		<td><g:textField name="text" value="${link.text}" /></td>
	</tr>
	<tr>
		<td><label for="text">Text</label></td>
		<td><textarea rows="5" cols="40" id="description" name="description">${link.description}</textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Save Changes"/></td>
	</tr>

	</table>
	
</g:formRemote>
	
</div>
