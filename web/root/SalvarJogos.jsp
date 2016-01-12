<%-- 
    Document   : SalvarGames
    Created on : 24/03/2015, 15:10:32
    Author     : Felipe Arruda Monteiro (grimmjhow)
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Jogos</title>
        <link rel="stylesheet" href="../js/css/bootstrap.min.css">
        <link rel="stylesheet" href="../js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="../js/css/bootstrap.css">
        <script src="../js/funcoes.js"></script>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="SalvarJogo">
            <input type="text" hidden="true" name="operacao" value="Salvar"/>
            <div class="container container-fluid"> <!-- Containter principal da aplicacao -->
                <div class="panel panel-info col-md-10" style="margin-top: 2%">
                    <div class="panel panel-heading">Dados do Jogo!</div>
                    <!-- Alertas da Pagina -->
                    <c:if test="${requestScope.erroSalvarJogo.size() gt 0}">
                        <c:forEach var="erro" items="${requestScope.erroSalvarJogo}">
                            <div class="alert alert-danger">${erro}</div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.sucessSalvarJogo ne null}">
                        <div class="alert alert-success">${requestScope.sucessSalvarJogo}</div>
                    </c:if>
                    <!-- Fim dos alertas da pagina  -->
                    <div class="panel panel-body">
                        <div class="form form-group form-horizontal">
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Titulo</span>
                                <input type="text" placeholder="Nome do Jogo" class="form-control" name="txtTitulo" value="${requestScope.jogo.titulo}"/>
                            </div>

                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Descrição</span>
                                <textarea class="form-control" maxlength="100" name="txtDescricao" value="${requestScope.jogo.descricao}">
                                    
                                </textarea>
                            </div>

                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Genero </span>
                                <input type="text" placeholder="action,funny" name="txtGenero" class="form-control" value="${requestScope.jogo.genero}"/>    
                            </div>
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Faixa Etária</span>
                                <input type="number" min="0" class="form-control" name="txtFaixa" placeholder="Digite a Faixa Etaria" value="${requestScope.jogo.faixaEtaria}"/>
                            </div>
                            <!-- 
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Plataforma</span>
                                <input type="text" class="form-control" placeholder="Nome da Plataforma"/>                           
                            </div>
                            -->
                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Valor</span>
                                <input type="text" class="form-control" name="txtValor" placeholder="50,00" value="${requestScope.jogo.valor}"/>
                            </div>

                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Lançamento</span>
                                <input type="text" name="txtAnoLancamento" placeholder="2015" class="form-control" value="${requestScope.jogo.ano}"/>
                            </div> 
                            <!-- CheckedBox dos Campos -->
                            <label>MultiPlayer</label>
                            <!-- Parei apartir daqui!!!!! -->
                            <input type="checkbox" name="ckbMultiPlayer"/>
                            <label>Online</label>
                            <input type="checkbox" name="ckbOnline"/>
                            <!-- Fim do checkBox! -->

                            <div class="input-group col-sm-4">
                                <span class="input-group-addon">Linguagens</span>
                                <select class="form-control" id="selecLinguages" onchange="addLinguagem()" >
                                    <option>Português</option>
                                    <option>Inglês</option>
                                    <option>Espanhol</option>
                                    <option>Francês</option>
                                    <option>Japonês</option>
                                </select>
                            </div>
                            <input type="text" name="txtLinguagem" id="txtLinguagem" class="form-control"/>
                            <div class="input-group col-sm-4" style="margin-top: 2%">
                                <span class="input-group-addon">Quantidade</span>
                                <input type="number" min="4" max="20" name="txtQuantidade" class="form-control" placeholder="Quantidade Estoque"/>
                            </div>
                            <div class="input-group col-sm-4" style="margin-top: 2%">
                                <span class="input-group-addon glyphicon glyphicon-picture"></span>
                                <input type="file" class="btn btn-default" name="txtImagem"/>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-footer form-inline">
                        <div class="form-group">
                            <input type="submit" value="Salvar" class="btn btn-success"/> 
                        </div>
                        <div class="form-group">
                            <a href="dashboard.jsp" class="btn btn-primary">Voltar</a>
                        </div>
                    </div>
                </div>
            </div> <!-- FIM do Containter principal da aplicacao -->
        </form>
        <!-- JavaScript for bootstrap !-->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/tests/vendor/jquery.min.js"></script>
        <script src="../js/dropdown.js"></script>
    </body>
</html>
