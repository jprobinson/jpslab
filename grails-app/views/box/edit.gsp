	<g:closeButton/>
	<g:formRemote name="editorForm" url="[controller:'editor',action:'saveBox']" onComplete="closeAndRefreshEditor(e);">

	<input type="hidden" id="id" name="id" value="${box.id}"/>
	<table>
	<tr>
		<td><label for="boxNameEdit">Name:</label></td>
		<td><span>${box.boxName}</span><input type="hidden" value="${box.boxName}" id="boxNameEdit"/></td>
	</tr>
	<tr>
		<td><label for="backColor">Background Color:</label></td>
		<td><input type="text" value="${box.backColor}" id="backColor" name="backColor" style="display:none;"/>
			<span  id="backDisplay">${box.backColor}</span>
			Use Slider:
			<input type="radio" id="backSlideRadio" checked="checked" name="backSwitch" value="slide"/>
			Manual Entry:
			<input type="radio" id="backManualRadio"  name="backSwitch" value="manual"/>
		<input type="hidden" value="${box.backRedNum}" id="backRedNum" name="backRedNum"/>
		<input type="hidden" value="${box.backBlueNum}" id="backBlueNum" name="backBlueNum"/>
		<input type="hidden" value="${box.backGreenNum}" id="backGreenNum" name="backGreenNum"/></td>
		
	</tr>
	<tr height="30px" >
		<td colspan="2" >
			<div id="backColorTrack" class="track" style="" >
					<div id="backRedHandle" class="rhandle" ></div>
					<div id="backBlueHandle" class="bhandle" ></div>
					<div id="backGreenHandle" class="ghandle" ></div>
			</div>

		</td>
	</tr>
	<tr>
		<td><label for="fontColor">Font Color:</label></td>
		<td><span  id="fontDisplay">${box.fontColor}</span>
		<input type="hidden" value="${box.fontColor}" id="fontColor" name="fontColor"/>
		<input type="hidden" value="${box.fontRedNum}" id="fontRedNum" name="fontRedNum"/>
		<input type="hidden" value="${box.fontBlueNum}" id="fontBlueNum" name="fontBlueNum"/>
		<input type="hidden" value="${box.fontGreenNum}" id="fontGreenNum" name="fontGreenNum"/></td>
	</tr>
	<tr height="30px" >
		<td colspan="2" align="center">
			<div id="fontColorTrack" class="track" style="" >
					<div id="fontRedHandle" class="rhandle" ></div>
					<div id="fontBlueHandle" class="bhandle" ></div>
					<div id="fontGreenHandle" class="ghandle" ></div>
			</div>
		</td>
	</tr>
	<tr>
		<td><label for="text">Text:</label></td>
		<td><textarea id="text" name="text" >${box.text}</textarea></td>
	</tr>
	<tr>
		<td><input type="submit" value="Save Changes"/></td><td><label for="boxEditorExpander">Large Editor:</label><input type="checkbox" id="boxEditorExpander"/></td>
	</tr>
	</table>

</g:formRemote>