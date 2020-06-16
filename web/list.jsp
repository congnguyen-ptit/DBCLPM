<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="${tit}">
    <jsp:attribute name="content">
    <main>
        <div class="container-fluid" style="background-color: white;">
            <br/>
                <c:if test="${notPay == true}">
                    <form action="<c:url value="/customers/not-pay"/>" method="post">
                        <div class="row">
                            <div class="col-2">
                                Tháng
                                <select name="time">
                                    <option value="0">All</option>
                                    <c:forEach items="${listTime}" var="time">
                                        <option value="${time.getId()}">${time.getName()}</option>
                                    </c:forEach> 
                                </select>
                            </div>
                            <div class="col-2">
                                <input type="submit" value="Lọc">
                            </div>
                        </div>
                    </form>
                </c:if>
                <c:if test="${notPay != true}">
                        <form action="<c:url value="/customers/paid"/>" method="post">
                        <div class="row">
                            <div class="col-2">
                                Tháng
                                <select name="time">
                                    <option value="0">All</option>
                                    <c:forEach items="${listTime}" var="time">
                                        <option value="${time.getId()}">${time.getName()}</option>
                                    </c:forEach> 
                                </select>
                            </div>
                            <div class="col-2">
                                <input type="submit" value="Lọc">
                            </div>
                        </div>
                    </form>
                </c:if>
            <br/>
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <td>#</td>
                            <td>Họ tên</td>
                            <td>Email</td>
                            <td>Số điện thoại</td>
                            <td>Trạng thái</td>
                            <td>Địa chỉ</td>
                            <td>Hành động</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listCustomers}" var="customer" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td class="tbName">${customer.getName().toString()}</td>
                            <td class="tbEmail">${customer.getEmail()}</td>
                            <td class="tbPhone">${customer.getPhone_number()}</td>
                            <td class="tbStage">
                                <c:if test="${customer.getStage() == 0}">
                                    Chưa thanh toán
                                </c:if>
                                <c:if test="${customer.getStage() == 1}">
                                    Đã thanh toán
                                </c:if>
                                <c:if test="${customer.getStage() == 2}">
                                    Đang nợ
                                </c:if>
                            </td>
                            <td class="tbLocation">
                                <c:forEach items="${customer.getLocation()}" var="location">
                                    ${location.toString()}
                                </c:forEach>
                            </td>
                            <td><a class="btn btn-info" href="<c:url value="/customer/send?id=${customer.getId()}" />">Gửi email</a></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>        
             </div>
        </div>
    </main>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#filter').click(function(event) {
                event.preventDefault();
                var start_date = $('#start_date').val();
                var end_date = $('#end_date').val();
                if (start_date === '') {
                    swal.fire({
                       title: 'Chọn ngày bắt đầu!',
                       icon: 'warning',
                    });
                    return;
                }
                if (end_date === '') {
                    swal.fire({
                       title: 'Chọn ngày kết thúc!',
                       icon: 'warning',
                    });
                    return;
                }
                $.ajax({
                   type: 'post',
                   url: "/day",
                   success: function(response) {
                       console.log(response);
                   },
                   error: function(error) {
                       console.log(error);
                   }
                });  
            });   
        });
        
    </script>
    </jsp:attribute>
</mt:app>
            
            
