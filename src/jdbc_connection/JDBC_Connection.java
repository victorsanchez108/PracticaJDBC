package jdbc_connection;

import java.sql.*;
import java.util.Scanner;

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


            boolean n = s.execute("CREATE TABLE if NOT EXISTS CLIENTESVICTOR (DNI CHAR(9) NOT NULL,"
                    + " APELLIDOS VARCHAR(32) NOT NULL, CP CHAR(5),"
                    + " PRIMARY KEY(DNI));");
            String meter;
            System.out.println("¿Quieres insertar datos?");
            System.out.println("1--> SI, OTROS --> NO");
            meter = sc.nextLine();
            if (meter.equals("1")) {
                s.executeUpdate("INSERT INTO CLIENTESVICTOR (DNI,APELLIDOS,CP) VALUES "
                        + "('78901234X','NADALES','44126'),"
                        + "('89012345E','HOJAS', null),"
                        + "('56789012B','SAMPER','29730'),"
                        + "('09876543K','LAMIQUIZ', null);");
            } else {
                System.out.println("No se ha introducido");
            }
            ResultSet rs = s.executeQuery("SELECT * FROM CLIENTESVICTOR");
            //EJERCICIO 1:
            System.out.println("--------------------------------------EJERCICIO 1--------------------------------------");
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
                    }default -> {
                        System.out.println("No existe la opción");
                    }
                }

            }


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
}