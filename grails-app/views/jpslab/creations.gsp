<html>
    <head>
   		 <meta name="layout" content="main" />
        <g:crudJavascript curLocation="${createLink(controller:'jpslab',action:'creations')}" />
        <g:photoJS curController="jpslab" curAction="creations" />
        <g:boxjs />
    </head>
    <body>
    	<g:render template="/creations/list"  model="['creationsList':creationsList]" />
    	<g:photoEditor/>
	</body>
</html>