package org.personal.mason.feop.server.blog.mvc.model;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.personal.mason.feop.server.blog.domain.model.Sery;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "series")
@JsonRootName("series")
public class SeryModel {
    private Long id;
    private String description;
    private String seriesName;
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public static Sery convert(SeryModel seryModel) {
        Sery sery = new Sery();
        sery.setId(seryModel.getId());
        sery.setDescription(seryModel.getDescription());
        sery.setSeriesName(seryModel.getSeriesName());
        return sery;
    }

    public static SeryModel revert(Sery sery) {
        SeryModel model = new SeryModel();
        model.setId(sery.getId());
        model.setSeriesName(sery.getSeriesName());
        model.setDescription(sery.getDescription());
        model.setCategoryId(sery.getCategory().getId());
        return model;
    }
}
