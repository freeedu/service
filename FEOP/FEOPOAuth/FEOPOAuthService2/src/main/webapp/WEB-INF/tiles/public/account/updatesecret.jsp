
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/account/findpassword" var="updateAction" />

<form id="change-password" class="editable" action="${updateAction }" method="post" name="changePasswordForm">
	<h1>Change Password</h1>
    <fieldset class="inputs">
    	<c:if test="${msg != null }">
			<div class="success">
				<c:out value="${msg }" />
			</div>
		</c:if>
		<s:hasBindErrors name="changePasswordForm">
			<c:if test="${errors.errorCount gt 0}">
				<div class="error">
					<b>Errors:</b><br />
					<c:forEach items="${errors.allErrors}" var="error">
						<s:message code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}" />
						<br />
					</c:forEach>
				</div>
			</c:if>
		</s:hasBindErrors>
        <input type="text" placeholder="token" name="token" value="${tkn }" required hidden>
        <input class="password" type="password" placeholder="New Password" name="newPassword" required>
        <input class="password" type="password" placeholder="Re Enter Password" name="repeatPassword" required>
    </fieldset>
    <fieldset class="actions">
    	<input type="submit" id="submit" value="Change">
    </fieldset>
</form>
