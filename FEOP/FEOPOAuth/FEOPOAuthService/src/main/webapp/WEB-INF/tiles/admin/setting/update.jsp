<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class=" col-md-8 col-md-offset-2">
    <h4>Edit Setting</h4>
    <hr>
    <c:url value="/admin/settings/update" var="update"/>
    <form action="${update }" method="post" class="form-horizontal" id="profile" name="setting">
        <s:hasBindErrors htmlEscape="true" name="systemSettingForm">
            <c:if test="${errors.errorCount gt 0}">
                <div class="form-group">
                    <div class="alert alert-danger">
                        <h5>Errors:</h5>
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
            <label for="key" class="col-sm-3">Setting Key:</label>

            <div class="col-sm-9">
                <input id="key" type="text" value="${setting.key }" name="key"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="value" class="col-sm-3">Setting Value:</label>

            <div class="col-sm-9"><input id="value" type="text" value="${setting.value }"
                                         name="value"
                                         class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="startDate" class="col-sm-3">Effect Start Date:</label>

            <div class="col-sm-9"><input id="startDate" type="datetime"
                                         value="${setting.startDate }"
                                         name="startDate" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="endDate" class="col-sm-3">Effect End Date:</label>

            <div class="col-sm-9">
                <input id="endDate" type="datetime" value="${setting.endDate }"
                       name="endDate" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="disabled" class="col-sm-3">Diabled:</label>

            <div class="col-sm-9">
                <input id="disabled" type="checkbox" value="${setting.disabled }"
                       name="disabled"
                       class="checkbox"/>
            </div>
        </div>
        <div class="form-group">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

        </div>
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <input type="submit" value="Update Profile" class="btn btn-primary btn-sm"/>
            </div>
        </div>
    </form>
</div>