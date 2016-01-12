<%-- 
    Document   : newLogin
    Created on : 05/05/2015, 16:25:14
    Author     : Felipe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html
    <html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="js/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="js/css/signin.css" rel="stylesheet">
</head>

<body>

    <div class="container">

        <form class="form-signin" method="post" action="ValidaUsuario">
            <h2 class="form-signin-heading">Login</h2>
            <label for="inputEmail" class="sr-only">Email</label>
            <input type="email" id="inputEmail" class="form-control"  name="txtEmail" placeholder="example@test.com.br" required autofocus>
            <label for="inputPassword" class="sr-only">Senha</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Senha" name="txtSenha"required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            <a href="SalvarCliente.jsp" class="btn btn-link">Não possui cadastro?</a>
            <c:if test="${msgLoginErro ne null}">
                <div style="margin-top: 2%" class="alert alert-danger">${msgLoginErro}</div>
            </c:if>
        </form>
    </div> <!-- /container -->
</body>
</html>

