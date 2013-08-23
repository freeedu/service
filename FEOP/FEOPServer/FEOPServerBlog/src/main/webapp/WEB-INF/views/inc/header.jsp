<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<header>
	<div id="header-left">
		<div id="logo-icon">
			<img src="<c:url value="/resources/images/logo.png"/>" width="224" height="98" />
		</div>
		<div id="logo-desc">
			<span>Free Online Education Platform</span>
		</div>
	</div>

	<div id="header-right">
		<a class="darkgray" href='<c:url value="/me/info"/>'>My Info</a> <a class="darkgray" href='<c:url value="/signin?type=feop"/>'>Login</a> <a class="gray" href="">Regist</a>
	</div>
</header>

<nav>
	<ul id="menu">
		<li><a href="#">Home</a></li>
		<li><a href="#">My Blog</a>
			<ul>
				<li><a href="#">Menu2</a></li>
				<li><a href="#">Menu2</a></li>
				<li><a href="#">Menu2</a>
					<ul>
						<li><a href="#">Menu3</a></li>
						<li><a href="#">Menu3</a></li>
						<li><a href="#">Menu3<span class="bubble">15</span></a></li>
					</ul></li>
			</ul></li>
		<li><a href="#">Contact</a></li>
		<li><a href="#">About</a></li>
	</ul>

	<div id="search-bar">
		<form class="search-form">
			<input type="submit" value="Search" class="submit"> <input type="text" class="content" placeholder="Search Site..." required>
		</form>
	</div>
</nav>