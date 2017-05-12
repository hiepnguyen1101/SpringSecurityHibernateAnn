package com.gcs.rms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gcs.rms.service.IAcccountService;

@Controller
public class MainController {
	
	@Autowired
	IAcccountService iAcccountService;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		ModelAndView model = new ModelAndView();
		if(getPrincipal()!=null){
			model.addObject("title", "RMS");
			model.addObject("message", "This is default page!");
			model.setViewName("hello");
			return model;
		}
		else{		
			model.setViewName("redirect:/login?message=You not yet logged in!");
			return model;
		}
	}

	@RequestMapping(value = "/admin/**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "RMS");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value="/changePass", method=RequestMethod.GET)
	public ModelAndView viewChangePassPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "message", required = false) String message){
		ModelAndView modelAndView = new ModelAndView("changePass");
		if(error!=null){
			modelAndView.addObject("error", "Change password failed! :[[");
		}
		if(message!=null){
			modelAndView.addObject("message", "Change password successfully :]]");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/processChangePass", method=RequestMethod.POST)
	public String processChangePass(HttpServletRequest request){
		if(iAcccountService.changePassword(request.getParameter("username"), 
				request.getParameter("oldPass"),
				request.getParameter("newPass"))){
			return "redirect:/changePass?messag=true";
		}	
		return "redirect:/changePass?error=true";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	 private String getPrincipal(){
	        String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();
	        } else {
	            userName = principal.toString();
	        }
	        return userName;
	    }

}