package org.personal.mason.feop.server.blog.mvc.rest;

import java.util.List;

import org.personal.mason.feop.server.blog.common.client.JDBCClientConfiguration;
import org.personal.mason.feop.server.blog.domain.model.SystemSettings;
import org.personal.mason.feop.server.blog.domain.service.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemSettingsController {

	private SystemSettingsService systemSettingsService;

	@Autowired
	public void setSystemSettingsService(SystemSettingsService systemSettingsService) {
		this.systemSettingsService = systemSettingsService;
	}

	@RequestMapping(value = { "/syssetting/oauthkeys" })
	@ResponseBody
	public List<String> listOauthKeys() {
		return JDBCClientConfiguration.getAllOauthSettings();
	}

	@RequestMapping(value = { "/syssetting/list" })
	@ResponseBody
	public List<SystemSettings> findSystemSettings(@RequestParam("profile") String profile) {
		return systemSettingsService.findByProfile(profile);
	}

	@RequestMapping(value = { "/syssetting/update" }, method = RequestMethod.PUT)
	@ResponseBody
	public List<SystemSettings> updateSystemSettings(SystemSettings... settings) {
		List<SystemSettings> update = systemSettingsService.update(settings);
		return update;
	}

	@RequestMapping(value = { "/syssetting/delete" }, method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteSystemSettings(SystemSettings... settings) {
		systemSettingsService.delete(settings);
	}
}
