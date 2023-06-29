package Hospital;

import javax.swing.*;
import java.sql.*;

public class BuscarDados {
    public static void buscarPaciente(String cpf,  GuiCadastroPaciente guiCadastroPaciente) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            String sql = ("select nome, cpf, telefone from paciente where cpf = ?");
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String nome = resultSet.getString("nome");
                String cpfEncontrado = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");

                SwingUtilities.invokeLater(() -> {
                    guiCadastroPaciente.tfNome.setText(nome);
                    guiCadastroPaciente.tfCPF.setText(cpfEncontrado);
                    guiCadastroPaciente.tfTelefone.setText(telefone);
                });
            }else {
                System.out.println("Paciente não encontrado!");
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (conexao != null){
                    conexao.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void buscarMedico(String cpf, GuiCadastroMedico guiCadastroMedico, JComboBox<String> cbEspecialidade) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            String sql = "select nome, cpf, especialidade from medico where cpf = ?";
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String nome = resultSet.getString("nome");
                String especialidade = resultSet.getString("especialidade");

                guiCadastroMedico.tfNome.setText(nome);
                guiCadastroMedico.cbEspecialidade.setSelectedItem(especialidade);
            }

            int registrosExcluidos = preparedStatement.executeUpdate();
            System.out.println("Registros excluídos: " + registrosExcluidos);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (conexao != null){
                    conexao.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void buscarConsulta(String cpf) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

            // Obter o ID do paciente com base no CPF
            String obterIdPacienteSql = "select id from paciente where cpf = ?";
            preparedStatement = conexao.prepareStatement(obterIdPacienteSql);
            preparedStatement.setString(1, cpf);
            int idPaciente = -1;
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                idPaciente = resultSet.getInt("id");
            }
            resultSet.close();

            // Excluir as consultas do paciente
            if (idPaciente != -1){
                String excluirConsultaSql = "delete from consulta where id_paciente = ?";
                preparedStatement = conexao.prepareStatement(excluirConsultaSql);
                preparedStatement.setInt(1, idPaciente);
                int registrosExcluidos = preparedStatement.executeUpdate();
                System.out.println("Consultas excluídas: " + registrosExcluidos);
            }else {
                System.out.println("Paciente não encontrato!");
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (conexao != null){
                    conexao.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
