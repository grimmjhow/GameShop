package games.teste;

import games.com.br.domain.Pedido;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
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
 * @author Felipe
 */
public class MeuBoleto
{

    public ByteArrayOutputStream getBoleto(Pedido pedido)
    {

        /*
         * INFORMANDO DADOS SOBRE O CEDENTE.
         */
        Cedente cedente = new Cedente("Felipe Monteiro", "10.230.110/0001-01");

        /*
         * INFORMANDO DADOS SOBRE O SACADO.
         */
        Sacado sacado = new Sacado(pedido.getCliente().getNome(), pedido.getCliente().getCpf());

        /* Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(UnidadeFederativa.RN);
        enderecoSac.setLocalidade("Natal");
        enderecoSac.setCep(new CEP("59064-120"));
        enderecoSac.setBairro("Grande Centro");
        enderecoSac.setLogradouro("Rua poeta dos programas");
        enderecoSac.setNumero("1");
        sacado.addEndereco(enderecoSac);*/

        /*
         * INFORMANDO OS DADOS SOBRE O TÍTULO.
         */
        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(1234, "1"));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(pedido.valorTotalDesconto(0.15f)));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.OUTROS);
        //titulo.setAceite(Titulo.EnumAceite.A);
        titulo.setDesconto(BigDecimal.ZERO);
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.valueOf(pedido.getFrete().getValor()));
        titulo.setValorCobrado(BigDecimal.valueOf(pedido.valorTotalDesconto(0.15f) + pedido.getFrete().getValor()));

        /*
         * INFORMANDO OS DADOS SOBRE O BOLETO.
         */
        Boleto boleto = new Boleto(titulo);

        boleto.setLocalPagamento("Pagar em qualquer lotéria ou "+
               "Boca de Caixa de qualquer Banco!");
        boleto.setInstrucaoAoSacado("Este Boleto precisar ser pago ainda hoje"
                + " para ter sua compra validada");
        boleto.setInstrucao8("APÓS o Vencimento, esse boleto não é mais válido!");

        /*
         * GERANDO O BOLETO BANCÁRIO.
         */
        BoletoViewer boletoViewer = new BoletoViewer(boleto);
        
        return boletoViewer.getPdfAsStream();
    }

    /**
     * Exibe o arquivo na tela.
     *
     * @param arquivoBoleto
     */
/*    private static void mostreBoletoNaTela(File arquivoBoleto)
    {

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        try
        {
            desktop.open(arquivoBoleto);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    } */
}
