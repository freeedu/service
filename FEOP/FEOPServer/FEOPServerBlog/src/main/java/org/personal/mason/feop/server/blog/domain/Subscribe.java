package org.personal.mason.feop.server.blog.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the subscribe database table.
 * 
 */
@Entity
public class Subscribe extends AbstractPersistable<Long> {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_date")
	private Timestamp createDate;

	private byte enable;

	@Column(name = "subscriber_uid")
	private String subscriberUid;

	@Column(name = "update_time")
	private Timestamp updateTime;

	// bi-directional many-to-one association to SubscribeTopic
	@ManyToOne
	@JoinColumn(name = "topic_id")
	private SubscribeTopic subscribeTopic;

	public Subscribe() {
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public byte getEnable() {
		return this.enable;
	}

	public void setEnable(byte enable) {
		this.enable = enable;
	}

	public String getSubscriberUid() {
		return this.subscriberUid;
	}

	public void setSubscriberUid(String subscriberUid) {
		this.subscriberUid = subscriberUid;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public SubscribeTopic getSubscribeTopic() {
		return this.subscribeTopic;
	}

	public void setSubscribeTopic(SubscribeTopic subscribeTopic) {
		this.subscribeTopic = subscribeTopic;
	}

}