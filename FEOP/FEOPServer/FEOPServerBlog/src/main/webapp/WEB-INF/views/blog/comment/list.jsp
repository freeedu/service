<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${not empty comments }">
		<div class="page page-default">
			<div class="page-header">Comments</div>
		</div>

		<div class="panel-group">
			<c:forEach items="${comments }" var="comment">
				<c:if test="${comment != null }">
					<div class="panel panel-default small">
						<div class="panel-heading">

							<span><i class="glyphicon glyphicon-user"></i> <c:out value="${comment.author }" /></span> <span class="pull-right"> <i
								class="glyphicon glyphicon-calendar"></i> <c:out value="${comment.createTime }" />
							</span>
						</div>
						<div class="panel-body">
							<p>${comment.commentContent }</p>
						</div>
					</div>
				</c:if>
			</c:forEach>
			<jsp:include page="../../inc/pagination.jsp" />
		</div>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>