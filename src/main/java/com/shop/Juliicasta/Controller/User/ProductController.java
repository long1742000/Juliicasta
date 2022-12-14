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
	
	// H??m ki???m tra m???ng ???? c?? size n??y ch??a 
	public boolean checkSizeExist(ArrayList<Size> list, Size item) {
		for(int i=0; i<list.size(); i++) {
			if(item.getName().equals(list.get(i).getName())) {
				return false;
			}
		}
		return true;
	}
	
	// H??m ki???m tra m???ng ???? c?? color n??y ch??a 
		public boolean checkColorExist(ArrayList<Color> list, Color item) {
			for(int i=0; i<list.size(); i++) {
				if(item.getName().equals(list.get(i).getName())) {
					return false;
				}
			}
			return true;
		}
	
	// H??m ki???m tra list ???? c?? s???n ph???m x ch??a, n???u c?? r???i th?? b??? x ra kh???i list
	public List<Product> checkProductExist(List<Product> list, Product pro) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getId()==pro.getId()) {
				list.remove(i);
				return list;
			}
		}
		return list;
	}

	// L???y danh s??ch s???n ph???m t????ng ???ng v???i y??u c???u c???a kh??ch h??ng
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public ModelAndView listproduct(@RequestParam("idCate") int idCate, @RequestParam("filter") String filter,
			@RequestParam("page") int page, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/listproduct");

		request.getSession().setAttribute("search", 0); // T???t search ??i
		
		request.getSession().setAttribute("url", "product?idCate=0&filter=0&page=1");

		request.getSession().setAttribute("idCate", idCate); // L???y idCate hi???n t???i
		request.getSession().setAttribute("filter", filter); // L???y ki???u l???c hi???n t???i

		String cate = request.getSession().getAttribute("idCate").toString(); // ??p th??nh String
		String fil = request.getSession().getAttribute("filter").toString();

		int idCategory = Integer.parseInt(cate); // ??p idCate th??nh Integer

		CategoryExample categoryExample = new CategoryExample();
		ProductExample productExample = new ProductExample();
		List<Category> listCate = categoryMapper.selectByExample(categoryExample);
		List<Product> listProduct;

		Category category = categoryMapper.selectById(idCategory);

		int totalPage, restProduct, totalProduct, start;
		int pre, next;
		pre = 1;

		if (idCate == 0) {
			mv.addObject("cate", "S???n ph???m");
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
					case "Inc": // Tr??? v??? danh s??ch s???n ph???m v???i gi?? t??ng d???n
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectPriceInc(start);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "Dec": // Tr??? v??? danh s??ch s???n ph???m v???i gi?? gi???m d???n
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectPriceDec(start);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "NO": // Tr??? v??? danh s??ch s???n ph???m v???i s???n ph???m m???i ?????n c??
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectDateNew(start);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "ON": // Tr??? v??? danh s??ch s???n ph???m v???i s???n ph???m c?? ?????n m???i
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectDateOld(start);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
					case "Inc": // Tr??? v??? danh s??ch s???n ph???m v???i gi?? t??ng d???n
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectPriceInc(0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "Dec": // Tr??? v??? danh s??ch s???n ph???m v???i gi?? gi???m d???n
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectPriceDec(0);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "NO": // Tr??? v??? danh s??ch s???n ph???m v???i s???n ph???m m???i ?????n c??
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectDateNew(0);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "ON": // Tr??? v??? danh s??ch s???n ph???m v???i s???n ph???m c?? ?????n m???i
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectDateOld(0);

						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
			mv.addObject("cate", "S???n ph???m - " + category.getName());
			if (fil.equals("0")) {
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					start = (page - 1) * 16;

					listProduct = productMapper.selectAllProductByCate(idCate, start);
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
						mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
					case "Inc": // Tr??? v??? danh s??ch s???n ph???m v???i gi?? t??ng d???n
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByPriceInc(idCate, start);
						mv.addObject("listCate", listCate);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}
						
						mv.addObject("listProduct", listProduct);
						break;
					case "Dec": // Tr??? v??? danh s??ch s???n ph???m v???i gi?? gi???m d???n
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByPriceDec(idCate, start);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "NO": // Tr??? v??? danh s??ch s???n ph???m v???i s???n ph???m m???i ?????n c??
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByNew(idCate, start);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "ON": // Tr??? v??? danh s??ch s???n ph???m v???i s???n ph???m c?? ?????n m???i
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByOld(idCate, start);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
					case "Inc": // Tr??? v??? danh s??ch s???n ph???m v???i gi?? t??ng d???n
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByPriceInc(idCate, 0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}
						
						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "Dec": // Tr??? v??? danh s??ch s???n ph???m v???i gi?? gi???m d???n
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByPriceDec(idCate, 0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "NO": // Tr??? v??? danh s??ch s???n ph???m v???i s???n ph???m m???i ?????n c??
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByNew(idCate, 0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
						}

						mv.addObject("listCate", listCate);
						mv.addObject("listProduct", listProduct);
						break;
					case "ON": // Tr??? v??? danh s??ch s???n ph???m v???i s???n ph???m c?? ?????n m???i
						listCate = categoryMapper.selectByExample(categoryExample);
						listProduct = productMapper.selectByCateAndSortByOld(idCate, 0);
						
						if(listProduct.size()==0) {
							mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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

	// T??m ki???m s???n ph???m - T??m ki???m s???n ph???m s??? kh??ng bao g???m l???c
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

		String search = "%" + searchName + "%"; // ??p keyword th??nh '%key%'

		if (idCate == 0) { // N???u ch??a ch???n lo???i s???n ph???m th?? t??m ki???m tr??n t???t c??? s???n ph???m
			mv.addObject("cate", "S???n ph???m");
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				start = (page - 1) * 16;

				listProduct = productMapper.selectByName(search, start);
				
				if(listProduct.size()==0) {
					mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
					mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
				}
				
				mv.addObject("listCate", listCate);
				mv.addObject("totalpage", totalPage);
				mv.addObject("page", page);
				mv.addObject("pre", pre);
				mv.addObject("next", next);
				mv.addObject("listProduct", listProduct);
				return mv;
			}
		} else { // N???u ???? ch???n lo???i s???n ph???m th?? t??m ki???m c??c s???n ph???m trong lo???i ????
			Category category = categoryMapper.selectById(idCate);
			mv.addObject("cate", "S???n ph???m - " + category.getName());
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				start = (page - 1) * 16;

				listProduct = productMapper.selectByCateAndName(idCate, search, start);
				
				if(listProduct.size()==0) {
					mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
					mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
	
	// L???c theo gi?? c???
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
				mv.addObject("cate", "S???n ph???m");
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					start = (page - 1) * 16;

					listProduct = productMapper.selectByPrice(x, y, start);
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
						mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
				mv.addObject("cate", "S???n ph???m - " + category.getName());
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
					start = (page - 1) * 16;

					listProduct = productMapper.selectByCateAndPrice(idCate, x, y, start);
					
					if(listProduct.size()==0) {
						mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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
						mv.addObject("noneProduct", "Hi???n kh??ng c?? s???n ph???m b???n c???n t??m");
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

	
	// Xem chi ti???t c???a 1 s???n ph???m
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
		
		// L???y id c???a s???n ph???m hi???n t???i
		Product pro = productMapper.selectByPrimaryKey(id);
		
		// L???y detail c???a s???n ph???m ???? ra
		List<ProductDetail> proDetail = productDetailMapper.selectByProductID(id);
		List<Image> img = imageMapper.selectByProductID(id);
		List<Product> listProduct;
		
		Category cate = categoryMapper.selectById(pro.getCategoryid());
		
		// l???y danh s??ch s???n ph???m t????ng t???
		listProduct = productMapper.selectAllProductByCateForProductDetail(pro.getCategoryid());
		
		// L???y danh s??ch comment li??n quan ?????n s???n ph???m
		List<Comment> listComment = commentMapper.selectByProductID(pro.getId(), 0);
		
		if(listComment.size()>0) {
			for(int i=0; i<listComment.size(); i++) {
				Account account = accountMapper.selectByPrimaryKey(listComment.get(i).getAccountid());
				mv.addObject("account", account.getUsername());
			}
			mv.addObject("listComment", listComment);
		}
		
		// N???u s???n ph???m n??y c?? Size th?? tr??? v??? 1 danh s??ch Size c???a s???n ph???m
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
		
		// N???u s???n ph???m n??y c?? Color th?? tr??? v??? 1 danh s??ch Color c???a s???n ph???m
		if(pro.getHavecolor()) {
			for(int i=0; i<proDetail.size(); i++) {
				allColor.add(colorMapper.selectByID(proDetail.get(i).getColorid()));
			}
			for(int i=0; i<allColor.size(); i++) {
				if(checkColorExist(listColor, allColor.get(i))) {
					listColor.add(allColor.get(i));
				}
			}
			Color color = listColor.get(0);						// L???y color m???c ?????nh
			mv.addObject("defaultColor", color.getCode());
			mv.addObject("listColor", listColor);
		}
		
		// D??ng h??m checkProductExist ????? s???n ph???m t????ng t??? "KH??NG B??? TR??NG" v???i s???n ph???m ??ang xem
		if(checkProductExist(listProduct, pro).size()>0) {
			mv.addObject("listProduct", checkProductExist(listProduct, pro));
		}
		
		if(request.getSession().getAttribute("user")==null) {
			mv.addObject("please", "Vui l??ng ????ng nh???p ????? s??? d???ng t??nh n??ng n??y");
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
	
	// ?????i h??nh ???nh
	@RequestMapping(value = { "product/changeImage" }, method = RequestMethod.GET)
	public void ChangeImageProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idImg = Integer.parseInt(request.getParameter("imgItem"));
		Image img = imageMapper.selectByPrimaryKey(idImg);
		PrintWriter out = response.getWriter();
	
		out.println("<img src=\""+request.getContextPath()+"/Images/Products/"+img.getName()+"\" alt=\"product\"/>");
	}
	
	// ?????i h??nh ???nh
	@RequestMapping(value = { "product/changeMainImage" }, method = RequestMethod.GET)
	public void changeMainImageProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idPro = Integer.parseInt(request.getParameter("imgItem"));
		Product pro = productMapper.selectByPrimaryKey(idPro);
		PrintWriter out = response.getWriter();
	
		out.println("<img src=\""+request.getContextPath()+"/Images/Products/"+pro.getMainimage()+"\" alt=\"product\"/>");
	}
	
	// ?????i m??u
	@RequestMapping(value = { "product/chooseColor" }, method = RequestMethod.GET)
	public void chooseColor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int colorId = Integer.parseInt(request.getParameter("colorId"));
		Color color = colorMapper.selectByID(colorId);
		PrintWriter out = response.getWriter();
		
		out.println("<div class='selected-color' style='background-color: "+color.getCode()+";'></div>");
	}
}
