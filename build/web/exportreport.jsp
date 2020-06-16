<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Xuất báo cáo">
    <jsp:attribute name="content">
    <main>
        <div class="container-fluid" style="background-color: white;">
            <br/>
            <form method="post" action="<c:url value="/export-report"/>" id="dayfilter" onsubmit="return check()">
                <div class="row">
                    <div class="col-3">
                        Trạng thái
                        <select name="status" id="status">
                            <option value="2">Tất cả</option>
                            <option value="0">Chưa thanh toán</option>
                            <option value="1">Đã thanh toán</option>
                        </select>
                    </div>
                    <div class="col-4">
                        Từ ngày <input type="date" id="start_date" name="start_date">
                    </div>
                    <div class="col-4">
                        Đến ngày<input type="date" id="end_date" name="end_date">
                    </div>
                    <div class="col-1">
                        <input type="submit" id="filter" name="filter" value="Lọc">
                    </div>
                </div>
            </form>
            <br/>
            <div class="table-responsive">
                <form data-id="${type}" id="export-form">
                    <input type="hidden" value="${start}" id="s"/>
                    <input type="hidden" value="${end}" id="e"/>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <td>#</td>
                            <td>Họ tên</td>
                            <td>Số CMND</td>
                            <td>Số tiền</td>
                            <td>Trạng thái</td>
                            <td>Ngày đóng</td>
                            <td>Địa chỉ</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listMeters}" var="meter" varStatus="loop">
                        <tr>
                            <td class="stt">${loop.index + 1}</td>
                            <td class="name">${meter.getCustomer().getName().toString()}</td>
                            <td class="phone_number">${meter.getCustomer().getPhone_number()}</td>
                            <td class="amount">${meter.getAmount()}</td>
                            <td class="tbStage">
                                <c:if test="${meter.getCustomer().getStage() == 0}">
                                    Chưa thanh toán
                                </c:if>
                                <c:if test="${meter.getCustomer().getStage() == 1}">
                                    Đã thanh toán
                                </c:if>
                                <c:if test="${meter.getCustomer().getStage() == 2}">
                                    Đang nợ
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${meter.getCustomer().getStage() == 1}">
                                    ${meter.getStart_date()}
                                </c:if>
                            </td>
                            <td class="tbLocation">
                                <c:forEach items="${meter.getCustomer().getLocation()}" var="location">
                                    ${location.toString()}
                                </c:forEach>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                    <div class="container-fluid">
                        <div class="row">
                            Tổng tiền: ${total} VND
                        </div>
                        <div class="row">
                            <input class="btn btn-danger" type="submit" value="Xuất báo cáo">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </main>
    <script type="text/javascript">
        function check() {
            var url = $(this).attr("action");
            var start_date = $("#start_date").val();
            var end_date = $("#end_date").val();
            var status = $("#status option:selected").val();
            if (start_date > end_date) {
                Swal.fire({
                   icon: 'error',
                   title: 'Chọn ngày bắt đầu nhỏ hơn ngày kết thúc',
                });
                return false;
            }
            if (start_date == '') {
                Swal.fire({
                   icon: 'error',
                   title: 'Chọn ngày bắt đầu',
                });
                return false;
            }
            if (end_date == '') {
                Swal.fire({
                   icon: 'error',
                   title: 'Chọn ngày kết thúc',
                });
                return false;
            }
            return true;
        };
        $('#export-form').submit(function(event){
           event.preventDefault();
           var type = $(this).data("id");
           $.ajax({
              type: 'post',
              url: '/SQA/export',
              data:{
                  status: type,
                  start_date: $('#s').val(),
                  end_date: $('#e').val()
              },
              success: function(response) {
                  Swal.fire({
                   icon: 'success',
                   title: 'Tạo thành công',
                });
              },
              error: function(error){
                  console.log(error);
              }
           });
        });
    </script>
    </jsp:attribute>
</mt:app>
            
            
