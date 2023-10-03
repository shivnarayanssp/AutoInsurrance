package com.incapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.User;

public interface UserService {
	ResponseEntity<String> userRegister(User u);
	ResponseEntity<String[]> checkUserLogin(String email,String password);
	ResponseEntity<String> userPhotoUpload(MultipartFile image,String email);
	ResponseEntity<byte[]> getUserImage(String email);
	
}
