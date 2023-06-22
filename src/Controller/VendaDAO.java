/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.ItensVenda;
import Model.Produto;
import Model.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Cleber
 */
public class VendaDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private ResultSet rs2;
    private final String registraVenda = "insert into venda (id_cliente, data_venda) values(?, ?)";
    private final String registraItensVenda = "insert into itens_venda (id_produto, id_venda, qtde, valor) values(?, ?, ?, ?)";
    private final String consultaUltimoId = "select max(id) as id from venda";
    private final String consultaVendaPeriodo = "select v.*, c.* from venda v join cliente c on v.id_cliente = c.id where v.data_venda between ? and ?";
    private final String consultaItensVenda = "select i.*, p.* from itens_venda i join produto p on i.id_produto = p.id where i.id_venda = ?";

    public boolean registrarVenda(Venda venda) {
        try {
            Conexao.conectar();
            ps = Conexao.conectar().prepareStatement(registraVenda);
            ps.setInt(1, venda.getCliente().getId());
            ps.setDate(2, venda.getDataVenda());

            ps.executeUpdate();

            ps = Conexao.conectar().prepareStatement(consultaUltimoId);
            rs = ps.executeQuery();
            rs.next();
            venda.setId(rs.getInt("id"));

            for (int i = 0; i < venda.getItensVenda().size(); i++) {
                ps = Conexao.conectar().prepareStatement(registraItensVenda);
                ps.setInt(1, venda.getItensVenda().get(i).getProduto().getId());
                ps.setInt(2, venda.getId());
                ps.setInt(3, venda.getItensVenda().get(i).getQtde());
                ps.setDouble(4, venda.getItensVenda().get(i).getValor());

                ps.executeUpdate();
            }

            Conexao.desconectar(Conexao.conectar());
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return false;
    }

    public List<Venda> consultaVendaPeriodo(Date dataInicial, Date dataFinal) {
        List<Venda> listaVendas = new ArrayList<>();
        Venda venda;
        try {
            Conexao.conectar();
            ps = Conexao.conectar().prepareStatement(consultaVendaPeriodo);
            ps.setDate(1, dataInicial);
            ps.setDate(2, dataFinal);

            rs = ps.executeQuery();
            while (rs.next()) {
                venda = new Venda();
                venda.setId(rs.getInt("v.id"));
                venda.setDataVenda(rs.getDate("v.data_venda"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("c.id"));
                cliente.setNome(rs.getString("c.nome"));
                cliente.setEndereco(rs.getString("c.endereco"));
                cliente.setBairro(rs.getString("c.bairro"));
                cliente.setCidade(rs.getString("c.cidade"));
                cliente.setCep(rs.getString("c.cep"));
                cliente.setUf(rs.getString("c.uf"));
                cliente.setTelefone(rs.getString("c.telefone"));
                cliente.setEmail(rs.getString("c.email"));

                venda.setCliente(cliente);

                ps = Conexao.conectar().prepareStatement(consultaItensVenda);
                ps.setInt(1, venda.getId());
                rs2 = ps.executeQuery();

                List<ItensVenda> listaItensVenda = new ArrayList<>();
                while (rs2.next()) {
                    ItensVenda itenVenda = new ItensVenda();
                    itenVenda.setId(rs2.getInt("i.id"));
                    itenVenda.setQtde(rs2.getInt("i.qtde"));
                    itenVenda.setValor(rs2.getDouble("i.valor"));

                    Produto produto = new Produto();
                    produto.setId(rs2.getInt("p.id"));
                    produto.setNome(rs2.getString("p.nome"));
                    produto.setQtdeEstoque(rs2.getInt("p.qtde_estoque"));
                    produto.setValor(rs2.getDouble("p.valor"));

                    itenVenda.setProduto(produto);

                    listaItensVenda.add(itenVenda);
                }
                venda.setItensVenda(listaItensVenda);
                listaVendas.add(venda);
            }
            Conexao.desconectar(Conexao.conectar());
            return listaVendas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaVendas;
    }
}
