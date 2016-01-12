package games.com.br.domain;

import games.teste.MeuBoleto;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
 *
 * @author Felipe Monteiro
 */
public class BoletoDomain extends EntidadeDominio
{
    private final Cedente cedente;
    private final Sacado sacado;
    private final org.jrimum.domkee.comum.pessoa.endereco.Endereco endereco;
    private final ContaBancaria contaBancaria;
    private final Titulo titulo;
    private MeuBoleto boleto;
    private BoletoViewer boletoViewer;
    private File fileBoleto;

    public BoletoDomain(Pedido pedido)
    {
        /**
         * CLIENTE DO DOMINIO DA APLICACAO
         */
        Usuario cliente = pedido.getCliente();

        /**
         * INFORMANDO DADOS SOBRE O CEDENTE
         */
        cedente = new Cedente("Felipe Monteiro", "10.230.110/0001-01");

        /**
         * INFORMANDO DADOS SOBRE O SACADO
         */
        sacado = new Sacado(cliente.getNome(), cliente.getCpf());

        /**
         * PEGANDO ENDERECO DO SACADO
         */
        endereco = new org.jrimum.domkee.comum.pessoa.endereco.Endereco();
        endereco.setLocalidade(cliente.getEndereco().getLogradouro());
        endereco.setBairro(cliente.getEndereco().getBairro());
        endereco.setCep(cliente.getEndereco().getCEP());

        //ATRIBUINDO ENDERECO AO SACADO
        sacado.addEndereco(endereco);

        /**
         * INFORMANDO CONTA BANCARIA PARA DEPOSITO
         */
        contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
        contaBancaria.setAgencia(new Agencia(0142));
        contaBancaria.setCarteira(new Carteira(0241));
        contaBancaria.setNumeroDaConta(new NumeroDaConta(6584, "6"));

        /**
         * CRIANDO TITULAR DO BOLETO
         */
        titulo = new Titulo(contaBancaria, sacado, cedente);
        int numeroDocumento = (int) Math.random();

        titulo.setNumeroDoDocumento("" + numeroDocumento);
        titulo.setNossoNumero("12032608");
        titulo.setDigitoDoNossoNumero("13");
        titulo.setValor(BigDecimal.valueOf(pedido.ValorTotal() + pedido.getFrete().getValor()));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.FAT_FATURA);
        //titulo.setAceite(Titulo.EnumAceite.A);
        titulo.setDesconto(BigDecimal.valueOf(0.15));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);
    }
}
