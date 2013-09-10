<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/account/update" var="update" />
<c:if test="${userForm != null}">
	<div class="col-md-8 col-md-offset-2">

		<div class="row">
			<div>
				<h3>
					User Profile <a href="${update }" class="pull-right btn btn-link btn-sm">Edit Profile</a>
				</h3>
			</div>

			<hr>
		</div>

		<div class="row">
			<div class="col-md-3">User Id:</div>
			<div class="col-md-9">
				<c:out value="${userForm.userId }" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">First Name:</div>
			<div class="col-md-9">
				<c:out value="${userForm.firstName }" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">Last Name:</div>
			<div class="col-md-9">
				<c:out value="${userForm.lastName }" />
			</div>
		</div>

		<div class="row">
			<div class="col-md-3">User Name:</div>
			<div class="col-md-9">
				<c:out value="${userForm.userName }" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">Email:</div>
			<div class="col-md-9">
				<c:out value="${userForm.email }" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">Phone:</div>
			<div class="col-md-9">
				<c:out value="${userForm.phone }" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">Status:</div>
			<div class="col-md-9">
				<c:out value="${userForm.activated }" />
			</div>
		</div>
	</div>
</c:if>