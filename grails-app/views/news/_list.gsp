<div class="navBar"><g:render template="/jpslab/navBar"  model="['currPage':'news']" /></div>
<div class="newsList">
   	
   		<g:each var="news" in="${newsList}">
   				<div class="newsHeader">&nbsp;</div>
				<div class="newsBody" >
					
					<g:if test="${session.user != null}">
						<div class="newsBodyText">
							<g:editDeleteBox 
								editUrl="${createLink(controller:'news', action:'edit')+'?id='+news.id}"
								deleteUrl="${createLink(controller:'news', action:'delete')+'?id='+news.id}"
								itemId="${news.id}"
								objectName="news" />
						</div>
					</g:if>
					<div class="newsBodyText">
						<span class="newsHeadline">${news.headline} </span><br/>
						<span class="newsDate"><g:formatDate format="MM/dd/yy hh:mm a" date="${news.date}"/></span><br/>
						<br/>
     					<g:textAndImages value="${news.text}"/>
     					<li class="commentListing"><g:commentListing objectName="news" objectId="${news.id}"/></li>
     						
     					<li class="shareLink">Share this news: ${createLink(controller:'jpslab',action:'news',absolute:'true')}/<g:formatDate format="yyyy" date="${news.date}"/>/<g:formatDate format="MM" date="${news.date}"/>/<g:formatDate format="dd" date="${news.date}"/>/${news.link}</li>
     					<br/>
     				</div>
     			</div>
    			<div class="newsFooter">&nbsp;</div>
    			<span style="height:5px;display:block;position:relative;">&nbsp;</span>
			</g:each>	
 </div>
 <g:login  curLocation="${createLink(controller:'jpslab',action:'index')}" />