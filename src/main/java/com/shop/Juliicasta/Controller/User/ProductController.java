package com.shop.Juliicasta.Controller.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioFormat.Encoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shop.Juliicasta.Mapper.AccountMapper;
import com.shop.Juliicasta.Mapper.CategoryMapper;
import com.shop.Juliicasta.Mapper.ColorMapper;
import com.shop.Juliicasta.Mapper.CommentMapper;
import com.shop.Juliicasta.Mapper.ImageMapper;
import com.shop.Juliicasta.Mapper.ProductDetailMapper;
import com.shop.Juliicasta.Mapper.ProductMapper;
import com.shop.Juliicasta.Mapper.SizeMapper;
import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.Category;
import com.shop.Juliicasta.Model.CategoryExample;
import com.shop.Juliicasta.Model.Color;
import com.shop.Juliicasta.Model.Comment;
import com.shop.Juliicasta.Model.Image;
import com.shop.Juliicasta.Model.Product;
import com.shop.Juliicasta.Model.ProductDetail;
import com.shop.Juliicasta.Model.ProductExample;
import com.shop.Juliicasta.Model.Size;
import com.shop.Juliicasta.Model.Slide;
import com.shop.Juliicasta.Model.SlideExample;

@Controller
@RequestMapping("/user")
public class ProductController {
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	ProductMapper productMapper;

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	ProductDetailMapper productDetailMapper;
	
	@Autowired
	ImageMapper imageMapper;
	
	@Autowired
	SizeMapper sizeMapper;
	
	@Autowired
	ColorMapper colorMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	// Hàm kiểm tra mảng đã có size này chưa 
	public boolean checkSizeExist(ArrayList<Size> list, Size item) {
		for(int i=0; i<list.size(); i++) {
			if(item.getName().equals(list.get(i).getName())) {
				return false;
			}
		}
		return true;
	}
	
	// Hàm kiểm tra mảng đã có color này chưa 
		public boolean checkColorExist(ArrayList<Color> list, Color item) {
			for(int i=0; i<list.size(); i++) {
				if(item.getName().equals(list.get(i).getName())) {
					return false;
				}
			}
			return true;
		}
	
