
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/oauth/login.do" var="loginAction" />
<c:url value="/account/resetpassword" var="resetPasswordAction"/>

<form id="login" name="loginForm" class="editable" action="${loginAction }" method="post">
	<h1>Welcome</h1>
	<fieldset class="inputs">
		<c:if test="${param.error != null}">
			<div class="error">
				Failed to login.
				<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
	              Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</c:if>
			</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div class="alert alert-success">You have been logged out.</div>
		</c:if>
		<input id="username" type="text" placeholder="Username" autofocus required name='j_username'> <input id="password" type="password"
			placeholder="Password" required name='j_password'>
	</fieldset>

	<fieldset class="actions">
		<input type="submit" id="submit" value="Log in"> <a href="${resetPasswordAction }">Forgot your password?</a>
	</fieldset>
</form>