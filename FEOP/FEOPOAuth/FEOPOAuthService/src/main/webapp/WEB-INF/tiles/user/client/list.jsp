<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="col-md-8 col-md-offset-2">

    <h4>User Applications</h4>

    <hr>
    <c:choose>
        <c:when test="${not empty applications  }">
            <div class="panel-group" id="applications">
                <c:forEach items="${applications}" var="app">
                    <table class="panel table table-responsive table-hover">
                        <thead>
                        <tr>
                            <td class="left col-sm-4">
                                <c:url value="/client/view/${app.clientId }" var="viewTarget"/>
                                <span class="glyphicon glyphicon-chevron-down btn-link btn-sm" data-toggle="collapse"
                                      data-parent="#applications"
                                      href="#collapse${app.clientId}"></span>
                                <a href="${viewTarget }" class="btn btn-link btn-normal"><c:out
                                        value="${app.clientId }"/></a>

                            </td>
                            <td class="right col-sm-8">
                                <a href='<c:url value="/client/delete/${app.clientId }"/>'
                                   class="pull-right btn btn-link btn-sm">Delete</a>
                            </td>
                        </tr>
                        </thead>


                        <tbody class="collapse" id="collapse${app.clientId}">
                        <tr>
                            <td class="col-sm-4">
                                Application Id:
                            </td>
                            <td class="left col-sm-8" style="word-break: break-all;"><c:out
                                    value="${app.clientId }"/></td>
                        </tr>

                        <tr>
                            <td class="right col-sm-4">Client Secret:</td>
                            <td class="left col-sm-8" style="word-break: break-all;"><c:out
                                    value="${app.clientSecret }"/></td>
                        </tr>

                        <tr>
                            <td class="right col-sm-4">Authorized Grant Types:</td>
                            <td class="left col-sm-8" style="word-break: break-all;"><c:out
                                    value="${app.authorizedGrantTypes }"/></td>
                        </tr>

                        <tr>
                            <td class="right col-sm-4">Authorities:</td>
                            <td class="left col-sm-8" style="word-break: break-all;"><c:out
                                    value="${app.authorities }"/></td>
                        </tr>

                        <tr>
                            <td class="right col-sm-4">Resource Ids:</td>
                            <td class="left col-sm-8" style="word-break: break-all;"><c:out
                                    value="${app.resourceIds }"/></td>
                        </tr>

                        <tr>
                            <td class="right col-sm-4">Scope:</td>
                            <td class="left col-sm-8" style="word-break: break-all;"><c:out
                                    value="${app.scope }"/></td>
                        </tr>

                        <tr>
                            <td class="right col-sm-4">Redirect Uri:</td>
                            <td class="left col-sm-8" style="word-break: break-all;"><c:out
                                    value="${app.webServerRedirectUri }"/></td>
                        </tr>

                        <tr>
                            <td class="right col-sm-4">Additional Info:</td>
                            <td class="left col-sm-8" style="word-break: break-all;"><c:out
                                    value="${app.additionalInfo }"/></td>
                        </tr>

                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning">
                You have no applications. <a href="<c:url value="/client/new"/>">Create</a> now.
            </div>
        </c:otherwise>
    </c:choose>
</div>