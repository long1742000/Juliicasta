package com.shop.Juliicasta.Controller.Admin;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.ImageMapper;
import com.shop.Juliicasta.Mapper.ProductDetailMapper;
import com.shop.Juliicasta.Mapper.ProductMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import com.shop.Juliicasta.Model.Bill;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Image;
import com.shop.Juliicasta.Model.Product;
import com.shop.Juliicasta.Model.ProductDetail;
import com.shop.Juliicasta.Model.Slide;
import com.shop.Juliicasta.Model.SlideExample;

@Controller
@RequestMapping("/admin/Juliicasta/Manager")
public class AdminProductController {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	ProductDetailMapper producdeDetailMapper;
	
	@Autowired
	ImageMapper imageMapper;
	
	@GetMapping("/listProduct")
	public ModelAndView listProduct(HttpServletRequest request, Principal principal) {
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/listProduct");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		int cate = Integer.parseInt(request.getParameter("cate"));
		
		if(cate==0) {
			List<Product> listProduct = productMapper.selectAllProductForAdmin();
			mv.addObject("listProduct", listProduct);
		}
		else {
			List<Product> listProduct = productMapper.selectProductByCateForAdmin(cate);
			mv.addObject("listProduct", listProduct);
		}
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		return mv;
	}
	
	@GetMapping("/Product")
	public ModelAndView productDetail(HttpServletRequest request, Principal principal) {
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/productDetail");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = productMapper.selectByPrimaryKey(id);
		
		if(!product.getHavecolor() && !product.getHavesize()) {
			ProductDetail proDetail = producdeDetailMapper.selectByProductID(id).get(0);
			int quantity = proDetail.getQuantity();
			mv.addObject("quantity", quantity);
		}
		
		if(product.getHavecolor() || product.getHavesize()) {
			List<ProductDetail> listDetail = producdeDetailMapper.selectByProductID(id);
			mv.addObject("listDetail", listDetail);
		}
		
		List<Image> img = imageMapper.selectByProductID(id);
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		mv.addObject("pro", product);
		mv.addObject("listImage", img);
		return mv;
	}
}
