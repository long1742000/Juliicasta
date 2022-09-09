package com.shop.Juliicasta.Controller.Admin;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

@Controller
@RequestMapping("/admin/Juliicasta/Manager")
public class AdminSlideController {
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
	
	@Autowired
	ServletContext application;
	
	@ResponseBody
	@PostMapping("/changeSlide")
	public ModelAndView changeSlide(HttpServletRequest request, Principal principal) throws IOException, ServletException{
		int slideId = Integer.parseInt(request.getParameter("slide"));
		Part part = request.getPart("image");
		
		Slide slide = slideMapper.selectByPrimaryKey(slideId);
		
		String dirUrl = System.getProperty("user.dir")+"/src/main/resources/static/Images/Slide/";
		
		File deleteFile = new File(dirUrl+slide.getImagename());
		
		if(deleteFile.delete()) {
			System.out.println("xóa oke");
		}
		part.write(dirUrl+part.getSubmittedFileName());
		
		slideMapper.updateByPrimaryKey(part.getSubmittedFileName(), slideId);
		
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
