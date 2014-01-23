<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class=" col-sm-8 col-sm-offset-2">
    <h4>Email Templates</h4>
    <hr>
    <c:choose>
        <c:when test="${not empty templates  }">
            <div class="panel-group" id="templates">

                <c:forEach items="${templates}" var="template">
                    <table class="panel table table-responsive table-hover">
                        <thead>
                        <tr>
                            <td class="left col-sm-3">
                                <span class="glyphicon glyphicon-chevron-down btn-link btn-sm" data-toggle="collapse"
                                      data-parent="#templates" href="#collapse${template.id }"></span>
                            </td>
                            <td class="right col-sm-9">
                                <c:url value="/admin/et/view?id=${template.id }" var="viewTarget"/>
                                <a href="${viewTarget }">${template.name }</a>

                                <div class="btn-group btn-group-sm pull-right">
                                    <c:url value="/admin/et/edit?id=${template.id }" var="update"/>
                                    <a href="${update }" class="btn btn-link btn-sm">Edit</a>
                                    <a href='<c:url value="/admin/et/delete?id=${template.id }"/>'
                                       class="btn btn-link btn-sm">Delete</a>

                                </div>
                            </td>
                        </tr>
                        </thead>


                        <tbody class="collapse" id="collapse${template.id }">
                        <tr>
                            <td class="right col-sm-3">Template Id:</td>
                            <td class="left col-sm-9">
                                <c:out value="${template.id }"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="right col-sm-3">Name:</td>
                            <td class="left col-sm-9">
                                <c:out value="${template.name }"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="right col-sm-3">Subject:</td>
                            <td class="left col-sm-9">
                                <c:out value="${template.subject }"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="right col-sm-3">Version:</td>
                            <td class="left col-sm-9">
                                <c:out value="${template.version }"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="right col-sm-3">Content:</td>
                            <td class="left col-sm-9">
                                <textarea rows="6" disabled="disabled" class="col-sm-12">${template.content }</textarea>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning">
                There is no Setting now.. <a href="<c:url value="/admin/et/new"/>">Add</a> Template..
            </div>
        </c:otherwise>
    </c:choose>
</div>