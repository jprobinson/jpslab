
    			<g:if test="${session.user!=null}">
    			<div class="${currPage}BodyText">
    				<g:addBox addUrl="${createLink(controller:currPage, action:'add')}" objectName="${currPage}" />
    			</div>
    			</g:if>
    				<li class="navBarLink <g:if test="${currPage=='news'}">currPage</g:if>"> <a id="homeLink" href="${createLink(controller:'jpslab',action:'index')}">&nbsp;</a> </li>
					<li class="navBarLink <g:if test="${currPage=='creations'}">currPage</g:if>"> <a id="creationsLink" href="${createLink(controller:'jpslab',action:'creations')}">&nbsp;</a> </li>
					<li class="navBarLink <g:if test="${currPage=='about'}">currPage</g:if>"> <a id="aboutLink"  href="${createLink(controller:'jpslab',action:'about')}">&nbsp;</a> </li>
					