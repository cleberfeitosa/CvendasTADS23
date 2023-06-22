/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View;

import Controller.FornecedorDAO;
import Model.Fornecedor;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import util.Constantes;

/**
 *
 * @author clebe
 */
public class FrmFornecedor extends javax.swing.JInternalFrame {

    private int modo;
    private List<Fornecedor> lista;
    private FrmProduto frmproduto;
    
    public FrmFornecedor() {
        initComponents();
        jBtnSelecionarFornecedor.setVisible(false);
    }
    
    public FrmFornecedor(FrmProduto frmProduto){
        initComponents();
        jBtnSelecionarFornecedor.setVisible(true);
        this.frmproduto = frmProduto;
    }

    public void listar() {
        FornecedorDAO fornecedorDao = new FornecedorDAO();
        lista = fornecedorDao.consultarFornecedor();
        DefaultTableModel dados = (DefaultTableModel) jTblFornecedor.getModel();
        dados.setNumRows(0);

        for (Fornecedor fornecedor : lista) {
            dados.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome(),});

        }
        
        

    }

    private void habilitarCampos() {
        jTxtNome.setEnabled(true);
        jTxtEndereco.setEnabled(true);
        jTxtBairro.setEnabled(true);
        jTxtCidade.setEnabled(true);
        jTxtCEp.setEnabled(true);
        jTxtTelefone.setEnabled(true);
        jcbxUF.setEnabled(true);
        jTxtEmail.setEnabled(true);
    }

    private void desabilitarCampos() {
        jTxtNome.setEnabled(false);
        jTxtEndereco.setEnabled(false);
        jTxtBairro.setEnabled(false);
        jTxtCidade.setEnabled(false);
        jTxtCEp.setEnabled(false);
        jTxtTelefone.setEnabled(false);
        jcbxUF.setEnabled(false);
        jTxtEmail.setEnabled(false);
    }

    private void desabilitarBotoes() {
        jBtnSalvar.setEnabled(false);
        jBtnCancelar.setEnabled(false);
        jBtnNovo.setEnabled(true);
        jBtnAlterar.setEnabled(true);
        jBtnExcluir.setEnabled(true);
    }

    private void habilitarBotoes() {
        jBtnSalvar.setEnabled(true);
        jBtnCancelar.setEnabled(true);
        jBtnNovo.setEnabled(false);
        jBtnAlterar.setEnabled(false);
        jBtnExcluir.setEnabled(false);
    }

    public void incluiFornecedor() {
        if (jTxtNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do Fornecedor", "Erro", JOptionPane.ERROR_MESSAGE);
            jTxtNome.requestFocus();
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(jTxtNome.getText().trim());
            fornecedor.setEndereco(jTxtEndereco.getText().trim());
            fornecedor.setBairro(jTxtBairro.getText().trim());
            fornecedor.setCidade(jTxtCidade.getText().trim());
            fornecedor.setUf(jcbxUF.getSelectedItem().toString());
            String cep = jTxtCEp.getText().replace(".","");
            cep = cep.replace("-","");
            fornecedor.setCep(cep);
            fornecedor.setTelefone((String) jTxtTelefone.getValue());
            fornecedor.setEmail(jTxtEmail.getText());

            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            if (fornecedorDAO.icluirFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Fornecedor Cadastrado com Sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o Fornecedor!!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void alteraFornecedor(){
        if(jTxtNome.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Informe o nome do Fornecedor", "Erro",JOptionPane.ERROR_MESSAGE);
            jTxtNome.requestFocus();
        }else{
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(lista.get(jTblFornecedor.getSelectedRow()).getId());
            fornecedor.setNome(jTxtNome.getText().trim());
            fornecedor.setEndereco(jTxtEndereco.getText().trim());
            fornecedor.setBairro(jTxtBairro.getText().trim());
            fornecedor.setCidade(jTxtCidade.getText().trim());
            fornecedor.setUf(jcbxUF.getSelectedItem().toString());
            String cep = jTxtCEp.getText().replace(".","");
            cep = cep.replace("-","");
            fornecedor.setCep(cep);
            fornecedor.setTelefone((String) jTxtTelefone.getValue());
            fornecedor.setEmail(jTxtEmail.getText());
            
            FornecedorDAO fornecedorDao = new FornecedorDAO();
            if(fornecedorDao.alterarFornecedor(fornecedor)){
                JOptionPane.showMessageDialog(this, "Fornecedor alterado com Sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                desabilitarBotoes();
                desabilitarCampos();
            }else{
                JOptionPane.showMessageDialog(this, "Informe o nome do Fornecedor", "Erro",JOptionPane.ERROR_MESSAGE);
            
            }
        }
    }
    
    private void excluiFornecedor() {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        if (fornecedorDAO.excluirFornecedor(lista.get(jTblFornecedor.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Dados do fornecedor excluídos com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            listar();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir os dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTxtFiltroNome = new javax.swing.JTextField();
        jBtnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblFornecedor = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTxtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtEndereco = new javax.swing.JTextField();
        Bairro = new javax.swing.JLabel();
        jTxtBairro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtCidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcbxUF = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTxtCEp = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jTxtTelefone = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jTxtEmail = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jBtnSelecionarFornecedor = new javax.swing.JButton();
        jBtnNovo = new javax.swing.JButton();
        jBtnAlterar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jBtnSalvar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Java GUI Swing - TADS 2023 ! ! !");
        setPreferredSize(new java.awt.Dimension(700, 580));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fornecedor");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Filtro por Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTxtFiltroNome, gridBagConstraints);

        jBtnPesquisar.setMnemonic('p');
        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jBtnPesquisar, gridBagConstraints);

        jTblFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblFornecedor.getTableHeader().setReorderingAllowed(false);
        jTblFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblFornecedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblFornecedor);
        if (jTblFornecedor.getColumnModel().getColumnCount() > 0) {
            jTblFornecedor.getColumnModel().getColumn(0).setMinWidth(1);
            jTblFornecedor.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        jTxtNome.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtNome, gridBagConstraints);

        jLabel4.setText("Endereço");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        jTxtEndereco.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtEndereco, gridBagConstraints);

        Bairro.setText("Bairro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(Bairro, gridBagConstraints);

        jTxtBairro.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtBairro, gridBagConstraints);

        jLabel6.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        jTxtCidade.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtCidade, gridBagConstraints);

        jLabel5.setText("UF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel5, gridBagConstraints);

        jcbxUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MT", "MS", "SP", "PR" }));
        jcbxUF.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jcbxUF, gridBagConstraints);

        jLabel7.setText("CEP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel7, gridBagConstraints);

        try {
            jTxtCEp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTxtCEp.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtCEp, gridBagConstraints);

        jLabel8.setText("Telefone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel8, gridBagConstraints);

        try {
            jTxtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)# ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTxtTelefone.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtTelefone, gridBagConstraints);

        jLabel9.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel9, gridBagConstraints);

        jTxtEmail.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTxtEmail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jBtnSelecionarFornecedor.setText("Selecionar Fornecedor");
        jBtnSelecionarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSelecionarFornecedorActionPerformed(evt);
            }
        });
        jPanel4.add(jBtnSelecionarFornecedor);

        jBtnNovo.setText("Novo");
        jBtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNovoActionPerformed(evt);
            }
        });
        jPanel4.add(jBtnNovo);

        jBtnAlterar.setText("Alterar");
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarActionPerformed(evt);
            }
        });
        jPanel4.add(jBtnAlterar);

        jBtnExcluir.setText("Excluir");
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(jBtnExcluir);

        jBtnSalvar.setText("Salvar");
        jBtnSalvar.setEnabled(false);
        jBtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(jBtnSalvar);

        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.setEnabled(false);
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(jBtnCancelar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

    }//GEN-LAST:event_formInternalFrameActivated

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        String nome = "%" + jTxtFiltroNome.getText() + "%";
        FornecedorDAO fornecedorDao = new FornecedorDAO();
        List<Fornecedor> lista = fornecedorDao.consultaFornecedorNome(nome);
        DefaultTableModel dados = (DefaultTableModel) jTblFornecedor.getModel();
        dados.setRowCount(0);
        for (Fornecedor fornecedor : lista) {
            dados.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome(),});
        }
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    private void jBtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNovoActionPerformed
        habilitarCampos();
        habilitarBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_jBtnNovoActionPerformed

    private void jBtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            incluiFornecedor();
        }else if(modo == Constantes.EDIT_MODE){
            alteraFornecedor();
        }
    }//GEN-LAST:event_jBtnSalvarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        listar();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jTblFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblFornecedorMouseClicked
        if(jTblFornecedor.getSelectedRow()!= -1){
           int indice =jTblFornecedor.getSelectedRow();
           jTxtNome.setText(lista.get(indice).getNome());
           jTxtEndereco.setText(lista.get(indice).getEndereco());
           jTxtBairro.setText(lista.get(indice).getBairro());
           jTxtCidade.setText(lista.get(indice).getCidade());
           jcbxUF.setSelectedItem(lista.get(indice).getUf());
           jTxtCEp.setText(lista.get(indice).getCep());
           jTxtTelefone.setText(lista.get(indice).getTelefone());
           jTxtEmail.setText(lista.get(indice).getEmail());
        }

    }//GEN-LAST:event_jTblFornecedorMouseClicked

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
        if(jTblFornecedor.getSelectedRow()!= -1){
            habilitarBotoes();
            habilitarCampos();
            modo = Constantes.EDIT_MODE;
        }else{
            JOptionPane.showMessageDialog(this,"Selecione um fornecedor da Lista" , "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
       if(jTblFornecedor.getSelectedRow() != -1){
           int resposta = JOptionPane.showConfirmDialog(this, "Confirma exclusão de fornecedor?", "Confirmação", JOptionPane.YES_NO_OPTION);
           if(resposta == JOptionPane.YES_OPTION){
               excluiFornecedor();
           }
           }else{
               JOptionPane.showMessageDialog(this, "Selecione um fornecedor na Lista");
       
       }
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        desabilitarBotoes();
        desabilitarCampos();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnSelecionarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSelecionarFornecedorActionPerformed
       if (jTblFornecedor.getSelectedRow() != -1) {
            frmproduto.setFornecedor(lista.get(jTblFornecedor.getSelectedRow()));
            this.dispose();
            frmproduto.toFront();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnSelecionarFornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bairro;
    private javax.swing.JButton jBtnAlterar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnNovo;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jBtnSalvar;
    private javax.swing.JButton jBtnSelecionarFornecedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblFornecedor;
    private javax.swing.JTextField jTxtBairro;
    private javax.swing.JFormattedTextField jTxtCEp;
    private javax.swing.JTextField jTxtCidade;
    private javax.swing.JTextField jTxtEmail;
    private javax.swing.JTextField jTxtEndereco;
    private javax.swing.JTextField jTxtFiltroNome;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JFormattedTextField jTxtTelefone;
    private javax.swing.JComboBox<String> jcbxUF;
    // End of variables declaration//GEN-END:variables
}
