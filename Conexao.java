package ComandosDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao()
    {
        try{
            return DriverManager.getConnection("jdbc:sqlite:"+ "C:\\Users\\Dipir\\Desktop\\db_sqlite\\pilha");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
