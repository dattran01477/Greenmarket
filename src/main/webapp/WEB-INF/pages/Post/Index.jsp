<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="u"%>
<div class="row" style="margin:50px">
      <div class="col-sm-3 col-md-offset-2">
       <img src="${pageContext.request.contextPath}/static/assets/paper_img/traicheri.jpg" alt="Rounded Image" class="img-rounded img-responsive">
      </div>
      <div class="col-sm-5">
        <h2> <b> ${post.title}</b></h2>
        <hr>
        <h4> Bài viết được tạo bởi<a href="#"> ${post.user.name}</a></h4>
        <h5> Ngày đăng ${post.createdtime} </h5>
        
        <br>
        <br>
        Lượt xem   : 174
        <i class="fa fa-eye"></i>
        
      </div>
    </div>
    <hr>
    <div class="row" style="margin:20px">
    <div class="col-md-8 col-md-offset-2">
        <h2 class="text-center" style="margin-bottom:20px"> Giới thiệu nông sản</h2>
    
    	<p> ${post.content }
    
    </div>
    </div>
    
    <hr>
<div class="row" style="margin:50px">
	<div class="col-sm-8 col-md-offset-2">

	<h3><b>Nhận xét</b></h3>
       <textarea class="form-control" rows="4"  placeholder="Để lại bình luận của bạn ở đây" style="background-color:#fceffa;color:#000; margin-left: 20px;margin-right:20px"></textarea>
       <div class="row">
           <div class="col-md-4 ">
               <button class="btn btn-danger btn-block btn-lg btn-fill" style="margin:20px">Bình luận</button>
           </div>
       </div>
    <div>
    <blockquote>
      <div class="img-details">
                             <div class="logo-container">
                <div class="logo">
                    <img src="assets/paper_img/new_logo.png" alt="Creative Tim Logo">
                </div>
                <h3>
                    Thành Đồng
                </h3>
            </div>


                            </div>
        <h5>
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam.
        </h5>

    </blockquote>
    </div>
<div>
    
    <blockquote>
      <div class="img-details">
                             <div class="logo-container">
                <div class="logo">
                    <img src="assets/paper_img/new_logo.png" alt="Creative Tim Logo">
                </div>
                <h3>
                    Creative Tim
                </h3>
            </div>


                            </div>
        <h5>
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam.
        </h5>

    </blockquote>
    </div>
</div>
</div>
