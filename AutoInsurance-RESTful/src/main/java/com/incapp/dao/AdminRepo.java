package com.incapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.incapp.beans.Company;

@Repository
public class AdminRepo {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String checkAdminLogin(String aid,String password){
		class AdminMapper implements RowMapper{
			public String mapRow(ResultSet rs,int rowNum)throws SQLException{
				
				return 	rs.getString("name");
			}
		}
		try {
			final String query ="select * from admin where aid=? and password=?";
			String r=(String) jdbcTemplate.queryForObject(query,new AdminMapper(),new Object[] {aid,password});
			return r;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public List<Company> getCompanies(String status){
		class DataMapper implements RowMapper{
			public Company mapRow(ResultSet rs,int rowNum)throws SQLException{
				Company c=new Company();
				c.setEmail(rs.getString("email"));
				c.setName(rs.getString("name"));
				c.setPhone(rs.getString("phone"));
				c.setRegistration(rs.getString("registration"));
				c.setAddress(rs.getString("address"));
				return 	c;
			}
		}
		try {
			final String query ="select * from companies where status=?";
			List<Company> c= jdbcTemplate.query(query,new DataMapper(),new Object[] {status});
			return c;
		}catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	public String changeStatus(String e,String s){
		try {
			String query="update companies set status=? where email=?";
			jdbcTemplate.update(query,new Object[] {s,e});
			
			return "success";
		}catch(Exception ex) {
			//ex.printStackTrace();
			return "Not Found";
		}
	}
	
}
