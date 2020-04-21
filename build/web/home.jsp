<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Admin home">
    <jsp:attribute name="content">
    <main>
        <div class="container-fluid">
            <div class="text-center" style="margin-top: 100px;">
                <ul style="list-style: none">
                    <li ><a class="btn btn-info" href="./sendnotification.jsp">Send Email</a></li>
                    <li style="margin-top: 20px"><a class="btn btn-info" href="./list.jsp">View List</a></li>
                    <li style="margin-top: 20px"><a class="btn btn-info" href="./config.jsp">Config</a> </li>
                    <li style="margin-top: 20px"><a class="btn btn-info" href="./config.jsp">Export Report</a> </li>
                </ul>
            </div>
        </div>
    </main>
    </jsp:attribute>
</mt:app>
            
            
