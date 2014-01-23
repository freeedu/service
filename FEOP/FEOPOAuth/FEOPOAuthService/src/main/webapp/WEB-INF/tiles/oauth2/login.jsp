<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url value="/oauth/login.do" var="loginAction"/>

<sec:authorize ifNotGranted="ROLE_USER">

    <div class="col-md-4 col-md-offset-4">
        <form role="form" class="form-horizontal" name="loginForm" action="${loginAction}" method="post">


            <h4>Login</h4>
            <hr>

            <c:if test="${param.error != null}">
                <div class="form-group">
                    <div class="alert alert-danger">
                        Failed to login.
                        <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                            <br>
                            Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                        </c:if>
                    </div>
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="form-group">
                    <div class="alert alert-info">You have been logged out.</div>
                </div>
            </c:if>
            <c:if test="${param.expired != null}">
                <div class="form-group ">
                    <div class="alert alert-warning">Your session is expired. Please login again.</div>
                </div>
            </c:if>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon "><i class="glyphicon glyphicon-user"></i></span><input
                        class="form-control" type="email|tel|text"
                        placeholder="Username" autofocus required name='username'>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon "><i class="glyphicon glyphicon-lock"></i></span><input
                        class="form-control" type="password"
                        placeholder="Password" required name='password'>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <input
                            class="form-control" type="hidden"
                            name="${_csrf.parameterName}"
                            value="${_csrf.token}"/>
                </div>
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-sm" value="Log in">
            </div>

        </form>
    </div>

</sec:authorize>

