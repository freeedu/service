<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url value="/client/save" var="newAction"/>

<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <form action="${newAction }" method="post" name="clientForm" class="form-horizontal">
            <div class="form-group">
                <h3>Create Application</h3>
                <hr>
            </div>
            <s:hasBindErrors htmlEscape="true" name="clientForm">
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
                <label class="left">Client Name</label> <input name="clientName" class="form-control" required/>
            </div>
            <div class="form-group ">
                <label>Client Type</label>

                <div class="col-sm-offset-3">
                    <c:forEach items="${client_types}" var="type">
                        <label><input type="radio" name="clientType" value="${type }"> <c:out value="${type }"/></label>
                        <br/>
                    </c:forEach>
                </div>
            </div>
            <div class="form-group">
                <label class="left">Redirect Url</label> <input name="redirectUrl" class="form-control" required/>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </div>
            </div>
            <div class="form-group">
                <input type="submit" value="Create Account" class="btn btn-primary btn-sm"/>
            </div>
        </form>
    </div>
</div>