<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="List">
    <jsp:attribute name="content">
    <main>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <input type="date" name="start_date">
                </div>
                <div class="col-md-4">
                    <input type="date" name="end_date">
                </div> 
                <div class="col-md-4">
                    <input type="submit" name="filter" value="Filter">
                </div>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <td>#</td>
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
                            <td><input type="checkbox"></td>
                            <td>1</td>
                            <td>Cong Nguyen</td>
                            <td>congnx@gmail.com</td>
                            <td>091681468</td>
                            <td>Chua tra</td>
                            <td><a class="btn btn-info">Send email</a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>2</td>
                            <td>Nguyen Cong</td>
                            <td>gnx@gmail.com</td>
                            <td>011681468</td>
                            <td>Chua tra</td>
                            <td><a class="btn btn-info">Send email</a></td>
                        </tr>
                    </tbody>
                </table>
             </div>
        </div>
    </main>
    </jsp:attribute>
</mt:app>
            
            
