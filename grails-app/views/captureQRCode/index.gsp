<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Springone2GX - GR8Conf </title></head>

  <body>
	<g:render template="before"/>
	<g:render template="form"/>
	<g:if test="${qrcode}">
		<p>URL: ${qrcode}</p>
		<p>Name: ${name}</p>
		<p>Email: ${email}</p>
	</g:if>
  </body>
</html>
