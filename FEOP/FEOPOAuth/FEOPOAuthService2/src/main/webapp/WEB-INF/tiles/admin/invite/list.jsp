<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="invite">
	<a id="generate" href='<c:url value="/admin/invite/generate"/>'>Generate 10 more Code</a>
</div>
<c:forEach items="${invites}" var="invite">
	<div class="viewable invite">
		<div class="widget">
			<h1>
				<a><c:out value="${invite.inviteCode }" /></a>
			</h1>
			<div class="widget-content">
				<div class="line">
					<label class="left">Invide Code: </label> <label class="right"><c:out value="${invite.inviteCode }" /></label>
				</div>
				<div class="line">
					<label class="left">Used: </label> <label class="right"><c:out value="${invite.used }" /></label>
				</div>
				<div class="line">
					<label class="left"><a href='<c:url value="/admin/invite/delete?id=${invite.id }"/>'>Delete</a></label>
				</div>
			</div>
		</div>
	</div>
</c:forEach>