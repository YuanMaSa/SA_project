package com.practice.webapp.controller;

import java.util.ArrayList;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.webapp.dao.A_categoryDAO;
import com.practice.webapp.dao.CommentDAO;
import com.practice.webapp.dao.MemberDAO;
import com.practice.webapp.dao.ProductDAO;
import com.practice.webapp.dao.Product_categoryDAO;
import com.practice.webapp.dao.ShoppingDetailDAO;
import com.practice.webapp.entity.A_category;
import com.practice.webapp.entity.Comment;
import com.practice.webapp.entity.Discount;
import com.practice.webapp.entity.DiscountDetail;

import com.practice.webapp.entity.Member;
import com.practice.webapp.entity.Product;
import com.practice.webapp.entity.Product_category;
import com.practice.webapp.entity.ShoppingDetail;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.ui.Model;
@Controller
public class ProductController {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	@RequestMapping(value = "/Product/iPhone", method = RequestMethod.GET)
	public ModelAndView getProductiPhone(String name) {
		ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
		ModelAndView model = new ModelAndView("Product_iPhone");
		// = model.setViewName("Product_iPhone");
		ProductDAO Productdao = (ProductDAO)context.getBean("ProductDAO"); //defined in spring-webapp.xml
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO)context.getBean("Product_categoryDAO");
		List<Product> ProductList = new ArrayList<Product>();
		List<Product_category> Product_categoryList = new ArrayList<Product_category>();
		ProductList=Productdao.getList();
		Product_categoryList=Product_categoryDAO.getList();
		model.addObject("ProductList",ProductList);
		model.addObject("Product_categoryList",Product_categoryList);
		model.addObject("message");
		return model;
	}
	@RequestMapping(value = "/Product/iPad", method = RequestMethod.GET)
	public ModelAndView getProductiPad(String name) {
		ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
		ModelAndView model = new ModelAndView("Product_iPad");
		// = model.setViewName("Product_iPad");
		ProductDAO Productdao = (ProductDAO)context.getBean("ProductDAO"); //defined in spring-webapp.xml
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO)context.getBean("Product_categoryDAO");
		List<Product> ProductList = new ArrayList<Product>();
		List<Product_category> Product_categoryList = new ArrayList<Product_category>();
		ProductList=Productdao.getList();
		Product_categoryList=Product_categoryDAO.getList();
		model.addObject("ProductList",ProductList);
		model.addObject("Product_categoryList",Product_categoryList);
		model.addObject("message");
		return model;
	}
	@RequestMapping(value = "/Product/Macbook", method = RequestMethod.GET)
	public ModelAndView getProductMacbook(String name) {
		ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
		ModelAndView model = new ModelAndView("Product_Macbook");
		// = model.setViewName("Product_Macbook");
		ProductDAO Productdao = (ProductDAO)context.getBean("ProductDAO"); //defined in spring-webapp.xml
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO)context.getBean("Product_categoryDAO");
		List<Product> ProductList = new ArrayList<Product>();
		List<Product_category> Product_categoryList = new ArrayList<Product_category>();
		ProductList = Productdao.getList();
		Product_categoryList = Product_categoryDAO.getList();
		model.addObject("ProductList", ProductList);
		model.addObject("Product_categoryList", Product_categoryList);
		return model;
	}
	@RequestMapping(value = "/Product", method = RequestMethod.GET)
	public ModelAndView getiPhone7(@ModelAttribute("id") int id,HttpServletRequest request) {
		ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
		ModelAndView model = new ModelAndView("Product");
		// = model.setViewName("Product");
		ProductDAO Productdao = (ProductDAO)context.getBean("ProductDAO"); //defined in spring-webapp.xml
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO)context.getBean("Product_categoryDAO");
		List<Product> ProductList = new ArrayList<Product>();
		List<Product_category> Product_categoryList = new ArrayList<Product_category>();
		ProductList=Productdao.getList();
		Product product=new Product();
		for(int i = 0 ; i < ProductList.size();i++){
			if (id==ProductList.get(i).getP_id()){
				product=ProductList.get(i);
			}
		}

