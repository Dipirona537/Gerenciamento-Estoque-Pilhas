package Outros;

import Interface.Botao;

import java.sql.SQLException;

public class Main {
    /*Tabelas
        pilha -> tipo INT, nome STRING, quant INT
        pessoa -> id INT, nome STRING, quant INT, dados_emp STRING
        pessoa/quant -> quant total emprestada
        pessoa/dados_emp -> string contendo dados de empresitmo, data, tipo e quant emprestada
    */
    public static void main(String[] args) throws SQLException {
        Botao.gerar_botao();
    }
}
