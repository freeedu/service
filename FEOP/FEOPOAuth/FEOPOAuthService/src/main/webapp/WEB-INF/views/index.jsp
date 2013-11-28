<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="authz"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <c:set var="pageTitle" value="Open Auth Platform" scope="request"/>
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
        <div id="header-right">
            <authz:authorize ifAllGranted="ROLE_USER">
                <div style="text-align: center">

                    <a href="<c:url value="/oauth/logout.do"/>">Logout</a>
                </div>
            </authz:authorize>
            <authz:authorize ifNotGranted="ROLE_USER">
                <a href="<c:url value="/signup/"/>">Signup</a>
            </authz:authorize>
        </div>
    </div>

    <div id="content">
        <div id="content-header">
            <div id="content-header-left">
                <h2>Welcome to OAuth Platform</h2>
            </div>

        </div>
        <div id="content-body">
            <authz:authorize ifNotGranted="ROLE_USER">
                <div id="login-form">
                    <div id="login-form-header">Welcome to login Open Auth
                        Platform
                    </div>
                    <div id="login-form-content">
                        <form id="loginForm" name="loginForm"
                              action="<c:url value="/oauth/login.do"/>" method="post">
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
            </authz:authorize>
        </div>
    </div>
</div>
<div id="footer">Copyright &copy 2013 oauth.com. All rights
    reserved.
</div>
</body>
</html>
