package org.personal.mason.feop.server.blog.mvc.rest;

import java.util.ArrayList;
import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.personal.mason.feop.server.blog.domain.service.SeryService;
import org.personal.mason.feop.server.blog.mvc.model.SeryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class SeryApi {
	private SeryService seryService;
	private CategoryService categoryService;

	@Autowired
	public void setSeryService(SeryService seryService) {
		this.seryService = seryService;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 
	 * @param seryModel
	 * @return
	 */
	@RequestMapping(value = "/sery/save", method = RequestMethod.POST)
	@ResponseBody
	public SeryModel saveSery(SeryModel seryModel) {
		Sery sery = SeryModel.convert(seryModel);
		Category category = categoryService.findById(seryModel.getCategoryId());
		sery.setCategory(category);

		seryService.save(sery);
		return SeryModel.revert(sery);
	}

	/**
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/sery/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteSery(@RequestParam("id") Long id) {
		seryService.delete(id);
	}

	/**
	 * 
	 * @param query
	 * @param categoryId
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/sery/list", method = RequestMethod.GET)
	@ResponseBody
	public List<SeryModel> findByName(@RequestParam(value = "q", required = false) String query,
			@RequestParam(value = "c", required = false) Long categoryId, Pageable pageable) {

		Page<Sery> series = null;

		if (query != null && !query.isEmpty()) {
			series = seryService.findByName(query, pageable);
		} else if (categoryId != null) {
			Category cat = categoryService.findById(categoryId);
			series = seryService.findByCategory(cat, pageable);
		}

		List<SeryModel> models = new ArrayList<>();
		if (series != null) {
			for (Sery sery : series) {
				SeryModel model = SeryModel.revert(sery);
				models.add(model);
			}
		}

		return models;
	}
}
