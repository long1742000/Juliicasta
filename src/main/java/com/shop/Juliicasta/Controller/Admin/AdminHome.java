package com.shop.Juliicasta.Controller.Admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.Juliicasta.Config.AES;
import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.BillMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.ProductMapper;
import com.shop.Juliicasta.Mapper.SlideMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import com.shop.Juliicasta.Model.Bill;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Product;
import com.shop.Juliicasta.Model.Slide;
import com.shop.Juliicasta.Model.SlideExample;
import com.shop.Juliicasta.Service.AdminDetailService;

@Controller
@RequestMapping("/admin/Juliicasta/Manager")
public class AdminHome {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	SlideMapper slideMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	BillMapper billMapper;
	
	@GetMapping("/index")
	public ModelAndView AdminIndex(Principal principal) {
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		SlideExample slideExample = new SlideExample();
		ModelAndView mv = new ModelAndView("admin/index");
		principal.getName();
		
		example.createCriteria().andUsernameEqualTo(principal.getName());
		
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		List<Slide> listSlide = slideMapper.selectByExample(slideExample);
		List<Product> listProduct = productMapper.selectTop(15);
		List<Bill> listBill = billMapper.selectNewListBill();
		
		Slide slide1 = listSlide.get(0);
		Slide slide2 = listSlide.get(1);
		Slide slide3 = listSlide.get(2);
		
		Account acc = list.get(0);
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		mv.addObject("slide1", slide1);
		mv.addObject("slide2", slide2);
		mv.addObject("slide3", slide3);
		mv.addObject("listProduct", listProduct);
		mv.addObject("listBill", listBill);
		return mv;
	}
}
