package org.personal.mason.feop.oauth.service.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.personal.mason.feop.oauth.service.domain.model.common.EmailTemplate;
import org.personal.mason.feop.oauth.service.domain.service.common.EmailTemplateService;
import org.personal.mason.feop.oauth.service.mvc.model.EmailTemplateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailTemplateController {

	private EmailTemplateService emailTemplateService;

	@Autowired
	public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
		this.emailTemplateService = emailTemplateService;
	}

	@RequestMapping(value = { "/admin/et/new" }, method = RequestMethod.GET)
	public String newEmailTemplate() {
		return "app.et.new";
	}

	@RequestMapping(value = { "/admin/et/new" }, method = RequestMethod.POST)
	public String saveEmailTemplate(@Valid EmailTemplateForm emailTemplateForm, BindingResult result, Model model) {
		EmailTemplate latestTemplate = emailTemplateService.findLatestTemplateWithName(emailTemplateForm.getName());
		EmailTemplate template;
		if (latestTemplate == null) {
			template = new EmailTemplate();
			template.setName(emailTemplateForm.getName());
			template.setVersion(1);
			template.setContent(emailTemplateForm.getContent());
			template.setSubject(emailTemplateForm.getSubject());
			emailTemplateService.save(template);
		} else {
			latestTemplate.setVersion(latestTemplate.getVersion() + 1);
			latestTemplate.setContent(emailTemplateForm.getContent());
			latestTemplate.setSubject(emailTemplateForm.getSubject());
			emailTemplateService.update(latestTemplate);
			template = latestTemplate;
		}

		model.addAttribute("template", template);
		return "app.et.view";
	}

	@RequestMapping(value = { "/admin/et/update" }, method = RequestMethod.GET)
	public String updateEmailTemplate(@RequestParam("id") Long id, Model model) {
		EmailTemplate template = emailTemplateService.findById(id);
		if(template == null){
			return null;
		}
		
		model.addAttribute("template", EmailTemplateForm.revert(template));
		
		return "app.et.update";
	}

	@RequestMapping(value = { "/admin/et/update" }, method = RequestMethod.POST)
	public String mergeEmailTemplate(@Valid EmailTemplateForm template, BindingResult result, Model model) {
		EmailTemplate editTemplate = emailTemplateService.findById(template.getId());

		editTemplate.setVersion(editTemplate.getVersion() + 1);
		editTemplate.setContent(template.getContent());

		model.addAttribute("template", editTemplate);
		return "app.et.view";
	}

	@RequestMapping(value = { "/admin/et/list" }, method = RequestMethod.GET)
	public String findEmailTemplates(EmailTemplateForm emailTemplateForm, Model model) {
		List<EmailTemplate> templates;
		if (emailTemplateForm == null || emailTemplateForm.getName() == null) {
			templates = emailTemplateService.findAll();
		} else {
			templates = emailTemplateService.findTemplatesByName(emailTemplateForm.getName());
		}
		model.addAttribute("templates", templates);
		return "app.et.list";
	}

	@RequestMapping(value = { "/admin/et/delete" }, method = RequestMethod.GET)
	public void deleteTemplate(@RequestParam("id") Long id) {
		EmailTemplate template = emailTemplateService.findById(id);
		if(template != null){
			emailTemplateService.delete(template);
		}
	}
	
	@RequestMapping(value = { "/admin/et/view" }, method = RequestMethod.GET)
	public String viewTemplate(@RequestParam("id") Long id, Model model) {
		EmailTemplate template = emailTemplateService.findById(id);
		if(template == null){
			return null;
		}
		
		model.addAttribute("template", template);
		return "app.et.view";
	}
}
