/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Cleber
 */
public class Venda  {
    private Integer id;
    private Date dataVenda;
    private Cliente cliente;
    private List<ItensVenda> itensVenda;

    public Venda() {
    }

    public Venda(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the itensVenda
     */
    public List<ItensVenda> getItensVenda() {
        return itensVenda;
    }

    /**
     * @param itensVenda the itensVenda to set
     */
    public void setItensVenda(List<ItensVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

}
