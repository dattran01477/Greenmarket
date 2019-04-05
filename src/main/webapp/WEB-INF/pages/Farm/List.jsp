<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-6 col-md-offset-3">
	<div class="alert alert-success text-center" style="color:#000"> <h3><b>Danh sách nhà vườn đăng tải </b></h3> </div>
</div>
<c:forEach items="${lstFarm}" var="farm">
 <div class="row">

<a href="${pageContext.request.contextPath}/farm/get/${farm.id}">
<div style="margin:20px; padding:30px" margin="margin:30px">
      <div class="col-md-8 col-md-offset-2 img-rounded">
          <div class="col-md-4">
              <img src="${pageContext.request.contextPath}/static/assets/paper_img/traicheri.jpg" alt="Rounded Image" class="img-rounded img-responsive" >
          </div>
          <div class="col-md-8">
              <h3> Chủ nhà vườn ${farm.farmer }
              </h3>
              <h3> Điện thoại: 0${farm.phone }
              </h3>
              <p> Địa chỉ: ${farm.address} </p>
          </div>
      </div>
      </div>
</a>
</div>
</c:forEach>
 
