<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/et/update" var="update" />
<form action="${update }" method="post" class="editable" id="profile" name="emailTemplateForm">
	<h1>Edit Template</h1>
	<fieldset class="inputs">
	<s:hasBindErrors htmlEscape="true" name="emailTemplateForm">
		<c:if test="${errors.errorCount gt 0}">
			<h4>Errors:</h4>
			<font color="red"> <c:forEach items="${errors.allErrors}" var="error">
					<s:message code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}" />
					<br />
				</c:forEach>
			</font>
		</c:if>
	</s:hasBindErrors>
			<label class="left">Template Id:</label><input type="text" value="${template.id }" name="id"  class="right" />
			<label class="left">Name:</label><input type="text" value="${template.name }" name="name" class="right" disabled="disabled"/>
			<label class="left">Subject:</label><input type="text" value="${template.subject }" name="subject" disabled="disabled" class="right" />
			<label class="left">Version:</label><input type="text" value="${template.version }" name="version" class="right" disabled="disabled"/>
			<label class="left">Content:</label><textarea name="content" class="right" rows="6" >${template.content }</textarea>
	</fieldset>
	<fieldset class="actions">
		<input type="submit" value="Update Profile" id="submit" />
	</fieldset>
</form>