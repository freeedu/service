<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url value="/account/edit" var="update"/>
<c:if test="${userForm != null}">
    <div class="col-sm-8 col-sm-offset-2">
        <div class="form-horizontal">
            <h4>
                User Profile <a href="${update }" class="pull-right btn btn-link btn-sm">Edit Profile</a>
            </h4>

            <hr>

            <div class="form-group">
                <label class="col-sm-3 control-label">User Id:</label>

                <div class="col-sm-9">
                        ${userForm.userId }
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">User Name:</label>

                <div class="col-sm-9">
                        ${userForm.userName }
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Email:</label>

                <div class="col-sm-9">
                    <p>
                            ${userForm.email }
                    </p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Phone:</label>

                <div class="col-sm-9">
                        ${userForm.phone }
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Status:</label>

                <div class="col-sm-9">
                        ${userForm.enabled }

                </div>
            </div>
        </div>
    </div>
</c:if>