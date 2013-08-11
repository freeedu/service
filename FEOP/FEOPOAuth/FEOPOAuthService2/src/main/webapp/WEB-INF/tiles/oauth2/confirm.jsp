<%@ page import="org.springframework.security.core.AuthenticationException"%>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException"%>

<div class="viewable center-block">
	<%
		if (session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null
				&& !(session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) instanceof UnapprovedClientAuthenticationException)) {
	%>
	<div class="error-box alert">
		<div class="msg">
			<p>
				Access could not be granted. (<%=((AuthenticationException) session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
						.getMessage()%>)
			</p>
		</div>
	</div>
	<%
		}
	%>
	<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" />

	<authz:authorize ifAllGranted="ROLE_USER">
		<h1>Authorize</h1>

		<p>
			Please Authorize "
			<c:out value="${client.clientId}" />
			" to visit your resources.
		</p>

		<form id="confirmationForm" name="confirmationForm" action="<%=request.getContextPath()%>/oauth/authorize" method="post">
			<input name="user_oauth_approval" value="true" type="hidden" /> <label><input name="authorize" value="Authorize" type="submit"
				class="confirm"></label>
		</form>
		<form id="denialForm" name="denialForm" action="<%=request.getContextPath()%>/oauth/authorize" method="post">
			<input name="user_oauth_approval" value="false" type="hidden" /> <label><input name="deny" value="Cancel" type="submit"
				class="unconfirm"></label>
		</form>
	</authz:authorize>
</div>