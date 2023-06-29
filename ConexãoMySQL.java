package Hospital;
import java.sql.*;

public class ConexãoMySQL {
    public static void main(String[] args) {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            System.out.println("Driver carregado com sucesso!");
        }catch (ClassNotFoundException e){
            System.out.println("Driver não pode ser carregado");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (conexao != null){
                    conexao.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
