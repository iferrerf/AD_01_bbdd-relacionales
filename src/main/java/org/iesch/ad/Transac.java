package org.iesch.ad;

import java.sql.*;

public class Transac {

    static final String IP = "127.0.0.1";
    static final String PUERTO = "3306";
    static final String BBDD = "personas";
    static final String USER = "root";
    static final String PASSWORD = "1234";

    static final String CADENA_CONEXION = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + BBDD;


    public static void main(String[] args) {

        // Error porque hemos establecido id como clave primaria y no se puede añadir fila con clave repetida
        String sql = "insert into person (id, name, last_name) values (1003, Mario, Paricio)";
        String sq2 = "alter table person add primary key (id)";
        Connection miConexion = null;

        try {
            miConexion = DriverManager.getConnection(CADENA_CONEXION, USER, PASSWORD);

            miConexion.setAutoCommit(false); // begin;
            Statement miStatement = miConexion.createStatement();
            miStatement.executeUpdate(sql);
            miConexion.commit();

        } catch (SQLException e) {
            System.out.println("Error");
            System.out.println("Hacemos el roolback porque ha habido un error");

            try {
                miConexion.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);

        }finally {
            try {
                miConexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
