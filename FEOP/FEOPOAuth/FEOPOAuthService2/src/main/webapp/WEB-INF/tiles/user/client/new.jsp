<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/client/create" var="newAction" />
<form action="${newAction }" method="post" name="clientForm" class="editable client2">
	<h1>Create Application</h1>
	<s:hasBindErrors htmlEscape="true" name="clientForm">
		<c:if test="${errors.errorCount gt 0}">
			<h4>Errors:</h4>
			<font color="red"> <c:forEach items="${errors.allErrors}" var="error">
					<s:message code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}" />
					<br />
				</c:forEach>
			</font>
		</c:if>
	</s:hasBindErrors>
	<fieldset class="inputs">

		<label class="left">Client Name</label> <input name="clientName" class="right" required/>
		<div class="left">
			<label>Client Type</label>
			<c:forEach items="${client_types}" var="type">
				<input class="radio" type="radio" name="clientType" value="${type }">
				<label><c:out value="${type }" /></label>
				<br />
			</c:forEach>
		</div>
		<label class="left">Redirect Url</label> <input name="redirectUrl" id="redirectUrl" class="right" required/>
	</fieldset>
	<fieldset id="actions">
		<input id="submit" type="submit" value="Create Account" class="left" />
	</fieldset>
	</div>

</form>