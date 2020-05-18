<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Trang chủ">
    <jsp:attribute name="content">
    <main>
        <div class="container-fluid">
            <div class="text-center" style="margin-top: 100px;">
                <ul style="list-style: none">
                    <li ><a class="btn btn-info" href="<c:url value="/customers/list" /> ">Gửi thông báo qua email</a></li>
                    <li style="margin-top: 20px"><a class="btn btn-info" href="<c:url value="/customers/paid" />">Đã đóng tiền</a></li>
                    <li style="margin-top: 20px"><a class="btn btn-info" href="<c:url value="/customers/not-pay" />">Chưa đóng tiền</a></li>
                    <li style="margin-top: 20px"><a class="btn btn-info" href="<c:url value="/export-report" />">Xuất báo cáo</a> </li>
                    <li style="margin-top: 20px"><a class="btn btn-info" href="./config.jsp">Cấu hình hệ thống</a> </li>
                </ul>
            </div>
        </div>
    </main>
    </jsp:attribute>
</mt:app>
            
            
