<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="viewable setting">
	<div class="widget">
		<h1>${setting.key }</h1>
		<c:url value="/admin/settings/update?id=${setting.id }" var="update" />
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
				<div class="left">
					<a href="${update }">Edit Setting</a>
				</div>
			</div>
		</div>
	</div>
</div>