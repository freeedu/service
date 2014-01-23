<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url value="/account/pwd/update" var="updateAction"/>


<div class="col-md-8 col-md-offset-2">
    <form class="form-horizontal" action="${updateAction }" method="post" name="changePasswordForm">

        <h4>Change Password</h4>
        <hr>

        <c:if test="${msg != null }">
            <div class="form-group">
                <div class="alert alert-success">
                    <c:out value="${msg }"/>
                </div>
            </div>
        </c:if>

        <s:hasBindErrors name="changePasswordForm">
            <c:if test="${errors.errorCount gt 0}">
                <div class="form-group">
                    <div class="alert alert-danger">
                        <b>Errors:</b><br/>
                        <c:forEach items="${errors.allErrors}" var="error">
                            <s:message code="${error.code}" arguments="${error.arguments}"
                                       text="${error.defaultMessage}"/>
                            <br/>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </s:hasBindErrors>

        <div class="form-group">
            <div class="input-group col-sm-12">
                <span class="input-group-addon "><i class="glyphicon glyphicon-lock"></i></span><input
                    class="form-control" type="password"
                    placeholder="Old Password" name="oldPassword" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group col-sm-12">
                <span class="input-group-addon "><i class="glyphicon glyphicon-lock"></i></span><input
                    class="form-control" type="password"
                    placeholder="New Password" name="newPassword" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group col-sm-12">
                <span class="input-group-addon "><i class="glyphicon glyphicon-lock"></i></span><input
                    class="form-control" type="password"
                    placeholder="Re Enter Password" name="repeatPassword" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group col-sm-12">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-12">
                <input type="submit" class="btn btn-primary btn-sm" value="Change Password">
            </div>
        </div>
    </form>
</div>

