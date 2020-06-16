<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Cấu hình">
    <jsp:attribute name="content">
    <main>
        <div class="container-fluid">
            <div class="text-center" style="margin-top: 100px;">
                <ul style="list-style: none">
                    <c:forEach items="${listLevel}" var="level" >
                        <li style="margin-top: 20px"><a class="btn btn-info show-level" href="#" data-id="${level.getId()}">${level.getName()}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="modal" id="updateModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Cập nhật</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="" data-url="<c:url value="/config/update" />" id="updateForm">
                            <input type="hidden" name="id" id="id" value=""/>
                            <div class="form-group">
                                Tên 
                                <input class="form-control" id="name" value=""/>
                            </div>
                            <div class="form-group">
                                Đơn giá
                                <input class="form-control" id="price"  value=""/>
                            </div>
                            <div class="form-group">
                                Chỉ số bắt đầu
                                <input class="form-control" id="start_index" value="" />
                            </div>
                            <div class="form-group">
                                Chỉ số kết thúc
                                <input class="form-control" id="end_index"  value="" />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="update">Cập nhật</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script type="text/javascript">
        $('.show-level').click(function(event) {
            event.preventDefault();
            var id = $(this).data('id');
            console.log(id);
            $.ajax({
                type: 'post',
                url: '/SQA/config',
                data: {
                    id: id,
                },
                success: function(res) {
                    console.log(res);
                    $('#updateModal').modal('show');
                    $('#name').val(res.name);
                    $('#price').val(res.price);
                    $('#start_index').val(res.start_index);
                    $('#end_index').val(res.end_index);
                    $('#id').val(res.id);
                }
            });
        });
        
        $('#update').click(function() {
            var url = $('#updateForm').data('url');
            var id = $('#id').val();
            var name = $('#name').val();
            var price = $('#price').val();
            var start_index = $('#start_index').val();
            var end_index = $('#end_index').val();
            console.log(name + "//" + price + "//" + start_index + "//" + end_index);
            if (name == '') {
                swal.fire({
                   icon: 'warning',
                   title: 'Không để trống tên',
                });
                $('.name').focus();
                return;
            } 
            if (price == ''){
                swal.fire({
                   icon: 'warning',
                   title: 'Không để trống đơn giá',
                });
                $('.price').focus();
                return;
            } 
            if (start_index == '') {
                swal.fire({
                   icon: 'warning',
                   title: 'Không để trống chỉ số bắt đầu',
                });
                $('.start_index').focus();
                return;
            }
            if (end_index == '') {
                swal.fire({
                   icon: 'warning',
                   title: 'Không để trống chỉ số kết thúc',
                });
                $('.end_index').focus();
                return;
            } 
            if (parseInt(start_index) >= parseInt(end_index)) {
                swal.fire({
                   icon: 'warning',
                   title: 'Chỉ số bắt đầu phải nhỏ hơn chỉ số kết thúc',
                });
                $('.start_index').focus();
                return;
            } 
            if (isNaN(start_index)) {
                swal.fire({
                   icon: 'warning',
                   title: 'Chỉ số bắt đầu phải là số',
                });
                $('.start_index').focus();
                return;
            } 
            if (isNaN(end_index)) {
                swal.fire({
                   icon: 'warning',
                   title: 'Chỉ số kết thúc phải là số',
                });
                $('.end_index').focus();
                return;
            } 
            if (isNaN(price)) {
                swal.fire({
                   icon: 'warning',
                   title: 'Đơn giá thúc phải là số',
                });
                $('.price').focus();
                return;
            }
            $.ajax({
                type: 'post',
                url: url,
                data: {
                    id: id,
                    name: name,
                    price: price,
                    start_index: start_index,
                    end_index: end_index
                },
                success: function(response) {
                    if (response.res == true) {
                        swal.fire({
                           icon: 'success',
                           title: 'Cập nhật thành công',
                        });                      
                    } else {
                        swal.fire({
                           icon: 'error',
                           title: 'Đã xảy ra lỗi vui lòng thử lại sau',
                        });
                    }
                    $('#updateModal').modal('hide');
                }
            });
            
        });
        
    </script>
    </jsp:attribute>
</mt:app>
            
            
