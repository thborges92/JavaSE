package SERVICES.CONTROLLERS;

import MODEL.Turma;
import SERVICES.CONNECT_DB.ConexaoMySQL;
import SERVICES.Get;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class TurmaController {

    public static void cadastrar() {

        String nome, turno;

        System.out.print("Nome da turma: ");
        nome = Get.texto();

        System.out.print("Turno: ");
        turno = Get.texto();

        Turma c = new Turma(nome, turno);

        Connection conexao = ConexaoMySQL.getConexaoMySQL();

        try {

            String sql = "INSERT INTO tab_turma "+
                    "(nome, turno) "+
                    "VALUES (?,?)";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1,c.getNome());
            statement.setString(2,c.getTurno());

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");
                System.out.println("Turma cadastrada com sucesso!");
                System.out.println("-------------------------------------");

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();

    }

    public static ArrayList<Turma> getAll() {

        Connection conexao = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Turma> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_turma";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Turma(
                        resultSet.getString("nome"),
                        resultSet.getString("turno"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();
        return lista;

    }

    public static void imprimirLista(ArrayList<Turma> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há turmas cadastradas!");
            System.out.println("---------------------------------------");

        } else {

            for(Turma c : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + c.getId());
                System.out.println("Nome: " + c.getNome());
                System.out.println("Turno: " + c.getTurno());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"-------------------");

        }

    }

    public static ArrayList<Turma> getClassByShift(String nome) {

        Connection conexao = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Turma> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_turma WHERE turno like '%" + nome + "%'";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Turma(
                        resultSet.getString("nome"),
                        resultSet.getString("turno"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();
        return lista;

    }

    public static boolean deleteByShift(String nome) {

        Connection conexao = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Turma> lista = getClassByShift(nome);

        System.out.println("========TURMAS DO TURNO - " + nome.toUpperCase() + "========");
        imprimirLista(lista);

        System.out.print("Confirme o ID à ser excluído: nº ");

        int id = Get.inteiro();

        try {

            String sql = "DELETE FROM tab_turma WHERE id = ?";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1,id);

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");

            }

            return true;

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();

        return false;

    }

    public static void gerarArquivo() {

        Connection conexao = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Turma> lista = new ArrayList<>();
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("d.M.y");
        String dataFormatada = formatar.format(data);

        try {

            String sql = "SELECT * FROM tab_turma";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Turma(
                        resultSet.getString("nome"),
                        resultSet.getString("turno"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();

        try {

            FileWriter arquivo = new FileWriter("c:\\Java\\Relatório Geral de Turmas - " + dataFormatada + ".txt");
            BufferedWriter buff = new BufferedWriter(arquivo);

            for(Turma c : lista) {

                buff.write("ID: " + c.getId());
                buff.write("Nome: " + c.getNome());
                buff.write("Turno: " + c.getTurno());
                buff.write("--------------------------------------------------------------------------");

            }

            buff.close();
            arquivo.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
