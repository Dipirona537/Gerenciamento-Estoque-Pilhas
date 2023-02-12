package Interface;

import ComandosDB.Conexao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChecarValor {
    public static boolean check_num(String str){
        for (int i = 0;i<str.length();i++)
        {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') return true;
        }
        return false;
    }

    public static boolean check_tip(String str, String id, String table) throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("SELECT "+id+" FROM "+table+";");
        while (rs.next())
        {
            int tipo = rs.getInt(id);
            if (tipo == Integer.parseInt(str))
            {
                st.close();
                return false;
            }
        }
        st.close();
        return true;
    }

    public static int maior(String table, String column) throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("SELECT "+column+" FROM "+table);
        int cont = 0;
        while (rs.next())
        {
            cont++;
        }
        st.close();
        return cont;
    }
}
