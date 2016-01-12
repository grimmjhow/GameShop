/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

/**
 *
 * @author Felipe
 */
public class Saldo extends EntidadeDominio
{
    private int idCliente;
    private float saldo;

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }

    public float getSaldo()
    {
        return saldo;
    }

    public void setSaldo(float saldo)
    {
        this.saldo = saldo;
    }
}
