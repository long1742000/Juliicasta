package com.shop.Juliicasta.Controller.Admin;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.BillMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import com.shop.Juliicasta.Model.Bill;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;

@Controller
@RequestMapping("/admin/Juliicasta/Manager")
public class AdminAccountController {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	BillMapper billMapper;
	
	@RequestMapping("/listAccount")
	public ModelAndView listAccount(HttpServletRequest request, Principal principal) throws IOException, ServletException{
		int enable = Integer.parseInt(request.getParameter("enable"));
		
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/listAccount");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		List<Account> listAccount = accountMapper.selectEnableAccount(enable);
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		mv.addObject("listAccount", listAccount);
		return mv;
	}
	
	@RequestMapping("/Account")
	public ModelAndView accountDetail(HttpServletRequest request, Principal principal) throws IOException, ServletException{
		int accId = Integer.parseInt(request.getParameter("id"));
		
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/accountDetail");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		Account accountDetail = accountMapper.selectByPrimaryKey(accId);
		List<Bill> listBill = billMapper.selectAllBillForAccount(accId);
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		mv.addObject("accountDetail", accountDetail);
		mv.addObject("listBill", listBill);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/editStatus" }, method = RequestMethod.GET)
	public void editStatus(@RequestParam int id) {
		Account acc = accountMapper.selectByPrimaryKey(id);
		
		if(acc.getEnable()) {
			accountMapper.editStatus(0, id);
		}
		else {
			accountMapper.editStatus(1, id);
		}
	}
}
