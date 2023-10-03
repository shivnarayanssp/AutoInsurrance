<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Auto Insurance</title>
        <link rel="icon" href="resource/autoinsurance-icon.png"/>
        
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
        
        <!-- AOS css and JS -->
        <link rel="stylesheet" href="resource/aos/aos.css">
        <script src="resource/aos/aos.js"></script>
        <!-- AOS css and JS END-->

        <!-- fontawesome -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" >
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <!-- fontawesome END -->

        <!-- Lightbox CSS & Script -->
        <script src="resource/lightbox/ekko-lightbox.js"></script>
        <link rel="stylesheet" href="resource/lightbox/ekko-lightbox.css"/>

        

        <!-- custom css-->
        <link rel="stylesheet" href="resource/custom.css"/>
        <!-- custom css END-->

        <meta name="author" content="Rahul Chauhan"/>
        <meta name="description" content="This is a Auto Insurance website"/>
        <meta name="keywords" content="best Insurance comapny in noida"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm sticky-top bg-light">
            <a class="navbar-brand c-font" href="">
              <img class="navbar-brand c-font" src='resource/autoinsurance-icon.png' width="30px"/> <span class="text-danger font-weight-bold ">Auto</span><span class="text-muted">Insurance</span>
            </a>
            <button class="navbar-toggler bg-light" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <!-- <span class="navbar-toggler-icon text-dark"> <i class="fas fa-sort-down"></i> </span> -->
                <span class="fa fa-bars"></span> Menu
            </button>
            <div class="collapse navbar-collapse " id="collapsibleNavbar"  >
              <ul class="navbar-nav mx-auto ">
                <li class="nav-item">
                  <a class="nav-link text-secondary" href="/">HOME</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link text-secondary" href="Company">Company</a>
                </li>  
              </ul>
              <div >
                  <a class="text-danger h5 mr-3" href="tel:+91-9811981198">CALL US TODAY: +91-9811981198</a>
              </div>
            </div>
        </nav>
        <c:if test="${result!=null}">
        	<p style='background-color:yellow;text-align:center;padding:10px;'> ${result} </p>
        </c:if>
       	<div class='container p-4'>
       		<div class='row'>
       			<div class='col-sm-4 bg-light p-4'>
       				<h4>Company Login:</h4>
       				<hr/>
		            <form action="CompanyLogin" method="post">
		              <div class="form-group">
		                <label>Company Email ID:</label>
		                <input type="email" class="form-control" placeholder="Enter email id" id="email" name="email" required>
		              </div>
		              <div class="form-group">
		                <label>Password:</label>
		                <input type="password" class="form-control" placeholder="Enter password" id="pwd" name="password" required>
		              </div>
		              <button type="submit" class="btn btn-primary">Login</button>
		            </form>
       			</div>
       			<div class='col-sm-2 '>
       			</div>
       			<div class='col-sm-6 bg-light p-4'>
       				<h4>Company Registration:</h4>
       				<hr/>
		            <form action="CompanyRegister" method="post">
		              <div class="form-group">
		                <label>Company Email ID:</label>
		                <input type="email" class="form-control" placeholder="Enter email id" id="email" name="email" required>
		              </div>
		              <div class="form-group">
		                <label>Company Name:</label>
		                <input type="text" class="form-control" pattern="[a-z A-Z]+" placeholder="Enter Company Name" id="name" name="name" required>
		              </div>
		              <div class="form-group">
		                <label>Registration Number:</label>
		                <input type="text" class="form-control" placeholder="Enter Registration Number" id="registration" name="registration" required>
		              </div>
		              <div class="form-group">
		                <label>Contact Number:</label>
		                <input type="text" maxlength="10" minlength="10" pattern="[0-9]+" class="form-control" placeholder="Contact Number" id="phone" name="phone" required>
		              </div>
		              <div class="form-group">
		                <label>Password:</label>
		                <input type="password" class="form-control" placeholder="Enter password" id="pwd" name="password" required>
		              </div>
		              <div class="form-group">
		                <label>Address:</label>
		                <textarea rows='3' class="form-control" placeholder="Enter Company Address" id="address" name="address" required></textarea>
		              </div>
		              <input type="hidden" name="status" value="Pending"/> 
		              <button type="submit" class="btn btn-primary">Register</button>
		            </form>
       			</div>
       		</div>
       	</div>
            
            
        <footer class="bg-dark p-4">
          <p class="text-white">
            Design by <a href="https://www.incapp.in" target="_blank">INCAPP</a> &copy; Rights reserved
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            Social Media: 
            <a href="https://facebook.com/incapp"><i class="fab fa-facebook"></i></a>
            &nbsp;
            <a href="https://instagram.com/incapp.in"><i class="fab fa-instagram"></i></a>
          </p>
        </footer>

        
    </body>
</html>