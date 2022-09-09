package com.shop.Juliicasta.Controller.User;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.Juliicasta.Config.AES;
import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.BillMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.ImageMapper;
import com.shop.Juliicasta.Mapper.ProductMapper;
import com.shop.Juliicasta.Mapper.SlideMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import com.shop.Juliicasta.Model.Bill;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Image;
import com.shop.Juliicasta.Model.Product;
import com.shop.Juliicasta.Model.ProductExample;
import com.shop.Juliicasta.Model.Slide;
import com.shop.Juliicasta.Model.SlideExample;
import com.shop.Juliicasta.Service.AdminDetailService;

@Controller
@RequestMapping("/user")
public class HomePage {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	SlideMapper slideMapper;
	
	@Autowired
	BillMapper billMapper;
	
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/index");
		
		request.getSession().removeAttribute("url");
		
		CategoryExample categoryExample = new CategoryExample();
		SlideExample slideExample = new SlideExample();
		ProductExample productExample = new ProductExample();
		
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		List<Slide> listSlide = slideMapper.selectByExample(slideExample);
		List<Product> listProduct = productMapper.selectTop(6);
		
		Slide slide1 = listSlide.get(0);
		Slide slide2 = listSlide.get(1);
		Slide slide3 = listSlide.get(2);
		
		mv.addObject("listCate", listCate);
		mv.addObject("listProduct", listProduct);
		mv.addObject("slide1", slide1);
		mv.addObject("slide2", slide2);
		mv.addObject("slide3", slide3);
		return mv;
	}
	
	@GetMapping("/back")
	public RedirectView back(HttpServletRequest request) {
		RedirectView redirectView;
		if(request.getSession().getAttribute("url")!=null) {
			String url = String.valueOf(request.getSession().getAttribute("url"));
			redirectView = new RedirectView(url, true);
		}
		else {
			redirectView = new RedirectView("/user/", true);
		}
		return redirectView;
	}
}

