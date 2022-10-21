package org.iesch.ad;

import java.sql.*;

public class ConsultaPre {

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

            // Nuevo
            PreparedStatement sentencia = miConexion.prepareStatement("select * from person where money > ?");
            sentencia.setFloat(1, 4000.00F);
            ResultSet miResultSet = sentencia.executeQuery();

            while (miResultSet.next()) {
                System.out.println(miResultSet.getInt(1) + "  " + miResultSet.getString(2)
                        + "  " + miResultSet.getString("last_name") + "  " + miResultSet.getString(4)
                        + "  " + miResultSet.getString(5) + "  " + miResultSet.getString(6)
                        + "  " + miResultSet.getString(7)
                );

            }

            miConexion.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
