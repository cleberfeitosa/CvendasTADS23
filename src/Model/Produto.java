/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;



/**
 *
 * @author T2Ti
 */
public class Produto {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private Integer qtdeEstoque;
    private Double valor;
    private Fornecedor fornecedor;

    public Produto() {
    }
    
    

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the qtdeEstoque
     */
    public Integer getQtdeEstoque() {
        return qtdeEstoque;
    }

    /**
     * @param qtdeEstoque the qtdeEstoque to set
     */
    public void setQtdeEstoque(Integer qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
