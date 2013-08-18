<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/admin/settings/new" var="newAction" />
<form action="${newAction }" method="post" name="systemSettingForm" class="editable setting">
	<h1>New Template</h1>
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
	<fieldset class="inputs">
		<label class="left" for="key">Key</label> <input type="text" name="key" class="right" required/>
		<label class="left" for="value">Value</label> <input type="text" name="value" class="right" required/>
		<label class="left" for="startDate">Effect Start Time</label> <input type="datetime" name="startDate" class="right" required/>
		<label class="left" for="endDate">Effect End Time</label> <input type="datetime" name="endDate" class="right" />
		<input id="checkbox" type="checkbox" name="diabled" class="left" checked="checked">
		<label for="diabled" class="checkbox-label"> Diable </label>
	</fieldset>
	<fieldset id="actions">
		<input id="submit" type="submit" value="Save Template" class="left" />
	</fieldset>
	

</form>