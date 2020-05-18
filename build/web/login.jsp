<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Đăng nhập</title>
        <link href="public/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Đăng nhập</h3></div>
                                    <div class="card-body">
                                        <form method="post" action="" id="login-form" data-url="<c:url value="/login" />">
                                            <input type="hidden" name="page" value="login">
                                            <div class="form-group"><label class="small mb-1" >Tên đăng nhập</label><input class="form-control py-4" type="text" name="username" placeholder="Điền tên đăng nhập" value="<c:out value="${username}"></c:out>" /></div>
                                            <div class="form-group"><label class="small mb-1" >Mật khẩu</label><input class="form-control py-4" id="inputPassword" type="password" name="password" placeholder="Điền mật khẩu" /></div>
                                            <div class="form-group">
                                                <font color="#F24638" id="error"></font>
                                                <font color="#F24638"><c:out value="${error}"></c:out></font>
                                            </div>
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0"><button type="submit" class="btn btn-primary">Đăng nhập</button></div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#login-form').submit(function(event) {
                $('#error').text('');
                event.preventDefault();
                var url = $(this).data('url');
                var username = $('#login-form input[name=username]').val();
                var password = $('#login-form input[name=password]').val();
                if (username == '') {
                    $('#error').text('Không được để trống!');
                    $('#login-form input[name=username]').focus();
                    return;
                }
                if (password == '') {
                    $('#login-form input[name=password]').focus();
                    $('#error').text('Không được để trống!');
                    return;
                }
                $.ajax({
                    type: 'post',
                    url: url,
                    data: {
                        username: username,
                        password: password,
                    },
                    success: function(response) {   
                        console.log(response);
                        if (response.url) {
                            Swal.fire({
                                title: 'Đăng nhập thành công',
                                width: 600,
                                padding: '3em',
                                background: '#fff url(/images/trees.png)',
                                backdrop: `
                                    rgba(0,0,123,0.4)
                                    url("/images/nyan-cat.gif")
                                    left top
                                    no-repeat
                                `
                            });
                            window.location.replace(response.url);
                        } 
                        else {
                            $('#error').text(response.error);
                            $('#login-form input[name=password]').empty();
                        }
                    },
                });
            });
        });
        
    </script>
</html>
