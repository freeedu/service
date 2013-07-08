package org.personal.mason.feop.server.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the media_info database table.
 * 
 */
@Entity
@Table(name = "media_info")
public class MediaInfo extends AbstractPersistable<Long> {
	private static final long serialVersionUID = 1L;

	@Column(name = "media_redundant_url")
	private String mediaRedundantUrl;

	@Column(name = "media_code")
	private String mediaCode;

	@Column(name = "media_name")
	private String mediaName;

	@Column(name = "media_type")
	private String mediaType;

	@Column(name = "media_url")
	private String mediaUrl;

	public MediaInfo() {
	}

	public String getMediaRedundantUrl() {
		return this.mediaRedundantUrl;
	}

	public void setMediaRedundantUrl(String mediaRedundantUrl) {
		this.mediaRedundantUrl = mediaRedundantUrl;
	}

	public String getMediaCode() {
		return this.mediaCode;
	}

	public void setMediaCode(String mediaCode) {
		this.mediaCode = mediaCode;
	}

	public String getMediaName() {
		return this.mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaUrl() {
		return this.mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

}