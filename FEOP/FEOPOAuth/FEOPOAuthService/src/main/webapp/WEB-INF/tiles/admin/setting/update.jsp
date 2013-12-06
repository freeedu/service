<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class=" col-md-8 col-md-offset-2">
    <div class="row">
        <div>
            <h3>Edit Setting</h3>
        </div>
        <hr>
    </div>
    <c:url value="/admin/settings/update" var="update"/>
    <form action="${update }" method="post" class="editable" id="profile" name="setting">
        <s:hasBindErrors htmlEscape="true" name="systemSettingForm">
            <c:if test="${errors.errorCount gt 0}">
                <div class="form-group">
                    <div class="alert alert-danger">
                        <h4>Errors:</h4>
                        <font color="red"> <c:forEach items="${errors.allErrors}" var="error">
                            <s:message code="${error.code}" arguments="${error.arguments}"
                                       text="${error.defaultMessage}"/>
                            <br/>
                        </c:forEach>
                        </font>
                    </div>
                </div>
            </c:if>
        </s:hasBindErrors>
        <div class="form-group">
            <input type="text" value="${setting.id }" name="id" class="right" hidden="true"/>
        </div>
        <div class="form-group">
            <label for="key">Setting Key:</label><input id="key" type="text" value="${setting.key }" name="key"
                                                        class="form-control"/>
        </div>
        <div class="form-group">
            <label for="value">Setting Value:</label><input id="value" type="text" value="${setting.value }" name="value"
                                                            class="form-control"/>
        </div>
        <div class="form-group">
            <label for="startDate">Effect Start Date:</label><input id="startDate" type="datetime" value="${setting.startDate }"
                                                                    name="startDate" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="endDate">Effect End Date:</label><input id="endDate" type="datetime" value="${setting.endDate }"
                                                                name="endDate" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="disabled">Diabled:</label><input id="disabled" type="checkbox" value="${setting.disabled }" name="disabled"
                                                         class="checkbox"/>
        </div>
        <div class="form-group">
            <input type="submit" value="Update Profile" class="btn btn-primary btn-sm"/>
        </div>
    </form>
</div>