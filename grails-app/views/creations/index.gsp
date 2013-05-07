<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Welcome to JPs Lab!</title>
		<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
		
        <g:javascript library="prototype" /> 
        <g:javascript library="scriptaculous" /> 
        <g:boxjs />
        <script language="javascript">
        	function addComment(transport){      	
        		var comment = transport.responseText.evalJSON();
        		$('comments').insert({
					top:'<div class="comment" id="newComment" style="display:none;"> \n'+                  
                	' \t<span class="commentLabel">Comment By:</span>&nbsp;&nbsp;'+comment.createdBy+'<br/>\n'+
                	' \t<span class="commentLabel">Created On:</span>&nbsp;&nbsp;'+comment.createTime+'<br/>\n'+
                	' \t'+comment.commentText+'<br/>\n'+
             		'</div>\n'	
        		});
        		Effect.Appear($('newComment'),{duration:3.5});
        		$('commentText').value='';
        		$('createdBy').value='';
        	}
        	function validateComment(e){
				if(($F('createdBy').replace(' ','')=='') || ($F('commentText').replace(' ','')=='')){
					Event.stop(e);
					alert('Name and Comment are required.');
				}
        	}
        </script>
    </head>
    <body id="body">
		<div class="maindiv">
        <img class="headerImage" src="${createLinkTo(dir:'images', file:'JPsLabHeader.jpg')}"/>
		  <g:customBox boxname="welcomeBox" height="20px" width="500px"></g:customBox>
		  <g:customBox boxname="test_box_one" public="true" height="20px" width="500px"></g:customBox>
		  <g:customBox boxname="toybox1" height="20px" width="500px"></g:customBox>
			<br/>
			<div class="toy">
				<g:link controller="demo" action="index">Here's</g:link> the latest toy 
				I've created whilest playing around with the 
				Javascript prototype and script.aculo.us libraries. 
			  <br/>
				<ul>Just a few notes about the toy:</ul>	
				<li>Once you have a lot of boxes created, try clicking some of the effects</li>
				<li>The Newmans are draggable and I'd recommend double clicking on them</li>
			 <g:formRemote name="commentForm" url="[controller:'home',action:'saveComment']" onComplete="addComment(e);">
					<table>
					<tr"center"><td colspan="2"><span class="commentLabel">Any Comments/Suggestions for the toy or this site?</span></td></tr>
					<tr><td><label for="createdBy">Name:</label></td><td><input type="text" name="createdBy" id="createdBy"></input><br/></td></tr>
					<tr><td><label for="commentText">Comment:</label></td><td><textarea name="commentText" rows="50" columns="3" id="commentText"></textarea></td></tr>
					<tr><td colspan="2"><input type="submit" onclick="validateComment(event);" value="Post a Comment"></input></td></tr>
					</table>
			 </g:formRemote>
				<div id="comments">
					<g:each in="${commentList}" status="i" var="commentInstance">
						<div class="comment">                    
                            <span class="commentLabel">Comment By:</span>&nbsp;&nbsp;${fieldValue(bean:commentInstance, field:'createdBy')}<br/>
                            <span class="commentLabel">Created On:</span>&nbsp;&nbsp;${fieldValue(bean:commentInstance, field:'createTime')}<br/>
                            ${fieldValue(bean:commentInstance, field:'commentText')}<br/>
                        </div>
					</g:each>
				</div>
			</div>
	</div>
	<g:login update="${createLinkTo(controller:'home', action:'home')}"/>
    </body>
</html>