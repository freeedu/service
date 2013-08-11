	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<sec:authorize ifNotGranted="ROLE_USER">
	<form id="login" class="editable" name="loginForm" action="<c:url value="/oauth/login.do"/>" method="post">
		<h1>Welcome</h1>
		<fieldset class="inputs">
			<input id="username" type="text" placeholder="Username" autofocus required name='j_username'> 
			<input id="password" type="password" placeholder="Password" required name='j_password'>
		</fieldset>

		<fieldset class="actions">
			<input type="submit" id="submit" value="Log in"> 
			<a href="<c:url value="/account/resetpassword"/>">Forgot your password?</a>
		</fieldset>
	</form>
</sec:authorize>