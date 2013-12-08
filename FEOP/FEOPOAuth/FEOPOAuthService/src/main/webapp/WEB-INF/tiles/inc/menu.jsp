<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<nav>
    <ul id="menu">
        <li><a href="<c:url value="/home"/>">Home</a></li>
        <li><a href="<c:url value="/account/profile"/>">Profile</a>
            <ul class="dropdown-menu">
                <li><a href="<c:url value="/account/pwd/edit"/>">Change Password</a></li>
                <li><a href="<c:url value="/account/profile"/>">Profile</a></li>
                <li><a href="<c:url value="/account/edit"/>">Update Profile</a></li>
            </ul>
        </li>

        <sec:authorize ifAllGranted="ROLE_USER" access="hasRole('ROLE_DEV')">
            <li><a href="<c:url value="/client/list"/>">Applications</a>
                <ul>
                    <li><a href="<c:url value="/client/list"/>">My Application</a></li>
                    <li><a href="<c:url value="/client/form"/>">New Application</a></li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize ifAllGranted="ROLE_USER" access="hasRole('ROLE_ADMIN')">
            <li><a href="<c:url value="/admin/et/list"/>">Template</a>
                <ul>
                    <li><a href="<c:url value="/admin/et/list"/>">Templates</a></li>
                    <li><a href="<c:url value="/admin/et/new"/>">New Template</a></li>
                </ul>
            </li>
            <li><a href="<c:url value="/admin/settings/list"/>">Settings</a>
                <ul>
                    <li><a href="<c:url value="/admin/settings/list"/>">All Settings</a></li>
                    <li><a href="<c:url value="/admin/settings/new"/>">New settings</a></li>
                    <li><a href="<c:url value="/admin/invite/list"/>">Inviting</a></li>
                </ul>
            </li>

        </sec:authorize>
    </ul>
</nav>