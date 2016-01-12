<%-- 
    Document   : ConsultarFornecedor
    Created on : 25/02/2015, 11:52:29
    Author     : Felipe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="maskCPF" class="games.com.core.impl.negocio.ValidadorCPF"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de Clientes</title>
    </head>
    <script language="JavaScript">

        function setId()
        {
            document.getElementById("txtIdCliente").setAttribute("value", 0);
            //alert(document.getElementById("txtIdCliente").getAttribute("value"));
        }
        function ChangeOperation(op)
        {
            //--> Linha de teste
            //alert(document.getElementById(op).getAttribute("value"));
            var op = document.getElementById(op).getAttribute("value");
            document.getElementById("operacao").setAttribute("value", op);

            if (op == 'Atualizar')
            {
                var x = confirm("Atualizar dados do Cliente?");
                if (x)
                {
                    document.getElementById("pageForm").setAttribute("action", "AtualizarCliente")
                    document.getElementById("pageForm").submit();
                }
            }
            else if (op == 'Excluir')
            {
                var x = confirm("Excluir dados do Cliente?");
                if (x)
                {
                    document.getElementById("pageForm").setAttribute("action", "ExcluirCliente")
                    document.getElementById("pageForm").submit();
                }
            }
            else
            {
                document.getElementById("pageForm").setAttribute("action", "ConsultarCliente")
                document.getElementById("pageForm").submit()
            }
        }
    </script>
    <body>
        <form method="post" action="ConsultarCliente" id="pageForm">
            <!-- Campo escondido para conter os valores da operacao !-->
            <input type="text" id="operacao" name="operacao" hidden="true"/>
            <div>
                Pesquisar: <input type="text" name="txtPesquisar" id="txtPesquisa" size="50">
                <input id="Consultar" type="button" value="Consultar" onclick="ChangeOperation('Consultar')"/>
            </div>
            <br/>
            <input value="${sessionScope.clientes[0].id}" name="txtIdCliente" id="txtIdCliente" hidden="true"/> <br/>
            Nome: <input value="${sessionScope.clientes[0].nome}" name="txtNome" id="txtNome" /> <br/>
            RG: <input maxlength="9" value="${sessionScope.clientes[0].rg}" name="txtRg" id="txtRg"/> <br/>
            CPF: <input maxlength="11" type="number" value="${sessionScope.clientes[0].cpf}" name="txtCpf" id="txtCpf"/> <br/>
            Sexo: <input maxlength="1" value="${sessionScope.clientes[0].sexo}" name="txtSexo" id="txtSexo"/> <br/>
            Nascimento: <input value="<fmt:formatDate value="${sessionScope.clientes[0].dataNascimento.time}" pattern="yyyy-MM-dd"/>"
                               name="txtDtNasc" id="txtDtNasc" type="date"/> <br/>
            Telefone:   <input value="${sessionScope.clientes[0].telefone}" name="txtTelefone"/> <br/>
            Celular: <input value="${sessionScope.clientes[0].celular}" name="txtCelular" id="txtCelular"/> <br/>
            Email: <input value="${sessionScope.clientes[0].email}" name="txtEmail"/> <br/>
            <h1>Endereco:</h1> <br/>
            <input type="text" name="txtIdEndereco" value="${sessionScope.clientes[0].endereco.id}" hidden="true"/>
            Logradouro: <input type="text" value="${sessionScope.clientes[0].endereco.logradouro}" name="txtLogradouro" /> <br/>
            Numero: <input type="number" min="1" value="${sessionScope.clientes[0].endereco.numero}" name="txtNumero"/> <br/>
            Bairro: <input type="text" value="${sessionScope.clientes[0].endereco.bairro}" name="txtBairro"/> <br/>
            CEP: <input type="text" value="${sessionScope.clientes[0].endereco.CEP}" name="txtCep"/> <br/>
            Cidade: <input type="text" value="${sessionScope.clientes[0].endereco.cidade}" name="txtCidade"/> <br/>
            Estado: <input type="text" value="${sessionScope.clientes[0].endereco.estado}" name="txtEstado"/> <br/><br/>

            <input id="Atualizar" name="Atualizar" type="button" value="Atualizar" onclick="ChangeOperation('Atualizar')"/>
            <input id="Excluir" name="Excluir" type="button" value="Excluir" onclick="ChangeOperation('Excluir')"/>
            <br/>
            <c:forEach var="mensagem" items="${sessionScope.msg}">
                ${mensagem} <br/>
            </c:forEach>
        </form>
    </body>
</html>


