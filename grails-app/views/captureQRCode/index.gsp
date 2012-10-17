<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Springone2GX - GR8Conf</title>
	<r:require module="application"/>
	<r:require module="grailsEvents"/>
	<r:script>
	 var grailsEvents = new grails.Events('${createLink(uri: '')}', {transport:'sse'});
	 grailsEvents.on('notifyClient', function(data){console.debug(data)}); //will listen for server events on 'savedTodo' topic
	</r:script>
</head>

<body>
<g:render template="common"/>
<p><strong>PS:</strong> If you load this page in your mobile browser, you can use the phones camera.</p>
<g:render template="questions"/>
</body>
</html>
