<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:choose>
<c:when test="${not empty applications  }">
<c:forEach items="${applications}" var="app">
	<div class="viewable client">
		<div class="widget">
			<h1>
				<c:url value="/client/view/${app.clientId }" var="viewTarget" />
				<a href="${viewTarget }"><c:out value="${app.clientId }" /></a>
			</h1>
			<div class="widget-content">
				<div class="line">
					<label class="left">Application Id:</label><label class="right"><c:out value="${app.clientId }" /></label>
				</div>
				<div class="line">
					<label class="left">Client Secret:</label><label class="right"><c:out value="${app.clientSecret }" /></label>
				</div>
				<div class="line">
					<label class="left">Authorized Grant Types:</label><label class="right"><c:out value="${app.authorizedGrantTypes }" /></label>
				</div>
				<div class="line">
					<label class="left">Authorities:</label><label class="right"><c:out value="${app.authorities }" /></label>
				</div>
				<div class="line">
					<label class="left">Resource Ids:</label><label class="right"><c:out value="${app.resourceIds }" /></label>
				</div>
				<div class="line">
					<label class="left">Scope:</label><label class="right"><c:out value="${app.clientId }" /></label>
				</div>
				<div class="line">
					<label class="left">Redirect Uri:</label><label class="right"><c:out value="${app.webServerRedirectUri }" /></label>
				</div>
				<div class="line">
					<label class="left">Additional Info:</label><label class="right"><c:out value="${app.additionalInfo }" /></label>
				</div>
				<div class="line">
					<label class="left"><a href='<c:url value="/client/delete/${app.clientId }"/>'>Delete</a></label>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</c:when> 
<c:otherwise>
	<div class="viewable client">
		<div class="warn">You have no applications. <a href="<c:url value="/client/"/>">Create</a> now.</div>
	</div>
</c:otherwise>
</c:choose>