<%-- 
    Document   : FinalizarCompra
    Created on : 04/04/2015, 11:16:01
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Finalizar Compra</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
        <script src="js/libs/jquery.mask/jquery.mask.min.js"></script>
        <script src="js/libs/jquery.maskedinput/jquery.maskedinput.js"></script>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="SelecionaPagamento">
            <div class="container container-fluid">
                <div class="panel panel-primary" style="margin-top: 10px">
                    <div class="panel panel-heading">Dados para Compra</div>
                    <div class="panel panel-body">
                        <div class="form-group form-inline"> 
                            <span>Tipo de Pagamento: </span>
                            <select class="form-control form-inline" name="txtTipoPagamento">
                                <option>Cartão de Crédito</option>
                                <!--<option>Boleto Bancário</option>
                                <option>Débito em Conta</option> !-->
                            </select>
                        </div>

                        <div class="form-group form-inline">
                            <input class="bnt btn-success form-control" type="submit" value="Continuar" />
                            <a href="Games.jsp">
                                <input class="bnt btn-default form-control" type="button" value="Cancelar" />
                            </a>
                        </div>
                    </div>    
                </div>
            </div>
        </form>
    </body>
    <!-- JavaScript dos componentes BootStrap !-->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
