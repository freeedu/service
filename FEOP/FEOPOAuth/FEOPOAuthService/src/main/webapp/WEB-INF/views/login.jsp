<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="authz"
           uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <c:set var="pageTitle" value="Please Login" scope="request"/>
    <c:url value="/oauth/login.do" var="loginUrl"/>
    <title><c:out value="${pageTitle }"/></title>
    <link type="text/css" rel="stylesheet"
          href="<c:url value="/resources/css/default.css"/>"/>
</head>
<body>
<div id="container">
    <div id="header">
        <div id="header-left">
            <authz:authorize ifAllGranted="ROLE_USER">
                <a href="<c:url value="/home"/>">Open Auth Platform</a>
            </authz:authorize>
            <authz:authorize ifNotGranted="ROLE_USER">
                <a href="<c:url value="/"/>">Open Auth Platform</a>
            </authz:authorize>
        </div>
    </div>

    <div id="content">
        <div id="content-header">
            <div id="content-header-left">
                <h2>Welcome to Login OAuth Platform</h2>
            </div>

        </div>
        <div id="content-body">
            <div id="login-form">
                <div id="login-form-header">Welcome to login Open Auth
                    Platform
                </div>
                <div id="login-form-content">
                    <form id="loginForm" name="loginForm" action="${loginUrl}"
                          method="post">

                        <c:if test="${param.error != null}">
                            <div class="alert alert-error">
                                Failed to login.
                                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                                    Reason: <c:out
                                        value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                                </c:if>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">You have been logged out.</div>
                        </c:if>

                        <div class="line">
                            <label class="left">Email: </label><input class="right"
                                                                      type='text' name='j_username'>
                        </div>
                        <div class="line">
                            <label class="left">Secret: </label><input class="right"
                                                                       type="password" name='j_password'>
                        </div>
                        <div class="line">
                            <input class="left" name="login" value="Login" type="submit">
                            <input class="left" name="reset" value="Reset" type="reset">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="footer">Copyright &copy 2013 oauth.com. All rights
    reserved.
</div>
</body>
</html>