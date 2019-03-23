<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="u"%>

            <div class="section landing-section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <h2 class="text-center">Thêm thông tin nhà vườn</h2>
                            <hr>
                            <u:form class="contact-form" method="POST" action="addFarm" modelAttribute="farm" >
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Chủ nhà vườn</label>
                                        <u:input class="form-control" placeholder="Nguyễn Văn A" path="farmer"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Tỉnh thành</label>
										<u:select class="form-control" path="provinceid">
                                        	<option> Chọn đi </option>
                                        </u:select>                                    
                                   	</div>
                                </div>
                                <hr>
                                
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Điện thoại</label>
                                        <u:input class="form-control" placeholder="(+84) XXX YYYY" path="phone"/>
                                    </div>
                                </div>
                                <hr>
   								<label>Địa chỉ</label>
                                <u:textarea class="form-control" rows="2" placeholder="Địa chỉ nông dân..." path="address"></u:textarea>
                                <hr>
                                <div class="row">
                                    <div class="col-md-10">
                                        <label>Hình ảnh</label>
                                        <u:input class="form-control" placeholder="Đường dẫn" path="images"/>
                                    </div>
                                    <div class="col-md-2">
                                        <label></label>
                                        <button class="form-control btn btn-success" style="margin-top:5px" >Chọn</button>
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