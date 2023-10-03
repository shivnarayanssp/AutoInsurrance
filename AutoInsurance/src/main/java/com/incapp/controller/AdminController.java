package com.incapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.incapp.beans.Company;

@Controller
public class AdminController {
	
	final static private String URL="http://localhost:8889/";
	RestTemplate restTemplate=new RestTemplate();
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/Logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	@PostMapping("/AdminLogin")
	public String adminLogin(String aid,String password,ModelMap m,HttpSession session) {
		
		String API="/adminLogin"+"/" + aid+"/"+password;
		ResponseEntity<String> result=restTemplate.getForEntity(URL+API,String.class);
		String r=result.getBody();
		if(r.equalsIgnoreCase("NotFound")) {
			m.addAttribute("result", "Invalid Credentials!");
			return "index";
		}else {
			session.setAttribute("adminName", r);
			
			API="/getCompanies"+"/" + "Pending";
			Company[] companies=restTemplate.getForObject(URL+API,Company[].class);
			m.addAttribute("companies", companies);
			return "adminHome";
		}
	}
	
	@RequestMapping("/adminHome")
	public String companyHome(ModelMap m,HttpSession session) {
		String API="/getCompanies"+"/" + "Pending";
		Company[] companies=restTemplate.getForObject(URL+API,Company[].class);
		m.addAttribute("companies", companies);
		return "adminHome";
	}
	
	@RequestMapping("/changeStatus")
	public String changeStatus(String email,String status,ModelMap m) {
		String API="/changeStatus"+"/" + email+"/" + status;
		String r=restTemplate.getForObject(URL+API,String.class);
		
		API="/getCompanies"+"/" + "Pending";
		Company[] companies=restTemplate.getForObject(URL+API,Company[].class);
		m.addAttribute("companies", companies);
		return "adminHome";
	}
	
}
