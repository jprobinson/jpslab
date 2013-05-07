<html>
    <head>
   		 <meta name="layout" content="main" />
        <g:crudJavascript curLocation="${createLink(controller:'jpslab',action:'index')}" />
        <g:photoJS curController="jpslab" curAction="index" />
    </head>
    <body>
    	<g:render template="/news/list"  model="['newsList':newsList]" />
    	<g:photoEditor/>
	</body>
</html>