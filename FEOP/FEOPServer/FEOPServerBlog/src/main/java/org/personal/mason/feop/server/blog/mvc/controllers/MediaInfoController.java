package org.personal.mason.feop.server.blog.mvc.controllers;

import java.security.Principal;

import org.personal.mason.feop.server.blog.domain.model.MediaInfo;
import org.personal.mason.feop.server.blog.domain.service.MediaInfoService;
import org.personal.mason.feop.server.blog.mvc.model.MediaInfoModel;
import org.personal.mason.feop.server.blog.mvc.utils.PrincipalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MediaInfoController {
	private MediaInfoService mediaInfoService;

	@Autowired
	public void setMediaInfoService(MediaInfoService mediaInfoService) {
		this.mediaInfoService = mediaInfoService;
	}

	@RequestMapping(value = "/media/create", method = RequestMethod.GET)
	public String createMedia(Model model) {
		model.addAttribute("mediaInfoModel", new MediaInfoModel());
		return "";
	}

	@RequestMapping(value = "/media/save", method = RequestMethod.POST)
	public String saveMedia(@Validated MediaInfoModel mediaInfoModel, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return null;
		}

		MediaInfo mediaInfo = MediaInfoModel.convert(mediaInfoModel);
		mediaInfo.setMediaOwnerUid(PrincipalUtils.getUid(principal));
		mediaInfoService.save(mediaInfo);

		return "";
	}

	@RequestMapping(value = "/media/update", method = RequestMethod.GET)
	public String updateMedia(@RequestParam("id") Long id, Model model) {
		MediaInfo mediaInfo = mediaInfoService.findById(id);
		MediaInfoModel modelInfo = MediaInfoModel.revert(mediaInfo);
		model.addAttribute("mediaInfoModel", modelInfo);
		return "";
	}

	@RequestMapping(value = "/media/update", method = RequestMethod.POST)
	public String updateMedia(@Validated MediaInfoModel mediaInfoModel, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}

		MediaInfo mediaInfo = MediaInfoModel.convert(mediaInfoModel);

		mediaInfoService.update(mediaInfo);
		return "";
	}

	@RequestMapping(value = "/media/m/list", method = RequestMethod.GET)
	public String findMyMedia(Pageable pageable, Principal principal, Model model) {
		String uid = PrincipalUtils.getUid(principal);
		Page<MediaInfo> mediaInfos = mediaInfoService.findByUid(uid, pageable);
		model.addAttribute("mediaInfos", mediaInfos);
		return "";
	}

	@RequestMapping(value = "/media/list", method = RequestMethod.GET)
	public String findMyMediaByType(@RequestParam("t") String type, Pageable pageable, Principal principal, Model model) {
		String uid = PrincipalUtils.getUid(principal);
		Page<MediaInfo> mediaInfos = mediaInfoService.findByUidAndType(uid, type, pageable);
		model.addAttribute("mediaInfos", mediaInfos);
		return "";
	}

	@RequestMapping(value = "/media/delete", method = RequestMethod.DELETE)
	public void delete(@RequestParam("id") Long id) {
		mediaInfoService.delete(id);
	}
}
