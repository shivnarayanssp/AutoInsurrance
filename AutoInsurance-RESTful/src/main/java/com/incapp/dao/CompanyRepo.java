package com.incapp.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Company;
import com.incapp.beans.Policy;

@Repository
public class CompanyRepo {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String addCompany(Company c){
		try {
			String query="insert into companies(email,name,registration,phone,password,address,status) values(?,?,?,?,?,?,?)";
			jdbcTemplate.update(query,new Object[] {c.getEmail(),c.getName(),c.getRegistration(),c.getPhone(),c.getPassword(),c.getAddress(),c.getStatus()});
			
			return "success";
		}catch(Exception ex) {
			//ex.printStackTrace();
			return "already";
		}
	}
	
	public String addPolicy(Policy p){
		try {
			String query="insert into policies(pname,amount,category,description,email,cname) values(?,?,?,?,?,?)";
			jdbcTemplate.update(query,new Object[] {p.getPname(),p.getAmount(),p.getCategory(),p.getDescription(),p.getEmail(),p.getCname()});
			
			return "success";
		}catch(Exception ex) {
			//ex.printStackTrace();
			return "failed";
		}
	}
	
	public String checkCompanyLogin(String email,String password){
		class DataMapper implements RowMapper{
			public String mapRow(ResultSet rs,int rowNum)throws SQLException{
				return 	rs.getString("name");
			}
		}
		try {
			final String query ="select * from companies where email=? and password=?";
			String r=(String) jdbcTemplate.queryForObject(query,new DataMapper(),new Object[] {email,password});
			return r;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public String getCompanyStatus(String email){
		class DataMapper implements RowMapper{
			public String mapRow(ResultSet rs,int rowNum)throws SQLException{
				return 	rs.getString("status");
			}
		}
		try {
			final String query ="select status from companies where email=?";
			String r=(String) jdbcTemplate.queryForObject(query,new DataMapper(),new Object[] {email});
			return r;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public List<Policy> getPolicies(String email){
		class DataMapper implements RowMapper{
			public Policy mapRow(ResultSet rs,int rowNum)throws SQLException{
				Policy p=new Policy();
				p.setPname(rs.getString("pname"));
				p.setAmount(rs.getInt("amount"));
				p.setCategory(rs.getString("category"));
				p.setDescription(rs.getString("description"));
				return 	p;
			}
		}
		try {
			final String query ="select * from policies where email=?";
			List<Policy> p= jdbcTemplate.query(query,new DataMapper(),new Object[] {email});
			return p;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public List<Policy> getPoliciesByCategory(String category){
		class DataMapper implements RowMapper{
			public Policy mapRow(ResultSet rs,int rowNum)throws SQLException{
				Policy p=new Policy();
				p.setEmail(rs.getString("email"));
				p.setCname(rs.getString("cname"));
				p.setPname(rs.getString("pname"));
				p.setAmount(rs.getInt("amount"));
				p.setCategory(rs.getString("category"));
				p.setDescription(rs.getString("description"));
				return 	p;
			}
		}
		try {
			final String query ="select * from policies where category=?";
			List<Policy> p= jdbcTemplate.query(query,new DataMapper(),new Object[] {category});
			return p;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public String companyLogoUpload(MultipartFile image,String email){
		try {
			String query="update companies set logo=? where email=?";
			jdbcTemplate.update(query,new Object[] {image.getInputStream(),email});
			
			return "success";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "failed";
		}
	}
	
	public byte[] getImage(String email){
		class DataMapper implements RowMapper{
			public byte[] mapRow(ResultSet rs,int rowNum)throws SQLException{
				return 	rs.getBytes("logo");
			}
		}
		try {
			final String query ="select logo from companies where email=?";
			byte[] r=(byte[]) jdbcTemplate.queryForObject(query,new DataMapper(),new Object[] {email});
			return r;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
}
