<g:closeButton/>
<div class="aboutMeEditor">
<g:formRemote name="editorForm" url="[controller:'about',action:'save']" onComplete="refreshPage();">
	<input type="hidden" id="id" name="id" value="${aboutMe.id}"/>
	<table>

	<tr>

		<td><label for="text">Text:</label></td>

		<td><textarea rows="30" cols="75" id="editText" name="text">${aboutMe.text}</textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Save Changes"/></td>
	</tr>

	</table>
	
</g:formRemote>

</div>
