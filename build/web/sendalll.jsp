<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mt"%>
<mt:app title="Gửi tất cả">
    <jsp:attribute name="content">
    <main>
        <div class="container" style="background-color: white;">
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <td>Người nhận: Tất cả</td>     
                        </tr>
                    </thead>
                </table>
                <form action="" method="post" data-url="<c:url value="/customers/send-all" />" id="sendall-form">
                    <div class="col-md-8">
                        <input class="form-control" type="text" name="subject" placeholder="Nhập tiêu đề">
                        <div class="form-group">
                            <font color="#F24638" id="subject-error"></font>
                        </div>
                        <div>Nội dung Email</div>
                        <div class="form-group form-group-textarea mb-md-0">
                            <textarea rows="15" class="form-control" name="content" placeholder="Hãy nhập nội dung mail" data-validation-required-message="Hãy nhập nội dung mail"></textarea>
                            <p class="help-block text-danger"></p>
                        </div>
                        <div class="form-group">
                            <font color="#F24638" id="content-error"></font>
                        </div>
                        <div>
                            <input type="submit" class="btn btn-info" value="Gửi" />
                        </div>
                    </div>
                </form>
             </div>
        </div>
    </main>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#sendall-form').submit(function(event) {
                event.preventDefault();
                $('#subject-error').text('');
                $('#content-error').text('');
                var url = $(this).data('url');
                var subject = $('#sendall-form input[name=subject]').val();
                var content = $('#sendall-form textarea[name=content]').val();
                if (subject === '') {
                    $('#subject-error').text('Nhập tiêu đề');
                    $('#sendall-form input[name=subject]').focus();
                }
                if (content === '') {
                    $('#content-error').text('Nhập nội dung mail');
                    $('#sendall-form textarea[name=content]').focus();
                }
                if(content !== '' && subject !== '') {
                    $.ajax({
                        type: 'post',
                        url: url,
                        data: {
                            subject: subject,
                            content: content,
                        }
                        success: function(response) {
                            console.log(response);
                        },
                        error: function(error) {
                            console.log(error);
                        },
                    });
                }
            });  
        });
    </script>
    </jsp:attribute>
</mt:app>