<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 

<html lang="en">
    <!-- Bean do jogo, para utilizar na consulta! -->
    <jsp:useBean id="jogo" class="games.com.br.domain.Jogo"/>
    <jsp:useBean id="jogoCmd" class="games.com.core.impl.Command.CommandConsultar"/>

    ${jogo.setTitulo("")} <!-- Setando titulo do jogo -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Bem Vindo - Administrador</title>

        <!-- Bootstrap core CSS -->
        <link href="../js/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="../js/css/dashboard.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="../js/jquery.twbsPagination.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#tableDash").twbsPagination({
                    totalPages: 35,
                    visiblePages: 7
                });
            });
            function showGrafics()
            {
                var div = document.getElementById("relatorios");

                if (div.hidden === true)
                    div.hidden = false;
                else
                    div.hidden = true;
            }

            function atualizaQuantidade(buttonId)
            {
                var button = document.getElementById(buttonId);

                if (confirm("Deseja realmente atualizar o valor de estoque desse produto?"))
                {
                    button.click();
                }
            }
            
            function deleteJogo(formId)
            {
                var form = document.getElementById(formId);
                
                if(confirm("Deseja realmente inativar: "+formId+"?"))
                    form.submit();
            }
        </script>
    </head>

    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="../Games.jsp">Loja de Jogos</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Ferramentas</a></li>
                        <li><a href="#">Perfil</a></li>
                    </ul>
                    <form class="navbar-form navbar-right" method="post" action="ConsultaJogos">
                        <div class="form form-group">
                            <input type="text" class="form-control" placeholder="Titulo" name="txtProcuraJogo">
                        </div>
                        <div class="form form-group">
                            <select class="form-control" name="selGenero">
                                <option>Genero</option>
                                <option>Aventura</option>
                                <option>FPS</option>
                                <option>Esporte</option>
                                <option>Corrida</option>
                                <option>Luta</option>
                                <option>RPG</option>
                            </select>
                        </div>
                        <div class="form form-group">
                            <input type="number" min="15" max="100" placeholder="Faixa" name="txtFaixa" class="form-control"/>
                        </div>
                        <div class="form form-group">
                            <input type="number" min="2000" max="2015" placeholder="Ano" name="txtAno" class="form-control"/>
                        </div>
                        <button type="submit" class="bnt btn-success form-control">
                            <span class="glyphicon glyphicon-saved"></span>
                        </button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li class="active"><a href="#">Lista de Jogos <span class="sr-only">(current)</span></a></li>
                        <li><a>Relatorios</a></li>
                        <li><a class="btn btn-link"onclick="showGrafics()">Relatórios de Clientes</a></li>
                        <li><a href="SalvarJogos.jsp">Cadastrar Jogos</a></li>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div class="form form-inline">
                        <h1 class="page-header">Dashboard</h1>
                        <c:if test="${MsgExcluirJogo ne null}">
                            <div class="alert alert-success">${MsgExcluirJogo}</div>
                            <% request.setAttribute("MsgExcluirJogo",null); %>
                        </c:if>
                        <c:if test="${erroMsgEstoque ne null}">
                            <c:forEach var="erro" items="${erroMsgEstoque}">
                                <div class="alert alert-danger">${erro}</div>
                                <% request.setAttribute("erroMsgEstoque", null); %>
                            </c:forEach>
                        </c:if>

                        <c:if test="${requestScope.sucessAtualizarJogo ne null}">
                            <div class="alert alert-success">${requestScope.sucessAtualizarJogo}</div>
                            <% request.setAttribute("sucessAtualizarJogo", null); %>
                        </c:if>

                        <c:if test="${sucessMsgEstoque ne null}">
                            <div class="alert alert-success">${sucessMsgEstoque}</div>
                            <% request.setAttribute("sucessMsgEstoque", null); %>
                        </c:if>
                    </div>

                    <h2 class="sub-header form form-inline">
                        Bem Vindo - ${sessionScope.user.nome}
                    </h2>

                    <c:if test="${requestScope.MsgExcluirJogo ne null}">
                        <div class="label label-success">
                            ${requestScope.MsgExcluirJogo}
                        </div>
                    </c:if>
                    <!--  -->
                    <div class="table-responsive">
                        <table class="table table-striped" id="tableDash">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Titulo</th>
                                    <th>Genero</th>
                                    <th>Faixa Etaria</th>
                                    <th class="text-center">Lançamento</th>
                                    <th class="text-center">Estoque</th>
                                    <th>Editar</th>
                                    <th>Excluir</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${requestScope.jogos eq null}">
                                    <% request.setAttribute("jogos", jogoCmd.execute(jogo).getEntidades());%>
                                </c:if>

                                <c:forEach var="jg" items="${requestScope.jogos}">
                                    <tr 
                                        <c:if test="${jg.quantidade lt 5}"> 
                                            style="background-color: #e7c3c3" 
                                        </c:if>>
                                        <td>
                                            ${jg.id} 
                                        </td>
                                        <td>
                                            ${jg.titulo} 
                                        </td>
                                        <td>
                                            ${jg.genero} 
                                        </td>
                                        <td>
                                            ${jg.faixaEtaria} 
                                        </td>
                                        <td class="text-center">
                                            ${jg.ano}
                                        </td>
                                        <td class="text-center">
                                            <form method="post" action="AtualizaQuantidade">
                                                <div class="form form-inline">
                                                    <input type="text" name="txtId" value="${jg.id}" hidden="true"/>
                                                    <input type="text" name="operacao" value="Atualizar" hidden="true"/>
                                                    <input type="number" value="${jg.quantidade}" name="txtQuantidade" class="form-control text-center"/>
                                                    <input type="submit" id="${jg.id.toString()}" hidden="true"/>
                                                    <input type="button" id="btnEstoque" value="Atualizar" class="btn btn-link" onclick="atualizaQuantidade(${jg.id.toString()})"/>
                                                </div>
                                            </form>
                                            <!-- ${jg.quantidade} -->
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <form method="post" action="EditarJogo" >
                                                    <input type="text" value="${jg.id}" name="txtId" hidden="true"/>
                                                    <input type="text" value="${jg.titulo}" name="txtTitulo" hidden="true"/>
                                                    <input type="text" value="${jg.genero}" name="txtGenero" hidden="true"/>
                                                    <input type="text" value="${jg.faixaEtaria}" name="txtFaixa" hidden="true"/>
                                                    <input type="text" value="${jg.ano}" name="txtAno" hidden="true"/>
                                                    <input type="text" value="${jg.descricao}" name="txtDescricao" hidden="true"/>
                                                    <input type="text" value="${jg.valor}" name="txtValor" hidden="true"/>
                                                    <input type="text" value="${jg.idioma}" name="txtLinguagem" hidden="true"/>
                                                    <input type="text" value="${jg.multiplayer}" name="txtMultiplayer" hidden="true"/>
                                                    <input type="text" value="${jg.online}" name="txtOnline" hidden="true"/>
                                                    <button type="submit" data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                                                        <span class="glyphicon glyphicon-pencil"></span>
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <form method="post" action="ExcluirJogo" id="${jg.titulo}">
                                                    <input type="text" value="Excluir" name="operacao" hidden="true"/>
                                                    <input type="text" value="${jg.id}" name="txtIdJogo" hidden="true"/>
                                                    <input type="text" value="${jg.titulo}" name="txtTitulo" hidden="true"/>
                                                    <button type="button" class="btn btn-default dropdown-toggle" onclick="deleteJogo('${jg.titulo}')">
                                                        <span class="glyphicon glyphicon-trash"></span>
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="../../dist/js/bootstrap.min.js"></script>
        <script src="../../assets/js/docs.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
