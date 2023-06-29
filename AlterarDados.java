package Hospital;

import java.sql.*;

public class AlterarDados {
    public static void alterarPaciente(String cpf, String novoNome, String novoTelefone) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

            String sql = "UPDATE paciente SET nome = ?, telefone = ? WHERE cpf = ?";
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, novoNome);
            preparedStatement.setString(2, novoTelefone);
            preparedStatement.setString(3, cpf);

            int registrosAtualizados = preparedStatement.executeUpdate();
            System.out.println("Registros atualizados: " + registrosAtualizados);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void alterarMedico(String cpf, String novoNome, String novaEspecialidade) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

            String sql = "UPDATE medico SET nome = ?, especialidade = ? WHERE cpf = ?";
            preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, novoNome);
            preparedStatement.setString(2, novaEspecialidade);
            preparedStatement.setString(3, cpf);

            int registrosAtualizados = preparedStatement.executeUpdate();
            System.out.println("Registros atualizados: " + registrosAtualizados);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void alterarConsulta(String cpf, String novaData, String novoHorario) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

            // Obter o ID do paciente com base no CPF
            String obterIdPacienteSql = "SELECT id FROM paciente WHERE cpf = ?";
            preparedStatement = conexao.prepareStatement(obterIdPacienteSql);
            preparedStatement.setString(1, cpf);
            int idPaciente = -1;
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idPaciente = resultSet.getInt("id");
            }
            resultSet.close();

            if (idPaciente != -1) {
                String sql = "UPDATE consulta SET data_consulta = ?, hora_consulta = ? WHERE id_paciente = ?";
                preparedStatement = conexao.prepareStatement(sql);
                preparedStatement.setString(1, novaData);
                preparedStatement.setString(2, novoHorario);
                preparedStatement.setInt(3, idPaciente);

                int registrosAtualizados = preparedStatement.executeUpdate();
                System.out.println("Registros atualizados: " + registrosAtualizados);
            } else {
                System.out.println("Paciente não encontrado!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
