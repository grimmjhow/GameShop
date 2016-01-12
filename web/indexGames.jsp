<%-- 
    Document   : Games
    Created on : 29/03/2015, 16:18:15
    Author     : Felipe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="gameDAO" class="games.com.core.impl.dao.JogoDAO"/>
    <jsp:useBean id="game" class="games.com.br.domain.Jogo"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Games!</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
    </head>
    <body style="background-color: whitesmoke" onload="showMessage()">
        <div class="container">
            <form method="post" action="SalvarItem" id="form">
                <div class="form-group col-lg-9 input-group" style="margin-top: 10px;float:left">
                    <input type="text" placeholder="Digite o nome do Jogo..." name="txtPesquisar" class="form-control"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Go!</button>
                    </span>
                </div>
                <div style="float: right; margin-top: 10px" class="btn-group btn-group-lg">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-align-left glyphicon-shopping-cart"> 
                            <span class="badge">${sessionScope.carrinho.quantidadeItens()}</span> 
                        </span>
                        <span class="caret"></span> 
                    </button>
                    <c:if test="${sessionScope.carrinho.quantidadeItens() > 0}">
                        <ul class="dropdown-menu" role="menu">
                            <li> <a class="btn btn-link" href="VisualizarCarrinho.jsp">Listar Pedidos </a> </li>
                            <li role="presentation" class="divider"></li>                        
                            <li> <a class="btn btn-link" href="SelecionarTipoPagamento.jsp">Finalizar Compra</a> </li>
                            <li role="presentation" class="divider"></li>
                            <li> <a class="btn btn-link">Limpar Carinho</a> </li>                        
                        </ul>
                    </c:if>
                </div>
            </form>
        </div>

        <!-- table para exibir os jogos cadastrados! !-->
        <div class="container">
            <div class="row">
                <fieldset title="Teste">
                    <div class="">
                        
                    </div>
                </fieldset>
            </div>
        </div>
    </body>
    <!-- JavaScript Includes -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
