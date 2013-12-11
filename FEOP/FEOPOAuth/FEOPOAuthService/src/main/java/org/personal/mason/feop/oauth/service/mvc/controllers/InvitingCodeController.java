package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.service.domain.model.common.InvitingCode;
import org.personal.mason.feop.oauth.service.domain.service.common.InvitingCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InvitingCodeController {
    private static final int DEFAULT_GENERATE_COUNT = 10;

    private InvitingCodeService invitingCodeService;

    @Autowired
    public void setInvitingCodeService(InvitingCodeService invitingCodeService) {
        this.invitingCodeService = invitingCodeService;
    }

    @RequestMapping(value = {"/admin/invite/generate"}, method = RequestMethod.GET)
    public String generateCodes() {
        invitingCodeService.generateInvitingCodes(DEFAULT_GENERATE_COUNT);
        return "redirect:/admin/invite/list";
    }

    @RequestMapping(value = {"/admin/invite/list"}, method = RequestMethod.GET)
    public String findInvitingCodes(Model model) {
        List<InvitingCode> codes = invitingCodeService.findAll();
        model.addAttribute("invites", codes);
        return "app.invite.list";
    }

    @RequestMapping(value = {"/admin/invite/delete"}, method = RequestMethod.GET)
    public String findInvitingCodes(@RequestParam("id") Long id) {
        invitingCodeService.delete(id);
        return "redirect:/admin/invite/list";
    }
}
