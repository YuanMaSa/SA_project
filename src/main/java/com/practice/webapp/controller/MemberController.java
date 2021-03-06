package com.practice.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.practice.webapp.dao.A_categoryDAO;
import com.practice.webapp.dao.AdministratorDAO;
import com.practice.webapp.dao.MemberDAO;

import com.practice.webapp.entity.A_category;
import com.practice.webapp.entity.Administrator;
import com.practice.webapp.entity.Member;
import com.practice.webapp.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

public class MemberController {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup(@ModelAttribute Member member, @ModelAttribute("checkid") String checkid,
			@ModelAttribute("result") String result, @ModelAttribute("flag") String flag,
			@ModelAttribute("checkpwd") String checkpwd, @ModelAttribute("checkphone") String checkphone) {
		ModelAndView model = new ModelAndView("signup");
		MemberDAO MemberDAO = (MemberDAO) context.getBean("MemberDAO");
		List<Member> memberList = new ArrayList<Member>();
		memberList = MemberDAO.getList();

		model.addObject("member", member);

		model.addObject("memberList", memberList);
		model.addObject("message");
		return model;

	}

	private Member member;

	@RequestMapping(value = "/AccountList", method = RequestMethod.GET)
	public ModelAndView getAccountList(String name,@ModelAttribute("checkpwd") String checkpwd,@ModelAttribute("checkid") String checkid,@ModelAttribute("checkphone") String checkphone) {
		ModelAndView model = new ModelAndView("AccountList");
		// = model.setViewName("Accountlist");
		MemberDAO memberdao = (MemberDAO) context.getBean("MemberDAO"); // defined
																		// in
																		// spring-webapp.xml
		AdministratorDAO AdministratorDAO = (AdministratorDAO) context.getBean("AdministratorDAO");
		A_categoryDAO A_categoryDAO = (A_categoryDAO) context.getBean("A_categoryDAO");
		List<Member> memberList = new ArrayList<Member>();
		List<Administrator> administratorList = new ArrayList<Administrator>();
		List<A_category> A_categoryList = new ArrayList<A_category>();
		memberList = memberdao.getList();
		administratorList = AdministratorDAO.getList();
		A_categoryList = A_categoryDAO.getList();
		model.addObject("memberList", memberList);
		model.addObject("administratorList", administratorList);
		model.addObject("A_categoryList", A_categoryList);

		return model;
	}

