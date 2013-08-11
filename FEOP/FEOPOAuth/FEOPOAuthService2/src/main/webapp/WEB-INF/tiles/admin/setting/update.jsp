<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/settings/update" var="update" />
<form action="${update }" method="post" class="editable" id="profile" name="setting">
	<h1>Edit Setting</h1>
	<fieldset class="inputs">
		<s:hasBindErrors htmlEscape="true" name="systemSettingForm">
			<c:if test="${errors.errorCount gt 0}">
				<h4>Errors:</h4>
				<font color="red"> <c:forEach items="${errors.allErrors}" var="error">
						<s:message code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}" />
						<br />
					</c:forEach>
				</font>
			</c:if>
		</s:hasBindErrors>
		<input type="text" value="${setting.id }" name="id" class="right" hidden="true" /> <label class="left">Setting Key:</label><input type="text"
			value="${setting.key }" name="key" class="right" /> <label class="left">Setting Value:</label><input type="text" value="${setting.value }"
			name="value" class="right" /> <label class="left">Effect Start Date:</label><input type="datetime" value="${setting.startDate }"
			name="startDate" class="right" /> <label class="left">Effect End Date:</label><input type="datetime" value="${setting.endDate }"
			name="endDate" class="right" /> <label class="left">Diabled:</label><input type="checkbox" value="${setting.disabled }" name="disabled"
			class="right" />

	</fieldset>
	<fieldset class="actions">
		<input type="submit" value="Update Profile" id="submit" />
	</fieldset>
</form>