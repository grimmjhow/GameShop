/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.teste;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import games.com.br.domain.Frete;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Felipe Arruda 
 */
public class TesteCalculoFrete
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException
    {
        URL url = new URL("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=&sDsSenha=&sCepOrigem=70002900&sCepDestino=71939360&nVlPeso=1&nCdFormato=1&nVlComprimento=30&nVlAltura=30&nVlLargura=30&sCdMaoPropria=n&nVlValorDeclarado=0&sCdAvisoRecebimento=n&nCdServico=40010&nVlDiametro=0&StrRetorno=xml&nIndicaCalculo=3");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream input = connection.getInputStream();
        
        XStream stream = new XStream(new DomDriver());
        stream.alias("Servicos", Servicos.class);
        Servicos servico = (Servicos) stream.fromXML(input);
        
        Frete frete = new Frete();
        frete.setValor(new Float(servico.cServico.Valor.replace(",",".")));
        frete.setEntrega(servico.cServico.PrazoEntrega);
        frete.setTipoServico(servico.cServico.Codigo);
        
        System.out.println(frete.getValor());
    }

    public List<Servicos> carrega(InputStream inputStream)
    {
        XStream stream = new XStream(new DomDriver());
        stream.fromXML(inputStream);
        stream.alias("frete", Servicos.class);
        return (List<Servicos>) stream.fromXML(inputStream);
    }
}
