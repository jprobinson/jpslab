<html>
    <head>
   		 <meta name="layout" content="main" />
        <g:crudJavascript curLocation="${createLink(controller:'jpslab',action:'about')}" />
        <g:photoJS curController="jpslab" curAction="about" />
    </head>
    <body>
    	<g:render template="/about/list"  model="['aboutMeList':aboutMeList]" /></td>
    	<g:photoEditor/>
	</body>
</html>