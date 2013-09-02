<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${pager != null  }">
	<div class="">
		<ul class="pagination">
			<c:choose>
				<c:when test="${pager.getPage() > 1 }">
					<li><a href='<c:url value="${pager.uri }?p_page=1${pager.queryString }"/>'><small>&lt;&lt;</small></a></li>
					<li><a href="<c:url value="${pager.uri }?p_page=${pager.page - 1}${pager.queryString }"/>"><small>&lt;</small></a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a><small>&lt;&lt;</small></a></li>
					<li class="disabled"><a><small>&lt;</small></a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pager.count <= 15  }">
					<c:forEach begin="1" end="${pager.count}" var="i">
						<c:choose>
							<c:when test="${pager.page eq i }">
								<li class="active"><a><c:out value="${i }" /></a></li>
							</c:when>
							<c:otherwise>
								<li><a href='<c:url value="${pager.uri }?p_page=${i}${pager.queryString }"/>'><c:out value="${i }" /></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${pager.page < 8 }">
							<c:forEach begin="1" end="13" var="i">
								<c:choose>
									<c:when test="${pager.page eq i }">
										<li class="active"><a><c:out value="${i }" /></a></li>
									</c:when>
									<c:otherwise>
										<li><a href='<c:url value="${pager.uri }?p_page=${i}${pager.queryString }"/>'><c:out value="${i }" /></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<li><a href='<c:url value="${pager.uri }?p_page=14${pager.queryString }"/>'><c:out value="..." /></a></li>
							<li><a href='<c:url value="${pager.uri }?p_page=${pager.count}${pager.queryString }"/>'><c:out value="${pager.count }" /></a></li>
						</c:when>
						<c:when test="${pager.page > (pager.count - 8) }">
							<li><a href='<c:url value="${pager.uri }?p_page=1${pager.queryString }"/>'>1</a></li>
							<li><a href='<c:url value="${pager.uri }?p_page=${pager.count - 13}${pager.queryString }"/>'><c:out value="..." /></a></li>

							<c:forEach begin="${pager.count - 12 }" end="${pager.count }" var="i">
								<c:choose>
									<c:when test="${pager.page eq i }">
										<li class="active"><a><c:out value="${i }" /></a></li>
									</c:when>
									<c:otherwise>
										<li><a href='<c:url value="${pager.uri }?p_page=${i}${pager.queryString }"/>'><c:out value="${i }" /></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li><a href='<c:url value="${pager.uri }?p_page=1${pager.queryString }"/>'>1</a></li>
							<li><a href='<c:url value="${pager.uri }?p_page=${pager.page - 6}${pager.queryString }"/>'><c:out value="..." /></a></li>

							<c:forEach begin="${pager.page - 5 }" end="${pager.page + 5 }" var="i">
								<c:choose>
									<c:when test="${pager.page eq i }">
										<li class="active"><a><c:out value="${i }" /></a></li>
									</c:when>
									<c:otherwise>
										<li><a href='<c:url value="${pager.uri }?p_page=${i}${pager.queryString }"/>'><c:out value="${i }" /></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<li><a href='<c:url value="${pager.uri }?p_page=${pager.page + 6}${pager.queryString }"/>'><c:out value="..." /></a></li>
							<li><a href='<c:url value="${pager.uri }?p_page=${pager.count}${pager.queryString }"/>'><c:out value="${pager.count }" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${pager.getPage() < pager.count }">
					<li><a href="<c:url value="${pager.uri }?p_page=${pager.page + 1}${pager.queryString }"/>"><small>&gt;</small></a></li>
					<li><a href='<c:url value="${pager.uri }?p_page=${pager.count}${pager.queryString }"/>'><small>&gt;&gt;</small></a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a><small>&gt;</small></a></li>
					<li class="disabled"><a><small>&gt;&gt;</small></a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
</c:if>