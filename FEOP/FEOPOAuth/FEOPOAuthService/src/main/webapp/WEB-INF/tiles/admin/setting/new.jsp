<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-md-8 col-md-offset-2">
    <h4>New Setting</h4>
    <hr>

    <c:url value="/admin/settings/save" var="newAction"/>
    <form action="${newAction }" method="post" name="systemSettingForm" class="form-horizontal">
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
            <label for="key" class="col-sm-3">Key</label>

            <div class="col-sm-9">
                <input id="key" type="text" name="key" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <label for="value" class="col-sm-3">Value</label>

            <div class="col-sm-9"><input id="value" type="text" name="value"
                                         class="form-control" required/>
            </div>
        </div>
        <div class="form-group">

            <label for="startDate" class="col-sm-3">Effect Start Time</label>

            <div class="col-sm-9"><input id="startDate" type="date"
                                         name="startDate"
                                         class="form-control"
                                         required/>
            </div>
        </div>
        <div class="form-group">
            <label for="endDate" class="col-sm-3">Effect End Time</label>

            <div class="col-sm-9">
                <input id="endDate" type="date" name="endDate"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="disabled" class="col-sm-3">Disable</label>
            <div class="col-sm-9">
                <input id="disabled" type="checkbox" name="disabled">
            </div>
        </div>
        <div class="form-group">

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

        </div>
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <input class="btn btn-primary btn-sm" type="submit" value="Create Setting"/>
                <input class="btn btn-default btn-sm" type="reset" value="Reset"/>
            </div>
        </div>
    </form>
</div>