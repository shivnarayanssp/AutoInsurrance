package com.incapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.User;
import com.incapp.dao.UserRepo;

@RestController
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	@PostMapping(value = "/userRegister") 
	public ResponseEntity<String> userRegister(@RequestBody User u) {
		String r=userRepo.addUser(u);
		if(r.equalsIgnoreCase("success")) {
			return new ResponseEntity<String>(u.getName()+" is Registered Successfully!", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(u.getName()+" is Already Exist!", HttpStatus.OK);
		}
	}
	
	@Override
	@RequestMapping(value = "/userLogin/{email}/{password}")
	public ResponseEntity<String[]> checkUserLogin(@PathVariable String email, @PathVariable String password) {
		String r=userRepo.checkUserLogin(email, password);
		if(r!=null)
			return new ResponseEntity<String[]>(new String[] {email,r}, HttpStatus.OK);
		else
			return new ResponseEntity<String[]>(new String[] {}, HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(value = "/userPhotoUpload/{email}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> userPhotoUpload(@RequestPart("photo") MultipartFile image,@PathVariable String email) {
		String r=userRepo.userPhotoUpload(image, email);
		return new ResponseEntity<String>(r, HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(value = "/getUserImage/{email}")
	public ResponseEntity<byte[]> getUserImage(@PathVariable String email){
		byte[] b=userRepo.getUserImage(email);
		if(b!=null) {
			return new ResponseEntity<byte[]>(b, HttpStatus.OK);
		}else {
			return null;
		}
	}
}
