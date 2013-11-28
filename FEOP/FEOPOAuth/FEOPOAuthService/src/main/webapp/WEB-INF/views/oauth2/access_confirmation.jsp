<%@ page
        import="org.springframework.security.core.AuthenticationException" %>
<%@ page
        import="org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException" %>
<%@ page
        import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ taglib prefix="authz"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="pageTitle" value="Access Confirmation" scope="request"/>
    <title><c:out value="${pageTitle }"/></title>
    <link type="text/css" rel="stylesheet"
          href="<c:url value="/resources/css/default.css"/>"/>
    <style type="text/css">
        form input {
            width: 80px;
            height: 30px;
        }
    </style>
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
        <%
            if (session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null
                    && !(session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) instanceof UnapprovedClientAuthenticationException)) {
        %>
        <div class="error">
            <h2>Woops!</h2>

            <p>
                Access could not be granted.
                (<%=((AuthenticationException) session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
                    .getMessage()%>)
            </p>
        </div>
        <%
            }
        %>
        <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>

        <authz:authorize ifAllGranted="ROLE_USER">
            <h2>Please Authorize:</h2>

            <p>
                Please Authorize "
                <c:out value="${client.clientId}"/>
                " to visit your resources.
            </p>

            <form id="confirmationForm" name="confirmationForm"
                  action="<%=request.getContextPath()%>/oauth/authorize"
                  method="post">
                <input name="user_oauth_approval" value="true" type="hidden"/> <label><input
                    name="authorize" value="Authorize" type="submit" class="button"></label>
            </form>
            <form id="denialForm" name="denialForm"
                  action="<%=request.getContextPath()%>/oauth/authorize"
                  method="post">
                <input name="user_oauth_approval" value="false" type="hidden"/> <label><input
                    name="deny" value="Cancel" type="submit" class="button"></label>
            </form>
        </authz:authorize>
    </div>
</div>
<div id="footer">Copyright &copy; 2013 oauth.com. All rights
    reserved.
</div>
</body>
</html>