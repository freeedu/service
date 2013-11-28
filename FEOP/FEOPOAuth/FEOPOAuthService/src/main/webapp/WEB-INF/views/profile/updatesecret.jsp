<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="authz"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <c:url var="defaultCss" value="/resources/css/default.css"/>

    <title>Open Auth Platform</title>
    <link rel="stylesheet" href="${defaultCss }"/>
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
                <h2>Change Password</h2>
            </div>
            <div id="content-header-right">
                <div id="menu-bar">
                    <ul id="main-menu">
                        <li><a href="<c:url value="/home"/>">Home</a></li>
                        <li><a href="<c:url value="/profile/"/>">Profile</a>
                            <ul class="sub-menu">
                                <li><a href="<c:url value="/profile/changepwd"/>">Change
                                    Password</a></li>
                                <li><a href="<c:url value="/profile/"/>">Profile</a></li>
                                <li><a href="<c:url value="/profile/update"/>">Update
                                    Profile</a></li>
                            </ul>
                        </li>
                        <li><a href="<c:url value="/client/list"/>">Applications</a>
                            <ul class="sub-menu">
                                <li><a href="<c:url value="/client/list"/>">My
                                    Application</a></li>
                                <li><a href="<c:url value="/client/"/>">New
                                    Application</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="content-body">
            <div id="login-form">
                <div id="login-form-header">Change Password</div>
                <div id="login-form-content">
                    <c:url value="/profile/changesecret" var="updateSecret"/>
                    <form id="loginForm" name="changePasswordForm"
                          action="${updateSecret }" method="post">
                        <c:if test="${null != msg}">
                            <div style="color: red;">
                                <label>Result: <c:out value="${msg }"></c:out></label>
                            </div>
                        </c:if>
                        <s:hasBindErrors htmlEscape="true" name="changePasswordForm">
                            <c:if test="${errors.errorCount gt 0}">
                                <h4>Errors:</h4>
                                <font color="red"> <c:forEach
                                        items="${errors.allErrors}" var="error">
                                    <s:message code="${error.code}"
                                               arguments="${error.arguments}"
                                               text="${error.defaultMessage}"/>
                                    <br/>
                                </c:forEach>
                                </font>
                            </c:if>
                        </s:hasBindErrors>
                        <div class="line">
                            <label class="left">Old Password: </label><input type="password"
                                                                             name='oldPassword' class="right"/>
                        </div>
                        <div class="line">
                            <label class="left">New Password: </label><input type="password"
                                                                             name='newPassword' class="right"/>
                        </div>
                        <div class="line">
                            <label class="left">Repeat Password: </label><input
                                type="password" name='repeatPassword' class="right"/>
                        </div>
                        <div class="line">
                            <input class="left" name="login" value="Change Password"
                                   type="submit">
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