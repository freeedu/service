package org.personal.mason.feop.server.blog.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the media_info database table.
 * 
 */
@Entity
@Table(name = "media_info")
public class MediaInfo extends BlogPersistable {

	private static final long serialVersionUID = 6925710733359457162L;

	@Column(name = "media_redundant_url")
	private String mediaRedundantUrl;

	@Column(name = "media_code", unique = true)
	private String mediaCode;

	@Column(name = "media_name")
	private String mediaName;

	@Column(name = "media_type")
	private String mediaType;

	@Column(name = "media_url")
	private String mediaUrl;

	@Column(name = "media_owner_uid")
	private String mediaOwnerUid;

	@Column(name = "media_addtime")
	private Date mediaAddTime;

	private Boolean script = false;

	@Column(name = "script_content", length = 1000)
	private String scriptContent;

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

	public String getMediaOwnerUid() {
		return mediaOwnerUid;
	}

	public void setMediaOwnerUid(String mediaOwnerUid) {
		this.mediaOwnerUid = mediaOwnerUid;
	}

	public void setMediaAddTime(Date mediaAddTime) {
		this.mediaAddTime = mediaAddTime;
	}

	public Date getMediaAddTime() {
		return mediaAddTime;
	}

	public Boolean getScript() {
		return script;
	}

	public void setScript(Boolean script) {
		this.script = script;
	}

	public String getScriptContent() {
		return scriptContent;
	}

	public void setScriptContent(String scriptContent) {
		this.scriptContent = scriptContent;
	}
}