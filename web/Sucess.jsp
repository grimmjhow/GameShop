<%-- 
    Document   : Sucess
    Created on : 05/04/2015, 18:29:54
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="10;Games.jsp">
        <title>Compra Efetuada!</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
        <script language="JavaScript">
            function redirecionar()
            {
                for(i = 0; i < 10; i++)
                { }
                alert("redi");
                location.href="Games.jsp";
            }
        </script>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="">
            <div class="container">
                <div class="alert alert-success" style="margin-top: 50px">
                    <h4>Parabéns,   <br/> </h4>
                    Seu pedido foi realizado com sucesso! <br/>
                    Se sua compra foi aprovada com sucesso, dentro de alguns instantes você pode efetuar o download do jogo em nosso Site <br/> <br/>
                    <h4>Dentro de alguns instantes você será redirecionado!</h4>
                </div>
            </div>
        </form>
    </body>
    <!-- JavaScript Includes -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
