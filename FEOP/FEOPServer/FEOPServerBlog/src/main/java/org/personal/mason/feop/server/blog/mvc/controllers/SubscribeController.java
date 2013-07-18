package org.personal.mason.feop.server.blog.mvc.controllers;

import java.security.Principal;

import org.personal.mason.feop.server.blog.domain.Subscribe;
import org.personal.mason.feop.server.blog.mvc.utils.CollectionUtils;
import org.personal.mason.feop.server.blog.mvc.utils.PrincipalUtils;
import org.personal.mason.feop.server.blog.spi.SubscribeService;
import org.personal.mason.feop.server.blog.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscribeController {

	private SubscribeService subscribeService;

	@Autowired
	public void setSubscribeService(SubscribeService subscribeService) {
		this.subscribeService = subscribeService;
	}

	@RequestMapping(value = "sub/create", method = RequestMethod.GET)
	public String createSubscribe(Principal principal) {
		if (principal == null || principal.getName().isEmpty()) {
			return null;
		}

		Subscribe sub = new Subscribe();
		sub.setEnable(true);
		sub.setCreateDate(TimeUtils.getCurrentTimestamp());

		subscribeService.save(sub);
		return "";
	}

	@RequestMapping(value = "sub/add", method = RequestMethod.GET)
	public void subscribeBlog(@RequestParam("bid") Long blogId, @RequestParam("cid") Long catId, @RequestParam("sid") Long seryId, Principal principal) {
		if (principal == null || principal.getName().isEmpty()) {
			return;
		}

		String uid = PrincipalUtils.getUid(principal);
		Subscribe sub = subscribeService.findBySubscribe(uid);
		if (sub == null) {
			sub = new Subscribe();
			sub.setEnable(true);
			sub.setCreateDate(TimeUtils.getCurrentTimestamp());
			subscribeService.save(sub);
		}

		sub.setUpdateTime(TimeUtils.getCurrentTimestamp());

		if (blogId != null) {
			String blogIds = sub.getBlogIds();
			if (blogIds == null || blogIds.isEmpty()) {
				blogIds = blogId.toString();
			} else if (!CollectionUtils.collectionContains(blogIds, blogId.toString())) {
				blogIds = String.format("%s,%s", blogIds, blogId.toString());
			}
			sub.setBlogIds(blogIds);
		}

		if (catId != null) {
			String catIds = sub.getCategoryIds();
			if (catIds == null || catIds.isEmpty()) {
				catIds = catId.toString();
			} else if (!CollectionUtils.collectionContains(catIds, catId.toString())) {
				catIds = String.format("%s,%s", catIds, catId.toString());
			}
			sub.setCategoryIds(catIds);
		}

		if (seryId != null) {
			String seryIds = sub.getSeryIds();
			if (seryIds == null || seryIds.isEmpty()) {
				seryIds = seryId.toString();
			} else if (!CollectionUtils.collectionContains(seryIds, seryId.toString())) {
				seryIds = String.format("%s,%s", seryIds, seryId.toString());
			}
			sub.setSeryIds(seryIds);
		}
		subscribeService.update(sub);
	}

	@RequestMapping(value = "sub/del", method = RequestMethod.DELETE)
	public void unsubscribeBlog(@RequestParam("bid") Long blogId, @RequestParam("cid") Long catId, @RequestParam("sid") Long seryId,
			Principal principal) {
		if (principal == null || principal.getName().isEmpty()) {
			return;
		}

		String uid = PrincipalUtils.getUid(principal);
		Subscribe sub = subscribeService.findBySubscribe(uid);
		if (sub == null) {
			sub = new Subscribe();
			sub.setEnable(true);
			sub.setCreateDate(TimeUtils.getCurrentTimestamp());
			subscribeService.save(sub);
		}

		sub.setUpdateTime(TimeUtils.getCurrentTimestamp());

		if (blogId != null) {
			String blogIds = sub.getBlogIds();
			if (CollectionUtils.collectionContains(blogIds, blogId.toString())) {
				blogIds = CollectionUtils.removeFromCollection(blogIds, blogId.toString());
			}
			sub.setBlogIds(blogIds);
		}

		if (catId != null) {
			String catIds = sub.getCategoryIds();
			if (CollectionUtils.collectionContains(catIds, catId.toString())) {
				catIds = CollectionUtils.removeFromCollection(catIds, catId.toString());
			}
			sub.setCategoryIds(catIds);
		}

		if (seryId != null) {
			String seryIds = sub.getSeryIds();
			if (CollectionUtils.collectionContains(seryIds, seryId.toString())) {
				seryIds = CollectionUtils.removeFromCollection(seryIds, seryId.toString());
			}
			sub.setSeryIds(seryIds);
		}
		subscribeService.update(sub);
	}

	@RequestMapping
	public String findMySubscribe(Principal principal, Model model) {
		String uid = PrincipalUtils.getUid(principal);
		Subscribe sub = subscribeService.findBySubscribe(uid);
		model.addAttribute("subscribe", sub);
		return "";
	}
}
