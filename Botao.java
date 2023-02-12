package Interface;

import ComandosDB.Comandos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Botao {
    public static JFrame p = new JFrame("Controle Pilhas");

    public static String input(String str)
    {
        return JOptionPane.showInputDialog(str);
    }
    public static void gerar_botao() throws SQLException {
        JOptionPane.showMessageDialog(p, "Existem "+ChecarValor.maior("pilha", "tipo")+" Tipos de pilhas cadastradas\nExistem "+ChecarValor.maior("pessoa", "id")+" pessoas cadastradas.");
        JButton b1 = new JButton("Alterar estoque");
        JButton b2 = new JButton("Cadastrar pilha");
        JButton b3 = new JButton("Cadastrar pessoa");
        JButton b4 = new JButton("Emprestar");
        JButton b5 = new JButton("Listar Usuarios");
        JButton b6 = new JButton("Listar pilhas");
        JButton b7 = new JButton("Histórico");
        JButton b8 = new JButton("Criar tabelas");
        b1.setBounds(10,10,170,40);
        b2.setBounds(200, 10, 170, 40);
        b3.setBounds(390, 10, 170, 40);
        b4.setBounds(10, 70, 170, 40);
        b5.setBounds(200, 70, 170, 40);
        b6.setBounds(390, 70, 170, 40);
        b7.setBounds(10, 130, 170,40);
        b8.setBounds(430, 390, 170, 40);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.add(b7);
        p.add(b8);
        p.setSize(640,480);
        p.setLayout(null);
        p.setVisible(true);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    botao1();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    botao2();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    botao3();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    botao4();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    botao5();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    botao6();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    botao7();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    botao8();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private static void botao1() throws SQLException {
        String str = input("Digite o valor a ser alterado");
        while (ChecarValor.check_num(str))
        {
            Texto.mensagem("Use apenas números!");
            str = input("Digite o valor a ser alterado");
        }
        String str2 = input("Digite o tipo de pilha");
        while (ChecarValor.check_num(str2) || ChecarValor.check_tip(str2, "tipo", "pilha"))
        {
            Texto.mensagem("Digite um tipo válido!");
            str2 = input("Digite o tipo de pilha");
        }
        //UPDATE pilha SET quant = 4 WHERE tipo = 1;
        Comandos.alterar_num("pilha", "quant", str, "tipo", str2);
    }
    private static void botao2() throws SQLException {
        String nome = input("Digite o tipo da pilha");
        Comandos.cadastrar_tipo(nome);
    }
    private static void botao3() throws SQLException {
        String nome = input("Digite o nome da pessoa");
        Comandos.cadastrar_pessoa(nome);
    }
    private static void botao4() throws SQLException {
        String quant = input("Digite a quantidade emprestada");
        while (ChecarValor.check_num(quant))
        {
            Texto.mensagem("Use apenas números!");
            quant = input("Digite a quantidade emprestada");
        }
        String tipo = input("Digite o tipo de pilha a ser emprestada");
        while (ChecarValor.check_num(tipo) || ChecarValor.check_tip(tipo, "tipo", "pilha"))
        {
            Texto.mensagem("Digite um tipo válido!");
            tipo = input("Digite o tipo de pilha a ser emprestada");
        }
        String id = input("Digite o ID da pessoa a ser emprestado");
        {
            while (ChecarValor.check_num(id) || ChecarValor.check_tip(id, "id", "pessoa"))
            {
                Texto.mensagem("Digite um ID Válido!");
                id = input("Digite o ID da pessoa a ser emprestado");
            }
        }
        Comandos.soma(-1*(Integer.parseInt(quant)), "quant", "pilha", "tipo", tipo);
        Comandos.data_emp(id, quant, tipo);
    }
    private static void botao5() throws SQLException {
        Comandos.listar_usuarios();
    }
    private static void botao6() throws SQLException {
        Comandos.listar_est();
    }
    private static void botao7() throws SQLException {
        String id = input("Digite o ID para buscar o histórico");
        while (ChecarValor.check_num(id) || ChecarValor.check_tip(id, "id", "pessoa"))
        {
            Texto.mensagem("Digite um ID válido!");
            id = input("Digite o ID para buscar o histórico");
        }
        Comandos.historico();
    }
    private static void botao8() throws SQLException {
        Texto.mensagem("Cuidado, irá excluir tabelas existentes.");
        String sim = input("Digite sim para continuar");
        if (sim.equals("sim"))
        {
            Comandos.recriartabelas();
        }
    }

}
