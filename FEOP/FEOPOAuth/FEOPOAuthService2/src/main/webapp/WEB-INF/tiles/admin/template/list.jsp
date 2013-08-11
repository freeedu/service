<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:forEach items="${templates}" var="template">
	<div class="viewable template">
		<div class="widget">
			<h1>
				<c:url value="/et/view?id=${template.id }" var="viewTarget" />
				<a href="${viewTarget }"><c:out value="${template.name }" /></a>
			</h1>
			<div class="widget-content">

				<div class="line">
					<div class="left">Template Id:</div>
					<div class="right">
						<c:out value="${template.id }" />
					</div>
				</div>
				<div class="line">
					<div class="left">Name:</div>
					<div class="right">
						<c:out value="${template.name }" />
					</div>
				</div>
				<div class="line">
					<div class="left">Subject:</div>
					<div class="right">
						<c:out value="${template.subject }" />
					</div>
				</div>
				<div class="line">
					<div class="left">Version:</div>
					<div class="right">
						<c:out value="${template.version }" />
					</div>
				</div>
				<div class="line">
					<div class="left">Content:</div>
					<div class="right"></div>
				</div>
				<textarea rows="6" disabled="disabled">${template.content }</textarea>

				<div class="line">
					<label class="left"><a href='<c:url value="/et/delete?id=${template.id }"/>'>Delete</a></label>
				</div>
			</div>
		</div>
	</div>
</c:forEach>