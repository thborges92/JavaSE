package SERVICES;

import MODEL.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UsuarioService {

    public static void cadastra() {

        Scanner get = new Scanner(System.in);
        Usuario u = new Usuario();

        System.out.println("### CADASTRO DE USUÁRIO ###");
        System.out.print("Login: ");
        u.setLogin(get.nextLine());

        System.out.print("Password: ");
        u.setPassword(get.nextLine());

        try {

            FileWriter file = new FileWriter("C:\\java\\projeto-logon.dat", true);
            BufferedWriter buff = new BufferedWriter(file);

            buff.write(u.getLogin() + "##@##" + u.getPassword() + "\n");
            buff.close();

        } catch (FileNotFoundException e) {

            System.err.println("## CAMINHO DO ARQUIVO INVÁLIDO ##");
            System.out.println(e);

        } catch (IOException e) {

            System.err.println("## ERRO DESCONHECIDO COM O ARQUIVO ##");
            System.out.println(e);
            System.out.println("## ENVIE UM PRINT PARA O SUPORTE");

        } catch (Exception e) {

            System.out.println("## ERRO DESCONHECIDO ##");
            System.out.println(e);
            System.out.println("## ENVIE UM PRINT PARA O SUPORTE");

        }

    }

    public static boolean logon() {

        Scanner get = new Scanner(System.in);
        Usuario u = new Usuario();

        System.out.print("Login: ");
        u.setLogin(get.nextLine());

        System.out.print("Password: ");
        u.setPassword(get.nextLine());

        try {

            FileReader file = new FileReader("C:\\java\\projeto-logon.dat");
            BufferedReader buff = new BufferedReader(file);

            String row = "";
            while ((row = buff.readLine()) != null) {

                String[] dados = row.split("##@##");

                if (dados[1].equals(u.getPassword()) && dados[0].equals(u.getLogin())) {

                    System.out.println("Login realizado com sucesso.");
                    return true;

                }

            }

            buff.close();
            file.close();

            System.out.println("Usuário ou senha incorretos.");
            return false;

        } catch (FileNotFoundException e) {

            System.err.println("## CAMINHO DO ARQUIVO INVÁLIDO ##");
            System.out.println(e);

        } catch (IOException e) {

            System.err.println("## ERRO DESCONHECIDO COM O ARQUIVO ##");
            System.out.println(e);
            System.out.println("## ENVIE UM PRINT PARA O SUPORTE");

        } catch (Exception e) {

            System.out.println("## ERRO DESCONHECIDO ##");
            System.out.println(e);
            System.out.println("## ENVIE UM PRINT PARA O SUPORTE");

        }

        return false;

    }

    public static void consultar() {

        int index = 1;
        Scanner get = new Scanner(System.in);

        System.out.println("###USUÁRIOS CADASTRADOS###");

        try {

            FileReader file = new FileReader("C:\\java\\projeto-logon.dat");
            BufferedReader buff = new BufferedReader(file);

            ArrayList<Usuario> usuario = new ArrayList<>();
            String row = "";

            while ((row = buff.readLine()) != null) {

                usuario.add(new Usuario(row.split("##@##")[0], row.split("##@##")[1]));

            }

            for (Usuario u : usuario) {

                System.out.println("Index: " + index);
                u.imprime();

                System.out.println("-------------------------------------");
                index++;

            }

        } catch (FileNotFoundException e) {

            System.err.println("## CAMINHO DO ARQUIVO INVÁLIDO ##");
            System.out.println(e);

        } catch (IOException e) {

            System.err.println("## ERRO DESCONHECIDO COM O ARQUIVO ##");
            System.out.println(e);
            System.out.println("## ENVIE UM PRINT PARA O SUPORTE");

        } catch (Exception e) {

            System.out.println("## ERRO DESCONHECIDO ##");
            System.out.println(e);
            System.out.println("## ENVIE UM PRINT PARA O SUPORTE");

        }

    }

}
