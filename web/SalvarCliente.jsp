<%-- 
    Document   : SalvarFornecedor
    Created on : 25/02/2015, 00:00:25
    Author     : Felipe
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Salvar Cliente</title>
        <!-- BootStrap !-->
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script type="text/javascript" src="js/jquery-1.2.6.pack.js"></script>
        <script type="text/javascript" src="js/jquery.maskedinput-1.1.4.pack.js"/></script>
    <script>
        function buscaCep()
        {
            var form = document.getElementById("form");

            form.setAttribute("action", "BuscaCep");

            form.submit();
        }
    </script>
</head>
<body style="background-color: whitesmoke">  
    <c:choose>
        <c:when test="${sessionScope.user ne null}">
            <form method="post" action="AtualizarCliente" id="form">
                <input type="text" hidden="true" value="Atualizar" name="operacao"/>
            </c:when>
            <c:otherwise>
                <form method="post" action="SalvarCliente" id="form">
                    <input type="text" hidden="true" value="Salvar" name="operacao"/>
                </c:otherwise>
            </c:choose>

            <div class="container">
                <div class="form-inline"> <!-- DIV PRINCIPAL INLINE -->
                    <div class="panel panel-success col-md-6"> <!-- FORMULARIO DE DADOS DO CLIENTE -->
                        <div class="panel panel-heading"><strong>Ainda não é Cadastrado?</strong></div>
                        <c:if test="${requestScope.MsgSucessCliente ne null}">
                            <div class="alert alert-success">${requestScope.MsgSucessCliente}</div>
                        </c:if>
                        <c:if test="${requestScope.MsgErrorCliente ne null}">
                            <c:forEach var="erro" items="${requestScope.MsgErrorCliente}">
                                <div class="alert alert-danger">${erro}</div>
                            </c:forEach>
                        </c:if>

                        <!-- Mensagem de erro para atualizacao de clientes -->
                        <c:if test="${requestScope.sucessAtualizaCliente ne null}">
                            <div class="alert alert-success">${requestScope.sucessAtualizaCliente}</div>
                        </c:if>
                        <c:if test="${requestScope.erroAtualizaCliente ne null}">
                            <c:forEach var="erro" items="${requestScope.erroAtualizaCliente}">
                                <div class="alert alert-danger">${erro}</div>
                            </c:forEach>
                        </c:if>
                        <div class="panel panel-body">
                            <c:if test="${sessionScope.user eq null}">
                                <h3>Login</h3>
                                <div class="input-group col-sm-7">
                                    <span class="input-group-addon">Email</span>
                                    <input type="email" name="txtEmail" class="form-control" placeholder="example@ex.com" value="${requestScope.user.email}"/>
                                </div>

                                <div class="input-group col-sm-7" style="margin-top: 2%">
                                    <span class="input-group-addon">Senha</span>
                                    <input type="password" name="txtSenha" class="form-control" value="${requestScope.user.senha}"/>
                                </div>
                            </c:if>
                            <!--<div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">Confirmar Senha</span>
                                <input type="password" name="txtConfirmarSenha" class="form-control"/>
                            </div> -->
                            <h3>Dados pessoais</h3>
                            <div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">Nome</span>
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="text" name="txtNome" class="form-control" placeholder="Ranger Azul" value="${sessionScope.user.nome}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="txtNome" class="form-control" placeholder="Ranger Azul" value="${requestScope.user.nome}"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">CPF</span>
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="text" name="txtCpf" class="form-control" placeholder="111.222.333-67" id="cpf" value="${sessionScope.user.cpf}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="txtCpf" class="form-control" placeholder="111.222.333-67" id="cpf" value="${requestScope.user.cpf}"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">Nascimento</span>
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="date" name="txtDataNasc" class="form-control" value="<f:formatDate pattern="yyyy-MM-dd" value="${sessionScope.user.dataNascimento.getTime()}"></f:formatDate>" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="date" name="txtDataNasc" class="form-control" value="<f:formatDate pattern="yyyy-MM-dd" value="${requestScope.user.dataNascimento.getTime()}"></f:formatDate>" />
                                    </c:otherwise>
                                </c:choose>
                                </div>

                                <div class="input-group col-sm-7" style="margin-top: 2%">
                                    <span class="input-group-addon">Sexo</span>
                                    <select class="form-control" name="selSexo">
                                        <option>
                                            M
                                        </option>
                                        <option>
                                            F
                                        </option>
                                    </select>
                                </div>

                                <div class="input-group col-sm-7" style="margin-top: 2%">
                                    <span class="input-group-addon">Telefone</span>
                                    <c:choose>
                                        <c:when test="${sessionScope.user ne null}">
                                            <input type="tel" name="txtTelefone" class="form-control" placeholder="(11) 1111-1111" value="${sessionScope.user.telefone}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="tel" name="txtTelefone" class="form-control" placeholder="(11) 1111-1111" value="${requestScope.user.telefone}"/>
                                        </c:otherwise>
                                    </c:choose>
                            </div>
                            <h4>Endereço Residêncial</h4>

                            <div class="input-group col-sm-7" style="margin-top: 2%">
                                <span class="input-group-addon">CEP</span>
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="text" name="txtCep" class="form-control" value="${sessionScope.user.endereco.CEP}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="txtCep" class="form-control" value="${requestScope.endereco.cep}"/>
                                    </c:otherwise>
                                </c:choose>
                                <span class="input-group-btn">
                                    <input type="button" class="btn btn-success" value="Go" onclick="buscaCep()"/>
                                </span>
                                
                            </div>

                            <c:if test="${requestScope.msgEndereco ne null}">
                                <div class="alert alert-danger col-sm-7" style="margin-top: 2%">
                                    <strong>${requestScope.msgEndereco}</strong>
                                </div>
                            </c:if>


                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Logradouro</span>
                                        <input type="text" name="txtLogradouro" class="form-control" value="${sessionScope.user.endereco.logradouro}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Logradouro</span>
                                        <input type="text" name="txtLogradouro" class="form-control" value="${requestScope.endereco.logradouro}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>


                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Número</span>
                                        <input type="text" name="txtNumero" class="form-control" value="${sessionScope.user.endereco.numero}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Número</span>
                                        <input type="text" name="txtNumero" class="form-control" value="${requestScope.endereco.numero}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Bairro</span>
                                        <input type="text" name="txtBairro" class="form-control" value="${sessionScope.user.endereco.bairro}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Bairro</span>
                                        <input type="text" name="txtBairro" class="form-control" value="${requestScope.endereco.bairro}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Cidade</span>
                                        <input type="text" name="txtCidade" class="form-control" value="${sessionScope.user.endereco.cidade}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Cidade</span>
                                        <input type="text" name="txtCidade" class="form-control" value="${requestScope.endereco.cidade}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${sessionScope.user ne null}">
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Estado</span>
                                        <input type="text" name="txtEstado" class="form-control" value="${sessionScope.user.endereco.estado}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group col-sm-7" style="margin-top: 2%">
                                        <span class="input-group-addon">Estado</span>
                                        <input type="text" name="txtEstado" class="form-control" value="${requestScope.endereco.estado}"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="panel panel-footer"> 
                            <div class="form-inline">
                                <c:choose>
                                    <c:when test="${sessionScope.user ne null}">
                                        <input type="submit" class="btn btn-success" value="Atualizar"/>
                                        <a href="Games.jsp" class="btn btn-primary">Voltar</a>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" class="btn btn-success" value="Cadastrar"/>
                                        <a href="Games.jsp" class="btn btn-primary">Voltar</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div> <!-- FORMULARIO DE DADOS DO CLIENTE -->
                </div> <!-- DIV PRINCIPAL INLINE -->
            </div>
        </form>
        <!-- JavaScript Includes -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/tests/vendor/jquery.min.js"></script>
        <script src="js/dropdown.js"></script>
</body>
</html>
