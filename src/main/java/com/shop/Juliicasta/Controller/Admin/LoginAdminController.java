package com.shop.Juliicasta.Controller.Admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Service.AdminDetailService;

@Controller
@RequestMapping("/admin/Juliicasta/Manager")
public class LoginAdminController {
	
	@GetMapping("/login")
	public ModelAndView loginAdmin() {
		ModelAndView mv = new ModelAndView("admin/login");
		return mv;
	}
}
