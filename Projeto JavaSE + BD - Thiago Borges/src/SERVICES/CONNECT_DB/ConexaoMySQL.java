package SERVICES.CONNECT_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    public static String status = "Não iniciado.";

    public ConexaoMySQL(){}

    public static Connection getConexaoMySQL(){
        Connection connection = null;

        try{
            //Carregar o Driver de conexão
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            //DADOS DA CONEXÃO
            String serverName = "localhost";
            String myDataBase = "db_projetoescola";
            String url = "jdbc:mysql://"+serverName+"/"+myDataBase;
            /*
            Pode acontecer que a conexão ao servidor exija porta
            porta padrão do Mysql 3306
            serverName = "localhost:3306";
             */
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url,username,password);

        } catch (ClassNotFoundException e) {

            System.err.println("\n\nFalha ao carregar DRIVER MYSQL!\n\n");

        } catch (SQLException e) {

            System.err.println("\n\nNão foi possível conectar ao banco!");
            System.out.println(e + "\n\n");

        }

        return connection;

    }

    public static String StatusConnection(){
        return status;
    }

    public static boolean fecharConexao(){

        try {

            ConexaoMySQL.getConexaoMySQL().close();
            return true;

        } catch (SQLException e) {

            System.err.println("Falha ao fechar a conexão!");
            System.out.println(e);

        }

        return false;

    }

    public static Connection ReiniciarConexao(){

        fecharConexao();

        return getConexaoMySQL();

    }

}
