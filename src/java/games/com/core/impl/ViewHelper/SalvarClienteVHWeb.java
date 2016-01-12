package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.Usuario;
import games.com.br.domain.Endereco;
import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Arruda de Melo
 * @version 1.0
 */
public class SalvarClienteVHWeb implements IViewHelper
{

    private Usuario cliente;

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        cliente = new Usuario();
        cliente.setId(0);
        cliente.setEmail(request.getParameter("txtEmail"));
        cliente.setSenha(request.getParameter("txtSenha"));
        cliente.setNome(request.getParameter("txtNome"));
        cliente.setCpf(request.getParameter("txtCpf"));
        //setando data de nascimento
        //SimpleDateFormat simple = new SimpleDateFormat("YYYY-mm-dd");
        DateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dt = new Date();

        if (request.getParameter("txtDataNasc") != null || !request.getParameter("txtDataNasc").equals(""))
        {
            try
            {
                dt = simple.parse(request.getParameter("txtDataNasc")); //pegando a data de nascimento
            } catch (ParseException ex)
            {
                ex.printStackTrace();
            }
            cliente.setDataNascimento(Calendar.getInstance());
            cliente.getDataNascimento().setTime(dt);
        }
        //se a data de nascimento for nula, o validador de campos deve capturar!
        
        cliente.setSexo(request.getParameter("selSexo"));
        cliente.setTelefone(request.getParameter("txtTelefone"));
        
        //Pegando dados do Endereco!
        Endereco end = new Endereco();

        end.setLogradouro(request.getParameter("txtLogradouro"));
        end.setNumero(request.getParameter("txtNumero"));
        end.setBairro(request.getParameter("txtBairro"));
        end.setCEP(request.getParameter("txtCep"));
        end.setCidade(request.getParameter("txtCidade"));
        end.setEstado(request.getParameter("txtEstado"));
        
        EnderecoEntrega entrega = new EnderecoEntrega();
        entrega.setLogradouro(request.getParameter("txtLogradouro"));
        entrega.setNumero(request.getParameter("txtNumero"));
        entrega.setBairro(request.getParameter("txtBairro"));
        entrega.setCep(request.getParameter("txtCep"));
        entrega.setCidade(request.getParameter("txtCidade"));
        entrega.setEstado(request.getParameter("txtEstado"));

        cliente.setEndereco(end);
        cliente.setEnderecoEntrega(entrega);
        
        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if (resultado.getListMensagens().isEmpty())
        {
            request.setAttribute("MsgSucessCliente", "Dados Gravados com Sucesso");
        } else
        {
            request.setAttribute("endereco", cliente.getEndereco());
            request.setAttribute("MsgErrorCliente", resultado.getListMensagens());
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
