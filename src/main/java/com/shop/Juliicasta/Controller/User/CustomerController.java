package com.shop.Juliicasta.Controller.User;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.BillMapper;
import com.shop.Juliicasta.Mapper.CartMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.CommentMapper;
import com.shop.Juliicasta.Mapper.ContactMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import com.shop.Juliicasta.Model.Bill;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Comment;
import com.shop.Juliicasta.Model.Contact;

@Controller
@RequestMapping("/user")
public class CustomerController {
	String currentUser;
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	CartMapper cartMapper;
	
	int OTPCode;					// Tạo biến toàn cục để lưu OTPCode được gửi qua mail
	String currentUsername;			// Biến toàn cục để lưu username được nhập từ input
	
	@Autowired
	MailSender mailSender;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	BillMapper billMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ContactMapper contactMapper;
	
	// ------------------------------------------------------------------------------------- Login vs logout
	@GetMapping("/login")
	public ModelAndView loginUser() {
		ModelAndView mv = new ModelAndView("user/login");
		return mv;
	}
	
	@PostMapping("/checkLogin")
	public RedirectView checkLogin(@ModelAttribute(name = "loginForm") Account account, RedirectAttributes redir,
			HttpServletRequest request) {
		// Lay username + password dc nhap tu form login
		String username = account.getUsername();
		String pass = account.getPassword();

		// Tao query lay account = username
		AccountExample accountExample = new AccountExample();
		accountExample.createCriteria().andUsernameEqualTo(username);

		List<Account> list = accountMapper.selectByExample(accountExample);
		if (list.size() > 0) { // Neu username ton tai thi xet password co dung ko
			Account acc = list.get(0);
			// So sanh pass vs password
			if (BCrypt.checkpw(pass, acc.getPassword())) {
				if (acc.getEnable()) { // Check xem account co bi khoa ko
					if (acc.getRole().equals("ROLE_CUSTOMER")) { // Check xem co phai account cua khach ko
						RedirectView redirectView;
						if(request.getSession().getAttribute("url")!=null) {
							String url = String.valueOf(request.getSession().getAttribute("url"));
							redirectView = new RedirectView(url, true);
						}
						else {
							redirectView = new RedirectView("/user/", true);
						}
						request.getSession().setAttribute("user", acc);
						request.getSession().setAttribute("userId", acc.getId());
						return redirectView;
					} else {
						RedirectView redirectView = new RedirectView("/user/login", true);

						redir.addFlashAttribute("er", "Tài khoản của bạn không phù hợp");
						return redirectView;
					}
				} else {
					RedirectView redirectView = new RedirectView("/user/login", true);

					redir.addFlashAttribute("er", "Tài khoản của bạn đã bị khóa");
					return redirectView;
				}
			} else {
				RedirectView redirectView = new RedirectView("/user/login", true);

				redir.addFlashAttribute("er", "Mật khẩu không đúng");
				return redirectView;
			}
		} else {
			RedirectView redirectView = new RedirectView("/user/login", true);

			redir.addFlashAttribute("er", "Tên đăng nhập không đúng");
			return redirectView;
		}
	}

	@GetMapping("/logout")
	public RedirectView logoutUser(RedirectAttributes redir, HttpServletRequest request) {
		RedirectView redirectView;
		if(request.getSession().getAttribute("url")!=null) {
			String url = String.valueOf(request.getSession().getAttribute("url"));
			redirectView = new RedirectView(url, true);
		}
		else {
			redirectView = new RedirectView("/user/", true);
		}
		HttpSession session = request.getSession();
		session.invalidate();

		return redirectView;
	}
	
