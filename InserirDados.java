package Hospital;
import java.sql.*;
public class InserirDados {
    public static void inserirPaciente(String nome, String cpf, String telefone){
        Connection conexao = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            stmt = conexao.createStatement();

            // Executa a inserção de dados na tabela de pacientes
            String query = "insert into paciente (nome, cpf, telefone) values ('" + nome + "', '" + cpf + "', '" + telefone + "')";
            stmt.executeUpdate(query);

            System.out.println("Paciente Cadastrado com sucesso!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Falha ao cadastrar o paciente!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void inserirMedico(String nome, String cpf, String especialidade) {
        Connection conexao = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            stmt = conexao.createStatement();

            // Executa a inserção de dados na tabela de pacientes
            String query = "insert into medico (nome, cpf, especialidade) values ('" + nome + "', '" + cpf + "', '" + especialidade + "')";
            stmt.executeUpdate(query);

            System.out.println("Médico Cadastrado com sucesso!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Falha ao cadastrar o médico!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void inserirConsulta(String data, String horario, String pacienteCPF, String medicoNome) {
        Connection conexao = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");

            // Obter o ID do paciente com base no CPF
            String obterIdPacienteSql = "SELECT id FROM paciente WHERE cpf = ?";
            preparedStatement = conexao.prepareStatement(obterIdPacienteSql);
            preparedStatement.setString(1, pacienteCPF);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idPaciente = -1;
            if (resultSet.next()){
                idPaciente = resultSet.getInt("id");
            }
            resultSet.close();

            // Obter o ID do médico com base no nome
            String obterIdMedicoSql = "SELECT id FROM medico WHERE nome like ?";
            preparedStatement = conexao.prepareStatement(obterIdMedicoSql);
            preparedStatement.setString(1, '%' + medicoNome + '%');
            resultSet = preparedStatement.executeQuery();
            int idMedico = -1;
            if (resultSet.next()){
                idMedico = resultSet.getInt("id");
            }
            resultSet.close();

            if (idPaciente != -1 && idMedico != -1){
                // Executa a inserção de dados na tabela de consulta
                String inserirConsultaSql = "INSERT INTO consulta (data_consulta, hora_consulta, id_paciente, id_medico) VALUES (?, ?, ?, ?)";
                preparedStatement = conexao.prepareStatement(inserirConsultaSql);
                preparedStatement.setString(1, data);
                preparedStatement.setString(2, horario);
                preparedStatement.setInt(3, idPaciente);
                preparedStatement.setInt(4, idMedico);
                preparedStatement.executeUpdate();

                System.out.println("Consulta Marcada com sucesso!");
            } else {
                System.out.println("Paciente ou médico não encontrado!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Falha ao marcar a consulta!");
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