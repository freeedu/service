package org.personal.mason.feop.server.blog.mvc.model;

import org.personal.mason.feop.server.blog.domain.model.BlogSetting;

public class BlogSettingModel {

	private Long id;
	private int price;
	private boolean pubPrivilege;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isPubPrivilege() {
		return pubPrivilege;
	}

	public void setPubPrivilege(boolean pubPrivilege) {
		this.pubPrivilege = pubPrivilege;
	}

	public static BlogSetting convert(BlogSettingModel blogSettingModel) {
		BlogSetting setting = new BlogSetting();
		setting.setId(blogSettingModel.getId());
		setting.setPubPrivilege(blogSettingModel.isPubPrivilege());
		setting.setPrice(blogSettingModel.getPrice());
		return setting;
	}

	public static BlogSettingModel revert(BlogSetting blogSetting) {
		BlogSettingModel model = new BlogSettingModel();
		model.setId(blogSetting.getId());
		model.setPubPrivilege(blogSetting.getPubPrivilege());
		model.setPrice(blogSetting.getPrice());
		return model;
	}

}
