<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>

    <div class="col-md-6 col-md-offset-3">
        <%
            if (session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null
                    && !(session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) instanceof UnapprovedClientAuthenticationException)) {
        %>
        <div class="form-group">
            <div class="alert alert-danger">
                <p>
                    Access could not be granted.
                    (<%=((AuthenticationException) session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
                        .getMessage()%>)
                </p>
            </div>
        </div>
        <%
            }
        %>
        <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>

        <sec:authorize ifAllGranted="ROLE_USER">
            <h1>Authorize</h1>
            <hr>

            <p>
                Please Authorize ' <b><c:out value="${client.clientId}"/></b> ' to visit your resources.
            </p>

            <form id="confirmationForm" name="confirmationForm" action="<%=request.getContextPath()%>/oauth/authorize"
                  method="post">
                <input name="user_oauth_approval" value="true" type="hidden"/>

                <div class="form-group">

                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                </div>
                <label><input name="authorize"
                              value="Authorize"
                              type="submit"
                              class="btn btn-info btn-sm"></label>
            </form>
            <form id="denialForm" name="denialForm" action="<%=request.getContextPath()%>/oauth/authorize"
                  method="post">
                <input name="user_oauth_approval" value="false" type="hidden"/>

                <div class="form-group">

                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                </div>
                <label><input name="deny" value="Deny"
                              type="submit"
                              class="btn btn-danger btn-sm"></label>
            </form>
        </sec:authorize>
    </div>
</div>