<%-- 
    Document   : VisualJogo
    Created on : 30/03/2015, 14:30:29
    Author     : Felipe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Jogo!</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="Carrinho">
            <input type="text" name="txtTitulo" hidden="true" value="${requestScope.jogo.titulo}"/>
            <input type="text" name="txtValor" hidden="true" value="${requestScope.jogo.valor}"/>
            <input type="text" name="txtCompraId" hidden="true" value="${requestScope.jogo.id}"/>

            <div class="container form-group">
                <!--<div class="row" style="background-color: red; width: 600px"> !-->
                <div class="col-xs-6 col-md-6 form-horizontal" style="margin-top: 10px;">
                    <div class="thumbnail" style="background-color: silver">
                        <img src="${requestScope.jogo.image}">
                        <div class="caption text-center">
                            <h2> <span class="label label-default">${requestScope.jogo.titulo}</span> </h2>
                        </div>
                        <p><b>Descrição:</b></p>
                        <p>${requestScope.jogo.descricao}</p>
                    </div>
                </div>
                <div class="form-group form-inline" style="margin-left: 10px">
                    <div>
                        <h3><span class="label label-primary col-lg-2" style="margin-right: 10px"> Genero</span> ${requestScope.jogo.genero}</h3>
                    </div>
                    <div>
                        <h3><span class="label label-primary col-lg-2" style="margin-right: 10px"> Faixa Etaria 
                            </span> ${requestScope.jogo.faixaEtaria}</h3>
                    </div>
                    <div>
                        <h3><span class="label label-primary col-lg-2" style="margin-right: 10px"> Ano Lançamento
                            </span> ${requestScope.jogo.ano}</h3>
                    </div>
                    <div>
                        <h3>
                            <span class="label label-primary col-lg-2" style="margin-right: 10px">
                                Valor
                            </span> 
                            R$
                            <f:formatNumber value="${requestScope.jogo.valor}" pattern="###.##"/>
                        </h3>
                    </div>
                    <div>
                        <h3>
                            <c:choose>
                                <c:when test="${requestScope.jogo.multiplayer eq 'S'}">
                                    <span class="label label-primary col-lg-2" style="margin-right: 10px"> Multiplayer    
                                    </span>Sim
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-primary col-lg-2" style="margin-right: 10px"> Multiplayer    
                                    </span>Não
                                </c:otherwise>
                            </c:choose>
                        </h3>
                    </div>
                    <div>
                        <h3>
                            <c:choose>
                                <c:when test="${requestScope.jogo.online eq 'S'}"> 
                                    <span class="label label-primary col-lg-2" style="margin-right: 10px"> Online
                                    </span> Sim
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-primary col-lg-2" style="margin-right: 10px"> Online
                                    </span>Não
                                </c:otherwise>
                            </c:choose>
                        </h3>
                    </div>
                    <!-- <div>
                        <h3><span class="label label-primary col-lg-2" style="margin-right: 10px"> Quantidade</span></h3>
                        <input type="text" class="form-control form-group" name="txtQuantidade"/>
                    </div> !-->
                    <br/><br/>
                    <input class="btn btn-success" value="Comprar" type="submit"/>
                    <a href="Games.jsp">
                        <button class="btn btn-default" type="button">
                            Voltar
                        </button>
                    </a>
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
