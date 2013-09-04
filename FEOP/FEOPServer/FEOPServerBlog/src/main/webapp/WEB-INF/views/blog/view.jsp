<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// Set specific variable to represent all iframe tags.
						var iFrames = document.getElementsByTagName('iframe');

						// Resize heights.
						function iResize() {
							// Iterate through all iframes in the page.
							for ( var i = 0, j = iFrames.length; i < j; i++) {
								// Set inline style to equal the body height of the iframed content.
								iFrames[i].style.height = iFrames[i].contentWindow.document.body.offsetHeight
										+ 'px';
							}
						}

						// Check if browser is Safari or Opera.
						if ($.browser.safari || $.browser.opera) {
							// Start timer when loaded.
							$('iframe').load(function() {
								setTimeout(iResize, 0);
							});

							// Safari and Opera need a kick-start.
							for ( var i = 0, j = iFrames.length; i < j; i++) {
								var iSource = iFrames[i].src;
								iFrames[i].src = "";
								iFrames[i].src = iSource;
							}
						} else {
							// For other good browsers.
							$('iframe')
									.load(
											function() {
												// Set inline style to equal the body height of the iframed content.
												this.style.height = this.contentWindow.document.body.offsetHeight
														+ 'px';
											});
						}
					});
</script>
<c:if test="${blog != null }">
	<c:url value="/blog/view?id=${blog.id }" var="viewblog" />
	<div class="page-header">
		<h3>
			<c:out value="${blog.blogTitle }" />
			<c:if test="${authentication != null}">
				<div class="btn-group pull-right">
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

	<div>
		<c:choose>
			<c:when test="${not empty blog.tags }">
				<c:forEach items="${blog.tags }" var="tag">
					<span class="label label-default"><c:out value="${tag.tagName }" /></span>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<span class="label "> </span>
			</c:otherwise>
		</c:choose>
		<div class="pull-right">
			<span class="label label-primary"><i class="glyphicon glyphicon-user"></i> <c:out value="${blog.authorName }" /></span> <span
				class="label label-primary"><i class="glyphicon glyphicon-calendar"></i> <c:out value="${blog.createDate }" /></span>

			<c:if test="${blog.category != null}">
				<c:url value="/blog/list?c=${blog.category.id }" var="viewCategoryBlog" />
				<a href="${viewCategoryBlog }" class="label label-primary"><i class="glyphicon glyphicon-list"></i> <c:out
						value="${blog.category.categoryName }" /></a>
			</c:if>

			<c:if test="${blog.sery != null}">
				<c:url value="/blog/list?s=${blog.sery.id }" var="viewSeryBlog" />
				<span><a href="${viewSeryBlog }" class="label label-primary"><i class="glyphicon glyphicon-book"></i> <c:out
							value="${blog.sery.seriesName }" /></a></span>
			</c:if>
			<span class="label label-primary"><i class="glyphicon glyphicon-comment"></i> <c:out value="${blog.comments }" /> Commons</span>
		</div>
	</div>
	<hr>

	<ul class="list-inline">
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
							</a> <small><button class="btn btn-link btn-xs pull-right" onclick="openCommentModal('section', '${section.id }');">Common</button></small>
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
		<button class="btn btn-link btn-sm" onclick="openCommentModal('blog', '${blog.id }');">Leave a Common</button>
	</div>

</c:if>

<div class="row-fluid">
	<div class="span12">
		<iframe width="100%" height="200" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
			src='<c:url value="/comment/list?b=${blog.id }"/>'></iframe>
	</div>
</div>
<br>


<!-- Modal -->
<div class="modal fade" id="new-comment" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<form method="post" id="new-comment-form" role="form" class="form-horizontal" action='<c:url value="/comment/save"/>'>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Comment</h4>
				</div>

				<div class="modal-body">

					<div class="form-group">
						<input class="form-control" id="blog-id-holder" name="blogId" type="hidden"> <input class="form-control" id="section-id-holder"
							name="blogSectionId" type="hidden"> <input class="form-control" id="comment-id-holer" name="commentId" type="hidden">
					</div>
					<div class="form-group">
						<label for="author" class="col-lg-2 control-label"><span>Name</span>(*)</label>
						<div class="col-lg-10">
							<c:choose>
								<c:when test="${not empty authentication }">
									<input class="form-control" name="author" type="text" placeholder="Author" value="${authentication.userInfo.screenName }" required>
								</c:when>
								<c:otherwise>
									<input class="form-control" name="author" type="text" placeholder="Author" autofocus required>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-lg-2 control-label"><span>Email</span>(*)</label>
						<div class="col-lg-10">
							<c:choose>
								<c:when test="${not empty authentication }">
									<input class="form-control" name="email" type="text" placeholder="Email" value="${authentication.userInfo.email}" required>
								</c:when>
								<c:otherwise>
									<input class="form-control" name="email" type="text" placeholder="Email" required>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
					<div class="form-group">
						<label for="site" class="col-lg-2 control-label"><span>Site</span></label>
						<div class="col-lg-10">
							<input class="form-control" name="site" type="text" placeholder="Site (Optional)">
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-2 control-label" for="sectionContent">Content</label>
						<div class="col-lg-10">
							<textarea class="form-control" name="commentContent" placeholder="Comment Content" rows="10" required></textarea>
						</div>
					</div>
					<!-- <div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<input type="submit" class="btn btn-primary" value="Comment">
							</div>
						</div> -->


				</div>
				<div class="modal-footer">
					<input type="submit" value="Comment" class="btn btn-primary">
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->