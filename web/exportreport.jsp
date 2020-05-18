<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Xuất báo cáo">
    <jsp:attribute name="content">
    <main>
        <div class="container-fluid">
            <br/>
            <div class="row">
                <div class="col-3">
                    Từ ngày <input type="date" id="start_date" name="start_date">
                </div>
                <div class="col-3">
                    Đến ngày<input type="date" id="end_date" name="end_date">
                </div>
                <div class="col-4">
                    <input type="submit" id="filter" name="filter" value="Lọc">
                </div>
            </div>
            <br/>
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <td>#</td>
                            <td>Name</td>
                            <td>Email</td>
                            <td>Phone number</td>
                            <td>Amount</td>
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
                            <td>100000</td>
                            <td>Chua tra</td>
                            <td><a class="btn btn-info">Details</a></td>
                        </tr>
                    </tbody>
                </table>
             </div>
            <div class="row">
                Total Amount: 300000 VND
            </div>
            <br>
            <div class="row">
                <a class="btn btn-danger">Export</a>
            </div>
        </div>
    </main>
    </jsp:attribute>
</mt:app>
            
            
