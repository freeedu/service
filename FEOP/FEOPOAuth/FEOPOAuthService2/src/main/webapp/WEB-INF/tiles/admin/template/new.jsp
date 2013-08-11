<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/et/new" var="newAction" />
<form action="${newAction }" method="post" name="emailTemplateForm" class="editable client2">
	<h1>New Template</h1>
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
	<fieldset class="inputs">
		<label class="left" for="name">Template Name</label> <input name="name" class="right" required/>
		<label class="left" for="subject">Subject</label> <input name="subject" class="right" required/>
		<label class="left" for="content">Content</label> <textarea name="content" class="right" required rows="6"></textarea>
	</fieldset>
	<fieldset id="actions">
		<input id="submit" type="submit" value="Save Template" class="left" />
	</fieldset>
	</div>

</form>