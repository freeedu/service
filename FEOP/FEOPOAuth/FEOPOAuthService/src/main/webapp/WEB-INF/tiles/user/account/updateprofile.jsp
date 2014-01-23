<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url value="/account/update" var="update"/>


<div class="col-md-8 col-md-offset-2">
    <form action="${update }" method="post" class="form-horizontal" name="userForm">
        <h4>Edit Profile</h4>
        <hr/>

        <c:if test="${msg != null }">
            <div class="form-group">
                <div class="alert alert-success">
                    <c:out value="${msg }"/>
                </div>
            </div>
        </c:if>
        <s:hasBindErrors name="userForm">
            <c:if test="${errors.errorCount gt 0}">
                <div class="form-group">
                    <div class="alert alert-danger">
                        <b>Errors:</b><br/>
                        <c:forEach items="${errors.allErrors}" var="error">
                            <s:message code="${error.code}" arguments="${error.arguments}"
                                       text="${error.defaultMessage}"/>
                            <br/>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </s:hasBindErrors>
        <div class="form-group">
            <input type="text" name="userId" hidden="true" class="right" value="${userForm.userId}"/>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">User Name:</label>

            <div class="col-sm-9 ">
                <input type="text" name="userName" class="form-control"
                       value="${userForm.userName}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Email:</label>

            <div class="col-sm-9 ">
                <input type="text" name="email" disabled="disabled"
                       class="form-control"
                       value="${userForm.email}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Phone:</label>

            <div class="col-sm-9 ">
                <input type="text" name="phone"
                       class="form-control"
                       value="${userForm.phone }"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label"></label>

            <div class="col-sm-9 ">
                <input type="submit" value="Update Profile" class="btn btn-primary btn-sm"/>
            </div>
        </div>
    </form>
</div>