	// ------------------------------------------------------------------------------------- Register
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("user/register");
		return mv;
	}
	
	@PostMapping("/registration")
	public ModelAndView registration(@ModelAttribute(name="registerForm") Account account, @ModelAttribute(name="confirmpassword") String confirm, Model model) {
		ModelAndView mv;
		AccountExample accountExample = new AccountExample();
		int countPhone = accountMapper.countByPhone(account.getPhone());		// Dem so luong sdt nay da tao account
		int countEmail = accountMapper.countByEmail(account.getEmail());		// Dem so luong email nay da tao account
		
		if(countPhone > 3) {								// Neu sdt nay da tao 4 account tro len thi ko dc tao nua
			mv = new ModelAndView("user/register");
			mv.addObject("er", "Số điện thoại này đã hết lượt tạo tài khoản");
			return mv;
		}
		else if(countEmail > 3) {							// Neu email nay da tao 4 account tro len thi ko dc tao nua
			mv = new ModelAndView("user/register");
			mv.addObject("er", "Email này đã hết lượt tạo tài khoản");
			return mv;
		}
		else if(account.getPassword().length()<6) {
			mv = new ModelAndView("user/register");
			mv.addObject("er", "Mật khẩu phải 6 ký tự trở lên");
			return mv;
		}
		else {
			accountExample.createCriteria().andUsernameEqualTo(account.getUsername());
			List<Account> list =  accountMapper.selectByExample(accountExample);
			if(list.size() > 0) {							// Neu username da ton tai thi ko duoc tao
				mv = new ModelAndView("user/register");
				mv.addObject("er", "Tên đăng nhập đã tồn tại, vui lòng đổi tên đăng nhập khác");
				return mv;
			}
			else {
				if(account.getPassword().equals(confirm)) {				// So sánh confirm password vs password
					account.setRole("ROLE_CUSTOMER");
					account.setEnable(true);
					account.setPassword(BCrypt.hashpw(confirm, BCrypt.gensalt()));
					accountMapper.insert(account);
					Account acc = accountMapper.selectNewAccount();
					cartMapper.insert(acc.getId());
					
					mv = new ModelAndView("user/login");
					mv.addObject("ok", "Tạo tài khoản mới thành công");
					return mv;
				}
				else {
					mv = new ModelAndView("user/register");
					mv.addObject("er", "Xác nhận mật khẩu không đúng");
					return mv;
				}
			}
		}
	}
	
	// ------------------------------------------------------------------------------------- Forgot password
	// Quên mật khẩu:
	// B1: Nhập username + email (Đã đăng kí vào account) để gửi mã OTP xác minh qua email
	// B2: Nhận dc OTP thì nhập vào để xác nhận đổi mật khẩu
	
	@GetMapping("/forgotPass/email")								// Nhập đúng email đã đăng kí trong account	
	public ModelAndView email() {
		ModelAndView mv = new ModelAndView("user/email");
		return mv;
	}
	
	@GetMapping("/forgotPass/confirmOTP")							// Xác nhận danh tính bằng OTP
	public ModelAndView confirmOTP() {
		ModelAndView mv = new ModelAndView("user/confirmOTP");
		return mv;
	}
	
	@GetMapping("/forgotPass/getNewPass")							// Đổi lại mật khẩu mới
	public ModelAndView getNewPass() {
		ModelAndView mv = new ModelAndView("user/getNewPass");
		return mv;
	}
	
	// Hàm gửi qua mail
	public void sendMail(String from, String to, String subject, String content) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);
		
		mailSender.send(mailMessage);
	}
	
	@PostMapping("/sendEmail")
	public ModelAndView sendEmail(@ModelAttribute(name="username") String username, @ModelAttribute(name="email") String email, Model model) {
		ModelAndView mv;
		AccountExample example = new AccountExample();
		example.createCriteria().andUsernameEqualTo(username);			// Lấy account có username = input
		List<Account> list = accountMapper.selectByExample(example);
		if(list.size() > 0) {											// Check xem account có tồn tại ko
			Account acc = list.get(0);
			if(acc.getEmail().equals(email)) {							// Nếu có thì check tiếp xem email của account này có giống vs input ko
				currentUsername = acc.getUsername();					// Lưu username hiện tại lại
				Random rand = new Random();
				OTPCode = rand.nextInt(100000) + 100000;				// Random mã OTP 6 chữ số
				sendMail("Juliicasta", email, "Juliicasta", "Day la ma OTP de xac minh danh tinh cua ban\nVui long khong chia se ma nay voi nguoi khac\nMa OTP cua ban la: " + OTPCode);
				mv = new ModelAndView("user/confirmOTP");
				return mv;
			}
			else {
				mv = new ModelAndView("user/email");
				mv.addObject("er", "Email này chưa được đăng kí để xác thực");
				return mv;
			}
		}
		else {
			mv = new ModelAndView("user/email");
			mv.addObject("er", "Tên đăng nhập không tồn tại");
			return mv;
		}
	}
	
	@PostMapping("/confirmOTP")
	public ModelAndView confirmOTP(@ModelAttribute(name="otp") String otp, Model model) {
		ModelAndView mv;
		if(OTPCode == Integer.parseInt(otp)) {				// So sánh mã OTP dc gửi qua Email có trùng vs OTP input ko
			OTPCode = 0;									// Nếu đúng thì set OTPCode = 0 rồi trả về page getNewPass
			mv = new ModelAndView("user/getNewPass");
			return mv;
		}
		else {
			mv = new ModelAndView("user/confirmOTP");
			mv.addObject("er", "Mã OTP không chính xác");
			return mv;
		}
	}
	
	@PostMapping("/getNewPassword")
	public ModelAndView getNewPassword(@ModelAttribute(name="newPass") String newPass, @ModelAttribute(name="confirmPass") String confirmPass, Model model) {
		ModelAndView mv;
		if(newPass.equals(confirmPass)) {								// So sánh password mới vs confirm password dc nhập từ input
			Map<String, Object> param = new HashMap<>();
			param.put("username", currentUsername);						// Update username đã được lưu lại
			param.put("password", BCrypt.hashpw(confirmPass, BCrypt.gensalt()));
			accountMapper.changePassword(param);
			currentUsername = "";										// Sau khi đổi mật khẩu xong thì gán currentUsername thành rỗng rồi trả về login
			mv = new ModelAndView("user/login");
			return mv;
		}
		else {
			mv = new ModelAndView("user/getNewPassword");
			mv.addObject("er", "Xác nhận mật khẩu không khớp");
			return mv;
		}
	}
	
	// ------------------------------------------------------------------------------------- Comment
	// 1 Sản phẩm chỉ có 5 comment, nếu tổng số comment > 5 thì xóa bớt những comment cũ
	
	@PostMapping("/comment")
	public RedirectView comment(@ModelAttribute(name = "commentForm") Comment com, RedirectAttributes redir,
			HttpServletRequest request) {
		Date currentDate = new Date();
		int accID = com.getAccountid();
		int proID = com.getProductid();
		String content = com.getContent();
		
		commentMapper.insert(accID, proID, content, currentDate);
			
		List<Comment> listComment = commentMapper.selectAllByProductID(proID);
			
		if(listComment.size()>5) {
			for(int i=0; i<=listComment.size(); i++) {
				commentMapper.deleteByPrimaryKey(listComment.get(0).getId());
			}
		}
			
		RedirectView redirectView;
		String url = String.valueOf(request.getSession().getAttribute("url"));
		redirectView = new RedirectView(url, true);
		return redirectView;
	}
	
	// ------------------------------------------------------------------------------------- Profile page
	@GetMapping("/infor")
	public ModelAndView infor(HttpServletRequest request) {
		if(request.getSession().getAttribute("user")==null) {
			ModelAndView mv = new ModelAndView("user/login");
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView("user/infor");
			
			int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
			
			List<Bill> listBill = billMapper.selectListBillForAccount(userId);
			
			CategoryExample categoryExample = new CategoryExample();
			
			List<Category> listCate = categoryMapper.selectByExample(categoryExample);		
			
			mv.addObject("listBill", listBill);
			mv.addObject("listCate", listCate);
			return mv;
		}
	}
	
	// ------------------------------------------------------------------------------------- Change password
	// Đổi password:
	// B1: Nhận OTP qua email đã đăng kí vào account
	// B2: Nhập OTP + mật khẩu mới + xác nhận mật khẩu mới
	
	@ResponseBody
	@RequestMapping(value = { "/sendEmailToChangePassword" }, method = RequestMethod.GET)
	public void sendEmailToChangePassword(HttpServletRequest request) throws IOException {
		int accId = Integer.parseInt(request.getParameter("id"));
		
		Account acc = accountMapper.selectByPrimaryKey(accId);
		
		System.out.println(acc.getEmail());
		
		Random rand = new Random();
		OTPCode = rand.nextInt(100000) + 100000;				// Random mã OTP 6 chữ số
		sendMail("Juliicasta", acc.getEmail(), "Juliicasta", "Day la ma OTP de xac minh danh tinh cua ban\nVui long khong chia se ma nay voi nguoi khac\nMa OTP cua ban la: " + OTPCode);
	}
	
	@ResponseBody
	@PostMapping("/changePassword")
	public RedirectView changePassword(HttpServletRequest request, RedirectAttributes redir) {
		RedirectView redirectView;
		redirectView = new RedirectView("infor", true);
		int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
		
		Account acc = accountMapper.selectByPrimaryKey(userId);
		
		List<Bill> listBill = billMapper.selectListBillForAccount(userId);
		
		CategoryExample categoryExample = new CategoryExample();
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		
		int OTP = Integer.parseInt(request.getParameter("OTP"));
		String newPass = request.getParameter("newPass");
		String confirmPass = request.getParameter("confirmPass");
		if(OTPCode == OTP) {
			OTPCode = 0;
			if(BCrypt.checkpw(newPass, acc.getPassword())) {
				redir.addFlashAttribute("er", "Bạn đã nhập mật khẩu cũ");
			}
			else {
				if(newPass.equals(confirmPass)) {
					Map<String, Object> param = new HashMap<>();
					param.put("username", acc.getUsername());
					param.put("password", BCrypt.hashpw(confirmPass, BCrypt.gensalt()));
					accountMapper.changePassword(param);
					redir.addFlashAttribute("ok", "Đổi mật khẩu thành công");
				}
				else {
					redir.addFlashAttribute("er", "Xác nhận mật khẩu mới không đúng");
				}
			}
		}
		else {
			redir.addFlashAttribute("er", "Mã OTP không khớp");
		}
		return redirectView;
	}
	
	// ------------------------------------------------------------------------------------- Edit Information
	// Thay đổi thông tin:
	// B1: Nhập mật khẩu để xác nhận chính chủ
	// B2: Nhập thông tin muốn thay đổi
	// B3: Nếu thay đổi email thì gửi OTP qua email mới
	// B4: Oke hết thì update account
	
	
	// Nhập mật khẩu xác nhận
	@PostMapping("/loginToEditInfor")
	public RedirectView loginToEditInfor(HttpServletRequest request, RedirectAttributes redir) {
		RedirectView redirectView;
		
		int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
		String pass = request.getParameter("password");
		Account acc = accountMapper.selectByPrimaryKey(userId);
		
		if(BCrypt.checkpw(pass, acc.getPassword())){
			request.getSession().setAttribute("already", true);
			redirectView = new RedirectView("editInformation", true);
		}
		else {
			redir.addFlashAttribute("er", "Mật khẩu không đúng");
			redirectView = new RedirectView("infor", true);
		}
		return redirectView;
	}
	
	// Gửi OTP sang email mới
	@RequestMapping(value = { "/sendEmailToEditInfor" }, method = RequestMethod.GET)
	public RedirectView sendEmailToEditInfor(HttpServletRequest request, RedirectAttributes redir){
		RedirectView redirectView;
		String email = request.getParameter("email");
		int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
		Account acc = accountMapper.selectByPrimaryKey(userId);
		
		if(email.equals(acc.getEmail())) {
			redir.addFlashAttribute("er", "Bạn đã nhập lại email cũ, vui lòng nhập email mới");
			redirectView = new RedirectView("editInformation", true);
		}
		else {
			int count = accountMapper.countByEmail(email);
			if(count>3) {
				redir.addFlashAttribute("er", "Email này đã hết lượt tạo tài khoản");
				redirectView = new RedirectView("editInformation", true);
			}
			else {
				Random rand = new Random();
				OTPCode = rand.nextInt(100000) + 100000;				// Random mã OTP 6 chữ số
				sendMail("Juliicasta", email, "Juliicasta", "Day la ma OTP de xac minh danh tinh cua ban\nVui long khong chia se ma nay voi nguoi khac\nMa OTP cua ban la: " + OTPCode);
				redirectView = new RedirectView("editInformation", true);
			}
		}
		return redirectView;
	}
	
	@RequestMapping("/editInformation")
	public ModelAndView editInformation(HttpServletRequest request) {
		if(request.getSession().getAttribute("already")==null) {
			ModelAndView mv = new ModelAndView("user/infor");
			
			int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
			
			List<Bill> listBill = billMapper.selectListBillForAccount(userId);
			
			CategoryExample categoryExample = new CategoryExample();
			
			List<Category> listCate = categoryMapper.selectByExample(categoryExample);		
			
			mv.addObject("listBill", listBill);
			mv.addObject("listCate", listCate);
			return mv;
		}
		else {
			request.getSession().removeAttribute("already");
			ModelAndView mv = new ModelAndView("user/editInformation");
			
			int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
			Account acc = accountMapper.selectByPrimaryKey(userId);
			
			CategoryExample categoryExample = new CategoryExample();
			List<Category> listCate = categoryMapper.selectByExample(categoryExample);
			
			mv.addObject("user", acc);
			mv.addObject("listCate", listCate);
			return mv;
		}	
	}
	
	@RequestMapping(value = { "/updateInfor" }, method = RequestMethod.POST)
	public RedirectView updateInfor(HttpServletRequest request, RedirectAttributes redir) {
		RedirectView redirectView;
		int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userId")));
		
		String regexPhone="^(03|05|07|08|09)+[0-9]{8}$";											// Tạo 1 biến phone type
		boolean check = false;																		// Biến check phone đúng hay sai
		
		String fullname = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Pattern pattern = Pattern.compile(regexPhone);
		Matcher matcher = pattern.matcher(phone);
		check = matcher.find();
		
		if(fullname.length()==0 || phone.length()==0 || address.length()==0) {
			redir.addFlashAttribute("er", "Thông tin đặt hàng không được để trống");
		}
		else if(check==false) {
			redir.addFlashAttribute("er", "Số điện thoại không hợp lệ");
		}
		else {
			accountMapper.updateByPrimaryKey(fullname, phone, address, userId);
			redir.addFlashAttribute("ok", "Chỉnh sửa thành công");
		}	
		
		redirectView = new RedirectView("editInformation", true);
		return redirectView;
	}
	
	// ------------------------------------------------------------------------------------- Send contact us
	@GetMapping("/contact")
	public ModelAndView contact(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/contact");
		
		request.getSession().setAttribute("url", "contact");
		
		CategoryExample categoryExample = new CategoryExample();
		
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		
		mv.addObject("listCate", listCate);
		return mv;
	}
	
	@PostMapping("/sendContact")
	public RedirectView sendContact(@ModelAttribute(name = "contactForm") Contact contact, RedirectAttributes redir,
			HttpServletRequest request) {
		RedirectView redirectView;
		
		String fullname = contact.getFullname();
		String phone = contact.getPhone();
		String type = contact.getType();
		String content = contact.getContent();
		boolean status = contact.getStatus();
		
		String regexPhone="^(03|05|07|08|09)+[0-9]{8}$";											// Tạo 1 biến phone type
		boolean check = false;
		Pattern pattern = Pattern.compile(regexPhone);
		Matcher matcher = pattern.matcher(phone);
		check = matcher.find();
		
		if(check==false) {
			redir.addFlashAttribute("er", "Số điện thoại không hợp lệ");
		}
		else {
			contactMapper.insert(contact);
			redir.addFlashAttribute("ok", "Gửi thành công");
		}

		redirectView = new RedirectView("contact", true);
		return redirectView;
	}
}