	@RequestMapping(value = "/insertMember", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView insertMember(@ModelAttribute Member member, @RequestParam("type") String type) {
		MemberDAO MemberDAO = (MemberDAO) context.getBean("MemberDAO");
		AdministratorDAO AdministratorDAO = (AdministratorDAO) context.getBean("AdministratorDAO");

		ModelAndView model = new ModelAndView();
		List<Administrator> administratorList = new ArrayList<Administrator>();
		List<Member> memberList = new ArrayList<Member>();
		administratorList = AdministratorDAO.getList();
		memberList = MemberDAO.getList();
		// = model.setViewName("signup");
		String Mid = member.getM_idName();
		String Mpwd = member.getM_pwd();
		String Mphone = member.getM_phone();
		int checkid = 1;// 1:true 0:false
		int checkpwd = 1;
		int checkphone = 1;
		int flag = 1;
		if (type.equals("Check")) {

			for (int i = 0; i < memberList.size(); i++) {
				if (Mid.equals(memberList.get(i).getM_idName())) {
					checkid = 0;// 帳號錯誤，到signup頁面
					flag = 0;
					System.out.println("1");
					break;
				}

			}
			for (int i = 0; i < administratorList.size(); i++) {
				if (Mid.equals(administratorList.get(i).getM_idName())) {
					checkid = 0;// 帳號錯誤，到signup頁面
					flag = 0;
					System.out.println("1");
					break;
				}

			}

			char mpwd[] = Mpwd.toCharArray();
			int countnum = 0;
			int countletter = 0;
			for (int i = 0; i < mpwd.length; i++) {
				if (Character.isLetter(mpwd[i])) {
					countletter++;
				}
				if (Character.isDigit(mpwd[i])) {
					countnum++;
				}

			}
			if (countletter == 0 || countnum == 0) {
				checkpwd = 0;// 密碼錯誤，到signup頁面
				flag = 0;
				System.out.println("2");

			}
			char mphone[] = new char[Mphone.length()];
			for (int j = 0; j < Mphone.length(); j++) {
				mphone[j] = Mphone.charAt(j);
				if (Character.isDigit(mphone[j]) == false) {
					checkphone = 0;// 電話錯誤，到signup頁面
					flag = 0;
					System.out.println("3");

					break;
				}
			}
			if (flag == 1) {
				MemberDAO.insert(member);
				System.out.println("4");
				model.setViewName("redirect:/login");

			} else {
				System.out.println("5");

				model.setViewName("redirect:/signup");

			}
		}
		model.addObject("memberList", memberList);
		model.addObject("flag", flag);
		model.addObject("checkid", checkid);
		model.addObject("checkpwd", checkpwd);
		model.addObject("checkphone", checkphone);

		return model;
	}

	@RequestMapping(value = "/loginAdministrator", method = RequestMethod.GET)
	public ModelAndView getloginAdministrator(String name) {
		ModelAndView model = new ModelAndView("loginAdministrator");
		// = model.setViewName("loginAdministrator");

		model.addObject("message");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView checkLogin(@ModelAttribute Member member) {
		MemberDAO memberDAO = (MemberDAO) context.getBean("MemberDAO");
		AdministratorDAO AdministratorDAO = (AdministratorDAO) context.getBean("AdministratorDAO");
		ModelAndView model = new ModelAndView();

		List<Member> memberList = new ArrayList<Member>();
		List<Administrator> administratorList = new ArrayList<Administrator>();
		System.out.println("id before called:" + member.getM_idName());
		memberList = memberDAO.getList();
		administratorList = AdministratorDAO.getList();
		// boolean result = memberDAO.checkLoginMember(member);
		String result = memberDAO.checkLoginMember(member);
		if (result.equals("1")) {
			model.addObject("result", result);
			model.addObject("loginsession", member.getM_idName());
			model.setViewName("redirect:/");
		} else if (result.equals("2")) {// 帳號對，密碼錯，顯示密碼錯
			model.addObject("result", result);
			model.setViewName("login");
		} else if (result.equals("3")) {
			model.addObject("result", result);
			model.setViewName("signup");
		}

		boolean result2 = memberDAO.checkLoginAdministrator(member);

		if (result2) {
			model.addObject("loginsession", member.getM_idName());
			model.setViewName("redirect:/dashboard");
		}
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLogin(@ModelAttribute("name") String name, SessionStatus sessionStatus,
			@ModelAttribute("result") String result, @ModelAttribute("flag") String flag) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
		ModelAndView model = new ModelAndView("login");
		MemberDAO Memberdao = (MemberDAO) context.getBean("MemberDAO");
		List<Member> MemberList = new ArrayList<Member>();
		MemberList = Memberdao.getList();
		Member member = new Member();
		sessionStatus.setComplete();
		// for(int i =0; i<MemberList.size(); i++){
		// if(name==MemberList.get(i).getM_name()){
		// member=MemberList.get(i);
		// }
		// }
		//
		/*
		 * ServletRequest session = null;
		 * if(session.getAttribute("login")!=null){
		 * session.removeAttribute("login"); }
		 */
		// = model.setViewName("login");
		model.addObject("Member", member);
		model.addObject("message");
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView getLogOut(SessionStatus sessionStatus) {

		ModelAndView model = new ModelAndView();
		MemberDAO Memberdao = (MemberDAO) context.getBean("MemberDAO");
		List<Member> MemberList = new ArrayList<Member>();
		MemberList = Memberdao.getList();
		Member member = new Member();

		// model.addObject("loginsession","logout");
		sessionStatus.setComplete();
		model.addObject("Member", member);
		model.setViewName("redirect:/");
		return model;
	}
	//
	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	// public ModelAndView getcheckLogOut(SessionStatus sessionStatus) {
	// ModelAndView model = new ModelAndView();
	// AdministratorDAO Administratordao = (AdministratorDAO)
	// context.getBean("AdministratorDAO");
	// List<Administrator> AdministratorList = new ArrayList<Administrator>();
	// AdministratorList = Administratordao.getList();
	// Administrator administrator = new Administrator();
	//
	// // model.addObject("loginsession","logout");
	// sessionStatus.setComplete();
	// model.addObject("Administrator", administrator);
	// model.setViewName("redirect:/");
	// return model;
	// }

	@RequestMapping(value = "/AccountList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView add(@ModelAttribute Member member, HttpServletRequest request,
			@RequestParam("action_type") String type, @RequestParam("m_category") String m_category,
			@ModelAttribute Administrator administrator) {
		MemberDAO MemberDAO = (MemberDAO) context.getBean("MemberDAO");
		AdministratorDAO AdministratorDAO = (AdministratorDAO) context.getBean("AdministratorDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);
		List<Administrator> administratorList = new ArrayList<Administrator>();
		List<Member> memberList = new ArrayList<Member>();
		administratorList = AdministratorDAO.getList();
		memberList = MemberDAO.getList();
		
		int checkid = 1;// 1:true 0:false
		int checkpwd = 1;
		int checkphone = 1;
		int flag = 1;

		if (type.equals("addMember")) {
			
			if (m_category.equals("Member")) {
				String Mid = member.getM_idName();
				String Mpwd = member.getM_pwd();
				String Mphone = member.getM_phone();
				for (int i = 0; i < memberList.size(); i++) {
					if (Mid.equals(memberList.get(i).getM_idName())) {
						checkid = 0;// 帳號錯誤，到signup頁面
						flag = 0;
						System.out.println("1");
						break;
					}

				}
				for (int i = 0; i < administratorList.size(); i++) {
					if (Mid.equals(administratorList.get(i).getM_idName())) {
						checkid = 0;// 帳號錯誤，到signup頁面
						flag = 0;
						System.out.println("1");
						break;
					}

				}

				char mpwd[] = Mpwd.toCharArray();
				int countnum = 0;
				int countletter = 0;
				for (int i = 0; i < mpwd.length; i++) {
					if (Character.isLetter(mpwd[i])) {
						countletter++;
					}
					if (Character.isDigit(mpwd[i])) {
						countnum++;
					}

				}
				if (countletter == 0 || countnum == 0) {
					checkpwd = 0;// 密碼錯誤，到signup頁面
					flag = 0;
					System.out.println("2");

				}
				char mphone[] = new char[Mphone.length()];
				for (int j = 0; j < Mphone.length(); j++) {
					mphone[j] = Mphone.charAt(j);
					if (Character.isDigit(mphone[j]) == false) {
						checkphone = 0;// 電話錯誤，到signup頁面
						flag = 0;
						System.out.println("3");

						break;
					}
				}
				if (flag == 1) {
					MemberDAO.insert(member);
					System.out.println("4");
					model.setViewName("redirect:/AccountList");

				} else {
					System.out.println("5");

					model.setViewName("redirect:/AccountList");

				}
				model.addObject("flag", flag);
				model.addObject("checkid", checkid);
				model.addObject("checkpwd", checkpwd);
				model.addObject("checkphone", checkphone);
			} else {
				String Mid = administrator.getM_idName();
				String Mpwd = administrator.getM_pwd();
				String Mphone = administrator.getM_phone();
				for (int i = 0; i < memberList.size(); i++) {
					if (Mid.equals(memberList.get(i).getM_idName())) {
						checkid = 0;// 帳號錯誤，到signup頁面
						flag = 0;
						System.out.println("1");
						break;
					}

				}
				for (int i = 0; i < administratorList.size(); i++) {
					if (Mid.equals(administratorList.get(i).getM_idName())) {
						checkid = 0;// 帳號錯誤，到signup頁面
						flag = 0;
						System.out.println("1");
						break;
					}

				}

				char mpwd[] = Mpwd.toCharArray();
				int countnum = 0;
				int countletter = 0;
				for (int i = 0; i < mpwd.length; i++) {
					if (Character.isLetter(mpwd[i])) {
						countletter++;
					}
					if (Character.isDigit(mpwd[i])) {
						countnum++;
					}

				}
				if (countletter == 0 || countnum == 0) {
					checkpwd = 0;// 密碼錯誤，到signup頁面
					flag = 0;
					System.out.println("2");

				}
				char mphone[] = new char[Mphone.length()];
				for (int j = 0; j < Mphone.length(); j++) {
					mphone[j] = Mphone.charAt(j);
					if (Character.isDigit(mphone[j]) == false) {
						checkphone = 0;// 電話錯誤，到signup頁面
						flag = 0;
						System.out.println("3");

						break;
					}
				}
				if (flag == 1) {
					AdministratorDAO.insert(administrator);
					System.out.println("4");
					model.setViewName("redirect:/AccountList");

				} else {
					System.out.println("5");

					model.setViewName("redirect:/AccountList");

				}
				model.addObject("flag", flag);
				model.addObject("checkid", checkid);
				model.addObject("checkpwd", checkpwd);
				model.addObject("checkphone", checkphone);
				
//				AdministratorDAO.insert(administrator);
			}

		}
		model.setViewName("redirect:/AccountList");
		return model;
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView updateMember(@ModelAttribute Member member, HttpServletRequest request,
			@RequestParam("type") String type) {
		MemberDAO MemberDAO = (MemberDAO) context.getBean("MemberDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);

		if (type.equals("modifyMember")) {
			MemberDAO.update(member);
		}
		model.setViewName("redirect:/AccountList");
		return model;
	}

	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView deleteMember(@ModelAttribute Member member, HttpServletRequest request,
			@RequestParam("type") String type) {
		MemberDAO MemberDAO = (MemberDAO) context.getBean("MemberDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);

		if (type.equals("deleteMember")) {
			MemberDAO.delete(member);
		}
		model.setViewName("redirect:/AccountList");
		return model;
	}

	@RequestMapping(value = "/updateAdministrator", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView updateAdministrator(@ModelAttribute Administrator admin, HttpServletRequest request,
			@RequestParam("type") String type) {
		AdministratorDAO AdministratorDAO = (AdministratorDAO) context.getBean("AdministratorDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);

		if (type.equals("modifyAdministrator")) {
			AdministratorDAO.update(admin);
		}
		model.setViewName("redirect:/AccountList");
		return model;
	}

	@RequestMapping(value = "/deleteAdministrator", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView deleteAdministrator(@ModelAttribute Administrator admin, HttpServletRequest request,
			@RequestParam("type") String type) {
		AdministratorDAO AdministratorDAO = (AdministratorDAO) context.getBean("AdministratorDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);

		if (type.equals("deleteAdministrator")) {
			AdministratorDAO.delete(admin);
		}
		model.setViewName("redirect:/AccountList");
		return model;
	}

	@RequestMapping(value = "/msearch", method = RequestMethod.POST)

	public ModelAndView msearch(@ModelAttribute Member member, HttpServletRequest request,
			@RequestParam("mkeyword") String mkeyword) {
		MemberDAO Memberdao = (MemberDAO) context.getBean("MemberDAO");
		ModelAndView model = new ModelAndView("AccountList");

		List<Member> MemberList = new ArrayList<Member>();

		MemberList = Memberdao.search(mkeyword);
		AdministratorDAO AdministratorDAO = (AdministratorDAO) context.getBean("AdministratorDAO");
		A_categoryDAO A_categoryDAO = (A_categoryDAO) context.getBean("A_categoryDAO");
		
		List<Administrator> administratorList = new ArrayList<Administrator>();
		List<A_category> A_categoryList = new ArrayList<A_category>();
		
		administratorList = AdministratorDAO.getList();
		A_categoryList = A_categoryDAO.getList();
		
		model.addObject("administratorList", administratorList);
		model.addObject("A_categoryList", A_categoryList);

		model.addObject("memberList", MemberList);

		return model;

	}

	@RequestMapping(value = "/searchmember", method = RequestMethod.GET)
	public ModelAndView searchmember(@ModelAttribute Member member, HttpServletRequest request) {
		MemberDAO Memberdao = (MemberDAO) context.getBean("MemberDAO");
		List<Member> MemberList = new ArrayList<Member>();

		ModelAndView model = new ModelAndView("AccountList");
		return model;
	}

	@RequestMapping(value = "/MemberData", method = RequestMethod.GET)
	public ModelAndView getMemberData(String name, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("MemberData");

		// = model.setViewName("MemberData");
		MemberDAO memberdao = (MemberDAO) context.getBean("MemberDAO"); // defined
																		// in
																		// spring-webapp.xml
		List<Member> memberList = new ArrayList<Member>();
		memberList = memberdao.getList();
		Member member = new Member();

		request.getSession().getAttribute("loginsession");
		String idName = (String) request.getSession().getAttribute("loginsession");
		MemberDAO Memberdao = (MemberDAO) context.getBean("MemberDAO"); // defined
																		// in
																		// spring-webapp.xml
		List<Member> MemberList = new ArrayList<Member>();
		MemberList = Memberdao.getList();
		System.out.print(idName);
		for (int i = 0; i < MemberList.size(); i++) {
			if (MemberList.get(i).getM_idName().equals(idName)) {
				member = MemberList.get(i);
				break;
			}
		}

		model.addObject("Member", member);
		model.addObject("memberList", memberList);

		return model;
	}

	@RequestMapping(value = "/updateMemberData", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView updateMemberData(@ModelAttribute Member member, HttpServletRequest request,
			@RequestParam("type") String type) {
		MemberDAO MemberDAO = (MemberDAO) context.getBean("MemberDAO");
		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);
		if (type.equals("modifyMemberData")) {
			MemberDAO.update(member);
		}
		model.setViewName("redirect:/MemberData");
		return model;

	}

	@RequestMapping(value = "/AdministratorCategory", method = RequestMethod.GET)
	public ModelAndView getA_CategoryList(String name) {
		ModelAndView model = new ModelAndView("AdministratorCategory");
		// = model.setViewName("AdministratorCategory");
		A_categoryDAO A_categoryDAO = (A_categoryDAO) context.getBean("A_categoryDAO");
		List<A_category> A_categoryList = new ArrayList<A_category>();
		A_categoryList = A_categoryDAO.getList();
		model.addObject("A_categoryList", A_categoryList);

		return model;
	}

	@RequestMapping(value = "/updateA_category", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView updateA_category(@ModelAttribute A_category a_category, HttpServletRequest request,
			@RequestParam("type") String type) {
		A_categoryDAO A_categoryDAO = (A_categoryDAO) context.getBean("A_categoryDAO");
		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);
		if (type.equals("modifyA_category")) {
			A_categoryDAO.update(a_category);
		}
		model.setViewName("redirect:/AdministratorCategory");
		return model;

	}

	@RequestMapping(value = "/addA_category", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView addA_category(@ModelAttribute A_category a_category, HttpServletRequest request,
			@RequestParam("action_type") String type) {
		A_categoryDAO A_categoryDAO = (A_categoryDAO) context.getBean("A_categoryDAO");

		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);

		if (type.equals("addA_category")) {
			A_categoryDAO.insert(a_category);

		}
		model.setViewName("redirect:/AdministratorCategory");
		return model;
	}
	@RequestMapping(value = "/AdministratorData", method = RequestMethod.GET)
	public ModelAndView getAdministratorData(String name, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("AdministratorData");
		// = model.setViewName("AdministratorData");
		AdministratorDAO administratordao = (AdministratorDAO) context.getBean("AdministratorDAO"); 
		A_categoryDAO a_categorydao = (A_categoryDAO) context.getBean("A_categoryDAO");
		List<Administrator> administratorList = new ArrayList<Administrator>();
		List<A_category> a_categoryList = new ArrayList<A_category>();
		administratorList = administratordao.getList();
		a_categoryList = a_categorydao.getList();
		Administrator administrator = new Administrator();

		request.getSession().getAttribute("loginsession");
		String idName = (String) request.getSession().getAttribute("loginsession"); // defined
		List<Administrator> AdministratorList = new ArrayList<Administrator>();
		AdministratorList = administratordao.getList();
		System.out.print(idName);
		for (int i = 0; i < AdministratorList.size(); i++) {
			if (AdministratorList.get(i).getM_idName().equals(idName)) {
				administrator = AdministratorList.get(i);
				break;
			}
		}

		model.addObject("Administrator", administrator);
		model.addObject("AdministratorList", AdministratorList);
		model.addObject("A_categoryList", a_categoryList);
		return model;
	}

	@RequestMapping(value = "/updateAdministratorData", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView updateAdministratorData(@ModelAttribute Administrator administrator, HttpServletRequest request,
			@RequestParam("type") String type) {
		AdministratorDAO AdministratorDAO = (AdministratorDAO) context.getBean("AdministratorDAO");
		ModelAndView model = new ModelAndView();
		System.out.println(request.getCharacterEncoding());
		System.out.println(type);
		if (type.equals("modifyAdministratorData")) {
			AdministratorDAO.update(administrator);
		}
		model.setViewName("redirect:/AdministratorData");
		return model;

	}
}
