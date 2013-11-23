package org.personal.mason.feop.oauth.contact.mvc.rest;

import org.personal.mason.feop.oauth.contact.mvc.model.ContactVO;
import org.personal.mason.feop.oauth.contact.mvc.model.InfoTypeVO;
import org.personal.mason.feop.oauth.contact.mvc.model.RecordVO;
import org.personal.mason.feop.oauth.contact.mvc.model.ResourceVO;
import org.personal.mason.feop.oauth.contact.spi.ContactInfoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("contact/q")
public class ContactQueryApi {

    private ContactInfoTypeService contactInfoTypeService;

    @Autowired
    public void setContactInfoTypeService(ContactInfoTypeService contactInfoTypeService) {
        this.contactInfoTypeService = contactInfoTypeService;
    }

    /**
     * List the contacts has remind date with the given date
     *
     * @param accountId
     * @param date
     * @return
     */
    @RequestMapping(value = {"remind"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ContactVO> findContactByRemindDate(Long accountId, Date date) {
        throw new RuntimeException("Not Implemented Exception");
    }

    /**
     * List the contacts which are binded and has information updated.
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = {"updates"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ContactVO> findUpdatedContact(Long accountId) {
        throw new RuntimeException("Not Implemented Exception");
    }

    /**
     * Find contact with record of mine
     *
     * @param accountId
     * @param record
     * @return
     */
    @RequestMapping(value = {"rec"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ContactVO> findContactWithRecord(Long accountId, RecordVO record) {
        throw new RuntimeException("Not Implemented Exception");
    }

    /**
     * Find contact with resource of mine
     *
     * @param accountId
     * @param resource
     * @return
     */
    @RequestMapping(value = {"res"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ContactVO> findContactWithResource(Long accountId, ResourceVO resource) {
        throw new RuntimeException("Not Implemented Exception");
    }

    /**
     * Find contacts with key word.
     *
     * @param accountId
     * @param query
     * @return
     */
    @RequestMapping(value = {"kw"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ContactVO> findContactWithString(Long accountId, String query) {
        throw new RuntimeException("Not Implemented Exception");
    }

    /**
     * Find the contacts that user may known
     *
     * @param accoutId
     * @return
     */
    @RequestMapping(value = {"known"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ContactVO> findContactsKnown(Long accoutId) {
        throw new RuntimeException("Not Implemented Exception");
    }

    /**
     * Find all the Contact info types.
     *
     * @return
     */
    @RequestMapping(value = {"info/type/list"}, method = RequestMethod.GET)
    @ResponseBody
    public List<InfoTypeVO> listAllTypes() {
        return contactInfoTypeService.allTypes();
    }
}