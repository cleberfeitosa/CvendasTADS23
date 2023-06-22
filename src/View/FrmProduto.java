/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * ClienteFrame.java
 *
 * Created on 20/02/2011, 11:05:14
 */
package View;

import Controller.ProdutoDAO;
import Model.Fornecedor;
import Model.Produto;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import util.Constantes;

/**
 *
 * @author Cleber
 */
public class FrmProduto extends javax.swing.JInternalFrame {

    //private DefaultTableModel tableModel;
    //private ListSelectionModel listModel;
    private List<Produto> produtos;
    private int modo;
    private Fornecedor fornecedor;
    private FrmRegistraVenda registraVenda;

    /**
     * Creates new form ClienteFrame
     */
    public FrmProduto() {
        initComponents();
        // defineModelo();
        btnSelecionaProduto.setVisible(false);
        listar();
    }
    
    public FrmProduto(FrmRegistraVenda registraVenda) {
        initComponents();
        listar();
        btnSelecionaProduto.setVisible(true);
        this.registraVenda = registraVenda;
    }

    public void listar() {
        ProdutoDAO produtoDao = new ProdutoDAO();
        produtos = produtoDao.consultaProduto();
        DefaultTableModel dados = (DefaultTableModel) jTblProdutos.getModel();
        dados.setNumRows(0);

        for (Produto produto : produtos) {
            dados.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),});
        }
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            jFtxtValor.setFormatterFactory(new DefaultFormatterFactory(formatterValor));

            DecimalFormat formatoEstoque = new DecimalFormat("#,###");
            NumberFormatter formatterEstoque = new NumberFormatter(formatoEstoque);
            formatterEstoque.setValueClass(Integer.class);
            jFtxtEstoque.setFormatterFactory(new DefaultFormatterFactory(formatterEstoque));
        } catch (Exception ex) {
            Logger.getLogger(FrmProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    public FrmProduto(RegistraVendaFrame registraVenda) {
        initComponents();
        defineModelo();
        btnSelecionaProduto.setVisible(true);
        this.registraVenda = registraVenda;
    }
     */
 /*
    private void defineModelo() {
        tableModel = (DefaultTableModel) tblProduto.getModel();
        listModel = tblProduto.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostraDetalheProduto();
                }
            }
        });
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            ftfValor.setFormatterFactory(new DefaultFormatterFactory(formatterValor));

            DecimalFormat formatoEstoque = new DecimalFormat("#,###");
            NumberFormatter formatterEstoque = new NumberFormatter(formatoEstoque);
            formatterEstoque.setValueClass(Integer.class);
            ftfEstoque.setFormatterFactory(new DefaultFormatterFactory(formatterEstoque));

            tblProduto.getColumnModel().getColumn(1).setPreferredWidth(700);
        } catch (Exception ex) {
            Logger.getLogger(FrmProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */
 /*
    private void atualizaTabela() {
        ProdutoDAO produtoBD = new ProdutoDAO();

        if (txtFiltroProduto.getText().trim().equals("")) {
            produtos = produtoBD.consultaProduto();
        } else {
            produtos = produtoBD.consultaProduto(txtFiltroProduto.getText().trim());
        }
        int numeroLinhas = tableModel.getRowCount();
        for (int i = 0; i < numeroLinhas; i++) {
            tableModel.removeRow(0);
        }
        for (int i = 0; i < produtos.size(); i++) {
            tableModel.insertRow(i, new Object[]{produtos.get(i).getId(), produtos.get(i).getNome()});
        }
    }
     */
 /*
    private void mostraDetalheProduto() {
        if (tblProduto.getSelectedRow() != -1) {
            int indice = tblProduto.getSelectedRow();
            txtNomeProduto.setText(produtos.get(indice).getNome());
            txtNomeFornecedor.setText(produtos.get(indice).getFornecedor().getNome());
            ftfEstoque.setValue(produtos.get(indice).getQtdeEstoque());
            ftfValor.setValue(produtos.get(indice).getValor());
        } else {
            txtNomeProduto.setText("");
            txtNomeFornecedor.setText("");
            ftfEstoque.setValue(null);
            ftfValor.setValue(null);
        }
    }
     */
    private void incluiProduto() {
        if (jTxtNomeProduto.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            jTxtNomeProduto.requestFocus();
        } else if (getFornecedor() == null) {
            JOptionPane.showMessageDialog(this, "Selecione o fornecedor!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Produto produto = new Produto();
            produto.setNome(jTxtNomeProduto.getText().trim());
            produto.setFornecedor(getFornecedor());
            produto.setQtdeEstoque((Integer) jFtxtEstoque.getValue());
            produto.setValor((Double) jFtxtValor.getValue());

            ProdutoDAO produtoDAO = new ProdutoDAO();
            if (produtoDAO.incluiProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                desabilitaBotoes();
                desabilitaCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void alteraProduto() {
        if (jTxtNomeProduto.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            jTxtNomeProduto.requestFocus();
        } else if (getFornecedor() != null) {
            JOptionPane.showMessageDialog(this, "Selecione o fornecedor!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Produto produto = new Produto();
            produto.setId(produtos.get(jTblProdutos.getSelectedRow()).getId());
            produto.setNome(jTxtNomeProduto.getText().trim());
            produto.setFornecedor(getFornecedor());
            produto.setQtdeEstoque((Integer) jFtxtEstoque.getValue());
            produto.setValor((Double) jFtxtValor.getValue());

            ProdutoDAO produtoBD = new ProdutoDAO();
            if (produtoBD.alteraProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Dados alterados com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                desabilitaBotoes();
                desabilitaCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void excluiProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (produtoDAO.excluiProduto(produtos.get(jTblProdutos.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Dados do produto excluídos com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            listar();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void habilitaCampos() {
        jTxtNomeProduto.setEditable(true);
        jFtxtEstoque.setEditable(true);
        jFtxtValor.setEditable(true);
        jBtnSelecionaFornecedor.setEnabled(true);
    }

    private void desabilitaCampos() {
        jTxtNomeProduto.setEditable(false);
        jFtxtEstoque.setEditable(false);
        jFtxtValor.setEditable(false);
        jBtnSelecionaFornecedor.setEnabled(false);
    }

    private void desabilitaBotoes() {
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnNovo.setEnabled(true);
        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }

    private void habilitaBotoes() {
        btnSalvar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }
    
    public void mostrar() {
        int index = jTblProdutos.getSelectedRow();
        if (jTblProdutos.getSelectedRow() != -1) {
            jTxtNomeProduto.setText(produtos.get(index).getNome());
            jFtxtEstoque.setText(String.valueOf(produtos.get(index).getQtdeEstoque()));
            jFtxtValor.setText(""+ produtos.get(index).getValor());
            setFornecedor(produtos.get(index).getFornecedor());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTxtNomeProduto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtNomeFornecedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBtnSelecionaFornecedor = new javax.swing.JButton();
        jFtxtValor = new javax.swing.JFormattedTextField();
        jFtxtEstoque = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTxtFiltroProduto = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblProdutos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnSelecionaProduto = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Java GUI Swing - TADS 2023 - IFMT/ROO");
        setPreferredSize(new java.awt.Dimension(795, 590));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produtos");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        jTxtNomeProduto.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTxtNomeProduto, gridBagConstraints);

        jLabel4.setText("Fornecedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        jTxtNomeFornecedor.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTxtNomeFornecedor, gridBagConstraints);

        jLabel5.setText("Estoque");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Valor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel6, gridBagConstraints);

        jBtnSelecionaFornecedor.setText("..");
        jBtnSelecionaFornecedor.setEnabled(false);
        jBtnSelecionaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSelecionaFornecedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jBtnSelecionaFornecedor, gridBagConstraints);

        jFtxtValor.setEditable(false);
        try {
            jFtxtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#,###.00")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jFtxtValor, gridBagConstraints);

        jFtxtEstoque.setEditable(false);
        try {
            jFtxtEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#,###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jFtxtEstoque, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Filtro Pelo Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtFiltroProduto, gridBagConstraints);

        btnFiltrar.setText("Pesquisar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnFiltrar, gridBagConstraints);

        jTblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTblProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblProdutos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnSelecionaProduto.setText("Seleciona Produto");
        btnSelecionaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaProdutoActionPerformed(evt);
            }
        });
        jPanel4.add(btnSelecionaProduto);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNovo);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAlterar);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(btnExcluir);

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalvar);

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
       ProdutoDAO produtoDao = new ProdutoDAO();
        produtos = produtoDao.consultaProduto(jTxtFiltroProduto.getText());
        DefaultTableModel dados = (DefaultTableModel) jTblProdutos.getModel();
        dados.setNumRows(0);

        for (Produto produto : produtos) {
            dados.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),});
        }
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            jFtxtValor.setFormatterFactory(new DefaultFormatterFactory(formatterValor));

            DecimalFormat formatoEstoque = new DecimalFormat("#,###");
            NumberFormatter formatterEstoque = new NumberFormatter(formatoEstoque);
            formatterEstoque.setValueClass(Integer.class);
            jFtxtEstoque.setFormatterFactory(new DefaultFormatterFactory(formatterEstoque));
        } catch (Exception ex) {
            Logger.getLogger(FrmProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        habilitaCampos();
        habilitaBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            incluiProduto();
        } else if (modo == Constantes.EDIT_MODE) {
            alteraProduto();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        desabilitaBotoes();
        desabilitaCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (jTblProdutos.getSelectedRow() != -1) {
            habilitaCampos();
            habilitaBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (jTblProdutos.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirma exclusão de produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluiProduto();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jBtnSelecionaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSelecionaFornecedorActionPerformed
        FrmFornecedor frmfornecedor = new FrmFornecedor(this);
        frmfornecedor.setVisible(true);
        this.getDesktopPane().add(frmfornecedor);
        frmfornecedor.toFront();
    }//GEN-LAST:event_jBtnSelecionaFornecedorActionPerformed

    private void btnSelecionaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaProdutoActionPerformed
       if (jTblProdutos.getSelectedRow() != -1) {
            int numeroLinha = jTblProdutos.getSelectedRow();
            registraVenda.setProduto(produtos.get(jTblProdutos.getSelectedRow()));
            this.dispose();
            registraVenda.toFront();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelecionaProdutoActionPerformed

    private void jTblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblProdutosMouseClicked
       mostrar();
    }//GEN-LAST:event_jTblProdutosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSelecionaProduto;
    private javax.swing.JButton jBtnSelecionaFornecedor;
    private javax.swing.JFormattedTextField jFtxtEstoque;
    private javax.swing.JFormattedTextField jFtxtValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblProdutos;
    private javax.swing.JTextField jTxtFiltroProduto;
    private javax.swing.JTextField jTxtNomeFornecedor;
    private javax.swing.JTextField jTxtNomeProduto;
    // End of variables declaration//GEN-END:variables

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
        jTxtNomeFornecedor.setText(fornecedor.getNome());
    }
}
