/**
 * 
 */
package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller is used to provide functionality for the home page.
 * 
 * @author Mularien
 *
 */
@Controller
public class HomeController {

	@RequestMapping(method=RequestMethod.GET,value="/home")
	public String home() {
		return "home";
	}
}
