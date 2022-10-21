package org.iesch.ad;

import java.sql.*;

public class Info {

    static final String IP = "127.0.0.1";
    static final String PUERTO = "3306";
    static final String BBDD = "personas";
    static final String USER = "root";
    static final String PASSWORD = "1234";

    static final String CADENA_CONEXION = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + BBDD;


    public static void main(String[] args) {

        String sql = "select * from person order by id limit 10";

        try {
            Connection miConexion = DriverManager.getConnection(CADENA_CONEXION, USER, PASSWORD);

            DatabaseMetaData bbddinfo = miConexion.getMetaData();
            System.out.println(bbddinfo.getDatabaseProductName());
            System.out.println(bbddinfo.getDatabaseProductVersion());
            ResultSet result = bbddinfo.getCatalogs();
            while(result.next()){
                System.out.println(result.getString(1));
            }

            miConexion.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
