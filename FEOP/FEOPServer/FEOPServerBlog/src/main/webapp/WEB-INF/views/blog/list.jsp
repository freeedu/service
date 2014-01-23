<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${blogs }" var="blog">
    <c:if test="${blog != null }">
        <c:url value="/blog/view?id=${blog.id }" var="viewblog"/>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="page-header">
                    <h3>
                        <a href="${viewblog }">${blog.blogTitle }</a>
                    </h3>

                    <div>
                        <span class="label label-primary"><i class="glyphicon glyphicon-user"></i> <c:out
                                value="${blog.authorName }"/></span> <span
                            class="label label-primary"><i class="glyphicon glyphicon-calendar"></i> <c:out
                            value="${blog.createDate }"/></span>

                        <c:if test="${blog.category != null}">
                            <c:url value="/blog/list?c=${blog.category.id }" var="viewCategoryBlog"/>
                            <a href="${viewCategoryBlog }" class="label label-primary"><i
                                    class="glyphicon glyphicon-list"></i> <c:out
                                    value="${blog.category.categoryName }"/></a>
                        </c:if>

                        <c:if test="${blog.sery != null}">
                            <c:url value="/blog/list?s=${blog.sery.id }" var="viewSeryBlog"/>
							<span><a href="${viewSeryBlog }" class="label label-primary"><i
                                    class="glyphicon glyphicon-book"></i> <c:out
                                    value="${blog.sery.seriesName }"/></a></span>
                        </c:if>
                        <span class="label label-primary"><i class="glyphicon glyphicon-comment"></i> <c:out
                                value="${blog.comments }"/> Commons</span>
                    </div>

                </div>
                <p>
                    <c:choose>
                        <c:when test="${not empty blog.tags }">
                            <c:forEach items="${blog.tags }" var="tag">
                                <span class="label label-default">${tag.tagName }</span>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <span class="label "> </span>
                        </c:otherwise>
                    </c:choose>

                </p>
                <div class="content">
                    <p>${blog.blogDesc }</p>
                </div>
                <p>
                    <a href="${viewblog }">Read More</a>
                </p>
            </div>

        </div>
    </c:if>
</c:forEach>

<jsp:include page="../inc/pagination.jsp"/>
