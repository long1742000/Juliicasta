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
	// C???p nh???t tr???ng th??i bill:
	// B1: L???y ???????c c??c th??ng tin c???n thi???t
	// B2.1: N???u user hi???n t???i l?? admin th?? admin c?? th??? s???a status tr??n select => status ko c???n +1
	// B2.2: N???u user hi???n t???i l?? employee th?? status ph???i +1
	// B3: N???u bill ???? c?? nh??n vi??n x??? l?? r???i th?? ko c???n ph???i c???p nh???t EmployeeId, c??n ko th?? c???p nh???t user hi???n t???i
	// B4: N???u status = 3 => h??ng ???? ???????c giao => t???n kho s???n ph???m s??? gi???m theo tr??n h??a ????n
	// **NOTE: N???u admin chuy???n status t??? "Ho??n t???t" sang status kh??c th?? t???n kho tr??? v??? nh?? c??
	@ResponseBody
	@RequestMapping(value = { "/updateBill" }, method = RequestMethod.GET)
	public void updateBill(@RequestParam String datajson) {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(datajson);
		JsonNode jsonObject;
		try {
			jsonObject = objectMapper.readTree(datajson);
			
			// L???y th??ng tin c???n thi???t
			int statusid = jsonObject.get("status").asInt();
			int empId = jsonObject.get("employee").asInt();
			int billId = jsonObject.get("billid").asInt();
			
			Account emp = accountMapper.selectByPrimaryKey(empId);
			
			Bill bill = billMapper.selectByPrimaryKey(billId);
			List<BillDetail> listBillDetail = billDetailMapper.selectByBillID(billId);
			
			if(emp.getRole().equals("ROLE_ADMIN")) {																			// N???u user hi???n t???i l?? admin
				int currentStatus = jsonObject.get("currentStatus").asInt();
				Status status = statusMapper.selectByPrimaryKey(statusid);							// Th?? statusid s??? l???y theo select
				if(bill.getEmployeeid()==null) {													// N???u bill ch??a c?? ng?????i x??? l?? th?? c???p nh???t EmployeeId l?? user hi???n t???i
					billMapper.updateBillAndEmp(statusid, status.getName(), empId, billId);
				}
				else {
					billMapper.updateBill(statusid, status.getName(), billId);
				}
				
				if(status.getId()==3) {																// N???u status = 3 th?? gi???m t???n kho c???a s???n ph???m
					for(int i=0; i<listBillDetail.size(); i++) {
						ProductDetail proDetail = productDetailMapper.selectByPrimaryKey(listBillDetail.get(i).getProductdetailid());
						int quantity = listBillDetail.get(i).getQuantity();
						productDetailMapper.updateQuantity(proDetail.getQuantity() - quantity, proDetail.getId());
					}
				}
				
				if(currentStatus==3 || currentStatus==4) {																// N???u status hi???n t???i l?? 3 ho???c 4
					if(statusid!=3 && statusid!=4) {																// N???u admin ?????i sang 1 status kh??c 3 v?? 4
						for(int i=0; i<listBillDetail.size(); i++) {
							ProductDetail proDetail = productDetailMapper.selectByPrimaryKey(listBillDetail.get(i).getProductdetailid());
							int quantity = listBillDetail.get(i).getQuantity();
							productDetailMapper.updateQuantity(proDetail.getQuantity() + quantity, proDetail.getId());
						}
					}
				}
			}
			else {																					// N???u user hi???n t???i l?? employee
				statusid = statusid+1;																// Th?? statusid s??? +1
				Status status = statusMapper.selectByPrimaryKey(statusid);
				if(bill.getEmployeeid()==null) {
					billMapper.updateBillAndEmp(statusid, status.getName(), empId, billId);
				}
				else {
					billMapper.updateBill(statusid, status.getName(), billId);
				}
				
				if(status.getId()==3) {																// N???u status = 3 th?? gi???m t???n kho c???a s???n ph???m
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
		// Note l??n bill n??y b??? b??ng h??ng - Employee m???i c?? n??t note do Admin c?? th??? update t??y ?? theo select:
		// B1: L???y ???????c c??c th??ng tin c???n thi???t
		// B2: N???u bill ???? c?? nh??n vi??n x??? l?? r???i th?? ko c???n ph???i c???p nh???t EmployeeId, c??n ko th?? c???p nh???t user hi???n t???i
		// B3: Update StatusId = 7 v?? Statusname = B??ng h??ng
	@ResponseBody
	@RequestMapping(value = { "/noteBill" }, method = RequestMethod.GET)
	public void noteBill(@RequestParam String datajson) {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(datajson);
		JsonNode jsonObject;
		try {
			jsonObject = objectMapper.readTree(datajson);
			
			// L???y th??ng tin c???n thi???t
			int empId = jsonObject.get("employee").asInt();
			int billId = jsonObject.get("billid").asInt();
			
			Bill bill = billMapper.selectByPrimaryKey(billId);
			
			if(bill.getEmployeeid()==null) {
				billMapper.updateBillAndEmp(7, "B??ng h??ng", empId, billId);
			}
			else {
				billMapper.updateBill(7, "B??ng h??ng", billId);
			}	
		} catch (IOException e) {
			
		}
	}
}
