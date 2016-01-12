/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.teste;

/**
 *
 * @author Felipe
 */
public class Servicos
{

    public Servicos()
    {
        cServico = new cServico();
    }
    public final cServico cServico;
    
    public class cServico
    {
        public String Codigo;
        public String Valor;
        public String PrazoEntrega;
        public String ValorSemAdicionais;
        public String ValorMaoPropria;
        public String ValorAvisoRecebimento;
        public String ValorValorDeclarado;
        public String EntregaDomiciliar;
        public String EntregaSabado;
        public String Erro;
        public String MsgErro;
    }
}
