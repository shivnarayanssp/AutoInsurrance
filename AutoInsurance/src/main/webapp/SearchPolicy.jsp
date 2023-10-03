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
        <p style='background-color:yellow;text-align:center;'> ${loginResult} </p>
        <c:if test="${userName==null}">
	        <nav class="navbar navbar-expand-sm sticky-top bg-light">
	            <a class="navbar-brand c-font" href="index.jsp">
	              <img src='resource/autoinsurance-icon.png' width="30px"/> <span class="text-danger font-weight-bold ">Auto</span><span class="text-muted">Insurance</span>
	            </a>
	            <button class="navbar-toggler bg-light" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	                <!-- <span class="navbar-toggler-icon text-dark"> <i class="fas fa-sort-down"></i> </span> -->
	                <span class="fa fa-bars"></span> Menu
	            </button>
	            <div class="collapse navbar-collapse " id="collapsibleNavbar"  >
	              <ul class="navbar-nav mx-auto ">
	                <li class="nav-item">
	                  <a class="nav-link text-secondary" href="index.jsp">HOME</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link text-secondary" href="" data-toggle="modal" data-target="#adminModal" >Admin</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link text-secondary" href="Company.jsp">Company</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link text-secondary" href="" data-toggle="modal" data-target="#signInModal">User-Sign In</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link text-secondary" href="" data-toggle="modal" data-target="#signUpModal">User-Sign Up</a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link text-secondary" href="contact.jsp">CONTACT</a>
	                </li>    
	              </ul>
	              <div >
	                  <a class="text-danger h5 mr-3" href="tel:+91-9811981198">CALL US TODAY: +91-9811981198</a>
	              </div>
	            </div>
	        </nav>
        </c:if>
        <c:if test="${userName!=null}">
        <nav class="navbar navbar-expand-sm sticky-top bg-light">
			<img class="navbar-brand c-font" src='resource/autoinsurance-icon.png'
				width="30px" /> <span class="text-danger font-weight-bold ">Auto</span><span
				class="text-muted">Insurance</span>
	
			<button class="navbar-toggler bg-light" type="button"
				data-toggle="collapse" data-target="#collapsibleNavbar">
				<!-- <span class="navbar-toggler-icon text-dark"> <i class="fas fa-sort-down"></i> </span> -->
				<span class="fa fa-bars"></span> Menu
			</button>
			<div class="collapse navbar-collapse " id="collapsibleNavbar">
				<ul class="navbar-nav mx-auto ">
					<li class="nav-item"><a class="nav-link text-secondary"
						href="/">HOME</a></li>
					<li class="nav-item"><label class="nav-link"> Welcome:
							<b>${userName}</b> 
							<img src="getUserImage?email=${userEmail}" height="30px"/>
					</label></li>
					<li class="nav-item"><a class="nav-link text-secondary"
						href="UserProfile">Profile</a></li>
					<li class="nav-item"><a class="nav-link text-secondary"
					href="UserPolicies">View Policies</a></li>
					<li class="nav-item"><a class="nav-link text-secondary"
						href="Logout">Logout</a></li>
				</ul>
				<div>
					<a class="text-danger h5 mr-3" href="tel:+91-9811981198">CALL US
						TODAY: +91-9811981198</a>
				</div>
			</div>
		</nav>
		</c:if>
        <section class='container p-4'>
        	
        	<c:forEach items="${policies}" var="policy">
				<div class='p-2'>
    				<p class='bg-light p-2 my-2'>
    				Company Name: <b>${policy.cname}</b> <img src="getImage?email=${policy.email}" height="30px"/>
    				</p>
					<hr/>
					<p class='bg-light p-2 my-2'>  
    				Name: <b>${policy.pname}</b>  
    				Amount: <b>${policy.amount}</b>
    				Category: <b>${policy.category}</b>
    				<br/>
    				Description: <b>${policy.description}</b>
    				<form action="" method="post">
    					<button type="submit" class="btn btn-success">Get Policy</button>
    				</form>
    				<br/>
    				</p>
    			</div>
			</c:forEach>
   			
        </section>
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

        <a id="myTopBtn"><i class="fas fa-chevron-circle-up display-4 text-danger"></i></a>

        <!-- Modal Admin login code -->
        <div class="modal fade" id="adminModal"  aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header bg-danger text-white">
                <h3 class="modal-title" >Admin Login</h3>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form action="AdminLogin" method="post">
                  <div class="form-group">
                    <label for="email">Admin Email ID:</label>
                    <input type="text" class="form-control" placeholder="Enter admin id" id="aid" name="aid" required>
                  </div>
                  <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" placeholder="Enter password" id="pwd" name="password" required>
                  </div>
                  <button type="submit" class="btn btn-primary">Login</button>
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <!-- Modal Sign IN code -->
        <div class="modal fade" id="signInModal"  aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header bg-primary text-white">
                <h3 class="modal-title" >User Login</h3>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form action="UserLogin" method="post">
                  <div class="form-group">
                    <label for="email">User Email ID:</label>
                    <input type="email" class="form-control" placeholder="Enter User email id" id="email" name="email" required>
                  </div>
                  <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" placeholder="Enter password" id="pwd" name="password" required>
                  </div>
                  <button type="submit" class="btn btn-primary">Login</button>
                </form>
                <a href='ForgetPassword.jsp'>Forget Password?</a>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <!-- Modal Sign UP code -->
        <div class="modal fade" id="signUpModal"  aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header bg-success text-white">
                <h3 class="modal-title" >User Registration</h3>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form action="UserRegister" method="post" enctype="multipart/form-data">
                  <div class="form-group">
                    <label for="email">User Email ID:</label>
                    <input type="email" class="form-control" placeholder="Enter email id" id="email" name="email" required>
                  </div>
                  <div class="form-group">
                    <label for="email">User Name:</label>
                    <input type="text" class="form-control" placeholder="Enter name" id="name" name="name" required>
                  </div>
                  <div class="form-group">
                    <label for="email">User Phone:</label>
                    <input type="text" class="form-control" placeholder="Enter Phone" id="phone" name="phone" required>
                  </div>
                  <div class="form-group">
                    <label for="email">User Address:</label>
                    <textarea rows='3' class="form-control" placeholder="Enter Address" name="address" required></textarea>
                  </div>
                  <div class="form-group">
                    <label for="email">User Photo:</label>
                    <input type="file" class="form-control" name="photo" required>
                  </div>
                  <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" placeholder="Enter password" id="pwd" name="password" required>
                  </div>
                  <button type="submit" class="btn btn-primary">Register</button>
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
    </body>
    <script>
      //Script for Scroll to Top button for hide and show
      $(window).scroll(function() {
          if ($(this).scrollTop()) {
              $('#myTopBtn').fadeIn();
          } else {
              $('#myTopBtn').fadeOut();
          }
      });
      //script for scroll to top
      $("#myTopBtn").click(function () {
        $("html, body").animate({scrollTop: 0}, 1000);
      });

      
    </script>
</html>