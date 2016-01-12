<%-- 
    Document   : Pedidos
    Created on : 07/04/2015, 15:34:27
    Author     : Felipe Monteiro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Listar Pedido</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="ConsultarPedido">
            <input type="text" value="Consultar" hidden="true" name="operacao"/>
            <div class="container">
                <!-- div contendo os campos de pesquisa !-->
                <div id="divPesquisa">
                    <div class="input-group form-inline" style="margin-top: 10px">
                        <span class="input-group-addon">Mês e Ano</span>
                        <input type="date" placeholder="" class="form-control" name="txtDataPedido"/>
                        <span class="input-group-btn">
                            <input type="submit" value="Go!" class="btn btn-default" />
                        </span>
                    </div>
                </div>
            </div>
        </form>
        <div class="container">
            <c:if test="${pedidos.isEmpty() eq true}">
                <div class="alert alert-info" style="margin-top: 2%">Nenhum pedido foi encontrado nessa data!</div>
                <% request.setAttribute("pedidos", null); %>   <!--Removendo o atributo!-->
            </c:if>
            <c:if test="${msgPedido ne null}">
                <c:forEach var="erro" items="${requestScope.msgPedido}">
                    <div class="alert alert-danger" style="margin-top: 2%">${erro}</div>
                </c:forEach>
                <% request.setAttribute("msgPedido", null);%>   <!--Removendo o atributo!-->
            </c:if>
            <c:if test="${requestScope.SucessPedido ne null}">
                <div class="alert alert-success" style="margin-top: 2%">${requestScope.SucessPedido}</div>
            </c:if>
            <div id="divTablePedidos" style=" margin-top: 20px">
                <div class="panel panel-primary">
                    <div class="panel panel-heading">Pedidos da Data</div>
                    <div class="panel panel-body">
                        <table class="table table-bordered text-center table-striped table-responsive">
                            <td style="background-color: #66afe9">N. Pedido</td>
                            <td style="background-color: #66afe9">Data/Hora</td>
                            <td style="background-color: #66afe9">Tipo Pagamento</td>
                            <td style="background-color: #66afe9">Posicao</td>

                            <!-- Adicionando linhas da tabela !-->
                            <c:forEach var="pedido" items="${requestScope.pedidos}" >
                                <tr>
                                    <td>
                                        <form method="post" action="DetalhePedido">
                                            <input type="text" hidden="true" value="Consultar" name="operacao"/>
                                            <input type="text" hidden="true" name="txtId" value="${pedido.id}"/>
                                            <input type="text" hidden="true" name="txtFrete" value="${pedido.frete.valor}"/>
                                            <input type="text" hidden="true" name="txtTipoPagamento" value="${pedido.tipoPagamento}"/>
                                            <input type="text" hidden="true" name="txtStatus" value="${pedido.status}" />
                                            <input type="text" hidden="true" name="txtData" value="<f:formatDate pattern="dd/MM/yyyy" value="${pedido.dataPedido.getTime()}"></f:formatDate>"/>
                                            <input type="text" hidden="true" name="txtPosicao" value="${pedido.posicao}"/>
                                            <input type="text" hidden="true" name="txtIdEntrega" value="${pedido.entrega.id}" />
                                            <input type="submit" class="btn btn-link" value="${pedido.id}" title="Consultar Pedido"/>
                                        </form>
                                    </td>
                                    <td><f:formatDate pattern="dd/MM/yyyy" value="${pedido.dataPedido.getTime()}"></f:formatDate></td>
                                        <td>
                                        <c:choose>
                                            <c:when test="${pedido.tipoPagamento eq 'Boleto Bancario'}"> 
                                                <form method="post" action="SegundaViaBoleto">
                                                    <input type="text" name="txtIdEntrega" value="${pedido.entrega.id}" hidden="true" />
                                                    <input type="text" name="operacao" value="Consultar" hidden="true"/>
                                                    <input type="text" name="txtIdPedido" value="${pedido.id}" hidden="true"/>
                                                    <input type="submit" class="btn btn-link" value="${pedido.tipoPagamento}" title="Segunda via!" />
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                ${pedido.tipoPagamento}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${pedido.posicao eq 'Aguardando Pagamento'}">
                                                <form method="post" action="PagamentoDiretoBoleto">
                                                    <input type="text" name="txtIdEntrega" value="${pedido.entrega.id}" hidden="true" />
                                                    <input type="text" name="operacao" value="Atualizar" hidden="true"/>
                                                    <input type="text" name="txtIdPedido" value="${pedido.id}" hidden="true"/>
                                                    <input type="submit" class="btn btn-link" value="${pedido.posicao}"  title="Pagar Boleto!"/>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                ${pedido.posicao}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                    <div class="panel panel-footer">
                        <a href="Games.jsp" class="btn btn-default">Voltar</a>
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
