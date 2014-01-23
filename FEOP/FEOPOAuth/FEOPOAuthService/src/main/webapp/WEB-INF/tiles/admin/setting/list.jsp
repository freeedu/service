<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class=" col-sm-8 col-sm-offset-2">
    <h4>System Settings</h4>

    <hr>
    <c:choose>
        <c:when test="${not empty settings  }">
            <div class="panel-group" id="settings">
                <c:forEach items="${settings}" var="setting">
                    <table class="panel table table-responsive table-hover">
                        <thead>
                        <tr>
                            <td class="col-sm-3">
                                <span class="glyphicon glyphicon-chevron-down btn-link btn-sm" data-toggle="collapse"
                                      data-parent="#settings" href="#collapse${setting.id }"></span>
                            </td>
                            <td class="col-sm-9">
                                <c:url value="/admin/settings/view?id=${setting.id }" var="viewTarget"/>
                                <a href="${viewTarget }" class="btn-link btn btn-sm">${setting.key }</a>

                                <div class="btn-group-sm pull-right">
                                    <c:url value="/admin/settings/edit?id=${setting.id }" var="update"/>
                                    <a href="${update }" class="btn btn-link btn-sm">Edit</a>
                                    <a href='<c:url value="/admin/settings/delete?id=${setting.id }"/>'
                                       class="btn btn-link btn-sm">Delete</a>
                                </div>
                            </td>
                        </tr>
                        </thead>
                        <tbody class="collapse" id="collapse${setting.id }">
                        <tr>
                            <td class="col-sm-3">Setting Key:</td>
                            <td class="col-sm-9">${setting.key }
                            </td>
                        </tr>
                        <tr>
                            <td class="col-sm-3">Setting Value:</td>
                            <td class="col-sm-9">
                                    ${setting.value }
                            </td>
                        </tr>
                        <tr>
                            <td class="col-sm-3">Effect Start Date:</td>
                            <td class="col-sm-9">
                                    ${setting.startDate }
                            </td>
                        </tr>
                        <tr>
                            <td class="col-sm-3">Effect End Date:</td>
                            <td class="col-sm-9">
                                    ${setting.endDate }
                            </td>
                        </tr>
                        <tr>
                            <td class="col-sm-3">Diabled:</td>
                            <td class="col-sm-9">
                                    ${setting.disabled }
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning">
                There is no Setting now.. <a href="<c:url value="/admin/settings/new"/>">Add</a> one..
            </div>
        </c:otherwise>
    </c:choose>
</div>