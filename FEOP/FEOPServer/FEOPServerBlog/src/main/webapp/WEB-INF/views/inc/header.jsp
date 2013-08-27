<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<header>
	<div id="header-left">
		<c:url value="/index" var="targetUrl" />
		<div id="logo-icon">
			<a href="${targetUrl }"> <img src="<c:url value="/resources/images/logo.png"/>" width="224" height="98" />
			</a>
		</div>
		<div id="logo-desc">
			<span>Free Online Education Platform</span>
		</div>
	</div>

	<div id="header-right">
		<c:choose>
			<c:when test="${empty authentication }">
				<a class="darkgray" href='<c:url value="/user/login"/>'>Login</a>
				<a class="gray" href="/signup">Regist</a>
			</c:when>
			<c:otherwise>
				<label> Welcome <c:out value="${authentication.userInfo.screenName }"></c:out>!
				</label>
				<a class="gray" href="/user/logout">Logout</a>
			</c:otherwise>
		</c:choose>
	</div>
</header>

<nav>
	<ul id="menu">
		<li><a href='<c:url value="/blog/list"/>'>Home</a></li>
		<c:if test="${not empty authentication }">
			<c:url value="/my/blog/list" var="myblogs"/>
			<c:url value="/my/blog/create" var="createblog"/>
			
			<li><a href='${myblogs }'>My Blog</a>
				<ul>
					<li><a href="${myblogs }">My Blogs</a></li>
					<li><a href="${createblog }">Write a Blog</a></li>
					<li><a href="#">Menu2</a>
						<ul>
							<li><a href="#">Menu3</a></li>
							<li><a href="#">Menu3</a></li>
							<li><a href="#">Menu3<span class="bubble">15</span></a></li>
						</ul></li>
				</ul></li>
		</c:if>
		<li><a href="#">Contact</a></li>
		<li><a href="#">About</a></li>
	</ul>

	<div id="search-bar">
		<c:url value="/blog/search" var="search" />
		<form class="search-form" action="${search }" method="get">
			<input type="submit" value="Search" class="submit"> <input type="text" class="content" name="q" placeholder="Search Site..." required>
		</form>
	</div>
</nav>