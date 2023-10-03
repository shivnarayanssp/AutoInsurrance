package com.incapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.incapp.beans.Company;

public interface AdminService {
	ResponseEntity<String> checkAdminLogin(String aid,String password);
	List<Company> getCompanies(String status);
	ResponseEntity<String> changeStatus(String email,String status);
}
