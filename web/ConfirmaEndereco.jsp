<%-- 
    Document   : ConfirmaEndereco
    Created on : 29/04/2015, 17:17:28
    Author     : Felipe Monteiro
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Endereco</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
        <script src="js/limpar.js"></script>

        <!-- ============ Script para setar URL do Form =============== -->
        <script>
            function changeAction(url)
            {
                var form = document.getElementById("formEndereco");

                if (url === "SalvarEnderecoEntrega")
                {
                    form.setAttribute("action", "SalvarEnderecoEntrega");
                    form.submit();
                }
                else if(url === "SelecionarEndereco")
                {
                    form.setAttribute("action","SelecionarEndereco");
                    form.submit();
                }
                else
                {
                    form.setAttribute("action", "CarregaCep");
                    form.submit();
                }
            }
        </script>
    </head>

    <body style="background-color: threedshadow">
        <form method="post" id="formEndereco">
            <input type="text" name="operacao" value="Salvar" hidden="true" />
            <div class="container">
                <div class="jumbotron" style="margin-top: 5%">
                    <h2>
                        <strong>
                            Olá, selecione um endereco para entrega: 
                            <div class="form form-inline">
                                <div>
                                    <select name="selEndereco" class="form-control col-sm-5">
                                        <c:forEach var="end" items="${sessionScope.user.enderecos}">
                                            <option>
                                                ${sessionScope.user.enderecos.indexOf(end)+1}
                                                -
                                                ${end.toString()}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <input type="button" class="btn btn-success" value="Selecionar" style="margin-left: 2%" onclick="changeAction('SelecionarEndereco')"/>
                                </div>
                            </div>
                        </strong>
                    </h2>
                    <!-- ================ inicio do formulario de Endereco =========== -->
                    <div class="form-inline">
                        <strong>Ou procure um novo Endereço aqui <span class="glyphicon glyphicon-arrow-right"></span></strong>
                        <div class="input-group">
                            <span class="input-group-addon">CEP</span>
                            <input type="text" placeholder="Ex. 11111-111" name="txtCep" class="form-control" value="${requestScope.endereco.cep}"/>
                        </div>

                        <button type="submit" class="btn btn-success" onclick="changeAction('CarregaCep')"> 
                            <span class="glyphicon glyphicon-refresh"></span>
                        </button>

                        <c:if test="${requestScope.msgEndereco ne null}">
                            <c:forEach var="erro" items="${requestScope.msgEndereco}">
                                <div class="alert alert-danger" style="margin-top: 1%">
                                    ${erro}
                                </div>
                            </c:forEach>
                        </c:if>

                        <c:if test="${requestScope.msgEnderecoSucess ne null}">
                            <div class="alert alert-success" style="margin-top: 1%">
                                <strong>
                                    ${requestScope.msgEnderecoSucess}
                                </strong>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-horizontal">
                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Logradouro</span>
                            <input type="text" value="${requestScope.endereco.logradouro}" name="txtLogradouro" class="form-control"/>
                        </div>

                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Numero</span>
                            <input type="text" value="${requestScope.endereco.numero}" name="txtNumero" class="form-control" placeholder="111 ou S/N"/>
                        </div>

                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Bairro</span>
                            <input type="text" value="${requestScope.endereco.bairro}" name="txtBairro" class="form-control"/>
                        </div>

                        <!--<div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">CEP</span>
                            <input type="text" value="${sessionScope.user.enderecoEntrega.cep}" name="txtCep" class="form-control" placeholder="Ex. 11111-111"/>
                        </div> -->

                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Cidade</span>
                            <input type="text" value="${requestScope.endereco.cidade}" name="txtCidade" class="form-control"/>
                        </div>

                        <div class="form-inline input-group col-md-5" style="margin-top: 2%">
                            <span class="input-group-addon">Estado</span>
                            <input type="text" value="${requestScope.endereco.estado}" name="txtEstado" class="form-control"/>
                        </div>

                        <div class="form-inline input-group" style="margin-top: 2%">
                            <a class="btn btn-success" onclick="changeAction('SalvarEnderecoEntrega')">
                                Salvar e continuar <strong>Compra</strong>
                            </a>

                            <!-- <input type="submit" class="btn btn-primary" value="Editar Endereço" style="margin-left: 2px" onclick="changeAction('AtualizarEndereco')"/> -->

                            <a href="Games.jsp" class="btn btn-danger" style="margin-left: 2px">
                                Cancelar Compra
                            </a>
                        </div>
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
