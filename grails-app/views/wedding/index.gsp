<html>
    <head>
        <title>JP & Kelsey's Wedding - Sept. 25, 2010</title>
        <link rel="shortcut icon" href="http://jpslab.com/images/favicon.ico" type="image/x-icon" />
        <g:javascript library="prototype"/>
        <g:javascript library="scriptaculous"/>
        <g:crudJavascript curLocation="${createLink(controller:'wedding',action:'index')}" />
        <link type="text/css" href="${createLinkTo(dir:'css',file:'wedding.css')}" rel="stylesheet" />
    </head>
    <body id="body">
    	<div class="wrapper">
    	<div class="container">
    			<div class="weddingHeader"></div>
    			<div id="hotelList" class="linkList">
    				<div class="hotelImage"></div>
    				<g:addBox addUrl="${createLink(controller:'wedding', action:'addLink')+'?objectName=hotel'}" objectName="hotel" />
    				<g:each var="hotel" in="${hotelLinks}">
						<g:if test="${session.user != null}">
							<g:editDeleteBox 
								editUrl="${createLink(controller:'wedding', action:'editLink')+'?id='+hotel.id+'&objectName=hotel'}"
								deleteUrl="${createLink(controller:'wedding', action:'deleteLink')+'?id='+hotel.id+'&objectName=hotel'}"
								itemId="${hotel.id}"
								objectName="hotel" />
						</g:if>
						<ul class="linkUrl"><a href="${hotel.url}">${hotel.text}</a></ul>
						<ul class="linkDescription">${hotel.description}</ul>	
					</g:each>	
    			</div>
    			<div id="venueList" class="linkList">
    				<div class="venueImage"></div>
    				<g:addBox addUrl="${createLink(controller:'wedding', action:'addLink')+'?objectName=venue'}" objectName="venue" />
    				<g:each var="venue" in="${venueLinks}">
						<g:if test="${session.user != null}">
							<g:editDeleteBox 
								editUrl="${createLink(controller:'wedding', action:'editLink')+'?id='+venue.id+'&objectName=venue'}"
								deleteUrl="${createLink(controller:'wedding', action:'deleteLink')+'?id='+venue.id+'&objectName=venue'}"
								itemId="${venue.id}"
								objectName="venue" />
						</g:if>
						<ul class="linkUrl"><a href="${venue.url}">${venue.text}</a></ul>
						<ul class="linkDescription">${venue.description}</ul>	
					</g:each>
    			</div>
    			<div id="stLouisList" class="linkList">
    				<div class="stLouisImage"></div>
    				<g:addBox addUrl="${createLink(controller:'wedding', action:'addLink')+'?objectName=stLouis'}" objectName="stLouis" />
    				<g:each var="stLouis" in="${stLouisLinks}">
						<g:if test="${session.user != null}">
							<g:editDeleteBox 
								editUrl="${createLink(controller:'wedding', action:'editLink')+'?id='+stLouis.id+'&objectName=stLouis'}"
								deleteUrl="${createLink(controller:'wedding', action:'deleteLink')+'?id='+stLouis.id+'&objectName=stLouis'}"
								itemId="${stLouis.id}"
								objectName="stLouis" />
						</g:if>
						<ul class="linkUrl"><a href="${stLouis.url}">${stLouis.text}</a></ul>
						<ul class="linkDescription">${stLouis.description}</ul>	
					</g:each>
    			</div>
    			<div id="registryList" class="linkList">
    				<div class="registryImage"></div>
    				<g:addBox addUrl="${createLink(controller:'wedding', action:'addLink')+'?objectName=registry'}" objectName="registry" />
    				<g:each var="registry" in="${registryLinks}">
						<g:if test="${session.user != null}">
							<g:editDeleteBox 
								editUrl="${createLink(controller:'wedding', action:'editLink')+'?id='+registry.id+'&objectName=registry'}"
								deleteUrl="${createLink(controller:'wedding', action:'deleteLink')+'?id='+registry.id+'&objectName=registry'}"
								itemId="${registry.id}"
								objectName="registry" />
						</g:if>
						<ul class="linkUrl"><a href="${registry.url}">${registry.text}</a></ul>
						<ul class="linkDescription">${registry.description}</ul>	
					</g:each>
    			</div>
    			<div id="weddingCommentList" class="weddingCommentList">
    				<div class="commentFormDiv">
    				<ul>Feel free to post any Questions/Comments</ul>
    				<g:form class="commentForm" name="commentForm" url="[controller:'wedding',action:'saveComment']" onComplete="refreshPage();">
						<ul class="commentNameInput">Name<br/><g:textField name="commentor" value="" /></ul>
						<ul>Comment/Question<br/><textarea rows="5" cols="50" id="text" name="text"></textarea></ul>
						<ul class="commentSubmit"><input  type="submit" value="Add Comment"/></ul>
					</g:form>
					</div>
					<div class="commentList">
						<div class="commentDivider"></div>
    				<g:each var="comment" in="${weddingComments}">
  						<g:if test="${session.user != null}">
  						<div class="editDeleteBox" id="deleteComment${comment.id}">
							<img onclick="deleteObject('${createLink(controller:'wedding', action:'deleteComment')+'?id='+comment.id}','comment');" id="delete${comment.id}" src="${createLinkTo(dir:'images', file:'delete.gif')}" class="deleteLink" />
						</div>
						</g:if>
						<ul class="commentor">${comment.commentor}</ul>
						<ul class="date"><g:formatDate format="MM/dd/yy hh:mm a" date="${comment.date}"/></ul>
						<ul class="text">${comment.text}</ul>
						<div class="commentDivider"></div>
					</g:each>
					</div>
    			</div> 	
    			
				<div class="theMap">
						<iframe width="850" height="450" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" 
							src="http://maps.google.com/maps/ms?ie=UTF8&hl=en&msa=0&msid=115870649482182065668.00047fadc872838a5b98a&ll=38.629611,-90.235691&spn=0.058031,0.120032&output=embed"></iframe>
							<br />
							<small style="color:#FFFFFF;">View <a href="http://maps.google.com/maps/ms?ie=UTF8&hl=en&msa=0&msid=115870649482182065668.00047fadc872838a5b98a&ll=38.629611,-90.235691&spn=0.058031,0.120032&source=embed" style="color:#FFFFFF;text-align:left">JP & Kelsey's Wedding Locations</a> in a larger map</small>
				</div>		    
		</div>
		</div>	
	</body>
</html>

