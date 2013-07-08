package org.personal.mason.feop.server.blog.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the subscribe_topic database table.
 * 
 */
@Entity
@Table(name = "subscribe_topic")
public class SubscribeTopic extends AbstractPersistable<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "public_usage")
	private byte publicUsage;

	@Column(name = "topic_name")
	private String topicName;

	// bi-directional many-to-one association to Subscribe
	@OneToMany(mappedBy = "subscribeTopic")
	private List<Subscribe> subscribes;

	// bi-directional many-to-one association to Blog
	@ManyToOne
	private Blog blog;

	// bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	// bi-directional many-to-one association to Sery
	@ManyToOne
	@JoinColumn(name = "series_id")
	private Sery sery;

	public SubscribeTopic() {
	}

	public byte getPublicUsage() {
		return this.publicUsage;
	}

	public void setPublicUsage(byte publicUsage) {
		this.publicUsage = publicUsage;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<Subscribe> getSubscribes() {
		return this.subscribes;
	}

	public void setSubscribes(List<Subscribe> subscribes) {
		this.subscribes = subscribes;
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Sery getSery() {
		return this.sery;
	}

	public void setSery(Sery sery) {
		this.sery = sery;
	}

}