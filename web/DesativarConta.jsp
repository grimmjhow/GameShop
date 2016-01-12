<%-- 
    Document   : DesativarConta
    Created on : 17/06/2015, 14:55:38
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%> 
<%@taglib uri="/WEB-INF/cewolf.tld.xml" prefix="cewolf" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Desativar Conta</title>
        <!-- Bootstrap components -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Bootstrap core CSS -->
        <link href="js/css/bootstrap.min.css" rel="stylesheet">
        <!-- Script para desativar a conta -->
        <script>
            function desativar()
            {
                var form = document.getElementById("form");

                if (confirm("Deseja realmente desativar sua conta?"))
                    form.submit();
            }
        </script>
    </head>
    <body>
        <form method="post" action="DesativarConta" id="form">
            <input type="text" value="Excluir" name="operacao" hidden="true"/>
            <div class="container" style="margin-top: 10%">
                <div class="well well-lg">
                    <div class="alert alert-danger">
                        <p>
                        <h1> <b>ATENÇÃO!</b> </h1>
                        <br/>
                        <h3>Sua conta será desativa e só poderá ser reativada, após 1 semana!</h3>
                        <div class="form form-inline">
                            <input type="button" value="Desativar" class="btn btn-danger" id="btnDesativar" onclick="desativar()"/>
                            <a class="btn btn-success" href="Games.jsp">Voltar</a>
                        </div>
                        </p>
                    </div>
                </div>
                <c:if test="${requestScope.MsgDesativarErro ne null}">
                    <div class="alert alert-danger">
                        <h1>Descuple, algum erro inesperado aconteceu! <br/> Tente Desativar sua conta mais tarde!</h1>
                        Você vai ser redirecionado em breve!
                    </div>
                </c:if>
                <c:if test="${requestScope.MsgDesativarErro eq null and sessionScope.user eq null}">
                    <div class="alert alert-success">
                        <h1>Conta desativada com sucesso!</h1>
                        Você vai ser redirecionado em breve!
                    </div>
                </c:if>
                <script>
                    window.setTimeout("location.href='Games.jsp'",2000)
                </script> 
            </div>
        </form>
    </body>
</html>
