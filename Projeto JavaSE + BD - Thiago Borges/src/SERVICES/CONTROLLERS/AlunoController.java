package SERVICES.CONTROLLERS;

import MODEL.Aluno;
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

public class AlunoController {

    public static void cadastrar() {

        String nome, telefone, endereco;

        System.out.print("Nome do aluno: ");
        nome = Get.texto();

        System.out.print("Telefone: ");
        telefone = Get.texto();

        System.out.print("Endereço: ");
        endereco = Get.texto();

        Aluno s = new Aluno(nome, telefone, endereco);

        Connection conexao = ConexaoMySQL.getConexaoMySQL();

        try {

            String sql = "INSERT INTO tab_aluno "+
                    "(nome, telefone, endereco) "+
                    "VALUES (?,?,?)";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1,s.getNome());
            statement.setString(2,s.getTelefone());
            statement.setString(3,s.getEndereco());

            int rows = statement.executeUpdate();

            if(rows>0){

                System.out.println("\n\n-------------------------------------");
                System.out.println("Aluno cadastrado com sucesso!");
                System.out.println("-------------------------------------");

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();

    }

    public static ArrayList<Aluno> getAll() {

        Connection conexao = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Aluno> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_aluno";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Aluno(
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();
        return lista;

    }

    public static void imprimirLista(ArrayList<Aluno> l){

        if(l.isEmpty()) {

            System.out.println("\n---------------------------------------");
            System.out.println("Não há alunos cadastrados!");
            System.out.println("---------------------------------------");

        } else {

            for(Aluno s : l) {

                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ID: " + s.getId());
                System.out.println("Nome: " + s.getNome());
                System.out.println("Telefone: " + s.getTelefone());
                System.out.println("Endereço: " + s.getEndereco());
                System.out.println("--------------------------------------------------------------------------");

            }

            LocalDate data = LocalDate.now();
            System.out.println("------------Gerado em: " + data.getDayOfMonth() + "/" + (data.getMonthValue()) + "/" + data.getYear()+"-------------------");

        }

    }

    public static ArrayList<Aluno> getStudentByName(String nome) {

        Connection conexao = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Aluno> lista = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tab_aluno WHERE nome like '%" + nome + "%'";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Aluno(
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();
        return lista;

    }

    public static boolean deleteByName(String nome) {

        Connection conexao = ConexaoMySQL.getConexaoMySQL();
        ArrayList<Aluno> lista = getStudentByName(nome);

        System.out.println("========ALUNOS COM NOME " + nome.toUpperCase() + "========");
        imprimirLista(lista);

        System.out.print("Confirme o ID à ser excluído: nº ");

        int id = Get.inteiro();

        try {

            String sql = "DELETE FROM tab_aluno WHERE id = ?";

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
        ArrayList<Aluno> lista = new ArrayList<>();
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("d.M.y");
        String dataFormatada = formatar.format(data);

        try {

            String sql = "SELECT * FROM tab_aluno";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                lista.add(new Aluno(
                        resultSet.getString("nome"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getInt("id")
                ));

            }

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        ConexaoMySQL.fecharConexao();

        try {

            FileWriter arquivo = new FileWriter("c:\\Java\\Relatório Geral de Alunos - " + dataFormatada + ".txt");
            BufferedWriter buff = new BufferedWriter(arquivo);

            for(Aluno s : lista) {

                buff.write("--------------------------------------------------------------------------");
                buff.write("ID: " + s.getId());
                buff.write("Nome: " + s.getNome());
                buff.write("Telefone: " + s.getTelefone());
                buff.write("Endereço: " + s.getEndereco());
                buff.write("--------------------------------------------------------------------------");

            }

            buff.close();
            arquivo.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
