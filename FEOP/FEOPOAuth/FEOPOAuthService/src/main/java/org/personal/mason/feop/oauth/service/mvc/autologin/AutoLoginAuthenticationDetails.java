package org.personal.mason.feop.oauth.service.mvc.autologin;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class AutoLoginAuthenticationDetails implements Serializable {

	private static final long serialVersionUID = 4443428766397691997L;

	private final String origin;

	private String sessionId;

	private String clientId;

	public AutoLoginAuthenticationDetails(HttpServletRequest request) {
		WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetails(request);
		this.origin = webAuthenticationDetails.getRemoteAddress();
		this.sessionId = webAuthenticationDetails.getSessionId();
		String clientId = request.getParameter("client_id");
		if (clientId != null) {
			this.clientId = clientId;
		}
	}

	public String getClientId() {
		return clientId;
	}

	public String getOrigin() {
		return origin;
	}

	public String getSessionId() {
		return sessionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutoLoginAuthenticationDetails other = (AutoLoginAuthenticationDetails) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AutoLoginAuthenticationDetails [origin=" + origin + ", sessionId=" + sessionId + ", clientId=" + clientId + "]";
	}

}
