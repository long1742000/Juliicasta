package com.shop.Juliicasta.Controller.Admin;

import java.io.IOException;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.BillDetailMapper;
import com.shop.Juliicasta.Mapper.BillMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.ProductDetailMapper;
import com.shop.Juliicasta.Mapper.StatusMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import com.shop.Juliicasta.Model.Bill;
import com.shop.Juliicasta.Model.BillDetail;
import com.shop.Juliicasta.Model.BillExample;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Product;
import com.shop.Juliicasta.Model.ProductDetail;
import com.shop.Juliicasta.Model.Status;

@Controller
@RequestMapping("/admin/Juliicasta/Manager")
public class AdminBillController {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	BillMapper billMapper;
	
	@Autowired
	BillDetailMapper billDetailMapper;
	
	@Autowired
	ProductDetailMapper productDetailMapper;
	
	@Autowired
	StatusMapper statusMapper;
	
	@GetMapping("/listBill")
	public ModelAndView listBill(HttpServletRequest request, Principal principal) {
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/listBill");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		BillExample billExample = new BillExample();
		List<Bill> listBill = billMapper.selectByExample(billExample);
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		mv.addObject("listBill", listBill);
		return mv;
	}
	
	@GetMapping("/Bill")
	public ModelAndView billDetail(HttpServletRequest request, Principal principal) {
		AccountExample example = new AccountExample();
		CategoryExample categoryExample = new CategoryExample();
		ModelAndView mv = new ModelAndView("admin/billDetail");
		principal.getName();
		example.createCriteria().andUsernameEqualTo(principal.getName());
		List<Account> list = accountMapper.selectByExample(example);
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		Account acc = list.get(0);
		
		int billId = Integer.parseInt(request.getParameter("billId"));
		
		List<BillDetail> listBillDetail = billDetailMapper.selectByBillID(billId);
		Bill bill = billMapper.selectByPrimaryKey(billId);
		
		Account accountDetail = accountMapper.selectByPrimaryKey(bill.getAccountid());
		
		if(bill.getEmployeeid()!=null) {
			Account emp = accountMapper.selectByPrimaryKey(bill.getEmployeeid());
			mv.addObject("emp", emp);
		}
		
		int p=0;
		int ship = 20000;
		for(int i=0; i<listBillDetail.size(); i++) {
			p+=listBillDetail.get(i).getAmount();
		}
		if(p>=500000) {
			ship = 0;
		}
		int total = p + ship;
		
		mv.addObject("user", acc);
		mv.addObject("listCate", listCate);
		mv.addObject("accountDetail", accountDetail);
		mv.addObject("bill", bill);
		mv.addObject("ship", ship);
		mv.addObject("total", total);
		mv.addObject("listBillDetail", listBillDetail);
		return mv;
	}
	
