<%-- 
    Document   : BoletoBancario
    Created on : 04/04/2015, 12:49:47
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagamento: Crédido</title>
        <link rel="stylesheet" href="js/css/bootstrap.min.css">
        <link rel="stylesheet" href="js/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="js/css/bootstrap.css">
        <script src=js/jquery-1.11.2.min.js"></script>
        <script src="js/libs/jquery.mask/jquery.mask.min.js"></script>
        <script src="js/libs/jquery.maskedinput/jquery.maskedinput.js"></script>
    </head>
    <body style="background-color: whitesmoke">
        <form method="post" action="FinalizarCompra">
            <input type="text" hidden="true" value="Salvar" name="operacao"/>
            <div class="container">
                <div class="form-group form-inline">
                    <div class="panel panel-primary col-sm-04" style="margin-top: 10px;background-color: whitesmoke">
                        <div class="panel panel-heading">Dados do Cartão</div>
                        <div class="panel panel-body" style="background-color: whitesmoke">
                            <div class="input-group col-sm-4" style="margin-top: 10px">
                                <span class="input-group-addon">
                                    Número Cartão
                                </span>
                                <input type="text" class="form-control" placeholder=" Ex: 1111-2222-3333-4444"/>
                            </div>

                            <div class="input-group col-sm-4" style="margin-top: 10px">
                                <span class="input-group-addon">
                                    Codigo Segurança
                                </span>
                                <input type="text" class="form-control" placeholder="Ex: 111"/>
                            </div>

                            <div class="input-group col-sm-3" style="margin-top: 10px">
                                <span class="input-group-addon">Parcelas:</span>
                                <select class="form-control">
                                    <option>1x</option> 
                                    <option>2x</option>
                                    <option>3x</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                    <div class="form-inline">
                        <div class="panel panel-primary" style="background-color: whitesmoke">
                            <div class="panel panel-heading">Calculo do Frete</div>
                            <div class="panel panel-body" style="background-color: whitesmoke">
                                <div class="input-group cl-sm-4">
                                    <span class="input-group-addon">CEP</span>
                                    <input type="text" name="txtFreteCEP" class="form-control" value="${sessionScope.user.endereco.CEP}"/>
                                </div>
                                <input class="btn btn-success" type="submit" value="Calcular"/>
                            </div>
                            <div class="panel panel-footer">Valor: R$ 00.00</div>
                        </div>
                    </div>
                <div class="panel panel-primary" style="margin-top: 20px;background-color: whitesmoke">
                    <div class="panel panel-heading">Endereco de Entrega</div>
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="input-group col-sm-4">
                                <span class="input-group-addon">Logradouro </span>
                                <input type="text" name="txtEndereco" class="form-control" value="${sessionScope.user.endereco.logradouro}"/>
                            </div>

                            <div class="input-group col-sm-2">
                                <span class="input-group-addon">Número </span>
                                <input type="text" name="txtNumero" class="form-control" value="${sessionScope.user.endereco.numero}" />
                            </div>

                            <div class="input-group col-sm-4">
                                <span class="input-group-addon">Bairro</span>
                                <input type="text" name="txtBairro" class="form-control" value="${sessionScope.user.endereco.bairro}"/>
                            </div>
                        </div>

                        <div class="form-horizontal" style="margin-top: 10px">
                            <div class="form-inline">               
                                <div class="input-group col-sm-4">
                                    <span class="input-group-addon">Cidade</span>
                                    <input type="text" name="txtCidade" class="form-control" value="${sessionScope.user.endereco.cidade}"/>
                                </div>               
                                <div class="input-group col-sm-2">
                                    <span class="input-group-addon">Estado</span>
                                    <input type="text" name="txtEstado" class="form-control" value="${sessionScope.user.endereco.estado}"/>
                                </div>               
                                <div class="input-group col-sm-4">
                                    <span class="input-group-addon">CEP</span>
                                    <input type="text" name="txtCEP" class="form-control" value="${sessionScope.user.endereco.CEP}"/>
                                </div>
                            </div>    
                        </div>
                    </div>
                    
                    <div class="panel panel-footer">
                        <input type="submit" value="Editar" class="btn btn-primary"/>
                    </div>
                </div>
                <div class="form-group form-inline">
                    <input class="btn btn-success" value="Continuar Comprando" type="submit"/>
                    <a href="Games.jsp"><input class="btn btn-default" value="Cancelar"/> </a>
                </div>
            </div>
        </form>
    </body>
    <!-- JavaScript dos componentes BootStrap !-->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/tests/vendor/jquery.min.js"></script>
    <script src="js/dropdown.js"></script>
</html>
