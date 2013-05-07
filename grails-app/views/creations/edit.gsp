<g:closeButton/>
<div class="creationEditor">
<g:formRemote name="editorForm" url="[controller:'creations',action:'save']" onComplete="refreshPage();">
	<input type="hidden" id="id" name="id" value="${creation.id}"/>

	<table>
	<tr>

		<td><label for="boxNameEdit">Name</label></td>
		<td><g:textField name="name" value="${creation.name}" /></td>

	</tr>

	<tr>

		<td><label for="text">Description:</label></td>

		<td><textarea rows="10" cols="75" id="editText" name="description">${creation.description}</textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Save Changes"/></td>
	</tr>

	</table>
	
</g:formRemote>
	
</div>
