package com.shop.Juliicasta.Controller.Admin;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.ContactMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import com.shop.Juliicasta.Model.Bill;
import com.shop.Juliicasta.Model.BillExample;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Contact;
import com.shop.Juliicasta.Model.ContactExample;

@Controller
@RequestMapping("/admin/Juliicasta/Manager")
public class AdminContactController {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ContactMapper contactMapper;
	
	@GetMapping("/listContact")
	public ModelAndView listContact(HttpServletRequest request, Principal principal) {
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/listContact");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		ContactExample contactExample = new ContactExample();
		List<Contact> listContact = contactMapper.selectByExample(contactExample);
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		mv.addObject("listContact", listContact);
		return mv;
	}
	
	@GetMapping("/Contact")
	public ModelAndView contactDetail(HttpServletRequest request, Principal principal) {
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/contactDetail");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactMapper.selectByPrimaryKey(contactId);
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		mv.addObject("contact", contact);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/editContactStatus" }, method = RequestMethod.GET)
	public void editContactStatus(@RequestParam int id) {
		Contact contact = contactMapper.selectByPrimaryKey(id);
		
		if(contact.getStatus()) {
			contactMapper.editContactStatus(0, id);
		}
		else {
			contactMapper.editContactStatus(1, id);
		}
	}
}
