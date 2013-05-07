<button type="input" class="closeButton" onclick="closeLoginBox();">X</button>
<br/>
<br/>
<g:formRemote name="loginForm" url="[controller:'login',action:'login']" onComplete="refreshLogin(e);" >
	<table class="userForm">
		<tr class='prop'>
			<td>
				<label for='login'>Login:</label>
			</td>
			<td>
				<input id="login" type='text' name='login' value='${user?.login}' />
			</td>
		</tr>
		<tr>
			<td>
				<label for='password'>Password:</label>
			</td>
			<td>
				<input id="password" type='password' name='password'
					value='${user?.password}' />
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="Login"></input></td>
		</tr>
	</table>
</g:formRemote>
