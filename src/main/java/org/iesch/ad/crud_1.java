package org.iesch.ad;

import java.sql.*;
import java.util.Scanner;

import static java.nio.file.Files.delete;

public class crud_1 {

    static final String IP = "127.0.0.1";
    static final String PUERTO = "3306";
    static final String BBDD = "personas";
    static final String USER = "root";
    static final String PASSWORD = "1234";

    static final String CADENA_CONEXION = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + BBDD;

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("CRUD");
        System.out.println("Selecciona una opcion:");
        System.out.println("1 - Select");
        System.out.println("2 - Insert");
        System.out.println("3 - Update");
        System.out.println("4 - Delete");


        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                String sql = "select * from person where id = ?";
                conexionBD(sql, 1, select());
                break;
            case 2:
                String[] datos = insert();
                String sql2 = "insert into person (" + datos[0] + ") values ";
                conexionBD(sql2, 1, select());
                insert();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
        }

    }

    private static void delete() {
    }

    private static void update() {
        
    }

    private static String[] insert() {
        System.out.println("Escribe el id del nuevo usuario");
        String id = sc.nextLine();

        System.out.println("Escribe el nombre del nuevo usuario");
        String datos = sc.nextLine();

        return new String[] {id, datos};
    }

    private static int select() {
        System.out.println("Escribe el id de la fila que quieres ver datos");
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    private static void conexionBD(String sql, int numParam, int value) {

        try {
            Connection miConexion = DriverManager.getConnection(CADENA_CONEXION, USER, PASSWORD);

            // Nuevo
            PreparedStatement sentencia = miConexion.prepareStatement(sql);
            sentencia.setFloat(numParam, value);
            ResultSet miResultSet = sentencia.executeQuery();

            while (miResultSet.next()) {
                System.out.println(miResultSet.getInt(1) + "  " + miResultSet.getString(2)
                        + "  " + miResultSet.getString(3) + "  " + miResultSet.getString(4)
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
