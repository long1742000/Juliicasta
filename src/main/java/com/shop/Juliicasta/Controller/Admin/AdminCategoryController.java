package com.shop.Juliicasta.Controller.Admin;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;

@Controller
@RequestMapping("/admin/Juliicasta/Manager")
public class AdminCategoryController {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@RequestMapping("/listCategory")
	public ModelAndView listCategory(HttpServletRequest request, Principal principal) throws IOException, ServletException{
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/listCategory");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		return mv;
	}
}
