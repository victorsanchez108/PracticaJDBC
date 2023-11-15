package jdbc_connection;

import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class JDBC_Connection {
    public static void muestraErrorSQL(SQLException e) {
        System.err.println("SQL ERROR mensaje: " + e.getMessage());
        System.err.println("SQL Estado: " + e.getSQLState());
        System.err.println("SQL código específico: " + e.getErrorCode());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String basedatos = "libro_ad";
        String host = "localhost";
        String port = "3306";
        String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "libro_ad";
        String pwd = "1234"; ///

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd);
             Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE ,ResultSet.CONCUR_UPDATABLE);) {


                    s.execute("CREATE TABLE if NOT EXISTS CLIENTESVICTOR (DNI CHAR(9) NOT NULL,"
                    + " APELLIDOS VARCHAR(32) NOT NULL, CP CHAR(5),test int,"
                    + " PRIMARY KEY(DNI));");
            String meter;
            System.out.println("¿Quieres insertar datos?");
            System.out.println("1--> SI, OTROS --> NO");
            meter = sc.nextLine();
            if (meter.equals("1")) {
                s.executeUpdate("INSERT INTO CLIENTESVICTOR  (DNI,APELLIDOS,CP,test) VALUES "
                        + "('78901234X','NADALES','44126',1),"
                        + "('89012345E','HOJAS', null,1),"
                        + "('56789012B','SAMPER','29730',1),"
                        + "('09876543K','LAMIQUIZ', null,1);");
            } else {
                System.out.println("No se ha introducido");
            }
            ResultSet rs = s.executeQuery("SELECT * FROM CLIENTESVICTOR");

            //EJERCICIO 1:
            System.out.println("--------------------------------------EJERCICIO 1--------------------------------------");
            ejercicio1(rs,sc);
            //EJERCICIO 2:
            System.out.println("--------------------------------------EJERCICIO 2--------------------------------------");
            ejercicio2(rs,sc);


            rs.close();
            s.close();
            c.close();


        } catch (SQLException e) {
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    public static void ejercicio1(ResultSet rs,Scanner sc){
        try {

            int i = 0;
            String siguiente = "";
            while (!siguiente.equals("7")) {
                System.out.println("Quieres irte ---> Presiona 7");
                System.out.println("Quieres seguir ---> Presiona k");
                System.out.println("Quieres ir para atras ---> Presiona d");

                siguiente = sc.nextLine();
                switch (siguiente.toLowerCase()) {
                    case "7" -> System.out.println("Adios");
                    case "k" -> {
                        if (!rs.isLast()) {
                            rs.next();
                            i++;
                            System.out.println("[" + (i) + "]");
                            System.out.println("DNI: " + rs.getString("DNI"));
                            System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                            System.out.println("CP: " + rs.getString("CP"));
                        } else {
                            System.out.println("No hay mas crack");
                        }
                    }
                    case "d" -> {
                        if (!rs.isFirst()) {
                            rs.previous();
                            i--;
                            System.out.println("[" + (i) + "]");
                            System.out.println("DNI: " + rs.getString("DNI"));
                            System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
                            System.out.println("CP: " + rs.getString("CP"));

                        } else {
                            System.out.println("No hay mas crack");
                        }
                    }
                    default -> {
                        System.out.println("No existe la opción");
                    }
                }

            }


        }catch (SQLException e ){
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
        }
    }

    public static void ejercicio2(ResultSet rs,Scanner sc){
        try {

            int i = 0;
            String siguiente = "";
            while (!siguiente.equals("7")) {
                System.out.println("Quieres irte ---> Presiona 7");
                System.out.println("Quieres seguir ---> Presiona k");
                System.out.println("Quieres ir para atras ---> Presiona d");
                siguiente = sc.nextLine();
                switch (siguiente.toLowerCase()) {
                    case "7" -> System.out.println("Adios");
                    case "k" -> {
                        if (!rs.isLast()) {
                            rs.next();
                            i++;
                            System.out.println("[" + (i) + "]");
                            for (int x = 1;x <=rs.getMetaData().getColumnCount();x++){
                                System.out.println(rs.getMetaData().getColumnName(x) +  " : " + rs.getString(x));
                            }


                        } else {
                            System.out.println("No hay mas crack");
                        }
                    }
                    case "d" -> {
                        if (!rs.isFirst()) {
                            rs.previous();
                            i--;
                            System.out.println("[" + (i) + "]");
                            for (int x = 1;x <=rs.getMetaData().getColumnCount();x++){
                                System.out.println(rs.getMetaData().getColumnName(x) +  " : " + rs.getString(x));
                            }

                        } else {
                            System.out.println("No hay mas crack");
                        }
                    }
                    default -> {
                        System.out.println("No existe la opción");
                    }
                }

            }


        }catch (SQLException e ){
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
        }
    }










    public static void datosejer3(Statement s){
        try {
        s.execute("CREATE TABLE EMPLEADOSVICTOR (DNI_NIF CHAR(9) NOT NULL, NOMBRE\n" +
                "VARCHAR(32) NOT NULL, PRIMARY KEY(DNI_NIF));\n" +
                "CREATE TABLE PROYECTOS (NUM_PROY INTEGER AUTO_INCREMENT\n" +
                "NOT NULL, NOMBRE VARCHAR(32) NOT NULL, DNI_NIF_JEFE_PROY\n" +
                "CHAR(9) NOT NULL, F_INICIO DATE NOT NULL, F_FIN DATE, PRIMARY\n" +
                "KEY(NUM_PROY), FOREIGN KEY FK_PROY_JEFE(DNI_NIF_JEFE_PROY)\n" +
                "REFERENCES EMPLEADOS (DNI_NIF));:\n" +
                "CREATE TABLE ASIG_PROYECTOS(DNI_NIF_EMP CHAR(9), NUM_PROY\n" +
                "INTEGER NOT NULL, F_INICIO DATE NOT NULL, F_FIN DATE, PRIMARY\n" +
                "KEY(DNI_NIF_EMP, NUM_PROY, F_INICIO), FOREIGN KEY\n" +
                "F_ASIG_EMP(DNI_NIF_EMP) REFERENCES EMPLEADOS (DNI_NIF),\n" +
                "FOREIGN KEY F_ASIG_PROY(NUM_PROY) REFERENCES PROYECTOS\n" +
                "(NUM_PROY));\n");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}