	// Hàm kiểm tra list đã có sản phẩm x chưa, nếu có rồi thì bỏ x ra khỏi list
	public List<Product> checkProductExist(List<Product> list, Product pro) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getId()==pro.getId()) {
				list.remove(i);
				return list;
			}
		}
		return list;
	}

	// Lấy danh sách sản phẩm tương ứng với yêu cầu của khách hàng
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public ModelAndView listproduct(@RequestParam("idCate") int idCate, @RequestParam("filter") String filter,
			@RequestParam("page") int page, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/listproduct");

		request.getSession().setAttribute("search", 0); // Tắt search đi
		
		request.getSession().setAttribute("url", "product?idCate=0&filter=0&page=1");

		request.getSession().setAttribute("idCate", idCate); // Lấy idCate hiện tại
		request.getSession().setAttribute("filter", filter); // Lấy kiểu lọc hiện tại

		String cate = request.getSession().getAttribute("idCate").toString(); // Ép thành String
		String fil = request.getSession().getAttribute("filter").toString();

		int idCategory = Integer.parseInt(cate); // Ép idCate thành Integer

		CategoryExample categoryExample = new CategoryExample();
		ProductExample productExample = new ProductExample();
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		List<Product> listProduct;

		Category category = categoryMapper.selectById(idCategory);

		int totalPage, restProduct, totalProduct, start;
		int pre, next;
		pre = 1;

		if (idCate == 0) {
			mv.addObject("cate", "Sản phẩm");
			if (fil.equals("0")) {
				if (request.getParameter("page") != null) {
					start = (page - 1) * 16;

					listProduct = productMapper.selectAllProduct(start);

					totalProduct = (int) productMapper.countByExample(productExample);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("listCate", listCate);
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					mv.addObject("listProduct", listProduct);
					return mv;
				} else {
					listProduct = productMapper.selectAllProduct(0);
					totalProduct = (int) productMapper.countByExample(productExample);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("listCate", listCate);
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					mv.addObject("listProduct", listProduct);
					return mv;
				}
			} else {
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					start = (page - 1) * 16;

					totalProduct = (int) productMapper.countByExample(productExample);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					switch (fil) {
					case "Inc": // Trả về danh sách sản phẩm với giá tăng dần
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectPriceInc(start);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "Dec": // Trả về danh sách sản phẩm với giá giảm dần
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectPriceDec(start);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "NO": // Trả về danh sách sản phẩm với sản phẩm mới đến cũ
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectDateNew(start);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "ON": // Trả về danh sách sản phẩm với sản phẩm cũ đến mới
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectDateOld(start);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					return mv;
				} else {
					totalProduct = (int) productMapper.countByExample(productExample);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					switch (fil) {
					case "Inc": // Trả về danh sách sản phẩm với giá tăng dần
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectPriceInc(0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "Dec": // Trả về danh sách sản phẩm với giá giảm dần
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectPriceDec(0);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "NO": // Trả về danh sách sản phẩm với sản phẩm mới đến cũ
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectDateNew(0);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "ON": // Trả về danh sách sản phẩm với sản phẩm cũ đến mới
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectDateOld(0);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					return mv;
				}
			}
		} else {
			mv.addObject("cate", "Sản phẩm - " + category.getName());
			if (fil.equals("0")) {
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					start = (page - 1) * 16;

					listProduct = productMapper.selectAllProductByCate(idCate, start);
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
					}

					totalProduct = productMapper.countByCate(idCate);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					mv.addObject("listCate", listCate);
					mv.addObject("listProduct", listProduct);
					return mv;
				} else {
					listProduct = productMapper.selectAllProductByCate(idCate, 0);
					totalProduct = (int) productMapper.countByCate(idCate);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
					}
					
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					mv.addObject("listCate", listCate);
					mv.addObject("listProduct", listProduct);
					return mv;
				}
			} else {
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					start = (page - 1) * 16;

					totalProduct = (int) productMapper.countByCate(idCate);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					switch (fil) {
					case "Inc": // Trả về danh sách sản phẩm với giá tăng dần
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByPriceInc(idCate, start);
						mv.addObject("listCate", listCate);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listProduct", listProduct);
						break;
					case "Dec": // Trả về danh sách sản phẩm với giá giảm dần
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByPriceDec(idCate, start);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "NO": // Trả về danh sách sản phẩm với sản phẩm mới đến cũ
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByNew(idCate, start);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "ON": // Trả về danh sách sản phẩm với sản phẩm cũ đến mới
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByOld(idCate, start);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					return mv;
				} else {
					totalProduct = (int) productMapper.countByCate(idCate);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					switch (fil) {
					case "Inc": // Trả về danh sách sản phẩm với giá tăng dần
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByPriceInc(idCate, 0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "Dec": // Trả về danh sách sản phẩm với giá giảm dần
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByPriceDec(idCate, 0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "NO": // Trả về danh sách sản phẩm với sản phẩm mới đến cũ
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByNew(idCate, 0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "ON": // Trả về danh sách sản phẩm với sản phẩm cũ đến mới
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByOld(idCate, 0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					return mv;
				}
			}
		}
	}

	// Tìm kiếm sản phẩm - Tìm kiếm sản phẩm sẽ không bao gồm lọc
	@RequestMapping(value = { "product/search" }, method = RequestMethod.GET)
	public ModelAndView search(@RequestParam("searchName") String searchName, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/listproduct");
		CategoryExample categoryExample = new CategoryExample();

		request.getSession().setAttribute("search", 1);
		request.getSession().setAttribute("keyword", searchName);

		int idCate = Integer.parseInt(request.getSession().getAttribute("idCate").toString());

		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		List<Product> listProduct;
		int totalPage, restProduct, totalProduct, start;
		int pre, next, page;
		pre = 1;
		page = 1;

		String search = "%" + searchName + "%"; // Ép keyword thành '%key%'

		if (idCate == 0) { // Nếu chưa chọn loại sản phẩm thì tìm kiếm trên tất cả sản phẩm
			mv.addObject("cate", "Sản phẩm");
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				start = (page - 1) * 16;

				listProduct = productMapper.selectByName(search, start);
				
				if(listProduct.size()==0) {
					mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
				}

				totalProduct = (int) productMapper.countByName(search);
				totalPage = totalProduct / 16;
				restProduct = totalProduct - totalPage * 16;
				if (restProduct > 0) {
					totalPage += 1;
				}
				if (page > 1) {
					pre = page - 1;
				}
				next = totalPage;
				if (page < totalPage) {
					next = page + 1;
				}
				mv.addObject("listCate", listCate);
				mv.addObject("totalpage", totalPage);
				mv.addObject("page", page);
				mv.addObject("pre", pre);
				mv.addObject("next", next);
				mv.addObject("listProduct", listProduct);
				return mv;
			} else {
				listProduct = productMapper.selectByName(search, 0);
				totalProduct = (int) productMapper.countByName(search);
				totalPage = totalProduct / 16;
				restProduct = totalProduct - totalPage * 16;
				if (restProduct > 0) {
					totalPage += 1;
				}
				if (page > 1) {
					pre = page - 1;
				}
				next = totalPage;
				if (page < totalPage) {
					next = page + 1;
				}
				
				if(listProduct.size()==0) {
					mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
				}
				
				mv.addObject("listCate", listCate);
				mv.addObject("totalpage", totalPage);
				mv.addObject("page", page);
				mv.addObject("pre", pre);
				mv.addObject("next", next);
				mv.addObject("listProduct", listProduct);
				return mv;
			}
		} else { // Nếu đã chọn loại sản phẩm thì tìm kiếm các sản phẩm trong loại đó
			Category category = categoryMapper.selectById(idCate);
			mv.addObject("cate", "Sản phẩm - " + category.getName());
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				start = (page - 1) * 16;

				listProduct = productMapper.selectByCateAndName(idCate, search, start);
				
				if(listProduct.size()==0) {
					mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
				}

				totalProduct = (int) productMapper.countByCateAndName(idCate, search);
				totalPage = totalProduct / 16;
				restProduct = totalProduct - totalPage * 16;
				if (restProduct > 0) {
					totalPage += 1;
				}
				if (page > 1) {
					pre = page - 1;
				}
				next = totalPage;
				if (page < totalPage) {
					next = page + 1;
				}
				mv.addObject("listCate", listCate);
				mv.addObject("totalpage", totalPage);
				mv.addObject("page", page);
				mv.addObject("pre", pre);
				mv.addObject("next", next);
				mv.addObject("listProduct", listProduct);
				return mv;
			} else {
				listProduct = productMapper.selectByCateAndName(idCate, search, 0);
				totalProduct = (int) productMapper.countByCateAndName(idCate, search);
				totalPage = totalProduct / 16;
				restProduct = totalProduct - totalPage * 16;
				if (restProduct > 0) {
					totalPage += 1;
				}
				if (page > 1) {
					pre = page - 1;
				}
				next = totalPage;
				if (page < totalPage) {
					next = page + 1;
				}
				
				if(listProduct.size()==0) {
					mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
				}
				
				mv.addObject("listCate", listCate);
				mv.addObject("totalpage", totalPage);
				mv.addObject("page", page);
				mv.addObject("pre", pre);
				mv.addObject("next", next);
				mv.addObject("listProduct", listProduct);
				return mv;
			}
		}
	}
	
	// Lọc theo giá cả
		@RequestMapping(value = { "product/price" }, method = RequestMethod.GET)
		public ModelAndView price(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView("user/listproduct");
			CategoryExample categoryExample = new CategoryExample();
			ProductExample productExample = new ProductExample();
			
			request.getSession().setAttribute("search", 2);

			int idCate = Integer.parseInt(request.getSession().getAttribute("idCate").toString());
			int x = Integer.parseInt(request.getParameter("x"));
			int y = Integer.parseInt(request.getParameter("y"));
			mv.addObject("x", x);
			mv.addObject("y", y);

			List<Category> listCate = categoryMapper.selectByExample(categoryExample);
			List<Product> listProduct;
			int totalPage, restProduct, totalProduct, start;
			int pre, next, page;
			pre = 1;
			page = 1;
			
			if (idCate == 0) {
				mv.addObject("cate", "Sản phẩm");
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					start = (page - 1) * 16;

					listProduct = productMapper.selectByPrice(x, y, start);
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
					}

					totalProduct = productMapper.countByPrice(x, y);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("listCate", listCate);
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					mv.addObject("listProduct", listProduct);
					return mv;
				}
				else {
					listProduct = productMapper.selectByPrice(x, y, 0);
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
					}

					totalProduct = productMapper.countByPrice(x, y);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("listCate", listCate);
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					mv.addObject("listProduct", listProduct);
					return mv;
				}
			}
			else {
				Category category = categoryMapper.selectById(idCate);
				mv.addObject("cate", "Sản phẩm - " + category.getName());
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					start = (page - 1) * 16;

					listProduct = productMapper.selectByCateAndPrice(idCate, x, y, start);
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
					}

					totalProduct = productMapper.countByCateAndPrice(idCate, x, y);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("listCate", listCate);
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					mv.addObject("listProduct", listProduct);
					return mv;
				}
				else {
					listProduct = productMapper.selectByCateAndPrice(idCate, x, y, 0);
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hiện không có sản phẩm bạn cần tìm");
					}

					totalProduct = productMapper.countByCateAndPrice(idCate, x, y);
					totalPage = totalProduct / 16;
					restProduct = totalProduct - totalPage * 16;
					if (restProduct > 0) {
						totalPage += 1;
					}
					if (page > 1) {
						pre = page - 1;
					}
					next = totalPage;
					if (page < totalPage) {
						next = page + 1;
					}
					mv.addObject("listCate", listCate);
					mv.addObject("totalpage", totalPage);
					mv.addObject("page", page);
					mv.addObject("pre", pre);
					mv.addObject("next", next);
					mv.addObject("listProduct", listProduct);
					return mv;
				}
			}
		}

	
	// Xem chi tiết của 1 sản phẩm
	@RequestMapping(value = { "product/detail/{id}" }, method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable("id") int id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/productDetail");
		
		request.getSession().setAttribute("url", "product/detail/"+id);
		
		CategoryExample categoryExample = new CategoryExample();
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		ArrayList<Size> allSize = new ArrayList<Size>();
		ArrayList<Size> listSize = new ArrayList<Size>();
		ArrayList<Product> listPro = new ArrayList<Product>();
		
		ArrayList<Color> allColor = new ArrayList<Color>();
		ArrayList<Color> listColor = new ArrayList<Color>();
		
		// Lấy id của sản phẩm hiện tại
		Product pro = productMapper.selectByPrimaryKey(id);
		
		// Lấy detail của sản phẩm đó ra
		List<ProductDetail> proDetail = productDetailMapper.selectByProductID(id);
		List<Image> img = imageMapper.selectByProductID(id);
		List<Product> listProduct;
		
		Category cate = categoryMapper.selectById(pro.getCategoryid());
		
		// lấy danh sách sản phẩm tương tự
		listProduct = productMapper.selectAllProductByCateForProductDetail(pro.getCategoryid());
		
		// Lấy danh sách comment liên quan đến sản phẩm
		List<Comment> listComment = commentMapper.selectByProductID(pro.getId(), 0);
		
		if(listComment.size()>0) {
			for(int i=0; i<listComment.size(); i++) {
				Account account = accountMapper.selectByPrimaryKey(listComment.get(i).getAccountid());
				mv.addObject("account", account.getUsername());
			}
			mv.addObject("listComment", listComment);
		}
		
		// Nếu sản phẩm này có Size thì trả về 1 danh sách Size của sản phẩm
		if(pro.getHavesize()) {
			for(int i=0; i<proDetail.size(); i++) {
				allSize.add(sizeMapper.selectByID(proDetail.get(i).getSizeid()));
			}
			for(int i=0; i<allSize.size(); i++) {
				if(checkSizeExist(listSize, allSize.get(i))) {
					listSize.add(allSize.get(i));
				}
			}
			mv.addObject("listSize", listSize);
		}
		
		// Nếu sản phẩm này có Color thì trả về 1 danh sách Color của sản phẩm
		if(pro.getHavecolor()) {
			for(int i=0; i<proDetail.size(); i++) {
				allColor.add(colorMapper.selectByID(proDetail.get(i).getColorid()));
			}
			for(int i=0; i<allColor.size(); i++) {
				if(checkColorExist(listColor, allColor.get(i))) {
					listColor.add(allColor.get(i));
				}
			}
			Color color = listColor.get(0);						// Lấy color mặc định
			mv.addObject("defaultColor", color.getCode());
			mv.addObject("listColor", listColor);
		}
		
		// Dùng hàm checkProductExist để sản phẩm tương tự "KHÔNG BỊ TRÙNG" với sản phẩm đang xem
		if(checkProductExist(listProduct, pro).size()>0) {
			mv.addObject("listProduct", checkProductExist(listProduct, pro));
		}
		
		if(request.getSession().getAttribute("user")==null) {
			mv.addObject("please", "Vui lòng đăng nhập để sử dụng tính năng này");
		}
		
		mv.addObject("num", 0);
		mv.addObject("cate", cate);
		mv.addObject("pro", pro);
		mv.addObject("price", String.format("%,.0f",(float)pro.getPrice()));
		mv.addObject("listDetail", proDetail);
		mv.addObject("listImage", img);
		mv.addObject("listCate", listCate);
		return mv;
	}
	
	// Đổi hình ảnh
	@RequestMapping(value = { "product/changeImage" }, method = RequestMethod.GET)
	public void ChangeImageProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idImg = Integer.parseInt(request.getParameter("imgItem"));
		Image img = imageMapper.selectByPrimaryKey(idImg);
		PrintWriter out = response.getWriter();
	
		out.println("<img src=\""+request.getContextPath()+"/Images/Products/"+img.getName()+"\" alt=\"product\"/>");
	}
	
	// Đổi hình ảnh
	@RequestMapping(value = { "product/changeMainImage" }, method = RequestMethod.GET)
	public void changeMainImageProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idPro = Integer.parseInt(request.getParameter("imgItem"));
		Product pro = productMapper.selectByPrimaryKey(idPro);
		PrintWriter out = response.getWriter();
	
		out.println("<img src=\""+request.getContextPath()+"/Images/Products/"+pro.getMainimage()+"\" alt=\"product\"/>");
	}
	
	// Đổi màu
	@RequestMapping(value = { "product/chooseColor" }, method = RequestMethod.GET)
	public void chooseColor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int colorId = Integer.parseInt(request.getParameter("colorId"));
		Color color = colorMapper.selectByID(colorId);
		PrintWriter out = response.getWriter();
		
		out.println("<div class='selected-color' style='background-color: "+color.getCode()+";'></div>");
	}
}
