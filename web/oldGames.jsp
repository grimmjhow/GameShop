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
            <div class="panel panel-default">
                <div class="panel-heading">Jogos Disponíveis</div>
                <div class="panel panel-body">
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped table-hover table-responsive" id="tabelaJogos" onselect="changeUrlInfoGame()">
                            <tr style="text-align: center;background-color: #C2CCD1">
                                <td>Nome</td>
                                <td>Genero</td>
                                <td>Faixa Idade</td>
                                <td>Valor</td>
                                <td>Action</td>
                            </tr>
                            ${game.titulo = ""}
                            <c:if test="${sessionScope.jogos eq null}">
                                <c:forEach var="jogos" items="${gameDAO.consultar(game)}" >
                                    <tr>
                                        <td>${jogos.titulo}</td>
                                        <td style="text-align: center">${jogos.genero} </td>
                                        <td style="text-align: center">${jogos.faixaEtaria}</td>
                                        <td style="text-align: center">R$ ${jogos.valor}</td>
                                        <td style="text-align: center">
                                            <form method="post" action="InfoJogo" style="float: left">
                                                <input value="${jogos.titulo}" hidden="true" name="txtTitulo"/>
                                                <input value="Consultar" hidden="true" name="operacao"/>
                                                <input class="btn btn-link" type="submit" id="${jogos.id}" value="Informações"/>
                                            <form method="post" action="Carrinho">
                                                <input value="${jogos.id}" hidden="true" name="txtCompraId"/>
                                                <input value="${jogos.titulo}" hidden="true" name="txtTitulo"/>
                                                <input value="${jogos.valor}" hidden="true" name="txtValor"/>
                                                <input type="submit" class="btn btn-link" value="Adicionar"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <!-- Se ja estiver uma lista de jogos! !-->
                            <c:if test="${sessionScope.jogos ne null}">
                                <c:forEach var="jogo" items="${sessionScope.jogos}">
                                    <tr style="text-align: center">
                                        <td>${jogo.titulo}</td>
                                        <td>${jogo.genero}</td>
                                        <td>${jogo.faixaEtaria} </td>
                                        <td>R$ ${jogo.valor} </td>
                                        <td> <a> Informações </a> / <a> Add Carrinho </a> </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <!-- JavaScript Includes -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
