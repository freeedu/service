<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page page-default">
	<div class="page-header">Comments</div>
</div>

<div class="panel-group">
	<c:forEach items="${comments }" var="comment">
		<c:if test="${comment != null }">
			<div class="panel panel-default small">
				<div class="panel-heading">

					<i class="glyphicon glyphicon-user"></i><a href="#"><span><c:out value="${comment.author }" /></span></a> at <i
						class="glyphicon glyphicon-calendar"></i><span><c:out value="${comment.createTime }" /></span>

				</div>
				<div class="panel-body">
					<p>${comment.commentContent }</p>
				</div>
			</div>
		</c:if>
	</c:forEach>
	<jsp:include page="../../inc/pagination.jsp" />
</div>