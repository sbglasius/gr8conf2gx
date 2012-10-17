<%@ page import="gr8conf2gx.EntryStatus" %>
<div id="maincontent">
	<div id="header">
		<h1>Welcome to the</h1>

		<div id="gr8conf">
			<r:img uri="/images/gr8conf.png"/>
		</div>

		<h1>Europe prizedraw</h1>
	</div>
	<div id="camera">
		<g:if test="${status in EntryStatus.ERROR_CODES}">
			<h2>Oops...</h2>
			<g:if test="${status == EntryStatus.QR_CODE_UNREADABLE}">
			<div>we were not able to decode your QR code. Please try again...</div>
			</g:if>
			<g:if test="${status == EntryStatus.DOUBLE_ENTRY}">
			<div>You can not enter the prizedraw twice.</div>
			</g:if>
			<g:if test="${status == EntryStatus.NAME_OR_EMAIL_MISSING}">
			<div>we can not find your profile data on<g2:skanz/>. Please update your profile...</div>
			</g:if>
		</g:if>
		<button id="cameraButton">
			<r:img uri="/images/camera.png"/>
		</button>
	</div>

	<p><strong>Here's how it works:</strong> Upload a picture of your badges QR code and the application will lookup your name
	and email address on <g2:skanz/>. When Springone2GX is over, we will draw a winner of one full GR8Conf Europe 2013 conference ticket.
	</p>

	<p>To enter, it's required that you have a Springone2GX badge, and that you have created a profile on<g2:skanz/> with your name and email address.</p>


	<p class="privacy"><strong>Your privacy:</strong> We will not share your information with anyone. We will, however, add you to our mailinglist, where you will
	receive information about GR8Conf Europe.</p>
	<g:render template="form"/>
</div>
