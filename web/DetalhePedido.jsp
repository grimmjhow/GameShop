<%-- 
    Document   : DetalhePedido
    Created on : 14/05/2015, 10:35:27
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalhe do Pedido</title>
        <!-- BootStrap -->
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
    </head>
    <body style="background-color: whitesmoke">
        <div class="container control-label center-block">
            <div class="form-inline" style="margin-top: 2%"> <!-- Form de ajuste principal -->
                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <strong>${requestScope.pedido.id} - Informações do Pedido</strong>
                    </div>
                    <div class="panel panel-body">
                        <div class="form-horizontal">
                            <div class="form-inline text-center">
                                <h4>
                                    Data do Pedido: <div class="label label-success"><f:formatDate pattern="dd/MM/yyyy" value="${requestScope.pedido.dataPedido.getTime()}"></f:formatDate></div>
                                    Forma de Pagamento: <div class="label label-success">${requestScope.pedido.tipoPagamento}</div>
                                    Posição:<div class="label label-success"> ${requestScope.pedido.posicao}</div>
                                    Status:<div class="label label-success">${requestScope.pedidos.status}</div>
                                </h4>
                            </div>
                            <h3 class="well well-sm text-center">Endereço de Entrega</h3>
                            <table class="table table-responsive table-striped text-center">
                                <td class="text-info"><strong>Logradouro</strong></td>
                                <td class="text-info"><strong>N.</strong></td>
                                <td class="text-info"><strong>Bairro</strong></td>
                                <td class="text-info"><strong>CEP</strong></td>
                                <td class="text-info"><strong>Cidade</strong></td>
                                <td class="text-info"><strong>Estado</strong></td>

                                <!-- Linhas da tabela -->
                                <tr>
                                    <td>${requestScope.pedido.entrega.logradouro}</td>
                                    <td>${requestScope.pedido.entrega.numero}</td>
                                    <td>${requestScope.pedido.entrega.bairro}</td>
                                    <td>${requestScope.pedido.entrega.cep}</td>
                                    <td>${requestScope.pedido.entrega.cidade}</td>
                                    <td>${requestScope.pedido.entrega.estado}</td>
                                </tr>
                            </table>

                            <h3 class="well well-sm text-center">Itens do Pedido</h3>
                            <table class="table table-responsive table-striped">
                                <td class="text-info"><strong>Produto</strong></td>
                                <td class="text-info"><strong>Quantidade</strong></td>
                                <td class="text-info"><strong>Unitário</strong></td>
                                <td class="text-info"><strong>Total</strong></td>
                                <td class="text-info"><strong>Total Desconto</strong></td>

                                <!-- Linhas da tabela -->
                                <c:forEach var="itens" items="${requestScope.pedido.itens}">
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${itens.desconto ne null}">
                                                    <form method="post" action="InfoJogo">
                                                        <input hidden="true" type="text" name="operacao" value="Consultar"/>
                                                        <input hidden="true" type="text" value="${itens.jogo.titulo}" name="txtTitulo"/>
                                                        <input type="submit" value="${itens.jogo.titulo}" class="btn btn-link"/>
                                                        <span class="label label-success text-right">-<f:formatNumber pattern="##">${itens.desconto * 100}</f:formatNumber></span>
                                                        </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form method="post" action="InfoJogo">
                                                        <input hidden="true" type="text" name="operacao" value="Consultar"/>
                                                        <input hidden="true" type="text" value="${itens.jogo.titulo}" name="txtTitulo"/>
                                                        <input type="submit" value="${itens.jogo.titulo}" class="btn btn-link"/>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${itens.quantidade}</td>
                                        <td><f:formatNumber pattern="###,###.##" value="${itens.jogo.valor}"></f:formatNumber></td>
                                        <td><f:formatNumber pattern="###,###.##" value="${itens.jogo.valor * itens.quantidade}"></f:formatNumber></td>
                                        <td class="text-success"><f:formatNumber pattern="###,###.##" value="${itens.valorTotalItem()}"></f:formatNumber></td>
                                        </tr>
                                </c:forEach>
                            </table>
                            <h3 class="well well-sm">Resumo dos Valores</h3>
                            <div class="form-inline">
                                <h3>
                                    Sub-Total:<div class="label label-success">${requestScope.pedido.ValorTotal()}</div>
                                    +
                                    Frete:<div class="label label-success">${requestScope.pedido.frete.valor}</div>
                                    <strong>= </strong>
                                    <div class="label label-success">
                                        <f:formatNumber pattern="###,###.##">
                                            ${requestScope.pedido.ValorTotal() + requestScope.pedido.frete.valor}
                                        </f:formatNumber>
                                    </div>
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-footer">
                        <div class="form form-inline">
                            <a href="Pedidos.jsp" class="btn btn-default">Pedidos</a>
                            <a href="Games.jsp" class="btn btn-primary">Tela Principal</a>
                        </div>                        
                    </div>
                </div>
            </div> <!-- Form de ajuste principal -->
        </div>
    </body>
    <!-- JavaScript dos Componentes do BootStrap !-->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
