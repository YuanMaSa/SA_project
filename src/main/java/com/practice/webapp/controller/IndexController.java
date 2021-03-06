package com.practice.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.practice.webapp.dao.ProductDAO;
import com.practice.webapp.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Controller
@SessionAttributes("loginsession")
public class IndexController {

	ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getIndex(String name,@ModelAttribute("result") String result) {
		ModelAndView model = new ModelAndView("index");
		// = model.setViewName("index");
		ProductDAO Productdao = (ProductDAO)context.getBean("ProductDAO"); 
		List<Product> HotProductList = new ArrayList<Product>();
		HotProductList=Productdao.hotProduct();
		model.addObject("HotProductList",HotProductList);
		model.addObject("message");
		return model;
	}
	@RequestMapping(value = "/About", method = RequestMethod.GET)
	public ModelAndView getAbout(String name) {
		ModelAndView model = new ModelAndView("About");
		// = model.setViewName("About");
		model.addObject("message");
		return model;
	}
	@RequestMapping(value = "/FAQ", method = RequestMethod.GET)
	public ModelAndView getFAQ(String name) {
		ModelAndView model = new ModelAndView("FAQ");
		// = model.setViewName("FAQ");
		model.addObject("message");
		return model;
	}
	@RequestMapping(value = "/FAQ/iPad", method = RequestMethod.GET)
	public ModelAndView getFAQiPad(String name) {
		ModelAndView model = new ModelAndView("FAQiPad");
		// = model.setViewName("FAQiPad");
		model.addObject("message");
		return model;
	}
	@RequestMapping(value = "/FAQ/iPhone", method = RequestMethod.GET)
	public ModelAndView getFAQiPhone(String name) {
		ModelAndView model = new ModelAndView("FAQiPhone");
		// = model.setViewName("FAQiPhone");
		model.addObject("message");
		return model;
	}
	@RequestMapping(value = "/FAQ/Mac", method = RequestMethod.GET)
	public ModelAndView getFAQMac(String name) {
		ModelAndView model = new ModelAndView("FAQMac");
		// = model.setViewName("FAQMac");
		model.addObject("message");
		return model;
	}
	
//	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
//	public ModelAndView getdashboard(String name) {
//		ModelAndView model = new ModelAndView("dashboard");
//		// = model.setViewName("dashboard");
//		model.addObject("message");
//		return model;
//	}
//	
//	

}
