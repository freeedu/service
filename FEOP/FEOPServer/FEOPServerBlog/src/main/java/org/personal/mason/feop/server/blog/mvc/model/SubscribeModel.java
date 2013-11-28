package org.personal.mason.feop.server.blog.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.personal.mason.feop.server.blog.domain.model.Subscribe;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement(name = "subscribe")
@JsonRootName("subscribe")
public class SubscribeModel {

    private Long id;
    private Timestamp createDate;
    private boolean enable;
    private String subscriberUid;
    private Timestamp updateTime;
    private String blogIds;
    private String categoryIds;
    private String seryIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getSubscriberUid() {
        return subscriberUid;
    }

    public void setSubscriberUid(String subscriberUid) {
        this.subscriberUid = subscriberUid;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getBlogIds() {
        return blogIds;
    }

    public void setBlogIds(String blogIds) {
        this.blogIds = blogIds;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getSeryIds() {
        return seryIds;
    }

    public void setSeryIds(String seryIds) {
        this.seryIds = seryIds;
    }

    public static SubscribeModel revert(Subscribe subscribe) {
        SubscribeModel model = new SubscribeModel();
        model.setId(subscribe.getId());
        model.setEnable(subscribe.isEnable());
        model.setCreateDate(subscribe.getCreateDate());
        model.setUpdateTime(subscribe.getUpdateTime());
        model.setSubscriberUid(subscribe.getSubscriberUid());
        model.setBlogIds(subscribe.getBlogIds());
        model.setCategoryIds(subscribe.getCategoryIds());
        model.setSeryIds(subscribe.getSeryIds());

        return model;
    }

}
