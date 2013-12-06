<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%-- <header>
	<div id="header-left">
		<sec:authorize ifAllGranted="ROLE_USER">
			<c:url value="/home" var="targetUrl"/>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_USER">
			<c:url value="/index" var="targetUrl"/>
		</sec:authorize>
		<div id="logo-icon">
			<a href="${targetUrl }">
			<img src="<c:url value="/resources/images/logo.png"/>" width="224" height="98" />
			</a>
		</div>
		<div id="logo-desc">
			<span>Free Online Education Platform</span>
		</div>
	</div>

	<div id="header-right">
		<sec:authorize ifAllGranted="ROLE_USER">
			<a class="darkgray" href="<c:url value="/oauth/logout.do"/>">Logout</a>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_USER">
			<a class="gray" href="<c:url value="/signup/"/>">Signup</a>
		</sec:authorize>
	</div>
</header> --%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>"><img src="<c:url value="/resources/images/logo.png"/>"
                                                                   height="20"></a>
        </div>
        <div class="navbar-collapse collapse">
            <sec:authorize ifAllGranted="ROLE_USER">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<c:url value="/home"/>"><i class="glyphicon glyphicon-home"></i>
                        Home</a></li>
                    <li class="dropdown"><a href="<c:url value="/account/profile"/>" class="dropdown-toggle"
                                            data-toggle="dropdown">Profile<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/account/changepwd"/>">Change Password</a></li>
                            <li><a href="<c:url value="/account/profile"/>">Profile</a></li>
                            <li><a href="<c:url value="/account/update"/>">Update Profile</a></li>
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

            <div class="navbar-form navbar-right">
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