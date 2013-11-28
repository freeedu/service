<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="authz"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Client Info" scope="request"/>
<!DOCTYPE html>
<html>
<head>
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
                <h2>View Application</h2>
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
            <div style="width: 300px; left: 35%; position: relative;">
                <div class="widget">
                    <div class="widget-header">Your Application Information</div>
                    <div class="widget-body">
                        <div>Please remember the following information for using.</div>
                        <div class="line">
                            <label class="left">Client Id: </label> <label class="right"><c:out
                                value="${client.clientId }"/></label>
                        </div>
                        <div class="line">
                            <label class="left">Secret: </label> <label class="right"><c:out
                                value="${client.clientSecret }"/></label>
                        </div>
                    </div>
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