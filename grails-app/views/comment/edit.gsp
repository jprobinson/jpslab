<g:closeButton/>
<div class="commentEditor">
<g:formRemote name="editorForm" url="[controller:'comment',action:'save']" onComplete="refreshPage();">
	<input type="hidden" id="id" name="id" value="${comment.id}"/>
	<input type="hidden" id="objectId" name="objectId" value="${objectId}"/>
	<input type="hidden" id="objectName" name="objectName" value="${objectName}"/>
	<table>
	<tr>
		<td><label for="commentor">Name:</label></td>
		<td><g:textField name="commentor" value="${comment.commentor}" /></td>
	</tr>
	<tr>
		<td><label for="text">Text:</label></td>

		<td><textarea rows="10" cols="45" id="editText" name="text">${comment.text}</textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Add Comment"/></td>
	</tr>

	</table>
</g:formRemote>
</div>
