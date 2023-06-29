package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GuiHospital extends JFrame implements ActionListener{

    public static void main(String[] args) {
        new GuiHospital();
    }

    private JLabel lbNome, lbCPF, lbTelefone;
    private JTextField tfNome, tfCPF, tfTelefone;

    private JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair;
    private Connection conexao;
    private PreparedStatement statement;

    private GuiHospital(){
        setLayout(null);
        setTitle("Hospital IFG");
        setBounds(450,400,425,300);

        lbNome = new JLabel("CPF");
        lbCPF = new JLabel("Nome");
        lbTelefone = new JLabel("Telefone");

        tfNome = new JTextField(50);
        tfCPF = new JTextField(11);
        tfTelefone = new JTextField(11);

        btGravar = new JButton(null, new ImageIcon("src/Icons/gravar.png"));
        btGravar.setToolTipText("Gravar");
        btAlterar = new JButton(null, new ImageIcon("src/Icons/alterar.png"));
        btAlterar.setToolTipText("Alterar");
        btExcluir = new JButton(null, new ImageIcon("src/Icons/excluir.png"));
        btExcluir.setToolTipText("Excluir");
        btNovo = new JButton(null, new ImageIcon("src/Icons/novo.png"));
        btNovo.setToolTipText("Novo");
        btLocalizar = new JButton(null, new ImageIcon("src/Icons/localizar.png"));
        btLocalizar.setToolTipText("Localizar");
        btCancelar = new JButton(null, new ImageIcon("src/Icons/cancelar.png"));
        btCancelar.setToolTipText("Cancelar");
        btSair = new JButton(null, new ImageIcon("src/Icons/sair.png"));
        btSair.setToolTipText("Sair");

        lbNome.setBounds(30,30,120,25);
        lbCPF.setBounds(30,60,120,25);
        lbTelefone.setBounds(30,90,120,25);
        tfNome.setBounds(165,30,200,25);
        tfCPF.setBounds(165,60,200,25);
        tfTelefone.setBounds(165,90,200,25);
        btGravar.setBounds(20,200,40,40);
        btAlterar.setBounds(75,200,40,40);
        btExcluir.setBounds(130,200,40,40);
        btLocalizar.setBounds(185,200,40,40);
        btNovo.setBounds(240,200,40,40);
        btCancelar.setBounds(295,200,40,40);
        btSair.setBounds(350,200,40,40);

        add(lbNome);
        add(tfNome);
        add(lbCPF);
        add(tfCPF);
        add(lbTelefone);
        add(tfTelefone);
        add(btNovo);
        add(btLocalizar);
        add(btGravar);
        add(btAlterar);
        add(btExcluir);
        add(btCancelar);
        add(btSair);
        setResizable(false);
        setBotoes(true, true, false, false, false, true);

        btGravar.addActionListener(this);
        btAlterar.addActionListener(this);
        btExcluir.addActionListener(this);
        btNovo.addActionListener(this);
        btLocalizar.addActionListener(this);
        btCancelar.addActionListener(this);
        btSair.addActionListener(this);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Driver não encontrado!");
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
        setVisible(true);
    }
    private void limpaCampos(){
        tfNome.setText("");
        tfCPF.setText("");
        tfTelefone.setText("");
        tfCPF.requestFocus();
        setBotoes(true, true, false, false, false, false);
    }

    public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar,
                          boolean bAlterar, boolean bExcluir, boolean bCancelar){
        btNovo.setEnabled(bNovo);
        btLocalizar.setEnabled(bLocalizar);
        btGravar.setEnabled(bGravar);
        btAlterar.setEnabled(bAlterar);
        btExcluir.setEnabled(bExcluir);
        btCancelar.setEnabled(bCancelar);
    }
    private void buscaPaciente(int codigo){
        try{
            statement = conexao.prepareStatement("SELECT * FROM tabela1 WHERE codigo = ?");
            statement.setInt(1, codigo);
            ResultSet resultado = statement.executeQuery();
            setBotoes(true, true, false, true, true, true);

            if (resultado.next()){
                tfCPF.setText(resultado.getString("CPF"));
                tfNome.setText(resultado.getString("nome"));
                tfTelefone.setText(resultado.getString("telefone"));
            }else {
                JOptionPane.showMessageDialog(null,"Paciente não encontrado");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar o paciente!");
        }
    }
    //arrumar isso aqui
    private void cadastrarPaciente(){
        try {
            statement = conexao.prepareStatement("INSERT INTO tabela1 (cpf, nome, telefone) VALUES (?,?,?)");
            statement.setInt(1, Integer.parseInt(tfCPF.getText()));
            statement.setString(2,tfNome.getText());
            statement.setInt(3,Integer.parseInt(tfTelefone.getText()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");
            limpaCampos();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o paciente!");
        }
    }
    private void atualizarPaciente(){
        try{
            statement = conexao.prepareStatement("UPDATE tabela1 SET nome = ?, telefone = ? WHERE cpf = ?");
            statement.setString(1, tfNome.getText());
            statement.setInt(2, Integer.parseInt(tfTelefone.getText()));
            statement.setInt(3, Integer.parseInt(tfCPF.getText()));
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0){
                JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");
                limpaCampos();
            }else {
                JOptionPane.showMessageDialog(null, "Paciente não encontrado!");
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar paciente!");
        }
    }
    private void excluirPaciente(){
        try{
            statement = conexao.prepareStatement("DELETE FROM tabela1 WHERE codigo = ?");
            statement.setInt(1, Integer.parseInt(tfCPF.getText()));
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0){
                JOptionPane.showMessageDialog(null,"Produto excluido com sucesso!");
                limpaCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Filme não encontrado!");
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao excluir paciente!");
        }
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btNovo){
            limpaCampos();
            setBotoes(false, false, true,false,false, true);
        } else if (e.getSource() == btGravar){
            if (tfNome.getText().isEmpty() || tfCPF.getText().isEmpty() || tfTelefone.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else if (JOptionPane.showConfirmDialog(null, "Deseja gravar os dados?","Gravar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                cadastrarPaciente();
            }
        } else if (e.getSource() == btAlterar){
            if (tfNome.getText().isEmpty() || tfCPF.getText().isEmpty() || tfTelefone.getText().isEmpty()){
                JOptionPane.showConfirmDialog(null, "Preencha todos os campos!");
            } else if (JOptionPane.showConfirmDialog(null, "Deseja alterar os dados?", "Alterar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                atualizarPaciente();
            }
        } else if (e.getSource() == btExcluir){
            if (tfCPF.getText().isEmpty()){
                JOptionPane.showConfirmDialog(null, "Informe o CPF do paciente!");
            } else if (JOptionPane.showConfirmDialog(null, "Deseja excluir os dados?", "Excluir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                excluirPaciente();
            }
        } else if (e.getSource() == btLocalizar){
            if (tfCPF.getText().isEmpty()){
                JOptionPane.showConfirmDialog(null, "Informe o CPF do paciente!");
            } else {
                buscaPaciente(Integer.parseInt(tfCPF.getText()));
            }
        } else if (e.getSource() == btCancelar){
            limpaCampos();
        } else if (e.getSource() == btSair){
            if (JOptionPane.showConfirmDialog(null, "Deseja sair do programa?", "Sair", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
}
