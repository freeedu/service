<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url value="/resetpassword" var="resetAction"/>

<c:if test="${msg != null }">
    <div class="alert alert-success">
        <c:out value="${msg }"/>
    </div>
</c:if>
<div class="row">
    <div class="col-md-8 col-md-offset-2">

        <form class="form-horizontal" action="${resetAction }" method="post" name="resetPasswordForm">

            <div class="form-group">
                <h3>Reset password</h3>
                <hr>
            </div>

            <s:hasBindErrors name="resetPasswordForm">
                <c:if test="${errors.errorCount gt 0}">
                    <div class="alert alert-danger">
                        <b>Errors:</b><br/>
                        <c:forEach items="${errors.allErrors}" var="error">
                            <s:message code="${error.code}" arguments="${error.arguments}"
                                       text="${error.defaultMessage}"/>
                            <br/>
                        </c:forEach>
                    </div>
                </c:if>
            </s:hasBindErrors>
            <div class="form-group">
                <input class="form-control" type="email" name="email" placeholder="Enter your account email"/>
            </div>
            <div class="form-group">
                <input type="submit" value="Send" class="btn btn-primary"/>
            </div>
        </form>
    </div>
</div>