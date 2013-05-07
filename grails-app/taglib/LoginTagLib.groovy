class LoginTagLib {
	def login = { attrs, body ->
		
		def output = """
			<script language="javascript">
				function refreshLogin(transport){
						if(transport.responseText != 'fail'){
							document.location.href="${attrs['curLocation']}"+'?sess='+new Date().valueOf();
						}
						else{
							\$('loginBox').update('fail!');
						}
					}

				function showLogin(event){
						Event.stop(event);
						
						if(\$('loginBox')){
							\$('loginBox').remove();
						}
						
						var loginBox = Builder.node('div', {id: 'loginBox',
							'class':'editBox',
							style:"position:absolute; display:none;"});
						document.body.appendChild(loginBox);
					
						new Ajax.Updater(loginBox,"${createLink(controller:'login', action:'show')}",{
							method:'get',
							parameters:{time:new Date()},
							onComplete:function(){
								new Effect.Appear(loginBox,{duration:0.5, queue:'loginBox'});
								new Draggable(loginBox);
							}
						});
						
					}

					function logout(){
						var logoutQ = confirm('Are you sure you want to logout?');
						if(logoutQ){
							new Ajax.Request('${createLink(controller:'login',action:'logout')}',{
								method:'get',
								onComplete:function(transport){
									document.location.href="${attrs['curLocation']}"+'?sess='+new Date().valueOf();
								}
								});
							
						}
					}

					function closeLoginBox(){
							\$('loginBox').remove();
					}
			</script>
			"""
			def cal = Calendar.getInstance()
			
			if(!session.user){
				output += """<div class="copyright">${cal.get(Calendar.YEAR)} <a href="#" onclick="showLogin(event)">&#169;</a> JP Robinson </div>"""
			}
			else{
				output += """<div class="copyright">${cal.get(Calendar.YEAR)} <a href="#" onclick="logout();">&#169;</a> JP Robinson </div>"""
			}
			
			
		out << output
	}
}
