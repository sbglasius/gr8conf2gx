<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Simple GSP page</title></head>

  <body>
	<g:if test="${qrcode}">
		<p>URL: ${qrcode}</p>
		<p>Name: ${name}</p>
		<p>Email: ${email}</p>
	</g:if>
	<g:uploadForm action="analyze" >
		<input name="file" type="file" accept="image/*;capture=camera">
		<g:submitButton name="Submit"/>
	</g:uploadForm>
  </body>
</html>
