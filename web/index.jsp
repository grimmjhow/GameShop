<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
    </head>
    <body style="background-color: silver">
        <div class="container">
            <h1>Bem Vindo - ${sessionScope.user.nome}</h1>
            <div class="navbar navbar-default navbar-default" role="navigation">
                <ul class="nav navbar-nav">
                <li><a class="btn">Home</a></li>
                </ul>
                <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="btn dropdown-toggle" id="dpMinhaConta" data-toggle="dropdown" aria-expanded="true">Minha Conta <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="SalvarCliente.jsp">Cadastrar Cliente</a></li>
                        <li class="divider"></li>
                        <li><a href="ConsultarCliente.jsp">Editar Cliente</a></li>
                        <li class="divider"></li>
                        <li><a href="Pedidos.jsp">Meus Pedidos</a></li>
                    </ul>
                </li>
                </ul>
            </div>
        </div>
        <!-- JavaScript Includes -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/tests/vendor/jquery.min.js"></script>
        <script src="js/dropdown.js"></script>
    </body>
</html>