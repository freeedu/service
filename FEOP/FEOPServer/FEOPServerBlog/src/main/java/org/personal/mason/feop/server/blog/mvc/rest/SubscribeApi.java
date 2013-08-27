package org.personal.mason.feop.server.blog.mvc.rest;

import javax.servlet.http.HttpServletRequest;

import org.personal.mason.feop.server.blog.domain.model.Subscribe;
import org.personal.mason.feop.server.blog.domain.service.SubscribeService;
import org.personal.mason.feop.server.blog.mvc.model.SubscribeModel;
import org.personal.mason.feop.server.blog.mvc.utils.AuthenticationUtils;
import org.personal.mason.feop.server.blog.mvc.utils.CollectionUtils;
import org.personal.mason.feop.server.blog.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class SubscribeApi {
	private SubscribeService subscribeService;

	@Autowired
	public void setSubscribeService(SubscribeService subscribeService) {
		this.subscribeService = subscribeService;
	}

	/**
	 * 
	 * @param blogId
	 * @param catId
	 * @param seryId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sub/add", method = RequestMethod.GET)
	@ResponseBody
	public SubscribeModel subscribeBlog(@RequestParam(value = "bid", required = false) Long blogId,
			@RequestParam(value = "cid", required = false) Long catId, @RequestParam(value = "sid", required = false) Long seryId,
			HttpServletRequest request) {
		String uid = AuthenticationUtils.getUid(request);
		if (uid == null) {
			return null;
		}

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
		Subscribe updatedSubscribe = subscribeService.update(sub);
		return SubscribeModel.revert(updatedSubscribe);
	}

	/**
	 * 
	 * @param blogId
	 * @param catId
	 * @param seryId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sub/del", method = RequestMethod.GET)
	@ResponseBody
	public SubscribeModel unsubscribeBlog(@RequestParam(value = "bid", required = false) Long blogId,
			@RequestParam(value = "cid", required = false) Long catId, @RequestParam(value = "sid", required = false) Long seryId,
			HttpServletRequest request) {

		String uid = AuthenticationUtils.getUid(request);
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
		Subscribe updatedSubscribe = subscribeService.update(sub);
		return SubscribeModel.revert(updatedSubscribe);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sub/my", method = RequestMethod.GET)
	@ResponseBody
	public SubscribeModel findMySubscribe(HttpServletRequest request) {
		String uid = AuthenticationUtils.getUid(request);
		Subscribe sub = subscribeService.findBySubscribe(uid);
		return SubscribeModel.revert(sub);
	}
}
