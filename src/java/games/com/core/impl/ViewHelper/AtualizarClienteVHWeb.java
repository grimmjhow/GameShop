/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.Usuario;
import games.com.br.domain.Endereco;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public class AtualizarClienteVHWeb implements IViewHelper
{

    public AtualizarClienteVHWeb()
    {

    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Usuario cliente = (Usuario) request.getSession().getAttribute("user");
        
        Endereco endereco = cliente.getEndereco();
        
        cliente.setNome(request.getParameter("txtNome"));
        cliente.setStatus(1);
        cliente.setCpf(request.getParameter("txtCpf"));
        cliente.setSexo(request.getParameter("selSexo"));
        cliente.setTelefone(request.getParameter("txtTelefone"));

        if (request.getParameter("txtDataNasc").trim().equals(""))
        {
            cliente.setDataNascimento(null);
        } 
        else
        {
            try
            {
                //setando data
                DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dt = sdFormat.parse(request.getParameter("txtDataNasc"));
                cliente.setDataNascimento(Calendar.getInstance());
                cliente.getDataNascimento().setTime(dt);    //setando a data
            } catch (ParseException ex)
            {
                cliente.setDataNascimento(null);
            }
        }
        //setando endereco
        endereco.setLogradouro(request.getParameter("txtLogradouro"));
        endereco.setNumero(request.getParameter("txtNumero"));
        endereco.setBairro(request.getParameter("txtBairro"));
        endereco.setCEP(request.getParameter("txtCep"));
        endereco.setCidade(request.getParameter("txtCidade"));
        endereco.setEstado(request.getParameter("txtEstado"));

        cliente.setEndereco(endereco);

        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if (resultado.getListMensagens().isEmpty())
        {
            request.setAttribute("sucessAtualizaCliente", "Dados Atualizados com Sucesso!");
        } else
        {
            request.setAttribute("erroAtualizaCliente", resultado.getListMensagens());
        }

        try
        {
            request.getRequestDispatcher("SalvarCliente.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

}
