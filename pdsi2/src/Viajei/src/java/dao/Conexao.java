package dao;

import java.sql.*;
import java.util.logging.*;

public class Conexao {

    private static Connection conexao;

    private Conexao() {};

    public static synchronized Connection getConexao() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            connect();
        }
        return conexao;
    }

    private static synchronized void connect() throws SQLException {
        try {
            String driverName = "com.mysql.jdbc.Driver";

            Class.forName(driverName);

            String url = "jdbc:mysql://localhost/viajei";
            String username = "root";
            String password = "";

            conexao = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
