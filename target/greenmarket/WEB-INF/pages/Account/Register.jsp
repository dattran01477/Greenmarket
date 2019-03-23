<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="u"%>
 <nav class="navbar navbar-ct-transparent navbar-fixed-top" role="navigation-demo" id="register-navbar">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
            <span class="icon-bar">a</span>
            <span class="icon-bar">a</span>
            <span class="icon-bar">a</span>
          </button>
          <a class="navbar-brand" href="#">Boom Team</a>
        </div>
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navigation-example-2">
          <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="#" class="btn btn-simple">Trang chủ</a>
            </li>
            <li>
                <a href="#" class="btn btn-simple">Phản hồi</a>
            </li>
            <li>
                <a href="#" target="_blank" class="btn btn-simple"><i class="fa fa-twitter"></i></a>
            </li>
            <li>
                <a href="#" target="_blank" class="btn btn-simple"><i class="fa fa-facebook"></i></a>
            </li>
           </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-->
    </nav> 
    
    <div class="wrapper">
        <div class="register-background" style="background: url('${pageContext.request.contextPath}/static/assets/paper_img/landscape.jpg')"> 
            <div class="filter-black"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1 ">
                            <div class="register-card">
                                <h3 class="title"></h3>
                                <u:form class="register-form" method="POST" action="register" modelAttribute="refix">
                                    <label>Tài khoản</label>
 
                                    <u:input type="text" class="form-control" placeholder="Email" path="username"/>

                                    <label>Mật khẩu</label>
                                    <u:input type="password" class="form-control" placeholder="Password" path="password" />
                                    <label>Tên</label>
                                    <u:input type="text" class="form-control" placeholder="Name" path="name" />
                                    <label>Số điện thoại</label>
                                    <u:input type="text" class="form-control" placeholder="Phone" path="phone" />
                                    <button type="submit" class="btn btn-danger btn-block">Register</button>
                                   
                                </u:form>

                            </div>
                        </div>
                    </div>
                </div>     
            <div class="footer register-footer text-center">
                    <h6>&copy; 2015, made with <i class="fa fa-heart heart"></i> by BoooooooooooooM</h6>
            </div>
        </div>
    </div>      
