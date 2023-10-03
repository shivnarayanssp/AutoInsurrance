package com.incapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incapp.beans.Company;
import com.incapp.dao.AdminRepo;

@RestController
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepo adminRepo;
	
	@Override
	@RequestMapping(value = "/adminLogin/{aid}/{password}")
	public ResponseEntity<String> checkAdminLogin(@PathVariable String aid, @PathVariable String password) {
		String r=adminRepo.checkAdminLogin(aid, password);
		if(r!=null)
			return new ResponseEntity<String>(r, HttpStatus.OK);
		else
			return new ResponseEntity<String>("NotFound", HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(value = "/getCompanies/{status}")
	public List<Company> getCompanies(@PathVariable String status){
		List<Company> companies=adminRepo.getCompanies(status);
		return companies;
	}
	
	@Override
	@RequestMapping(value = "/changeStatus/{email}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable String email,@PathVariable String status){
		String r=adminRepo.changeStatus(email,status);
		return new ResponseEntity<String>(r, HttpStatus.OK);
	}
}
