<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Kết quả tìm kiếm">
    <jsp:attribute name="content">
    <main>
        <div class="container-fluid">
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
                            <td>${customer.getName().toString()}</td>
                            <td>${customer.getEmail()}</td>
                            <td>${customer.getPhone_number()}</td>
                            <td>
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
                            <td>
                                <c:forEach items="${customer.getLocation()}" var="location">
                                    ${location.toString()}
                                </c:forEach>
                            </td>
                            <td><a class="btn btn-info" href="<c:url value="/customer/send?id=${customer.getId()}" />">Send email</a></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
             </div>
        </div>
    </main>
    </jsp:attribute>
</mt:app>