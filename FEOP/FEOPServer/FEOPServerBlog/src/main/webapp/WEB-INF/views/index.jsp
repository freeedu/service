
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="inc/pagination.jsp"/>
<c:forEach items="${blogs }" var="blog">
	<c:if test="${blog != null }">
		<c:url value="/blog/view?id=${blog.id }" var="viewblog" />

		<div class="panel panel-default">
			<div class="panel-heading">
				<a href="${viewblog }"><c:out value="${blog.blogTitle }" /></a> <br> <small><c:out value="${blog.blogSubtitle }" /></small>
			</div>
			<div class="panel-body">
				<ul class="list-inline">
					<li><span><c:out value="${blog.createDate }" /></span></li>
					<li>By <a href="#"><span><c:out value="${blog.authorName }" /></span></a>
					</li>
					<c:if test="${blog.category != null}">
						<c:url value="/blog/list?c=${blog.category.id }" var="viewCategoryBlog" />
						<li><a href="${viewCategoryBlog }"><c:out value="${blog.category.categoryName }" /></a></li>
					</c:if>

					<c:if test="${blog.sery != null}">
						<c:url value="/blog/list?s=${blog.sery.id }" var="viewSeryBlog" />
						<li><a href="${viewSeryBlog }"><c:out value="${blog.sery.seriesName }" /></a></li>
					</c:if>

					<li><a><b><c:out value="${blog.comments }" /></b> Commons </a></li>
				</ul>

				<ul class="list-inline">
					<c:if test="${not empty blog.tags }">
						<c:forEach items="${blog.tags }" var="tag">
							<li><a href="#"><span class="label label-info"><c:out value="${tag.tagName }" /></span></a></li>
						</c:forEach>
					</c:if>
				</ul>

				<div>${blog.blogDesc }</div>

				<div>
					<a href="${viewblog }" class="btn btn-link btn-sm">Read More</a>
				</div>
			</div>
		</div>
	</c:if>
</c:forEach>

<jsp:include page="inc/pagination.jsp"/>