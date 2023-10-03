package com.incapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Company;
import com.incapp.beans.Policy;

@Controller
public class CompanyController {
	
	final static private String URL="http://localhost:8889/";
	RestTemplate restTemplate=new RestTemplate();
	
	@PostMapping("/CompanyRegister")
	public String CompanyRegister(@ModelAttribute Company c,ModelMap m) {
		String API="/companyRegister";
		HttpHeaders h=new HttpHeaders();
		HttpEntity<Company> requestEntity=new HttpEntity(c,h);
		ResponseEntity<String> result=restTemplate.postForEntity(URL+API,requestEntity,String.class);
		m.addAttribute("result", result.getBody());
		return "Company";
	}
	
	@PostMapping("/AddPolicy")
	public String addPolicy(@ModelAttribute Policy p,ModelMap m,HttpSession session) {
		String API="/addPolicy";
		HttpHeaders h=new HttpHeaders();
		HttpEntity<Company> requestEntity=new HttpEntity(p,h);
		ResponseEntity<String> result=restTemplate.postForEntity(URL+API,requestEntity,String.class);
		m.addAttribute("result", result.getBody());
		
		String email=(String)session.getAttribute("companyEmail");
		if(email==null) {
			return "Company";
		}
		API="/getCompanyStatus"+"/" + email;
		ResponseEntity<String> rr=restTemplate.getForEntity(URL+API,String.class);
		String status=rr.getBody();
		if(status.equalsIgnoreCase("Not Found")) {
			session.invalidate();
			return "Company";
		}else {
			m.addAttribute("status", status);
			return "companyHome";
		}
	}
	

	
	@PostMapping("/CompanyLogin")
	public String companyLogin(String email,String password,ModelMap m,HttpSession session) {
		
		String API="/companyLogin"+"/" + email+"/"+password;
		ResponseEntity<String[]> result=restTemplate.getForEntity(URL+API,String[].class);
		String r[]=result.getBody();
		if(r.length==0) {
			m.addAttribute("result", "Invalid Credentials!");
			return "Company";
		}else {
			session.setAttribute("companyEmail", r[0]);
			session.setAttribute("companyName", r[1]);
			
			API="/getCompanyStatus"+"/" + email;
			ResponseEntity<String> rr=restTemplate.getForEntity(URL+API,String.class);
			String status=rr.getBody();
			if(status.equalsIgnoreCase("Not Found")) {
				session.invalidate();
				return "Company";
			}else {
				m.addAttribute("status", status);
				return "companyHome";
			}
		}
	}
	
	@RequestMapping("/Company")
	public String company() {
		return "Company";
	}
	
	@RequestMapping("/companyHome")
	public String companyHome(ModelMap m,HttpSession session) {
		String email=(String)session.getAttribute("companyEmail");
		if(email==null) {
			return "Company";
		}
		String API="/getCompanyStatus"+"/" + email;
		ResponseEntity<String> result=restTemplate.getForEntity(URL+API,String.class);
		String status=result.getBody();
		if(status.equalsIgnoreCase("Not Found")) {
			session.invalidate();
			return "Company";
		}else {
			m.addAttribute("status", status);
			return "companyHome";
		}
	}
	
	@RequestMapping("/ViewAllPolicies")
	public String getPolicies(ModelMap m,HttpSession session) {
		String email=(String)session.getAttribute("companyEmail");
		if(email==null) {
			return "Company";
		}
		String API="/getPolicies"+"/" + email;
		Policy[] policies=restTemplate.getForObject(URL+API,Policy[].class);
		m.addAttribute("policies", policies);
		return "ViewAllPolicies";
	}
	
	@RequestMapping("/SearchPolicy")
	public String SearchPolicy(String category,ModelMap m) {
		
		String API="/getPoliciesByCategory"+"/" + category;
		Policy[] policies=restTemplate.getForObject(URL+API,Policy[].class);
		m.addAttribute("policies", policies);
		return "SearchPolicy";
	}
	
	@RequestMapping("/CompanyProfile")
	public String CompanyProfile(HttpSession session) {
		String email=(String)session.getAttribute("companyEmail");
		if(email==null) {
			return "Company";
		}
		return "CompanyProfile";
	}
	
	// Image upload code
	@PostMapping("/ImageUpload")
	public String ImageUpload(@RequestPart("logo") MultipartFile image,HttpSession session,ModelMap m) {
		String email=(String)session.getAttribute("companyEmail");
		
		String API="/companyLogoUpload"+"/" + email;
		HttpHeaders h=new HttpHeaders();
		h.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		LinkedMultiValueMap<String, Object> data=new LinkedMultiValueMap<>();
		
		data.add("logo", convert(image));
		
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity=new HttpEntity(data,h);
		
		
		ResponseEntity<String> result=restTemplate.postForEntity(URL+API,requestEntity,String.class);
		m.addAttribute("result", result.getBody());
		return "CompanyProfile";
	}
	
	public static FileSystemResource convert(MultipartFile file) {
		File convFile=new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos=new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return new FileSystemResource(convFile);
	}
	//END: Image upload code
	
	@RequestMapping("/getImage")
	public void getImage(String email,HttpServletResponse response) {
		try {
			String API="/getImage"+"/" + email;
			byte[] b=restTemplate.getForObject(URL+API,byte[].class);
			response.getOutputStream().write(b);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
