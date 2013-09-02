<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${blog != null }">
	<c:url value="/blog/view?id=${blog.id }" var="viewblog" />
	<div class="page-header">
		<h3>
			<c:out value="${blog.blogTitle }" />
			<c:if test="${authentication != null}">
				<div class="btn-group">
					<button class="btn btn-default dropdown-toggle btn-xs" data-toggle="dropdown">
						Action <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/my/blog/update?id=${blog.id }"/>">Update</a></li>
						<li><a>Setting</a></li>
						<li><a href="<c:url value="/my/blog/delete?id=${blog.id }"/>">Delete</a></li>
					</ul>
				</div>
			</c:if>
		</h3>
	</div>
	<c:if test="${blog.blogSubtitle != null}">
		<ul class="list-inline">
		<li><strong>Subtitle:</strong></li>
		<li><a class="inline"><c:out value="${blog.blogSubtitle }" /></a></li>
		</ul>
	</c:if>
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
		<li><strong>Labels:</strong></li>
		<c:if test="${not empty blog.tags }">
			<c:forEach items="${blog.tags }" var="tag">
				<li><a href="#"><span class="label label-info"><c:out value="${tag.tagName }" /></span></a></li>
			</c:forEach>
		</c:if>
	</ul>
	<ul class="list-inline">
		<li><strong>Description:</strong></li>
		<li><div>${blog.blogDesc }</div></li>
	</ul>
	
	<br>


	<c:if test="${not empty blog.sections }">
		<div class="panel-group" id="accordion">
			<c:forEach var="section" items="${blog.sections }">
				<div class="panel">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="#collapse${section.sequence }" class="accordion-toggle" data-toggle="collapse"> <c:out value="${section.sectionTitle }" />
							</a> <small><a class="btn btn-link btn-xs" href="/comment/create?s=${section.id }">Comment</a></small>
						</h4>
					</div>
					<div id="collapse${section.sequence }" class="panel-collapse collapse in">
						<div class="panel-body">${section.sectionContent}</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>


	<div>
		<a class="btn" href="<c:url value="/comment/create?b=${blog.id }"/>">Common</a>
	</div>

</c:if>
<br>