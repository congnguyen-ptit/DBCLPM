<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Gửi thông báo">
    <jsp:attribute name="content">
    <main>
        <br>
        <section class="page-section" id="contact">
            <div class="container" style="background-color: white;">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">Send email to: ${customer.getName().toString()}</h2>
                </div>
                <br>
                <form id="contactForm" action="" novalidate="novalidate" method="post" data-url="<c:url value="/customer/send" />">
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
                                Số điện thoại
                                <input class="form-control" id="phone" placeholder="${customer.getPhone_number()}" disabled />
                            </div>
                            <br>
                            <div class="form-group mb-md-0">
                                Địa chỉ
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
                            <input class="form-control" type="text" name="subject" placeholder="Nhập tiêu đề">
                            <div class="form-group">
                                <font color="#F24638" id="subject-error"></font>
                            </div>
                            <div>Nội dung Email</div>
                            <div class="form-group form-group-textarea mb-md-0">
                                <textarea rows="15" class="form-control" name="content" placeholder="Hãy nhập nội dung mail" required="required" data-validation-required-message="Hãy nhập nội dung mail"></textarea>
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <font color="#F24638" id="content-error"></font>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <div id="success"></div>
                        <button class="btn btn-primary btn-xl text-uppercase" id="sendMessageButton" type="submit">Gửi</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#contactForm').submit(function(event) {
                event.preventDefault();
                var url = $(this).data('url');
                $('#subject-error').text('');
                $('#content-error').text('');
                var subject = $('#contactForm input[name=subject]').val();
                var content = $('#contactForm textarea[name=content]').val();
                if (subject == '') {
                    $('#subject-error').text('Nhập tiêu đề');
                }
                if (content == '') {
                    $('#content-error').text('Nhập nội dung');
                }
                if (subject != '' && content != '') {
                    $.ajax({
                        type: 'post',
                        url: url,
                        data: {
                            subject: subject, 
                            content: content,
                        },
                        success: function(response) {
                            if (response.url) { 
                                Swal.fire({
                                    title: 'Gửi thành công',
                                    width: 600,
                                    padding: '3em',
                                    background: '#fff url(/images/trees.png)',
                                    backdrop: `
                                      rgba(0,0,123,0.4)
                                      url("/images/nyan-cat.gif")
                                      left top
                                      no-repeat
                                    `
                                });
                                window.location = response.url;
                            }
                            if (response.error) {
                                Swal.fire({
                                    title: 'Gửi mail thất bại, thử lại sau',
                                    icon: 'error',
                                });
                                window.location.replace(response.url); 
                            }
                        },
                    });
                }
            });
        });
    </script>
    </jsp:attribute>
</mt:app>
            
            
