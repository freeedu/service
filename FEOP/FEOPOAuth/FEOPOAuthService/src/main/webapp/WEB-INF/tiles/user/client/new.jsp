<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url value="/client/save" var="newAction"/>

<div class="col-md-8 col-md-offset-2">
    <form action="${newAction }" method="post" name="clientForm" class="form-horizontal">

        <h4>Create Application</h4>
        <hr>
        <s:hasBindErrors htmlEscape="true" name="clientForm">
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
            <label class="col-sm-3 control-label">Client Name:</label>

            <div class="col-sm-9">
                <input name="clientName" class="form-control" required/>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-3 control-label">Client Type:</label>

            <div class="col-sm-9">
                <c:forEach items="${client_types}" var="type">
                    <div>
                        <div class="radio-inline">
                            <input type="radio" name="clientType" value="${type }" id="${type}-id">
                            <label for="${type}-id" >${type }</label>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Redirect Url:</label>

            <div class="col-sm-9">
                <input type="url" name="redirectUrl" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <input type="submit" value="Create Account" class="btn btn-primary btn-sm"/>
            </div>
        </div>
    </form>

</div>