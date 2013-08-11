<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:forEach items="${settings}" var="setting">
	<div class="viewable setting">
		<div class="widget">
			<h1>
				<c:url value="/settings/view?id=${setting.id }" var="viewTarget" />
				<a href="${viewTarget }"><c:out value="${setting.key }" /></a>
			</h1>
			<div class="widget-content">
				<div class="line">
					<label class="left">Setting Key: </label> <label class="right"><c:out value="${setting.key }" /></label>
				</div>
				<div class="line">
					<label class="left">Setting Value: </label> <label class="right"><c:out value="${setting.value }" /></label>
				</div>
				<div class="line">
					<label class="left">Effect Start Date: </label> <label class="right"><c:out value="${setting.startDate }" /></label>
				</div>
				<div class="line">
					<label class="left">Effect End Date: </label> <label class="right"><c:out value="${setting.endDate }" /></label>
				</div>
				<div class="line">
					<label class="left">Diabled: </label> <label class="right"><c:out value="${setting.disabled }" /></label>
				</div>
				<div class="line">
					<label class="left"><a href='<c:url value="/settings/delete?id=${setting.id }"/>'>Delete</a></label>
				</div>
			</div>
		</div>
	</div>
</c:forEach>