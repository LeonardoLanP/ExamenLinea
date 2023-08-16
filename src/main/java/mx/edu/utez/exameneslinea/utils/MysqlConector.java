package mx.edu.utez.exameneslinea.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConector {
    final String DBNAME = "sugel",
            USER = "admin",
            PASSWORD = "Admin$321",
            TIMEZONE = "America/Mexico_City",
            USESSL = "false",
            PUBLICKEY = "true",
            DDLAUTO = "true",
            HOST = "sugel-db.c0q9hqcvrnlq.us-east-1.rds.amazonaws.com";

    public Connection connect() {
        String dataSource = String.format(
                "jdbc:mysql://%s:3306/%s?user=%s&password=%s&serverTimezone=%s&useSSL=%s&allowPublicKeyRetrieval=%s&createDatabaseIfNotExist=%s", HOST, DBNAME, USER, PASSWORD, TIMEZONE, USESSL, PUBLICKEY, DDLAUTO);
        try {
            DriverManager.registerDriver(
                    new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(
                    dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
        try {
            Connection conn= new

                    MysqlConector().connect();
            if (conn!=null){
                System.out.println("Conexion realizada");
                conn.close();
            }else{
                System.out.println("Conexion fallida");
            }
        }catch (SQLException e){
            e.printStackTrace();

        }

    }
}