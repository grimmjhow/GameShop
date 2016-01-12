<%-- 
    Document   : ConsultarTodos
    Created on : 04/03/2015, 14:28:06
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Todos Clientes</title>
        <!-- Boot Strap !-->
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
    </head>
    <body>
        <form method="post" action="ConsultarTodos">
            <input type="text" value="Consultar" hidden="true" name="operacao"/>
            <div class="jumbotron"> 
                <!-- Comeco do container -->
                <div class="container" style="height: 50px">
                    <div class="input-group">
                        <input class="form-control" name="txtPesquisar" placeholder="Digite CPF ou Nome do Cliente..."/>
                        <span class="input-group-btn">
                            <input type="submit" class="btn btn-default" value="Pesquisar" name="txtPesquisar"/>
                        </span>
                    </div>
                </div>
            </div> 
            <div class="panel panel-primary" style="margin-left: 10px; margin-right: 10px;">

                <div class="panel-heading">
                    <h3 class="panel-title">Consulta de Clientes</h3>
                </div>

                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered">
                            <tr style="text-align: center; background-color: #C2CCD1;" >
                                <td>Nome</td>
                                <td>CPF</td>
                                <td>Sexo</td>
                                <td>Email</td>
                                <td>Telefone</td>
                                <td>Celular</td>
                                <td>Endereco</td>
                                <td>Acao</td>
                            </tr>
                            
                            <c:forEach var="cl" items="${sessionScope.clientes}">
                                <tr>
                                    <td>${cl.nome}</td>
                                    <td>${cl.cpf}</td>
                                    <td>${cl.sexo}</td>
                                    <td>${cl.email}</td>
                                    <td>${cl.telefone}</td>
                                    <td>${cl.celular}</td>
                                    <td>${cl.endereco.logradouro},${cl.endereco.numero} - ${cl.endereco.bairro}</td>
                                    <td> </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </form>
        <!-- JavaScript Includes for bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/tests/vendor/jquery.min.js"></script>
    </body>
</html>
