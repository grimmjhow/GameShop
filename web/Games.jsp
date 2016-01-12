<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="pt-br">
    <head>
        <!-- <link rel="icon" href="http://getbootstrap.com/favicon.ico"> -->
        <!-- Tags to mobile -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>GameShop</title>

        <!-- Bootstrap core CSS -->
        <link href="js/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body style="background-color: #999999">
        <jsp:useBean id="jogo" class="games.com.br.domain.Jogo"/>
        <jsp:useBean id="jogoCmd" class="games.com.core.impl.Command.CommandConsultar"/>
        ${jogo.setTitulo("")} <!-- Setando titulo do Jogo -->
        <div class="navbar-wrapper">
            <div class="container">
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand">Ecommerce</a>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <form class="navbar-form navbar-left" role="search" method="post" action="ConsultaJogos">
                                <div class="form-group">
                                    <input type="text" name="operacao" value="Consultar" hidden="true"/>
                                    <input type="text" class="form-control" name="txtProcuraJogo" placeholder="Titulo...">
                                </div>
                                <div class="form-group">
                                    <select class="form-control" name="selGenero">
                                        <option>
                                            Genero
                                        </option>
                                        <option>
                                            Esporte
                                        </option>
                                        <option>
                                            FPS
                                        </option>
                                        <option>
                                            RPG
                                        </option>
                                        <option>
                                            Luta
                                        </option>
                                        <option>
                                            Aventura
                                        </option>
                                    </select>
                                </div>
                                <!-- -->
                                <div class="form-group">
                                    <input type="number" min="2000" max="2015" name="txtAno" placeholder="Ano" class="form-control"/>
                                </div>
                                <!-- -->
                                <div class="form-group">
                                    <input type="number" min="15" max="100" name="txtFaixa" placeholder="Idade" class="form-control"/>
                                </div>
                                <button type="submit" class="btn btn-default">Procurar</button>
                            </form>
                            <ul class="nav navbar-btn navbar-right">
                                <form method="post" action="VisualizarCarrinho.jsp">
                                    <input type="text" value="Consultar" hidden="true" name="operacao"/>
                                    <a>
                                        <button type="submit" class="btn btn-primary">
                                            <span class="glyphicon glyphicon-align-left glyphicon-shopping-cart">
                                                <c:if test="${sessionScope.carrinho.quantidadeItens() eq null}">
                                                    <span class="badge">Vazio</span>
                                                </c:if>
                                                <c:if  test="${sessionScope.carrinho.quantidadeItens() gt 0}">
                                                    <span class="badge">${sessionScope.carrinho.quantidadeItens()}</span>
                                                </c:if>
                                            </span>
                                        </button>
                                    </a>
                                </form>
                            </ul>
                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <ul class="nav navbar-nav navbar-right">
                                        <li class="dropdown dropdown-menu-right">
                                            <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                                <span class="glyphicon glyphicon-user"></span>
                                                <span class="caret"></span></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <c:choose>
                                                    <c:when test="${sessionScope.user.tipo eq 'admin'}">
                                                        <li><a href="/Ecommerce/root/dashboard.jsp" class="btn">Meu Painel</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><a href="Pedidos.jsp" class="btn">Meus Pedidos</a></li>
                                                        <li><a href="SalvarCliente.jsp" class="btn">Editar Perfil</a></li>
                                                        <li><a href="DesativarConta.jsp" class="btn">Desativar Conta</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                <form method="post" action="Logoff">
                                                    <li><a class="btn"><input type="submit" value="Sair" class="btn"/></a></li>
                                                </form>
                                            </ul>
                                        </li>
                                    </ul>
                                </c:when>
                                <c:otherwise>
                                    <ul class="nav navbar-nav navbar-right">
                                        <li class="dropdown dropdown-menu-right">
                                            <a class="btn" role="button" aria-expanded="false" href="SalvarCliente.jsp">N�o possui cadastro?</a>
                                        </li>
                                        <li class="dropdown dropdown-menu-right">
                                            <a class="btn" role="button" aria-expanded="false" href="login.jsp">Logar</a>
                                        </li>
                                    </ul
                                </c:otherwise>
                            </c:choose>
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>
            </div>
        </div>
        <!-- Carousel
        ================================================== -->
        <div id="margem" class="container">
        </div>
        <div id="margem" class="container">
            <c:if test="${requestScope.msgCompra ne null}">
                <div class="alert alert-success">
                    <strong>
                        Parab�ns!
                    </strong> ${requestScope.msgCompra}
                </div>
            </c:if>

            <c:if test="${requestScope.msgCompraDuplicada ne null}">
                <div class="alert alert-info">
                    ${requestScope.msgCompraDuplicada}
                </div>
            </c:if>
            <% request.removeAttribute("msgCompraDuplicada"); %>
            <% request.removeAttribute("msgCompra"); %>

            <c:if test="${requestScope.jogos eq null}">
                <% request.setAttribute("jogos", jogoCmd.execute(jogo).getEntidades());%>
           </c:if>

            <c:forEach var="jogo" items="${requestScope.jogos}">
                <div class="container control-label" style="float: left;width: 35%;margin-left: 10px;">
                    <div class="row">
                        <div class="" id="produto">
                            <div class="thumbnail">
                                <img src="${jogo.image}">
                                <div class="caption" style="background-color: whitesmoke">
                                    <center> <h3>${jogo.titulo}</h3> </center>
                                    <label>${jogo.descricao}</label>
                                    <h2 style="color: #366097; font-size: xx-large">
                                        <center>R$ <f:formatNumber pattern="##.##" value="${jogo.valor}"></f:formatNumber></center>
                                        </h2>
                                        <div class="alert alert-success" style="font-size: smaller">
                                            � vista Boleto ou Cart�o - 15% (R$ 
                                        <f:formatNumber pattern="##.##" value="${jogo.valor - (jogo.valor * 0.15)}">
                                        </f:formatNumber>)
                                    </div>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <h4>12x R$ 
                                                <f:formatNumber pattern="##.##" value="${(jogo.valor/12)}">
                                                </f:formatNumber></h4>
                                        </span>
                                    </div>
                                    <c:choose>
                                        <c:when test="${jogo.quantidade gt 0}">
                                            <form method="post" action="Carrinho">
                                                <input value="${jogo.id}" hidden="true" name="txtCompraId"/>
                                                <input value="${jogo.image}" hidden="true" name="txtImage"/>
                                                <input value="${jogo.titulo}" hidden="true" name="txtTitulo"/>
                                                <input value="${jogo.valor}" hidden="true" name="txtValor"/>
                                                <p><input style="float: left; margin-right: 10px" type="submit" value="Comprar" class="btn btn-success"</p>
                                            </form>
                                            <form method="post" action="InfoJogo">
                                                <input hidden="true" type="text" name="operacao" value="Consultar"/>
                                                <input hidden="true" type="text" value="${jogo.titulo}" name="txtTitulo"/>
                                                <p><input type="submit" value="Detalhes" class="btn btn-primary"</p>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <h3>Jogo Indispon�vel</h3>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-1.11.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    </body>
</html>