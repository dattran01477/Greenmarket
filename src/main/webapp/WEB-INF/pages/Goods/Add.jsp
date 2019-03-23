<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="u"%>

            <div class="section landing-section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <h2 class="text-center">Thêm thông tin nông sản</h2>
                            <hr>
                            <u:form class="contact-form" method="POST" action="addGoods" modelAttribute="Goods">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Tên nông sản</label>
                                        <u:input class="form-control" placeholder="Tên nông sản" path="product"/>
                                    </div>
                                   <div class="col-md-6">
                                        <label>Giá giao động</label>
                                        <u:input class="form-control" type="number" placeholder="VNĐ" path="price"/>
                                    </div>
                                </div>
                                
                                <hr>
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Điểm đánh giá</label>
                                        <u:input type="number" class="form-control" placeholder="Tên nông sản" path="quality"/>
                                    </div>
                                   <div class="col-md-6">
                                        <label>Danh mục nông sản</label>
                                      <%--   <u:select class="form-control" path="category">
                                        	<option> Đồng đẹp trai </option>
                                    	</u:select> --%>
                                    </div>
                                </div>
                                
                                <hr>
                              
                              	<div class="row">
                                    <div class="col-md-12">
                                        <label>Nhà vườn sỡ hữu</label>
                                       <%--  <u:select class="form-control" path="farm">
                                        	<option> Nhà của đồng đẹp trai hết sức</option>
                                    	</u:select> --%>
                                    </div>
                                </div>
                                
                                <hr>
                              
                                <div class="row">
                                    <div class="col-md-10">
                                        <label>Hình ảnh</label>
                                        <u:input class="form-control" placeholder="Đường dẫn" path="images"/>
                                    </div>
                                    <div class="col-md-2">
                                        <label></label>
                                        <button class="form-control btn btn-success" style="margin-top:5px">Chọn</button>
                                    </div>
                                </div>
                                
                                <hr>
                                
                              <div class="row" style="margin-top:20px">
                                    <div class="col-md-4 col-md-offset-4">
                                        <button type="submit" class="btn btn-danger btn-block btn-lg btn-fill">Lưu thông tin</button>
                                    </div>
                                </div>
                            </u:form>
                            <hr>
                        </div>
                    </div>
                    
                </div>
            </div>