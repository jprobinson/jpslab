<div class="navBar"><g:render template="/jpslab/navBar"  model="['currPage':'creations']" /></div>
<div class="creationList">
   	
   		<g:each var="creation" in="${creationsList}">
				<div class="creationsBody" >
					<g:if test="${session.user != null}">
						<div class="creationsBodyText">
							<g:editDeleteBox 
								editUrl="${createLink(controller:'creations', action:'edit')+'?id='+creation.id}"
								deleteUrl="${createLink(controller:'creations', action:'delete')+'?id='+creation.id}"
								itemId="${creation.id}"
								objectName="creation" />
						</div>
					</g:if>
					<div class="creationsBodyText">
						<span class="creationsName">${creation.name}</span> <br/>
     					<g:textAndImages value="${creation.description}"/>
     					<li class="commentListing">
     						<g:commentListing objectName="creation" objectId="${creation.id}"/>
     					</li>
     						
     					<li class="shareLink">Share this creation: ${createLink(controller:'jpslab',action:'creations',absolute:'true')}/<g:formatDate format="yyyy" date="${creation.date}"/>/<g:formatDate format="MM" date="${creation.date}"/>/<g:formatDate format="dd" date="${creation.date}"/>/${creation.link}</li>
     					<br/>
     				</div>
     			</div>
    			<span style="height:5px;display:block;position:relative;">&nbsp;</span>
			</g:each>
			<!-- <div class="creationsBody" >
					<div class="creationsHeader">&nbsp;</div>
					<g:if test="${session.user != null}">
						<div class="creationsBodyText">
							<g:editDeleteBox 
								editUrl="${createLink(controller:'creations', action:'edit')+'?id='+editableBox.id}"
								deleteUrl="${createLink(controller:'creations', action:'delete')+'?id='+editableBox.id}"
								itemId="${editableBox.id}"
								objectName="creation" />
						</div>
					</g:if>
					<div class="creationsBodyText">
						<span class="creationsName">${editableBox.name}</span> <br/>
     					<g:textAndImages value="${editableBox.description}"/>
     					<g:customBox boxname="box1" height="50px" width="500px"></g:customBox>
     					<li class="commentListing">
     						<g:commentListing objectName="creation" objectId="${editableBox.id}"/>
     					</li>
     						
     					<li class="shareLink">Share this creation: ${createLink(controller:'jpslab',action:'creations',absolute:'true')}/<g:formatDate format="yyyy" date="${editableBox.date}"/>/<g:formatDate format="MM" date="${editableBox.date}"/>/<g:formatDate format="dd" date="${editableBox.date}"/>/${editableBox.link}</li>
     					<br/>
     				</div>
     			</div>
    			<span style="height:5px;display:block;position:relative;">&nbsp;</span> -->
 <g:login  curLocation="${createLink(controller:'jpslab',action:'creations')}" />