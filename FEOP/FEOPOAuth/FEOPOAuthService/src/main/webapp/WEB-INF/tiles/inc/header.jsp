<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>">FOEP Oauth2</a>
            <button class="navbar-toggle" data-toggle="collapse" data-target=".nav-header-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse nav-header-collapse">
            <sec:authorize ifAllGranted="ROLE_USER">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<c:url value="/home"/>"><i class="glyphicon glyphicon-home"></i>
                        Home</a></li>
                    <li class="dropdown"><a href="<c:url value="/account/profile"/>" class="dropdown-toggle"
                                            data-toggle="dropdown">Profile<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/account/pwd/edit"/>">Change Password</a></li>
                            <li><a href="<c:url value="/account/profile"/>">Profile</a></li>
                            <li><a href="<c:url value="/account/edit"/>">Update Profile</a></li>
                        </ul>
                    </li>
                    <sec:authorize ifAllGranted="ROLE_USER" access="hasRole('ROLE_DEV')">
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"
                                                href="<c:url value="/client/list"/>">Applications<b
                                class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value="/client/list"/>">My Application</a></li>
                                <li><a href="<c:url value="/client/new"/>">New Application</a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize ifAllGranted="ROLE_USER" access="hasRole('ROLE_ADMIN')">
                        <li class="dropdown"><a href="<c:url value="/admin/et/list"/>" class="dropdown-toggle"
                                                data-toggle="dropdown">Template<b
                                class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value="/admin/et/list"/>">Templates</a></li>
                                <li><a href="<c:url value="/admin/et/new"/>">New Template</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="<c:url value="/admin/settings/list"/>" class="dropdown-toggle"
                                                data-toggle="dropdown">Settings<b
                                class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value="/admin/settings/list"/>">All Settings</a></li>
                                <li><a href="<c:url value="/admin/settings/new"/>">New settings</a></li>
                                <li><a href="<c:url value="/admin/invite/list"/>">Inviting</a></li>
                            </ul>
                        </li>

                    </sec:authorize>
                </ul>
            </sec:authorize>

            <div class="navbar-btn navbar-right">
                <sec:authorize ifAllGranted="ROLE_USER">
                    <a class="btn btn-primary btn-sm" href="<c:url value="/oauth/logout.do"/>">Logout</a>
                </sec:authorize>
                <sec:authorize ifNotGranted="ROLE_USER">
                    <a class="btn btn-default btn-sm" href="<c:url value="/signup/new"/>">Signup</a>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>