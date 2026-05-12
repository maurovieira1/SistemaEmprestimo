package br.edu.ifpa.laboratorio.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;

public class ConexaoMySQL {

    public static Connection getConexao() {
        Properties props = new Properties();
        try {
            // Busca o arquivo config.properties que está na raiz do seu projeto
            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);
            fis.close();

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Erro ao carregar conexão: " + e.getMessage());
            return null;
        }
    }
}