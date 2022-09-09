package com.shop.Juliicasta.Controller.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.CartDetailMapper;
import com.shop.Juliicasta.Mapper.CartMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.ColorMapper;
import com.shop.Juliicasta.Mapper.ProductDetailMapper;
import com.shop.Juliicasta.Mapper.ProductMapper;
import com.shop.Juliicasta.Mapper.SizeMapper;
import com.shop.Juliicasta.Mapper.SlideMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.Cart;
import com.shop.Juliicasta.Model.CartDetail;
import com.shop.Juliicasta.Model.CartDetailExample;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Color;
import com.shop.Juliicasta.Model.Image;
import com.shop.Juliicasta.Model.Product;
import com.shop.Juliicasta.Model.ProductDetail;
import com.shop.Juliicasta.Model.ProductExample;
import com.shop.Juliicasta.Model.Size;
import com.shop.Juliicasta.Model.Slide;
import com.shop.Juliicasta.Model.SlideExample;

@Controller
@RequestMapping("/user")
public class CartController {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	ProductDetailMapper productDetailMapper;
	
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	CartDetailMapper cartDetailMapper;
	
	@Autowired
	SizeMapper sizeMapper;
	
	@Autowired
	ColorMapper colorMapper;
	
	@Autowired
	SlideMapper slideMapper;
	
	// Hàm kiểm tra giỏ hàng đã có sản phẩm này chưa này chưa 
	public boolean checkProductExist(ArrayList<CartDetail> list, CartDetail item) {
		for(int i=0; i<list.size(); i++) {
			if(item.getProductname().equals(list.get(i).getProductname()) && item.getSizename().equals(list.get(i).getSizename()) && item.getColorname().equals(list.get(i).getColorname())) {
				return false;
			}
		}
		return true;
	}
	
	@RequestMapping(value = { "/cart/{accId}" }, method = RequestMethod.GET)
	public ModelAndView cart(HttpServletRequest request, @PathVariable("accId") int accId) {
		if(request.getSession().getAttribute("user")==null) {
			ModelAndView mv = new ModelAndView("user/login");
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView("user/cart");
			
			Cart cart = cartMapper.selectByAccount(accId);
			
			List<CartDetail> listCart = cartDetailMapper.selectByCartId(cart.getId());
			
			ArrayList<Integer> productName = new ArrayList<Integer>();
			
			if(listCart.size()>0) {
				int p=0;
				int ship = 20000;
				for(int i=0; i<listCart.size(); i++) {
					p+=listCart.get(i).getPrice()*listCart.get(i).getQuantity();
				}
				if(p>=500000) {
					ship = 0;
				}
				int total = p + ship;
				mv.addObject("p", String.format("%,.0f",(float)p));
				mv.addObject("ship", String.format("%,.0f",(float)ship));
				mv.addObject("total", String.format("%,.0f",(float)total));
				mv.addObject("listCart", listCart);
			}
			else {
				mv.addObject("nope", "Chưa có sản phẩm nào trong giỏ hàng của bạn");
				mv.addObject("p", 0);
				mv.addObject("ship", 0);
				mv.addObject("total", 0);
			}
			
			CategoryExample categoryExample = new CategoryExample();
			
			List<Category> listCate = categoryMapper.selectByExample(categoryExample);
			
			mv.addObject("listCate", listCate);
			return mv;
		}	
	}
	
//	// Thêm vào giỏ hàng kiểu truyền thống		
//	@RequestMapping(value = { "/addtocart" }, method = RequestMethod.POST)
//	public ModelAndView addtocart(HttpServletRequest request) {		
//		int proId = Integer.parseInt(request.getParameter("ProductID"));
//		int accId = Integer.parseInt(request.getParameter("AccountID"));
//		int sizeId = Integer.parseInt(request.getParameter("SizeID"));
//		int colorId = Integer.parseInt(request.getParameter("ColorID"));
//		
//		Cart cart = cartMapper.selectByAccount(accId);
//		ProductDetail productDetail = productDetailMapper.selectProductDetail(proId, sizeId, colorId);
//		
//		Product pro = productMapper.selectByPrimaryKey(proId);
//		Size size = sizeMapper.selectByID(sizeId);
//		Color color = colorMapper.selectByID(colorId);
//		
//		List<CartDetail> listCart = cartDetailMapper.selectByCartId(cart.getId());
//		
//		CartDetail item = cartDetailMapper.selectItem(cart.getId(), productDetail.getId());
//		
//		if(item==null) {
//			cartDetailMapper.insert(cart.getId(), productDetail.getId(), 1, pro.getName(), size.getName(), color.getName(), pro.getPrice(), pro.getMainimage());
//		}
//		else {
//			int add = item.getQuantity() + 1;
//			cartDetailMapper.addQuantity(add, item.getPrice()*add, item.getId());
//		}
//		
//		CategoryExample categoryExample = new CategoryExample();
//		
//		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
//		
//		ModelAndView mv = new ModelAndView("user/cart");
//		mv.addObject("listCate", listCate);
//		return mv;
//	}
	
	// Thêm vào giỏ hàng bằng Ajax
	@ResponseBody
	@RequestMapping(value = { "/addtocart" }, method = RequestMethod.GET)
	public void addtocart(@RequestParam String datajson) {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(datajson);
		JsonNode jsonObject;
		try {
			jsonObject = objectMapper.readTree(datajson);

			int proID = jsonObject.get("ProductID").asInt();
			int accID = jsonObject.get("AccountID").asInt();
			int sizeID = jsonObject.get("SizeID").asInt();
			int colorID = jsonObject.get("ColorID").asInt();

			Cart cart = cartMapper.selectByAccount(accID);
			ProductDetail productDetail = productDetailMapper.selectProductDetail(proID, sizeID, colorID);
			
			Product pro = productMapper.selectByPrimaryKey(proID);
			Size size = sizeMapper.selectByID(sizeID);
			Color color = colorMapper.selectByID(colorID);
			
			List<CartDetail> listCart = cartDetailMapper.selectByCartId(cart.getId());
			
			CartDetail item = cartDetailMapper.selectItem(cart.getId(), productDetail.getId());
			
			if(item==null) {
				cartDetailMapper.insert(cart.getId(), productDetail.getId(), 1, pro.getName(), size.getName(), color.getName(), pro.getPrice(), pro.getMainimage());
			}
			else {
				int add = item.getQuantity() + 1;
				cartDetailMapper.addQuantity(add, item.getPrice(), item.getId());
			}
		} catch (IOException e) {
			
		}
	}
	
	// Xóa sản phẩm trong giỏ hàng
	@ResponseBody
	@RequestMapping(value = { "/cart/remove" }, method = RequestMethod.GET)
	public void remove(@RequestParam String id) {
		cartDetailMapper.deleteByPrimaryKey(Integer.parseInt(id));
	}
	
	// Chỉnh sửa số lượng sản phẩm trong giỏ hàng
	@ResponseBody
	@RequestMapping(value = { "/cart/updateQuantity" }, method = RequestMethod.GET)
	public void updateQuantity(@RequestParam String id, @RequestParam String quantity) {
		CartDetail item = cartDetailMapper.selectByPrimaryKey(Integer.parseInt(id));
		cartDetailMapper.addQuantity(Integer.parseInt(quantity), item.getPrice(), Integer.parseInt(id));
	}
}
