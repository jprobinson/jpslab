<div class="navBar"><g:render template="/jpslab/navBar"  model="['currPage':'about']" /></div>
<div class="aboutList">
 	<span style="height:5px;display:block;position:relative;">&nbsp;</span>
   		<g:each var="aboutMe" in="${aboutMeList}">
				<div class="aboutBody" >
					<g:if test="${session.user != null}">
						<div class="aboutBodyText">
							<g:editDeleteBox 
								editUrl="${createLink(controller:'about', action:'edit')+'?id='+aboutMe.id}"
								deleteUrl="${createLink(controller:'about', action:'delete')+'?id='+aboutMe.id}"
								itemId="${aboutMe.id}"
								objectName="aboutMe" />
						</div>
					</g:if>
					<div class="aboutBodyText">
     					<g:textAndImages value="${aboutMe.text}"/>
     					<br/>
     				</div>
     			</div>
    			<span style="height:5px;display:block;position:relative;">&nbsp;</span>
			</g:each>	
 </div>
 <g:login  curLocation="${createLink(controller:'jpslab',action:'about')}" />