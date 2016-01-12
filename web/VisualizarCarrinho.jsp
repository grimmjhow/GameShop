<%-- 
    Document   : VizualizarCarrinho
    Created on : 30/03/2015, 23:35:57
    Author     : Felipe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    ${sessionScope.carrinho.ordenarItens()}
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrinho de Compras</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
        <script src="js/limpar.js"></script>

        <!-- ====================== Pequeno Script para verificar o CEP ========== -->
        <script>
            function verificaCompra()
            {
                var form = document.getElementById("formCompra");
                var cep = document.getElementById("txtCep").getAttribute("value");

                if (cep === "" || cep === null)
                {
                    //mantive essa linha apenas para testes
                    form.submit();
                    //alert("É preciso efetuar o calculo do frete para prosseguir!");
                }
                else
                {
                    form.submit();
                }
            }
        </script>

    </head>
    <body style="background-color: whitesmoke">
        <div class="container" style="margin-top: 20px; ">
            <div class="panel panel-success">
                <div class="panel panel-heading"><strong>Seus Produtos!</strong></div>
                <div class="panel panel-body">
                    <table class="table table-striped table-responsive">
                        <tr style="text-align: center; background-color: skyblue;">
                            <td><strong>Produto</strong></td>
                            <td><strong>Quantidade</strong></td>
                            <td><strong>Unitário</strong></td>
                            <td><strong>Total</strong></td>
                            <td><strong>Action</strong></td>
                        </tr>
                        <c:forEach var="item" items="${sessionScope.carrinho.itens}">
                            <tr style="text-align: center;">
                                <td>
                                    <form class="form" method="post" action="InfoJogo">
                                        <input hidden="true" type="text" name="operacao" value="Consultar"/>
                                        <input hidden="true" type="text" value="${item.jogo.titulo}" name="txtTitulo"/>
                                        <input type="submit" value="${item.jogo.titulo}" class="btn btn-link"/>
                                        <span class="label label-success" style="float: right">-10%</span>
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="AtualizarQuantidade">
                                        <input type="text" name="txtQuantidade" value="${item.quantidade}" style="text-align: center" min="1" required="required"/>
                                        <input type="text" name="txtTitulo" value="${item.jogo.titulo}" hidden="true"/>
                                        <input type="text" hidden="true" name="txtIndex" value="${sessionScope.carrinho.itens.indexOf(item)}"/>
                                        <input type="submit" value="Atualizar" class="btn btn-link"/>
                                    </form>
                                </td>
                                <td> 
                                    <strong>
                                        R$ 
                                        <f:formatNumber pattern="###,###.##">
                                            ${item.jogo.valor}
                                        </f:formatNumber>
                                    </strong>
                                </td>
                                <td> 
                                    <strong>
                                        R$ 
                                        <f:formatNumber>
                                            ${item.valorTotalItem()}
                                        </f:formatNumber>
                                    </strong>
                                </td>
                                <td> 
                                    <form method="post" action="RemoveItem">
                                        <input type="text" value="remover" name="operacao" hidden="true"/>
                                        <input type="text" hidden="true" name="txtIdItem" value="${sessionScope.carrinho.itens.indexOf(item)}"/>
                                        <input type="submit" value="Remover" class="btn btn-link"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="panel panel-footer form-inline">
                        <div class="form-group">
                            <c:if test="${sessionScope.carrinho.totalItens() gt 0}">
                                <form method="post" action="LimparCarrinho" id="FormClear">
                                    <input type="text" value="limpar" name="operacao" hidden="true"/>
                                    <input type="button" value="Limpar Carrinho" class="btn btn-warning" onclick="limpar()"/> 
                                </form>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <a href="Games.jsp" class="btn btn-primary">Continuar Comprando</a>
                        </div>

                        <c:if test="${requestScope.erroQtde ne null}">
                            <div class="alert alert-danger" style="margin-top: 2%">${requestScope.erroQtde}</div>
                        </c:if>
                    </div>
                </div>
            </div>

            <c:if test="${sessionScope.carrinho.totalItens() gt 0}">
                <!-- =============== Informa os valores a pagar ========== -->
                <div class="form-inline">
                    <div class="panel panel-default col-md-5">
                        <div class="panel panel-heading"><strong>Calculo de Frete</strong></div>
                        <div class="form-inline">
                            <!-- =========================== FORMULARIO PARA CONTER OS DADOS DO FRETE =========================== -->
                            <div class="form-horizontal" style="margin-left: 2%;margin-top: 2%;margin-bottom: 2%">
                                <div class="form-inline" style="margin-bottom: 1%">
                                    <form method="post" action="CalcularFrete">
                                        <input type="text" hidden="true" value="Consultar" name="operacao"/>
                                        <strong>CEP</strong> 
                                        <input type="text" placeholder="Ex. 11111-111" name="txtCep" class="form-control" value="${sessionScope.carrinho.frete.cep}"/>                                        
                                        <input type="submit" value="Calcular Frete" class="btn btn-link"/>
                                    </form>
                                </div>

                                <div class="form-inline">
                                    <div class="form-horizontal">
                                        <div class="form-inline">
                                            <strong>Sedex</strong>
                                            <h4><span class="label label-success">R$ ${sessionScope.carrinho.frete.valor}</span></h4>
                                        </div>
                                        <div class="form-inline">
                                            <strong>Entrega</strong>
                                            <h4><span class="label label-success"> ${sessionScope.carrinho.frete.entrega}</span></h4>

                                            <c:if test="${frete eq null and msgFrete ne null}">
                                                <div class="alert alert-danger"> ${msgFrete}</div>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- ==================== FIM DO FORMULARIO DE CALCULO DE FRETE ============ -->
                        </div>
                    </div>  <!-- ============================== FIM DO PRIMEIRO PANEL ====================== --> 

                    <!-- ================================= Inicio do Formulario de precos! ================== -->
                    <div class="panel panel-default col-md-5" id="formPrecos">
                        <div class="panel panel-heading"><strong>Total da Compra</strong></div>
                        <div class="form-horizontal" style="margin-top: 2%; margin-bottom: 2%;">
                            <div class="form-inline" style="margin-bottom: 1%">
                                <div class="well">
                                    <div class="form form-inline text-center">
                                        <h4>
                                            <strong style="color: orange">VALOR COM DESCONTO</strong>
                                        </h4>
                                        <h2>
                                            <strong style="color: green">R$ 
                                                <f:formatNumber pattern="###,###.##"> 
                                                    ${sessionScope.carrinho.valorTotalDesconto(0.1) + sessionScope.carrinho.frete.valor}
                                                </f:formatNumber>
                                            </strong>
                                        </h2>
                                    </div>
                                </div>
                                <h6>
                                    10% de desconto, boleto ou cartão!
                                </h6>
                            </div>
                            <p><b>Total SEM DESCONTO:</b> 
                                <strong style="color: red">R$
                                    <f:formatNumber pattern="###,###.##">
                                        ${sessionScope.carrinho.ValorTotal() + sessionScope.carrinho.frete.valor}
                                    </f:formatNumber>
                                </strong>
                            </p>
                            <p>Frete: 
                                <strong>R$ 
                                    <f:formatNumber pattern="###,###.##">
                                        <c:choose>
                                            <c:when test="${sessionScope.carrinho.frete.valor gt 0}">
                                                ${sessionScope.carrinho.frete.valor}
                                            </c:when>
                                            <c:otherwise>
                                                0
                                            </c:otherwise>
                                        </c:choose>
                                    </f:formatNumber>
                                </strong>
                            </p>
                            <!-- <p>Total com desconto: 
                                <strong style="color: green">R$ 
                            <f:formatNumber pattern="###,###.##">
                                ${sessionScope.carrinho.valorTotalDesconto(0.1) + sessionScope.carrinho.frete.valor}
                            </f:formatNumber>
                        </strong>
                    </p> -->
                            <p><b>cartão de crédito em até 12x de R$
                                    <f:formatNumber pattern="###,###.##">
                                        ${(sessionScope.carrinho.ValorTotal()/12)}
                                    </f:formatNumber>
                                </b>
                            </p>
                            <form method="post" action="ConfirmaEndereco.jsp" id="formCompra">
                                <input type="text" hidden="true" name="txtCep" id="txtCep" value="${frete.cep}" />
                                <button type="button" class="btn btn-success btn-lg" onclick="verificaCompra()">
                                    <span class="glyphicon glyphicon-thumbs-up"></span>
                                    Finalizar <strong>Compra</strong>
                                </button>
                            </form>
                        </div>
                    </div> 
                </div> <!-- ======================= Fim do Formulario do Endereco ======================= -->
            </c:if>
        </div>
    </body>
    <!-- JavaScript dos componentes BootStrap !-->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
