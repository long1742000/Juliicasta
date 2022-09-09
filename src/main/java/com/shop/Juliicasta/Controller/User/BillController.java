package com.shop.Juliicasta.Controller.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.BillDetailMapper;
import com.shop.Juliicasta.Mapper.BillMapper;
import com.shop.Juliicasta.Mapper.CartDetailMapper;
import com.shop.Juliicasta.Mapper.CartMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.ColorMapper;
import com.shop.Juliicasta.Mapper.ProductDetailMapper;
import com.shop.Juliicasta.Mapper.ProductMapper;
import com.shop.Juliicasta.Mapper.SizeMapper;
import com.shop.Juliicasta.Mapper.SlideMapper;
import com.shop.Juliicasta.Model.Bill;
import com.shop.Juliicasta.Model.BillDetail;
import com.shop.Juliicasta.Model.Cart;
import com.shop.Juliicasta.Model.CartDetail;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Color;
import com.shop.Juliicasta.Model.Product;
import com.shop.Juliicasta.Model.ProductDetail;
import com.shop.Juliicasta.Model.Size;

@Controller
@RequestMapping("/user")
public class BillController {
	@Autowired
	AccountMapper accountMapper;
	
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
	BillMapper billMapper;
	
	@Autowired
	BillDetailMapper billDetailMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@GetMapping("/addBill/{id}")
	public ModelAndView addBill(HttpServletRequest request, @PathVariable("id") int accId) {
		ModelAndView mv = new ModelAndView("user/addBill");
		
		Cart cart = cartMapper.selectByAccount(accId);
		List<CartDetail> listCart = cartDetailMapper.selectByCartId(cart.getId());
		
		int p=0;
		int ship = 20000;
		for(int i=0; i<listCart.size(); i++) {
			p+=listCart.get(i).getPrice()*listCart.get(i).getQuantity();
		}
		if(p>=500000) {
			ship = 0;
		}
		int total = p + ship;
		
		mv.addObject("ship", ship);
		mv.addObject("total", total);
		mv.addObject("listCart", listCart);
		return mv;
	}
	
//	// Addbill bằng Ajax	
//	@RequestMapping(value = { "/addNewBill" })
//	public ModelAndView addNewBill(HttpServletRequest request, @RequestParam String datajson) {
//		ModelAndView mv = new ModelAndView("user/infor");
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		JsonNode jsonObject;
//		try {
//			jsonObject = objectMapper.readTree(datajson);
//
//			int accID = jsonObject.get("accountid").asInt();
//			String phone = jsonObject.get("phone").asText();
//			String address = jsonObject.get("address").asText();
//
//			JsonNode jsonDetail = jsonObject.get("CartDetails");
//
//			for (JsonNode objectDetail : jsonDetail) {
//				int proDetailID = objectDetail.get("productdetailid").asInt();
//				int quantity = objectDetail.get("quantity").asInt();
//				int amount = objectDetail.get("amount").asInt();
//				System.out.println(quantity);
//			}
//			return mv;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return mv;
//	}
	
	@RequestMapping(value = { "/addNewBill" }, method = RequestMethod.POST)
	public ModelAndView addNewBill(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/infor");
		Random rand = new Random();
		int number = rand.nextInt(1000000) + 1000000;
		String billno = "Bill No."+number;
		Date currentDate = new Date();
		int accID = Integer.parseInt(request.getParameter("accountid"));
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
		
		billMapper.insert(billno, accID, 1, fm.format(currentDate), phone, address, "Mới");
		
		Bill bill = billMapper.selectNewBillForAccount(accID);
		
		Cart cart = cartMapper.selectByAccount(accID);
		
		List<CartDetail> listCart = cartDetailMapper.selectByCartId(cart.getId());
		for(int i=0; i<listCart.size(); i++) {
			int productdetailid = listCart.get(i).getProductdetailid();
			billDetailMapper.insert(bill.getId(), productdetailid, listCart.get(i).getProductname(), listCart.get(i).getSizename(), listCart.get(i).getColorname(), listCart.get(i).getQuantity(), listCart.get(i).getPrice() * listCart.get(i).getQuantity());	
		}
		
		List<Bill> listBill = billMapper.selectListBillForAccount(accID);
		
		cartDetailMapper.deleteByCartID(cart.getId());
		
		mv.addObject("listBill", listBill);
		return mv;
	}
	
	@GetMapping("/billDetail/{id}")
	public ModelAndView billDetail(HttpServletRequest request, @PathVariable("id") int billId) {
		ModelAndView mv = new ModelAndView("user/billDetail");
		
		List<BillDetail> listBillDetail = billDetailMapper.selectByBillID(billId);
		Bill bill = billMapper.selectByPrimaryKey(billId);
		
		int p=0;
		int ship = 20000;
		for(int i=0; i<listBillDetail.size(); i++) {
			p+=listBillDetail.get(i).getAmount();
		}
		if(p>=500000) {
			ship = 0;
		}
		int total = p + ship;
		
		CategoryExample categoryExample = new CategoryExample();
		
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);		
		
		mv.addObject("listCate", listCate);
		
		mv.addObject("bill", bill);
		mv.addObject("ship", ship);
		mv.addObject("total", total);
		mv.addObject("listBillDetail", listBillDetail);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/cancelBill" }, method = RequestMethod.GET)
	public void cancelBill(@RequestParam String datajson) {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(datajson);
		JsonNode jsonObject;
		try {
			jsonObject = objectMapper.readTree(datajson);

			int billId = jsonObject.get("billid").asInt();
			
			billMapper.cancelBill(billId);
			
		} catch (IOException e) {
			
		}
	}
}
