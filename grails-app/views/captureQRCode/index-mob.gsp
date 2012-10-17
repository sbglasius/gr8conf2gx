<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="mobile" />
	<title>Mobilepage</title></head>

  <body>
	<g:render template="before"/>
	<g:if test="${qrcode}">
		<p>URL: ${qrcode}</p>
		<p>Name: ${name}</p>
		<p>Email: ${email}</p>
	</g:if>

  </body>
</html>
