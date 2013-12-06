<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-md-8 col-md-offset-2">
    <div class="row">
        <div>
            <h3>New Setting</h3>
        </div>
        <hr>
    </div>

    <c:url value="/admin/settings/new" var="newAction"/>
    <form action="${newAction }" method="post" name="systemSettingForm" class="editable setting">
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
            <label for="key">Key</label> <input id="key" type="text" name="key" class="form-control" required/>
        </div>
        <div class="form-group">
            <label for="value">Value</label> <input id="value" type="text" name="value" class="form-control" required/>
        </div>
        <div class="form-group">

            <label for="startDate">Effect Start Time</label> <input id="startDate" type="date" name="startDate" class="form-control"
                                                                    required/>
        </div>
        <div class="form-group">
            <label for="endDate">Effect End Time</label> <input id="endDate" type="date" name="endDate" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="disabled"> <input id="disabled" type="checkbox" name="disabled" checked="checked">&nbsp;Disable
            </label>
        </div>
        <div class="form-group">
            <input class="btn btn-primary btn-sm" type="submit" value="Create Setting"/>
        </div>
    </form>
</div>