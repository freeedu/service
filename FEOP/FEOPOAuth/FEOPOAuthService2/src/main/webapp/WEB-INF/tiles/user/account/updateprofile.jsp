	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/profile/update" var="update" />
<form action="${update }" method="post" class="editable" id="profile" name="userForm">
	<h1>Edit Profile</h1>
	<fieldset class="inputs">
		<c:if test="${msg != null }">
			<div class="success">
				<c:out value="${msg }" />
			</div>
		</c:if>
		<s:hasBindErrors name="userForm">
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
		<input type="text" name="userId" hidden="true" class="right" value="${userForm.userId}" /> <label class="left">User Name:</label><input
			type="text" name="userName" class="right" value="${userForm.userName}" /> <label class="left">Email:</label><input type="text" name="email"
			disabled="disabled" class="right" value="${userForm.email}" /> <label class="left">Phone:</label><input type="text" name="phone"
			class="right" value="${userForm.phone }" />
	</fieldset>
	<fieldset class="actions">
		<input type="submit" value="Update Profile" id="submit" />
	</fieldset>
</form>