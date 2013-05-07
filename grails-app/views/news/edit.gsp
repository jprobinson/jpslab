<g:closeButton/>
<div class="newsEditor">
<g:formRemote name="editorForm" url="[controller:'news',action:'save']" onComplete="refreshPage();">
	<input type="hidden" id="id" name="id" value="${news.id}"/>

	<table>
	<tr>

		<td><label for="boxNameEdit">Headline</label></td>
		<td><g:textField name="headline" value="${news.headline}" /></td>

	</tr>

	<tr>

		<td><label for="text">Text</label></td>

		<td><textarea rows="10" cols="75" id="editText" name="text">${news.text}</textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Save Changes"/></td>
	</tr>

	</table>
	
</g:formRemote>
	
</div>
