package dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import models.*;

public class Persistencia {

    private static Connection con;

    private Persistencia() {};

    public static List busca(Busca busca, Preferencias preferencias) throws SQLException {
        con = Conexao.getConexao();
        List<Destino> destinos = new ArrayList();
        PreparedStatement buscaStr;
        
        if (preferencias.getClima().equals("")) {
            buscaStr = con.prepareStatement("SELECT * FROM destino WHERE gastos <= ?;");
            buscaStr.setFloat(1, busca.getValor());
        } else {
            buscaStr = con.prepareStatement("SELECT * FROM destino WHERE gastos <= ? AND clima = ?;");
            buscaStr.setFloat(1, busca.getValor());
            buscaStr.setString(2, preferencias.getClima());
        }
        ResultSet res = buscaStr.executeQuery();
        while (res.next()) {
            destinos.add(new Destino(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(4), res.getString(5)));
        }
        return destinos;
    }

    public static Destino getDestino(int id) throws SQLException, Exception {
        con = Conexao.getConexao();
        PreparedStatement destinoStr = con.prepareStatement("SELECT * FROM destino WHERE id = ?;");
        destinoStr.setInt(1, id);
        ResultSet res = destinoStr.executeQuery();
        if (res.next()) {
            return new Destino(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(4), res.getString(5));
        } else {
            throw new Exception("ID inválido" + res.toString());
        }
    }

    public static Preferencias getPreferencias(int uid) {
        return new Preferencias("Frio", "Avião", "Aventureiro,Explorador", "", "Culinária local", "Cartão");
    }
}
