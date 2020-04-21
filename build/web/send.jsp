<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Send">
    <jsp:attribute name="content">
    <main>
<!--        <div class="container">
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <td>Id</td>
                            <td>Name</td>
                            <td>Email</td>
                            <td>Phone number</td>
                            <td>State</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Cong Nguyen</td>
                            <td>congnx@gmail.com</td>
                            <td>091681468</td>
                            <td>Chua tra</td>
                            <td><a class="btn btn-info">Send email</a><a class="btn btn-warning" href="sendnotification.jsp">Cancel</a></td>
                        </tr>
                    </tbody>
                </table>
                <div>
                   <textarea rows="4" cols="100"></textarea>
                </div>
             </div>
        </div>-->
        <br>
        <section class="page-section" id="contact">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Send email to: ${customer.getName().toString()}</h2>
                </div>
                <br>
                <form id="contactForm" name="sentMessage" novalidate="novalidate">
                    <div class="row align-items-stretch mb-5">
                        <div class="col-md-4">
                            <div class="form-group">
                                Họ tên
                                <input class="form-control" id="name" placeholder="${customer.getName().toString()}" disabled />
                            </div>
                            <div class="form-group">
                                Email
                                <input class="form-control" id="email" placeholder="${customer.getEmail()}" disabled />
                            </div>
                            <div class="form-group mb-md-0">
                                Phone number
                                <input class="form-control" id="phone" placeholder="${customer.getPhone_number()}" disabled />
                            </div>
                            <br>
                            <div class="form-group mb-md-0">
                                Location
                                <c:forEach items="${customer.getLocation()}" var="location" >
                                    <input class="form-control" id="phone" placeholder="${location.toString()}" disabled />
                                </c:forEach>
                            </div>
                            <br>
                             <div class="form-group mb-md-0">
                                Trạng thái
                                <c:if test="${customer.getStage() == 0}">
                                    <input class="form-control" id="phone" placeholder="Chưa thanh toán" disabled />
                                </c:if>
                                <c:if test="${customer.getStage() == 1}">
                                    <input class="form-control" id="phone" placeholder="Đã thanh toán" disabled />
                                </c:if>
                                <c:if test="${customer.getStage() == 2}">
                                    <input class="form-control" id="phone" placeholder="Đang nợ" disabled />
                                </c:if>
                            </div>
                        </div>
                        <div class="col-md-8">
                            Nội dung Email
                            <div class="form-group form-group-textarea mb-md-0">
                                <textarea rows="15" class="form-control" id="message" placeholder="Your Message *" required="required" data-validation-required-message="Hãy nhập nội dung mail"></textarea>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <div id="success"></div>
                        <button class="btn btn-primary btn-xl text-uppercase" id="sendMessageButton" type="submit">Send Message</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
    </jsp:attribute>
</mt:app>
            
            
