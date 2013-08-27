<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:url value="/signup/new" var="newAction" />

<form id="registration" action="${newAction }" method="post" name="signupForm" class="editable">
	<h1>Create an Account</h1>

	<fieldset class="inputs">
		<c:if test="${msg != null }">
			<div class="success">
				<c:out value="${msg }" />
			</div>
		</c:if>

		<s:hasBindErrors name="signupForm">
			<c:if test="${errors.errorCount gt 0}">
				<div class="error">
					<b>Errors:</b><br />
					<c:forEach items="${errors.allErrors}" var="error">
						<s:message code="${error.code}" arguments="${error.arguments}" text="${error.defaultMessage}" />
						<br />
					</c:forEach>
				</div>
			</c:if>
		</s:hasBindErrors>
		<c:if test="${redirect_uri != null }">
			<input type="hidden" name="redirectUrl" value="${redirect_uri }">
		</c:if>
		<input id="name" name="firstName" type="text" class="text" value="" placeholder="First Name" required="required"/> 
		<input id="fname" name="lastName" type="text" class="text" value="" placeholder="Last Name" required="required"/>
		
		<c:if test="${requireInvite eq true}">
			<input id="inviteCode" type="text" placeholder="Inviting Code" name="inviteCode" required="required"/>
		</c:if>

		<input class="password" name="password" class="text" type="password" placeholder="Password" required="required"/>
		<input class="password" name="repeatPassword" class="text" type="password" placeholder="Password Again" required="required"/> 

		<input id="name" name="userName" type="text" class="text" value="" placeholder="Screen Name" />
	
		<input id="email" name="email" type="email" class="text" value="" placeholder="Email" required="required"/> 
		<input id="tel" name="phone" type="tel" class="text" value="" placeholder="Phone" /> 
		
		<select id="gender" name="gender" title="Gender" >
			<option value="" selected="selected">Choose ...</option>
			<option value="m">Male</option>
			<option value="f">Female</option>
		</select>
		
		<input id="birth" name="birth" type="date" class="text" value="" placeholder="Birthday"  >
		<input id="location" name="location" type="text" class="" value="" placeholder="Location">
		
		<input id="acceptTerms" name="acceptTerms" type="checkbox" required /> <label for="acceptTerms"> I
			agree to the <a href="">Terms and Conditions</a> and <a href="">Privacy Policy</a>
		</label>
	</fieldset>

	<fieldset class="actions">
		<button id="submit" type="submit">Create Account</button>
	</fieldset>
</form>