//		int count=0;
//		for(int j=0;j<ProductList.size()-1;j++){
//			if(ProductList.get(j).getClick_count()<ProductList.get(j+1).getClick_count()){
//				count=j+1;
//			}
//		}
//		for(int i = 0 ; i < ProductList.size();i++){
//			if (id==ProductList.get(i).getP_id()){
//				product=ProductList.get(i);
//			}
//		}
		request.getSession().getAttribute("loginsession");
		String idName=(String) request.getSession().getAttribute("loginsession");
		ShoppingDetailDAO shoppingDetaildao = (ShoppingDetailDAO) context.getBean("ShoppingDetailDAO");
		List<ShoppingDetail> ShoppingDetailList = new ArrayList<ShoppingDetail>();
		ShoppingDetailList=shoppingDetaildao.getList();
		CommentDAO commentDAO = (CommentDAO) context.getBean("CommentDAO");
		List<Comment>CommentList = new ArrayList<Comment>();
		CommentList = commentDAO.getList();
		int M_id=0;
		MemberDAO Memberdao = (MemberDAO) context.getBean("MemberDAO");
		List<Member> MemberList = new ArrayList<Member>();
		MemberList = Memberdao.getList();
		for(int i =0;i<MemberList.size();i++){
			if(MemberList.get(i).getM_idName().equals(idName)){
				M_id=MemberList.get(i).getM_id();
				break;
			}
		}
		int comment=0;
		int score_number=0;
		for(int i=0;i<CommentList.size();i++){
			if(CommentList.get(i).getComment_p_id()==id&&CommentList.get(i).getComment_M_id()==M_id){
				comment=1;
				score_number=CommentList.get(i).getScore();
				break;
			}
		}
		int already=0;
		for(int i=0;i<ShoppingDetailList.size();i++){
			if(ShoppingDetailList.get(i).getShopping_p_id()==id&&ShoppingDetailList.get(i).getShopping_M_id()==M_id){
				already=1;
				break;
			}
		}
		Product_categoryList=Product_categoryDAO.getList();
		model.addObject("Product",product);
		model.addObject("comment",comment);
		model.addObject("my_id",M_id);
		model.addObject("idName",idName);
		model.addObject("already",already);
		model.addObject("score_number",score_number);
		int click=Productdao.updateClick(product);
		System.out.println(id);
		System.out.println(product.getP_id());
		model.addObject("ProductList",ProductList);
		//model.addObject("count",count);
		model.addObject("message");
		return model;
	}
	@RequestMapping(value = "/ProductInfro", method = RequestMethod.GET)
	public ModelAndView getProduct(String name) {

		ModelAndView model = new ModelAndView("ProductInfro");
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO"); // define								
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO) context.getBean("Product_categoryDAO");
		List<Product> ProductList = new ArrayList<Product>();
		ProductList = Productdao.getList();
		List<Product_category> Product_categoryList = new ArrayList<Product_category>();
		
		Product_categoryList = Product_categoryDAO.getList();
		model.addObject("ProductList", ProductList);
		model.addObject("Product_categoryList", Product_categoryList);

		return model;
	}

	@RequestMapping(value = "/insertProduct", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView insertProduct(@ModelAttribute Product product, HttpServletRequest request,
			@RequestParam("type") String type) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);
		if (type.equals("insertProduct")) {

			Productdao.insert(product);
		}

		model.setViewName("redirect:/ProductInfro");
		return model;

	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView updateProduct(@ModelAttribute Product product, HttpServletRequest request,
			@RequestParam("type") String type) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);

		if (type.equals("modifyProduct")) {
			Productdao.update(product);
		}
		model.setViewName("redirect:/ProductInfro");
		return model;
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView deleteProduct(@ModelAttribute Product product, HttpServletRequest request,
			@RequestParam("type") String type) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);

		if (type.equals("deleteProduct")) {
			Productdao.delete(product);
		}
		model.setViewName("redirect:/ProductInfro");
		return model;
	}

	@RequestMapping(value = "/Inventory", method = RequestMethod.GET)
	public ModelAndView getInventory(String name) {
		ModelAndView model = new ModelAndView("Inventory");
		// = model.setViewName("Inventory");
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		List<Product> ProductList = new ArrayList<Product>();
		ProductList=Productdao.getList();
		Product product=new Product();
		for(int i = 0 ; i < ProductList.size();i++){
			if (ProductList.get(i).getP_inventory()<=20){
				product=ProductList.get(i);
			}
		}
		model.addObject("ProductList",ProductList);
		model.addObject("Product",product);
		model.addObject("message");
		return model;
	}
	
	@RequestMapping(value = "/addInventory", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	public ModelAndView getAddInventory(@ModelAttribute Product aproduct,HttpServletRequest request,@RequestParam("type") String type,@RequestParam("p_inventory") int p_inventory,@RequestParam("inventoryNumber") int inventory) {
		ModelAndView model = new ModelAndView();
		// = model.setViewName("Inventory");
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO)context.getBean("Product_categoryDAO");
		List<Product> ProductList = new ArrayList<Product>();
		List<Product_category> Product_categoryList = new ArrayList<Product_category>();		
		Product_categoryList = Product_categoryDAO.getList();
		ProductList=Productdao.getList();

		if (type.equals("modifyInventory")) {
			int total=inventory+p_inventory;
			aproduct.setP_inventory(total);
			Productdao.addInventory(aproduct);
		
		}
		model.addObject("ProductList",ProductList);
		model.addObject("Product_categoryList",Product_categoryList);
		model.addObject("message");
		model.setViewName("redirect:/Inventory");

		return model;
	}
	


	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute Product product, HttpServletRequest request,
		@RequestParam("keyword") String keyword) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		ModelAndView model = new ModelAndView("searchresult");
		//ModelAndView model1 = new ModelAndView("searchproduct");
		List<Product> ProductList = new ArrayList<Product>();
		//List<Product> Product1List = new ArrayList<Product>();
		ProductList=Productdao.search(keyword);
		//Product1List=Productdao.search(searchword);
		model.addObject("ProductList",ProductList);
		//model1.addObject("Product1List",Product1List);
		//model.setViewName("redirect:/searchresult");
		return model;
		
	}
	
	@RequestMapping(value = "/searchresult", method = RequestMethod.GET)
	public ModelAndView searchresullt(@ModelAttribute Product product, HttpServletRequest request) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		List<Product> ProductList = new ArrayList<Product>();
		ModelAndView model = new ModelAndView("searchresult");
		
		return model;
	}
	@RequestMapping(value = "/hotProduct", method = RequestMethod.POST)
	public ModelAndView hotProduct(@ModelAttribute Product product, HttpServletRequest request) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		ModelAndView model = new ModelAndView("hotProduct");
				List<Product> ProductList = new ArrayList<Product>(4);
				ProductList=Productdao.hotProduct();
		model.addObject("ProductList",ProductList);
		for(int i=0;i<ProductList.size();i++){
			System.out.println(ProductList.get(i).getClick_count());
			}
		model.addObject("ProductList",ProductList);
		model.addObject("message");

				return model;
		
	}
	
	@RequestMapping(value = "/hotget", method = RequestMethod.GET)
	public ModelAndView hotget(@ModelAttribute Product product, HttpServletRequest request) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		List<Product> ProductList = new ArrayList<Product>();
		ModelAndView model = new ModelAndView("hotget");
		
		model.addObject("ProductList",ProductList);
		model.addObject("message");

		return model;
	}

	@RequestMapping(value = "/psearch", method = RequestMethod.POST)
	public ModelAndView psearch(@ModelAttribute Product product, HttpServletRequest request,
		@RequestParam("searchword") String searchword) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		ModelAndView model = new ModelAndView("ProductInfro");
		//ModelAndView model1 = new ModelAndView("searchproduct");
		List<Product> ProductList = new ArrayList<Product>();
		//List<Product> Product1List = new ArrayList<Product>();
		ProductList=Productdao.search(searchword);
		//Product1List=Productdao.search(searchword);
		model.addObject("ProductList",ProductList);
		//model1.addObject("Product1List",Product1List);
		//model.setViewName("redirect:/searchresult");
		return model;
		
	}
	@RequestMapping(value = "/searchword", method = RequestMethod.GET)
	public ModelAndView searchword(@ModelAttribute Product product, HttpServletRequest request) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		List<Product> ProductList = new ArrayList<Product>();
		ModelAndView model = new ModelAndView("ProductInfro");
		return model;
		}
	@RequestMapping(value = "/isearch", method = RequestMethod.POST)
	public ModelAndView isearch(@ModelAttribute Product product, HttpServletRequest request,
		@RequestParam("keyword") String keyword) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		ModelAndView model = new ModelAndView("Inventory");
		//ModelAndView model1 = new ModelAndView("searchproduct");
		List<Product> ProductList = new ArrayList<Product>();
		//List<Product> Product1List = new ArrayList<Product>();
		ProductList=Productdao.search(keyword);
		//Product1List=Productdao.search(searchword);
		model.addObject("ProductList",ProductList);
		//model1.addObject("Product1List",Product1List);
		//model.setViewName("redirect:/searchresult");
		return model;
		
	}
	@RequestMapping(value = "/searchinventory", method = RequestMethod.GET)
	public ModelAndView searchinventory(@ModelAttribute Product product, HttpServletRequest request) {
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		List<Product> ProductList = new ArrayList<Product>();
		ModelAndView model = new ModelAndView("Inventory");
		
		return model;
	}
	@RequestMapping(value = "/removeInventory", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView deleteMember(@ModelAttribute Product aproduct, HttpServletRequest request,@RequestParam("sale") int sale,
			@RequestParam("type") String type) {

		ModelAndView model = new ModelAndView();
		ProductDAO Productdao = (ProductDAO) context.getBean("ProductDAO");
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO)context.getBean("Product_categoryDAO");
		List<Product> ProductList = new ArrayList<Product>();
		List<Product_category> Product_categoryList = new ArrayList<Product_category>();		
		Product_categoryList = Product_categoryDAO.getList();
		ProductList=Productdao.getList();
		System.out.println(type);

		if (type.equals("removeInventory")) {			
			int saleCheck=0;
			Product removeProduct =new Product();
			for(int i=0;i<ProductList.size();i++){
				if(aproduct.getP_id()==ProductList.get(i).getP_id()){
					removeProduct=ProductList.get(i);
					break;
				}
			}
			removeProduct.setSale(saleCheck);
			Productdao.update(removeProduct);

		}
		model.addObject("ProductList",ProductList);
		model.addObject("Product_categoryList",Product_categoryList);
		model.addObject("message");
		model.setViewName("redirect:/Inventory");
		return model;
	}
	
	@RequestMapping(value = "/ProductCategory", method = RequestMethod.GET)
	public ModelAndView getA_CategoryList(String name) {
		ModelAndView model = new ModelAndView("ProductCategory");
		// = model.setViewName("ProductCategory");
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO) context.getBean("Product_categoryDAO");
		List<Product_category> Product_categoryList = new ArrayList<Product_category>();
		Product_categoryList = Product_categoryDAO.getList();
		model.addObject("Product_categoryList", Product_categoryList);

		return model;
	}
	
	@RequestMapping(value = "/updateProduct_category", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView updateProduct_category(@ModelAttribute Product_category product_category, HttpServletRequest request,
			@RequestParam("type") String type) {
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO) context.getBean("Product_categoryDAO");
		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);
		if (type.equals("modifyProduct_category")) {
			Product_categoryDAO.update(product_category);
		}
		model.setViewName("redirect:/ProductCategory");
		return model;

	}
	
	@RequestMapping(value = "/addProduct_category", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView addA_category(@ModelAttribute Product_category product_category, HttpServletRequest request,
			@RequestParam("action_type") String type) {
		Product_categoryDAO Product_categoryDAO = (Product_categoryDAO) context.getBean("Product_categoryDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);

		if (type.equals("addProduct_category")) {
			Product_categoryDAO.insert(product_category);

		}
		model.setViewName("redirect:/ProductCategory");
		return model;
	}
	
}
