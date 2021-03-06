<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url value="/signup/save" var="newAction"/>

<div class="col-md-8 col-md-offset-2">
    <form class="form-horizontal" action="${newAction }" method="post" name="signupForm">

        <div class="form-group">
            <h4>Create an Account</h4>
            <hr>
        </div>

        <c:if test="${msg != null }">
            <div class="alert alert-success">
                <c:out value="${msg }"/>
            </div>
        </c:if>

        <s:hasBindErrors name="signupForm">
            <c:if test="${errors.errorCount gt 0}">
                <div class="alert alert-danger">
                    <b>Errors:</b><br/>
                    <c:forEach items="${errors.allErrors}" var="error">
                        <s:message code="${error.code}" arguments="${error.arguments}"
                                   text="${error.defaultMessage}"/>
                        <br/>
                    </c:forEach>
                </div>
            </c:if>
        </s:hasBindErrors>
        <c:if test="${redirect_uri != null }">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon "><i class="glyphicon glyphicon-user"></i></span><input
                        type="hidden" name="redirectUrl"
                        value="${redirect_uri }">
                </div>
            </div>
        </c:if>

        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon "><i class="glyphicon glyphicon-user"></i></span><input
                    name="userName" type="text" class="form-control" value="" placeholder="User Name"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon "><i class="glyphicon glyphicon-envelope"></i></span><input
                    name="email" type="email" class="form-control" value="" placeholder="Email"
                    required="required"/>
            </div>
        </div>

        <c:if test="${requireInvite eq true}">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon "><i class="glyphicon glyphicon-qrcode"></i></span><input
                        type="text" class="form-control" placeholder="Inviting Code" name="inviteCode"
                        required="required"/>
                </div>
            </div>
        </c:if>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon "><i class="glyphicon glyphicon-earphone"></i></span><input id="tel"
                                                                                                           name="phone"
                                                                                                           type="tel"
                                                                                                           class="form-control"
                                                                                                           value=""
                                                                                                           placeholder="Phone"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon "><i class="glyphicon glyphicon-lock"></i></span><input
                    name="password" class="form-control" type="password" placeholder="Password"
                    required="required"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon "><i class="glyphicon glyphicon-lock"></i></span><input
                    name="repeatPassword" class="form-control" type="password" placeholder="Password Again"
                    required="required"/>
            </div>
        </div>

        <div class="form-group ">
            <div class="input-group checkbox">
                <input id="acceptTerms" name="acceptTerms" type="checkbox" required/> <label for="acceptTerms"> I agree
                to the <a
                        href="">Terms and Conditions</a>
                and <a href="">Privacy Policy</a>
            </label>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <input class="btn btn-primary btn-sm" type="submit" value="Create Account"/>
            </div>
        </div>
    </form>
</div>
