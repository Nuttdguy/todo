package com.todo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.todo.model.User;

//  #0002.01  		//==	add controller
@Controller
public class LoginController {
	
	//  #0003.01  	//== 	add request mapping to handle url requests
	//  #0003.02  //==		add other controllers and request mappings
	//  #0003.03  //==		add html view templates for each controller
	@RequestMapping( path = "/login", method = RequestMethod.GET)
	public String loginForm(Model model, HttpServletRequest request) {
		
		//==//  add user class to current session by instantiating an empty object
		model.addAttribute("user", new User());
		
		try {
			
			//==// request the current session, get the session attribute "flash"
			Object flash = request.getSession().getAttribute("flash");
			
			//==// add the flash attribute to the model
			model.addAttribute("flash", flash);
			
			//==// get the current session, remove the flash attribute
			request.getSession().removeAttribute("flash");
			
		} catch (Exception ex) {
			// "flash" session attribute must not exist...do nothing and proceed normally
		}
		
		//==//  retrieve and forward result to login template
		return "login";
	}
	
	@RequestMapping("/access_denied")
	public String accessDenied() {
		return "access_denied";
	}
}
