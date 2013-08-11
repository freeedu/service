
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/account/resetpassword" var="resetAction" />

<c:if test="${msg != null }">
	<div class="success-box alert center-block">
		<div class="msg">
			<c:out value="${msg }" />
		</div>
	</div>
</c:if>
<form id="reset" class="editable" action="${resetAction }" method="post" name="resetPasswordForm">
	<s:hasBindErrors name="resetPasswordForm">
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
	<fieldset class="inputs">
		<input id="email" type="email" name="email" placeholder="Enter your account email" />
	</fieldset>
	<fieldset class="actions">
		<input type="submit" value="Send" id="submit" />
	</fieldset>
</form>