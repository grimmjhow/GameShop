<%-- 
    Document   : AtualizarJogo
    Created on : 17/06/2015, 22:48:52
    Author     : Felipe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <title>Atualizar Jogo</title>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="AtualizarJogo">
            <input type="text" value="${requestScope.jogo.id}" hidden="true" name="txtId"/>
            <input type="text" hidden="true" name="operacao" value="Atualizar"/>
            <div class="container container-fluid"> <!-- Containter principal da aplicacao -->
                <div class="panel panel-info col-md-10" style="margin-top: 2%">
                    <div class="panel panel-heading">Dados do Jogo!</div>
                    <!-- Alertas da Pagina -->
                    <c:if test="${requestScope.erroAtualizarJogo ne null}">
                        <c:forEach var="erro" items="${requestScope.erroAtualizarJogo}">
                            <div class="alert alert-danger">${erro}</div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.sucessAtualizarJogo ne null}">
                        <div class="alert alert-success">${requestScope.sucessAtualizarJogo}</div>
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
                                <!-- <textarea class="form-control" maxlength="100" name="txtDescricao">
                                    ${requestScope.jogo.descricao.trim()}
                                </textarea> -->
                                    <input type="text" name="txtDescricao" maxlength="50" class="form-control" value="${requestScope.jogo.descricao}"/>
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
                                <input type="text" class="form-control" name="txtValor" placeholder="50.00" value="${requestScope.jogo.valor}" min="10"/>
                            </div>

                            <div class="input-group col-sm-4" style="margin-bottom: 2%">
                                <span class="input-group-addon">Lançamento</span>
                                <input type="text" name="txtAno" placeholder="2015" class="form-control" value="${requestScope.jogo.ano}"/>
                            </div> 
                            <!-- CheckedBox dos Campos -->
                            <label>MultiPlayer</label>
                            <c:choose>
                                <c:when test="${requestScope.jogo.multiplayer eq 'S'}">
                                    <input type="checkbox" name="ckbMultiPlayer" checked="ON"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" name="ckbMultiPlayer"/>
                                </c:otherwise>
                            </c:choose>                            
                            <label>Online</label>
                            
                            <c:choose>
                                <c:when test="${requestScope.jogo.online eq 'S'}">
                                    <input type="checkbox" name="ckbOnline" checked="ON"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" name="ckbOnline" />
                                </c:otherwise>
                            </c:choose>
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
                            <input type="text" name="txtIdioma" id="txtLinguagem" class="form-control" value="${requestScope.jogo.idioma}"/>
                        </div>
                    </div>
                    <div class="panel panel-footer form-inline">
                        <div class="form-group">
                            <input type="submit" value="Atualizar" class="btn btn-success"/> 
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
