<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Send">
    <jsp:attribute name="content">
    <main>
        <div class="container">
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <td>Receiver(s): All</td>     
                            <td><a class="btn btn-info">Send</a>
                                <a class="btn btn-info">Cancel</a>
                            </td>
                        </tr>
                    </thead>
                </table>
                <div>
                   <textarea rows="4" cols="100"></textarea>
                </div>
             </div>
        </div>
    </main>
    </jsp:attribute>
</mt:app>
            
            
