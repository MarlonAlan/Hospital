package Hospital;

import java.sql.*;
public class ConexaoMySQL {
    public Connection connection = null;
    public boolean getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            System.out.println("Driver carregado com sucesso!");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não pode ser carregado!");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void close(){
        try {
            connection.close();
            System.out.println("Desconectou");
        }catch (SQLException erro){
        }
    }
}
