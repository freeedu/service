package org.personal.mason.feop.oauth.service.mvc;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserInfo {

	private String userId;

	/**
	 * The id of the user
	 * 
	 * @return The id of the user
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * The id of the user to whom the photo belongs.
	 * 
	 * @param userId
	 *            The id of the user
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