	// ------------------------------------------------------------------------------------- Update bill status
	// Cập nhật trạng thái bill:
	// B1: Lấy được các thông tin cần thiết
	// B2.1: Nếu user hiện tại là admin thì admin có thể sửa status trên select => status ko cần +1
	// B2.2: Nếu user hiện tại là employee thì status phải +1
	// B3: Nếu bill đã có nhân viên xử lý rồi thì ko cần phải cập nhật EmployeeId, còn ko thì cập nhật user hiện tại
	// B4: Nếu status = 3 => hàng đã được giao => tồn kho sản phẩm sẽ giảm theo trên hóa đơn
	// **NOTE: Nếu admin chuyển status từ "Hoàn tất" sang status khác thì tồn kho trở về như cũ
	@ResponseBody
	@RequestMapping(value = { "/updateBill" }, method = RequestMethod.GET)
	public void updateBill(@RequestParam String datajson) {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(datajson);
		JsonNode jsonObject;
		try {
			jsonObject = objectMapper.readTree(datajson);
			
			// Lấy thông tin cần thiết
			int statusid = jsonObject.get("status").asInt();
			int empId = jsonObject.get("employee").asInt();
			int billId = jsonObject.get("billid").asInt();
			
			Account emp = accountMapper.selectByPrimaryKey(empId);
			
			Bill bill = billMapper.selectByPrimaryKey(billId);
			List<BillDetail> listBillDetail = billDetailMapper.selectByBillID(billId);
			
			if(emp.getRole().equals("ROLE_ADMIN")) {																			// Nếu user hiện tại là admin
				int currentStatus = jsonObject.get("currentStatus").asInt();
				Status status = statusMapper.selectByPrimaryKey(statusid);							// Thì statusid sẽ lấy theo select
				if(bill.getEmployeeid()==null) {													// Nếu bill chưa có người xử lý thì cập nhật EmployeeId là user hiện tại
					billMapper.updateBillAndEmp(statusid, status.getName(), empId, billId);
				}
				else {
					billMapper.updateBill(statusid, status.getName(), billId);
				}
				
				if(status.getId()==3) {																// Nếu status = 3 thì giảm tồn kho của sản phẩm
					for(int i=0; i<listBillDetail.size(); i++) {
						ProductDetail proDetail = productDetailMapper.selectByPrimaryKey(listBillDetail.get(i).getProductdetailid());
						int quantity = listBillDetail.get(i).getQuantity();
						productDetailMapper.updateQuantity(proDetail.getQuantity() - quantity, proDetail.getId());
					}
				}
				
				if(currentStatus==3 || currentStatus==4) {																// Nếu status hiện tại là 3 hoặc 4
					if(statusid!=3 && statusid!=4) {																// Nếu admin đổi sang 1 status khác 3 và 4
						for(int i=0; i<listBillDetail.size(); i++) {
							ProductDetail proDetail = productDetailMapper.selectByPrimaryKey(listBillDetail.get(i).getProductdetailid());
							int quantity = listBillDetail.get(i).getQuantity();
							productDetailMapper.updateQuantity(proDetail.getQuantity() + quantity, proDetail.getId());
						}
					}
				}
			}
			else {																					// Nếu user hiện tại là employee
				statusid = statusid+1;																// Thì statusid sẽ +1
				Status status = statusMapper.selectByPrimaryKey(statusid);
				if(bill.getEmployeeid()==null) {
					billMapper.updateBillAndEmp(statusid, status.getName(), empId, billId);
				}
				else {
					billMapper.updateBill(statusid, status.getName(), billId);
				}
				
				if(status.getId()==3) {																// Nếu status = 3 thì giảm tồn kho của sản phẩm
					for(int i=0; i<listBillDetail.size(); i++) {
						ProductDetail proDetail = productDetailMapper.selectByPrimaryKey(listBillDetail.get(i).getProductdetailid());
						int quantity = listBillDetail.get(i).getQuantity();
						productDetailMapper.updateQuantity(proDetail.getQuantity() - quantity, proDetail.getId());
					}
				}
			}
		} catch (IOException e) {
			
		}
	}
	
	// ------------------------------------------------------------------------------------- Note bill status
		// Note lên bill này bị bùng hàng - Employee mới có nút note do Admin có thể update tùy ý theo select:
		// B1: Lấy được các thông tin cần thiết
		// B2: Nếu bill đã có nhân viên xử lý rồi thì ko cần phải cập nhật EmployeeId, còn ko thì cập nhật user hiện tại
		// B3: Update StatusId = 7 và Statusname = Bùng hàng
	@ResponseBody
	@RequestMapping(value = { "/noteBill" }, method = RequestMethod.GET)
	public void noteBill(@RequestParam String datajson) {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(datajson);
		JsonNode jsonObject;
		try {
			jsonObject = objectMapper.readTree(datajson);
			
			// Lấy thông tin cần thiết
			int empId = jsonObject.get("employee").asInt();
			int billId = jsonObject.get("billid").asInt();
			
			Bill bill = billMapper.selectByPrimaryKey(billId);
			
			if(bill.getEmployeeid()==null) {
				billMapper.updateBillAndEmp(7, "Bùng hàng", empId, billId);
			}
			else {
				billMapper.updateBill(7, "Bùng hàng", billId);
			}	
		} catch (IOException e) {
			
		}
	}
}
