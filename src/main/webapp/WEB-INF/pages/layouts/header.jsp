<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-ct-primary" role="navigation-demo"
	style="background-color: #7fefb6">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navigation-example-2">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">BoomGM</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navigation-example-2">
			<ul class="nav navbar-nav">
				<a href="${pageContext.request.contextPath}" target="_blank"
					class="btn btn-primary">Trang chủ</a>
				<a href="${pageContext.request.contextPath}/goods/all"
					target="_blank" class="btn btn-primary ">Nông sản</a>
				<a href="${pageContext.request.contextPath}/farm/getbyuser"
					target="_blank" class="btn btn-primary ">Nhà Vườn</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"> <i class="fa fa-facebook-square"></i>
						Facebook
				</a></li>
				<li><a href="#"> <i class="fa fa-twitter"></i> Twitter
				</a></li>
				<li><a href="#"> <i class="fa fa-envelope"></i> Email
				</a></li>
				<li><c:if
						test="${pageContext.request.userPrincipal.name != null}"> ${pageContext.request.userPrincipal.name}
  
   | &nbsp;
     <a href="${pageContext.request.contextPath}/logout">Logout</a>

					</c:if></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-->
</nav>