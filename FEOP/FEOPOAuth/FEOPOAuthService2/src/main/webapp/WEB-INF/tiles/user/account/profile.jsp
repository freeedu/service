<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<c:if test="${userForm != null}"></c:if>
<div class="viewable" id="profile">
	<h1>User Profile</h1>
	<c:url value="/account/update" var="update" />
	<div class="line">
		<div class="left">User Id:</div><div class="right"><c:out value="${userForm.userId }" /></div>
	</div>
	<div class="line">
		<div class="left">User Name:</div><div class="right"><c:out value="${userForm.userName }" /></div>
	</div>
	<div class="line">
		<div class="left">Email:</div><div class="right"><c:out value="${userForm.email }" /></div>
	</div>
	<div class="line">
		<div class="left">Phone:</div><div class="right"><c:out value="${userForm.phone }" /></div>
	</div>
	<div class="line">
		<div class="left">Status:</div><div class="right"><c:out value="${userForm.activated }" /></div>
	</div>
	<div class="line">
		<div class="left"><a href="${update }">Edit Profile</a></div>
	</div>
</div>