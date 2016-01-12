/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.controle;

import games.com.br.application.Resultado;
import games.com.br.domain.Usuario;
import games.com.br.domain.Endereco;
import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Fornecedor;
import games.com.br.domain.Jogo;
import games.com.br.domain.Pedido;
import games.com.br.domain.Transacao;
import games.com.br.domain.TransacaoCartao;
import games.com.core.IFachada;
import java.util.Map;
import games.com.core.IDAO;
import games.com.core.IStrategy;
import games.com.core.impl.dao.ClienteDAO;
import games.com.core.impl.dao.EnderecoDAO;
import games.com.core.impl.dao.EnderecoEntregaDAO;
import games.com.core.impl.dao.FornecedorDAO;
import games.com.core.impl.dao.JogoDAO;
import games.com.core.impl.dao.PedidoDAO;
import games.com.core.impl.dao.TransacaoCartaoDAO;
import games.com.core.impl.dao.TransacaoDAO;
import games.com.core.impl.negocio.ComplementadorDtCadastro;
import games.com.core.impl.negocio.ValidaCamposEndereco;
import games.com.core.impl.negocio.ValidaCamposJogo;
import games.com.core.impl.negocio.ValidaDesconto;
import games.com.core.impl.negocio.ValidaEntrega;
import games.com.core.impl.negocio.ValidadorAtualizacaoCliente;
import games.com.core.impl.negocio.ValidadorCPF;
import games.com.core.impl.negocio.ValidadorCliente;
import games.com.core.impl.negocio.ValidadorDadosCliente;
import games.com.core.impl.negocio.ValidarSaldoCliente;
import games.com.core.impl.negocio.VerificaSeJogoExiste;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class Fachada implements IFachada
{
    private Map<String, IDAO> daos;
    private Map<String, Map<String, List<IStrategy>>> rns;
    private Resultado resultado;
    
    public Fachada()
    {
        //classe de mensageria
        resultado = new Resultado();
        
        //criando instancias de daos e Strategys(Regras de Negocio)
        daos = new HashMap<>();
        rns = new HashMap<>();  //suprimindo instancia com diamond notation

        //populando Map dos DAOS
        daos.put(Fornecedor.class.getName(), new FornecedorDAO());
        daos.put(Usuario.class.getName(), new ClienteDAO());
        daos.put(Endereco.class.getName(), new EnderecoDAO());
        daos.put(Jogo.class.getName(), new JogoDAO());
        daos.put(Pedido.class.getName(), new PedidoDAO());
        daos.put(Endereco.class.getName(), new EnderecoDAO());
        daos.put(EnderecoEntrega.class.getName(), new EnderecoEntregaDAO());
        daos.put(Pedido.class.getName(), new PedidoDAO());
        daos.put(Transacao.class.getName(), new TransacaoDAO());
        daos.put(TransacaoCartao.class.getName(), new TransacaoCartaoDAO());

        /* Criando instancias das regras de negocio */
        ValidadorCPF validarCPF = new ValidadorCPF();
        ValidadorCliente validarCliente = new ValidadorCliente();
        ValidadorDadosCliente ValidaDadosCliente = new ValidadorDadosCliente();

        /*populando lista de regras de Clientes*/
        List<IStrategy> rnsSalvarCliente = new ArrayList<>();
        rnsSalvarCliente.add(ValidaDadosCliente);
        rnsSalvarCliente.add(validarCPF);
        rnsSalvarCliente.add(validarCliente);
        rnsSalvarCliente.add(new ComplementadorDtCadastro());

        /*Criando map que pode conter todas as regras de cliente
         quando a operacao for Salvar*/
        Map<String, List<IStrategy>> rnsClientes = new HashMap<>();
        //Adicionando regra ao MAP
        rnsClientes.put("Salvar", rnsSalvarCliente);
        
        /* Criando lista de regras de Clientes ao Atualizar */
        List<IStrategy> rnsAtualizarCliente = new ArrayList<>();
        rnsAtualizarCliente.add(new ValidadorAtualizacaoCliente());
        rnsAtualizarCliente.add(new ValidadorCPF());
        //adicionando regras ao MAP
        rnsClientes.put("Atualizar", rnsAtualizarCliente);
        
        /* Criando instancia das regras do Endereco */
        IStrategy validaCampos = new ValidaCamposEndereco();
        
        /* Criando lista para conter as regras de validacao ao atualizar um Endereco */
        List<IStrategy> rnsAtualizarEndereco = new ArrayList<>();
        rnsAtualizarEndereco.add(validaCampos);
        
        List<IStrategy> rnsSalvarEndereco = new ArrayList<>();
        rnsSalvarEndereco.add(validaCampos);
        
        /* Criando Map que pode conter todas as regras do Endereco */
        Map<String,List<IStrategy>> rnsEndereco = new HashMap<>();
        rnsEndereco.put("Atualizar", rnsAtualizarEndereco);
        rnsEndereco.put("Salvar", rnsSalvarEndereco);
        
        //Criando lista de regras para Salvar Transacao Carta de Credito
        List<IStrategy> rnsSalvarTransacaoCartao = new ArrayList<>();
        //rnsSalvarTransacaoCartao.add(new ValidarCartaoCredito());
        rnsSalvarTransacaoCartao.add(new ValidaDesconto());
        rnsSalvarTransacaoCartao.add(new ValidarSaldoCliente());
        
        //Criando mapa para conter as regras
        Map<String,List<IStrategy>> rnsTransacaoCartao = new HashMap<>();
        rnsTransacaoCartao.put("Salvar",rnsSalvarTransacaoCartao);
        
        //criando lista de regras para transacoes com boleto
        List<IStrategy> rnsSalvarTransacao = new ArrayList<>();
        rnsSalvarTransacao.add(new ValidaDesconto());
        
        //criando lista para regras de atualizacao de uma transacao
        List<IStrategy> rnsAtualizaTransacao = new ArrayList<>();
        rnsAtualizaTransacao.add(new ValidarSaldoCliente());
        
        
        //criando mapa para conter as regras
        Map<String,List<IStrategy>> rnsTransacao = new HashMap<>();
        rnsTransacao.put("Salvar", rnsSalvarTransacao);
        rnsTransacao.put("Atualizar", rnsAtualizaTransacao);
        
        //criando lista de regras para salvar um jogo
        List<IStrategy> rnsSalvarJogo = new ArrayList<>();
        rnsSalvarJogo.add(new ValidaCamposJogo());
        rnsSalvarJogo.add(new VerificaSeJogoExiste());
        //criando mapa para conter a lista de regras pra salvar!
        Map<String,List<IStrategy>> rnsJogo = new HashMap<>();
        
        //criando a lista de regras para atualizar um jogo
        List<IStrategy> rnsAtualizarJogo = new ArrayList<>();
        rnsAtualizarJogo.add(new ValidaCamposJogo());
        
        rnsJogo.put("Atualizar",rnsAtualizarJogo);
        rnsJogo.put("Salvar", rnsSalvarJogo);
        
        //criando lista de regras para validar campos do endereco
        List<IStrategy> rnsSalvarEntrega = new ArrayList<>();
        rnsSalvarEntrega.add(new ValidaEntrega());
        
        Map<String,List<IStrategy>> rnsEnderecoEntrega = new HashMap<>();
        rnsEnderecoEntrega.put("Salvar", rnsSalvarEntrega);
        
        rns.put(EnderecoEntrega.class.getName(), rnsEnderecoEntrega);
        rns.put(Jogo.class.getName(), rnsJogo);
        rns.put(Transacao.class.getName(), rnsTransacao);
        rns.put(Usuario.class.getName(), rnsClientes);
        rns.put(Endereco.class.getName(), rnsEndereco);
        rns.put(TransacaoCartao.class.getName(), rnsTransacaoCartao);
    }

    /**
     * Fachada para salvar qualquer subclasse de EntidadeDomínio no banco de
     * dados
     *
     * @param entidade - Subclasse de EntidadeDominio
     * @return - Retorna Null se tudo deu certo Caso contrário retorna uma
     * String contendo as mensagens de erro
     */
    @Override
    public Resultado salvar(EntidadeDominio entidade)
    {
        String nameClass = entidade.getClass().getName();   //pegando nome da classe
        StringBuilder msg = new StringBuilder();
        Resultado rs = new Resultado();

        //List<IStrategy> regras = rns.get(nameClass);    //instanciando regra de negócio
        rs = executaRegras(entidade, "Salvar");
        
        if (!rs.getListMensagens().isEmpty())
        {
            return rs;
        }

        try
        {
            IDAO dao = daos.get(nameClass); //instancia o DAO correto da classe
            dao.salvar(entidade);   //salva entidade no banco de dados
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagem("Um Erro inesperado ocorreu ao processar seu pedido =[ \nTente Novamente mais tarde");
            return rs;
        }
    }

    @Override
    public Resultado atualizar(EntidadeDominio entidade)
    {
        Resultado rs = new Resultado();
        try
        {
            String nameClass = entidade.getClass().getName();
            
            IDAO dao = daos.get(nameClass); //instanciando o dao correto de cada entidade
                
            rs = executaRegras(entidade, "Atualizar");
                
            if(!rs.getListMensagens().isEmpty())
            {
                return rs;
            }
            
            dao.atualizar(entidade);
            
            return rs;
        } catch (SQLException ex)
        {
            rs.addMensagem("Erro ao Atualizar a entidade");
            return rs;
        }
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade)
    {
        String nameClass = entidade.getClass().getName();
        
        IDAO dao = daos.get(nameClass);
        
        Resultado resultado = new Resultado();
        
        try
        {
            dao.excluir(entidade);
            return resultado;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            resultado.addMensagem("Erro ao excluir entidade! ");
            return resultado;
        }
    }

    @Override
    public Resultado consultar(EntidadeDominio entidade)
    {
        String nameClass = entidade.getClass().getName();
        IDAO dao = daos.get(nameClass);
        Resultado rs = new Resultado();
        try
        {
            List<EntidadeDominio> entidades = dao.consultar(entidade);
            rs.setEntidades(entidades);
            return rs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            rs.addMensagem("Erro ao efetuar a Consulta");
            return rs;
        }
    }

    public Resultado executaRegras(EntidadeDominio entidade, String operacao)
    {
        String nameClass = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();
        Resultado rs = new Resultado();
        
        Map<String, List<IStrategy>> regrasOperacao = rns.get(nameClass);
        rns.get(nameClass);
        //Uma pequena gambiarra! devido a interface!
        if(entidade instanceof Jogo && ((Jogo)entidade).getQuantidade() != 0)
            regrasOperacao = null;
        
        if(regrasOperacao == null)  //nao existem regras para essa entidade?
        {
            return new Resultado();
        }
        
        if(!regrasOperacao.isEmpty())   //lista nao eh vazia?
        {//Sim!
            List<IStrategy> regras = regrasOperacao.get(operacao);
            
            for(IStrategy s : regras)
            {
                String m = s.processar(entidade);
                
                if(m != null)
                {
                    rs.addMensagem(m);
                }
            }
        }
        return rs;
    }
}
