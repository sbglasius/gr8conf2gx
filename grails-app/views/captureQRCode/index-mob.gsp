<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="mobile" content="main" />
	<title>Mobilepage</title></head>

  <body>
	Welcome to the mobilepage
	<g:if test="${qrcode}">
		<p>URL: ${qrcode}</p>
		<p>Name: ${name}</p>
		<p>Email: ${email}</p>
	</g:if>

  </body>
</html>
