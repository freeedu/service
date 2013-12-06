<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-md-8 col-md-offset-2">
    <div class="row">
        <div>
            <h3>Email Templates</h3>
        </div>
        <hr>
    </div>
    <c:choose>
        <c:when  test="${not empty templates  }">
    <c:forEach items="${templates}" var="template">
        <div class="panel panel-info">
            <div class="panel-heading">
                <c:url value="/admin/et/view?id=${template.id }" var="viewTarget"/>
                <a href="${viewTarget }"><c:out value="${template.name }"/></a> <a
                    href='<c:url value="/admin/et/delete?id=${template.id }"/>'
                    class="btn btn-link btn-sm pull-right">Delete</a>
                <c:url value="/admin/et/update?id=${template.id }" var="update"/>
                <a href="${update }" class="btn btn-link btn-sm pull-right">Edit</a>
            </div>
            <div class="panel-body">

                <div class="row">
                    <div class="col-md-3">Template Id:</div>
                    <div class="col-md-9">
                        <c:out value="${template.id }"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">Name:</div>
                    <div class="col-md-9">
                        <c:out value="${template.name }"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">Subject:</div>
                    <div class="col-md-9">
                        <c:out value="${template.subject }"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">Version:</div>
                    <div class="col-md-9">
                        <c:out value="${template.version }"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">Content:</div>
                    <textarea rows="6" disabled="disabled" class="col-md-9">${template.content }</textarea>
                </div>
            </div>
        </div>
    </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="alert alert-warning">
            There is no Setting now.. <a href="<c:url value="/admin/et/new"/>">Add</a> Template..
        </div>
    </c:otherwise>
    </c:choose>
</div>