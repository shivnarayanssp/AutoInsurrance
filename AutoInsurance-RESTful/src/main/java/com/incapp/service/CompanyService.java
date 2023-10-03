package com.incapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Company;
import com.incapp.beans.Policy;

public interface CompanyService {
	ResponseEntity<String> companyRegister(Company c);
	ResponseEntity<String[]> checkCompanyLogin(String email,String password);
	ResponseEntity<String> getCompanyStatus(String email);
	ResponseEntity<String> addPolicy(Policy p);
	List<Policy> getPolicies(String email);
	List<Policy> getPoliciesByCategory(String category);
	ResponseEntity<String> companyLogoUpload(MultipartFile image,String email);
	ResponseEntity<byte[]> getImage(String email);
	
}
