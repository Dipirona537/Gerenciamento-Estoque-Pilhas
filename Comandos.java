package ComandosDB;

import Interface.ChecarValor;
import Interface.Texto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Comandos {
    public static void recriartabelas() throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        st.execute("DROP TABLE pilha;");
        st.execute("DROP TABLE pessoa;");
        st.execute("CREATE TABLE pilha (tipo INTEGER, nome TEXT, quant TEXT);");
        st.execute("CREATE TABLE pessoa (id INTEGER, nome TEXT, quant INT, dados_emp TEXT);");
        st.close();
        Texto.mensagem("Tabelas recriadas!");
    }
    public static void historico() throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM pessoa");
        StringBuilder str = new StringBuilder();
        if (rs.next())
        {
            String nome = rs.getString("nome");
            int quant = rs.getInt("quant");
            String dados = rs.getString("dados_emp");
            str.append(nome).append(" teve ").append(quant).append(" pilhas emprestadas no total\nHistórico\n").append(dados);
        }
        st.close();
        Texto.mensagem(String.valueOf(str));
    }
    public static void listar_est() throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM pilha;");
        StringBuilder str = new StringBuilder();
        while (rs.next())
        {
            int quant = rs.getInt("quant");
            int tipo = rs.getInt("tipo");
            String nome = rs.getString("nome");
            str.append(tipo).append(" - ").append(nome).append(" - ").append(quant).append("\n");
        }
        st.close();
        Texto.mensagem("Quantidade de pilhas no estoque\n"+str);
    }
    public static void listar_usuarios() throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("SELECT id, nome FROM pessoa;");
        StringBuilder str = new StringBuilder();
        while (rs.next())
        {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            str.append(id).append(" - ").append(nome).append("\n");
        }
        st.close();
        Texto.mensagem("Lista ID's e nomes Registrados\n"+str);
    }
    public static void alterar_num(String tabela, String coluna, String valor_novo, String onde, String valor_id) throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        //UPDATE pilha SET quant = 4 WHERE tipo = 1;
        st.execute("UPDATE "+tabela+" SET "+coluna+" = "+valor_novo+" WHERE "+onde+" = "+valor_id);
        Texto.mensagem("Atualizada tabela "+tabela+", setado o valor "+valor_novo+" na coluna "+coluna+" onde "+onde+" = "+valor_id);
        st.close();
    }
    public static void soma(int somar, String column, String tabela, String id, String pesquisa) throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("SELECT "+column+" FROM "+tabela+" WHERE "+id+" = "+pesquisa+";");
        if (rs.next())
        {
            int quant = rs.getInt(column);
            quant += somar;
            st.close();
            alterar_num(tabela, column, String.valueOf(quant), id, pesquisa);
            Texto.mensagem("Estoque: "+quant);
        }else{
            Texto.mensagem("Valores incorretos, verifique e tente novamente.");
            st.close();
        }
    }
    public static void cadastrar_pessoa(String nome) throws SQLException {
        int id = ChecarValor.maior("pessoa", "id");
        Statement st = Conexao.getConexao().createStatement();
        st.execute("INSERT INTO pessoa (id, nome, quant, dados_emp) VALUES ("+id+", '"+nome+"', 0, '')");
        st.close();
        Texto.mensagem("Cadastrado usuario "+nome+" com ID "+id);
    }
    public static void cadastrar_tipo(String nome) throws SQLException {
        int tipo = ChecarValor.maior("pilha", "tipo");
        Statement st = Conexao.getConexao().createStatement();
        st.execute("INSERT INTO pilha (tipo, nome, quant) VALUES ("+tipo+", '"+nome+"', 0);");
        st.close();
        Texto.mensagem("Adicionado pilha "+nome+" com ID "+tipo);
    }
    public static String data_atual()
    {
        return String.valueOf(java.time.LocalDate.now());
    }
    public static void data_emp(String id, String quant, String tipo) throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("SELECT quant, dados_emp FROM pessoa WHERE id = "+id+";");
        StringBuilder str = new StringBuilder();
        if (rs.next())
        {
            int quant_final = Integer.parseInt(quant)+rs.getInt("quant");
            String fina = String.valueOf(quant_final);
            String dados = rs.getString("dados_emp");
            str.append(dados).append("Emprestado ").append(quant).append(" pilhas do tipo ").append(tipo).append(" para usuario com ID ").append(id).append(" na data ").append(Comandos.data_atual()).append("\n");
            st.close();
            Comandos.alterar_num("pessoa", "quant", fina, "id", id);
            Comandos.alterar_reg("pessoa", "dados_emp", String.valueOf(str), "id", id);
        }
    }
    public static void alterar_reg(String tabela, String coluna, String valor_novo, String onde, String valor_id) throws SQLException {
        Statement st = Conexao.getConexao().createStatement();
        //UPDATE pilha SET quant = 4 WHERE tipo = 1;
        st.execute("UPDATE "+tabela+" SET "+coluna+" = '"+valor_novo+"' WHERE "+onde+" = "+valor_id);
        st.close();
    }
}
