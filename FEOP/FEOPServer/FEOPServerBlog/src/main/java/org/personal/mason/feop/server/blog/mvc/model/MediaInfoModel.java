package org.personal.mason.feop.server.blog.mvc.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.personal.mason.feop.server.blog.domain.model.MediaInfo;

@XmlRootElement(name = "mediainfo")
@JsonRootName("mediainfo")
public class MediaInfoModel {

	private Long id;
	private String mediaRedundantUrl;
	private String mediaCode;
	private String mediaName;
	private String mediaType;
	private String mediaUrl;
	private String mediaOwnerUid;
	private Date mediaAddTime;
	private Boolean script = false;
	private String scriptContent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMediaRedundantUrl() {
		return mediaRedundantUrl;
	}

	public void setMediaRedundantUrl(String mediaRedundantUrl) {
		this.mediaRedundantUrl = mediaRedundantUrl;
	}

	public String getMediaCode() {
		return mediaCode;
	}

	public void setMediaCode(String mediaCode) {
		this.mediaCode = mediaCode;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaUrl() {
		return mediaUrl;
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

	public Date getMediaAddTime() {
		return mediaAddTime;
	}

	public void setMediaAddTime(Date mediaAddTime) {
		this.mediaAddTime = mediaAddTime;
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

	public static MediaInfo convert(MediaInfoModel model) {
		MediaInfo info = new MediaInfo();
		info.setId(model.getId());
		info.setMediaName(model.getMediaName());
		info.setMediaCode(model.getMediaCode());
		info.setMediaOwnerUid(model.getMediaOwnerUid());
		info.setMediaAddTime(model.getMediaAddTime());
		info.setMediaRedundantUrl(model.getMediaRedundantUrl());
		info.setMediaType(model.getMediaType());
		info.setMediaUrl(model.getMediaUrl());
		info.setScript(model.getScript());
		info.setScriptContent(model.getScriptContent());
		return info;
	}

	public static MediaInfoModel revert(MediaInfo mediaInfo) {
		MediaInfoModel model = new MediaInfoModel();
		model.setId(mediaInfo.getId());
		model.setMediaName(mediaInfo.getMediaName());
		model.setMediaCode(mediaInfo.getMediaCode());
		model.setMediaOwnerUid(mediaInfo.getMediaOwnerUid());
		model.setMediaAddTime(mediaInfo.getMediaAddTime());
		model.setMediaRedundantUrl(mediaInfo.getMediaRedundantUrl());
		model.setMediaType(mediaInfo.getMediaType());
		model.setMediaUrl(mediaInfo.getMediaUrl());
		model.setScript(mediaInfo.getScript());
		model.setScriptContent(mediaInfo.getScriptContent());
		return model;
	}
}
