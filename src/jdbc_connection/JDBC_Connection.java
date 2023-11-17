package jdbc_connection;

import java.sql.*;
import java.util.Scanner;
import java.util.SortedMap;
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

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {


            System.out.println("Si quieres borrar todas las tablas para asegurar que el programa funcione pulsa 1");
            int vez = sc.nextInt();
            if (vez == 1) {
                s.execute("DROP TABLE if EXISTS ASIG_PROYECTOSVICTOR");
                s.execute("DROP TABLE if EXISTS PROYECTOSVICTOR");
                s.execute("DROP TABLE if EXISTS EMPLEADOSVICTOR");
                s.execute("DROP TABLE if EXISTS CLIENTESVICTOR");

            }
            s.execute("CREATE TABLE if NOT EXISTS CLIENTESVICTOR (DNI CHAR(9) NOT NULL," + " APELLIDOS VARCHAR(32) NOT NULL, CP CHAR(5),test int,btest BOOLEAN," + " PRIMARY KEY(DNI));");
            String meter;
            System.out.println("¿Quieres insertar datos?");
            System.out.println("1--> SI, OTROS --> NO");
            sc.nextLine();
            meter = sc.nextLine();
            if (meter.equals("1")) {
                s.executeUpdate("INSERT INTO CLIENTESVICTOR  (DNI,APELLIDOS,CP,test,btest) VALUES " + "('78901234X','NADALES','44126',1,false)," + "('89012345E','HOJAS', null,1,true)," + "('56789012B','SAMPER','29730',1,false)," + "('09876543K','LAMIQUIZ', null,1,true);");
            } else {
                System.out.println("No se ha introducido");
            }
            ResultSet rs = s.executeQuery("SELECT * FROM CLIENTESVICTOR");

            //EJERCICIO 1:
            System.out.println("--------------------------------------EJERCICIO 1--------------------------------------");
            ejercicio1(rs, sc);
            //EJERCICIO 2:
            System.out.println("--------------------------------------EJERCICIO 2--------------------------------------");
            ejercicio2(rs, sc);
            System.out.println("--------------------------------------EJERCICIO 3--------------------------------------");
            datosejer3(s); // añadimos los datos que se nos piden para hacer los siguientes ejercicios
            ResultSet rs3 = s.executeQuery("SELECT NUM_PROY FROM PROYECTOSVICTOR");
            ejercicio3(s, rs3);
            System.out.println("--------------------------------------EJERCICIO 6--------------------------------------");
            ejercicio6(s,sc,c);
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


    public static void ejercicio1(ResultSet rs, Scanner sc) {
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


        } catch (SQLException e) {
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
        }
    }

    public static void ejercicio2(ResultSet rs, Scanner sc) {
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
                            for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
                                System.out.println(rs.getMetaData().getColumnName(x) + " : " + rs.getString(x));
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
                            for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
                                System.out.println(rs.getMetaData().getColumnName(x) + " : " + rs.getString(x));
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


        } catch (SQLException e) {
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());
        }
    }


    public static void ejercicio3(Statement s, ResultSet rs3) {
        try {
            //apartadoA
            System.out.println("Apartado A");
            GestorProyectos gs = new GestorProyectos();
            if (gs.nuevoEmpleado(s)) {
                System.out.println("Introducidos satisfactoriamente");
            } else {
                System.out.println("Ha habido un error");
            }
            System.out.println("Apartado B");
            //apartadoB
            gs.nuevoproyecto(s, rs3);
            //apartadoC
            gs.asignaEmpAproyecto(s);


        } catch (Exception e) {
            System.out.println("SQL mensaje: " + e.getMessage());
        }


    }


    public static void datosejer3(Statement s) {
        try {
            s.execute("CREATE TABLE if NOT EXISTS EMPLEADOSVICTOR (DNI_NIF CHAR(9) NOT NULL, NOMBRE VARCHAR(32) NOT NULL, PRIMARY KEY(DNI_NIF));");
            s.execute("CREATE TABLE if NOT EXISTS PROYECTOSVICTOR (NUM_PROY INTEGER AUTO_INCREMENT NOT NULL, NOMBRE VARCHAR(32) NOT NULL, DNI_NIF_JEFE_PROY CHAR(9) NOT NULL, F_INICIO DATE NOT NULL, F_FIN DATE, PRIMARY KEY(NUM_PROY), FOREIGN KEY FK_PROY_JEFE(DNI_NIF_JEFE_PROY) REFERENCES EMPLEADOSVICTOR (DNI_NIF));");
            s.execute("CREATE TABLE ASIG_PROYECTOSVICTOR(DNI_NIF_EMP CHAR(9), NUM_PROY INTEGER NOT NULL, F_INICIO DATE NOT NULL, F_FIN DATE," +
                    "PRIMARY KEY(DNI_NIF_EMP, NUM_PROY, F_INICIO), " +
                    "FOREIGN KEY F_ASIG_EMP(DNI_NIF_EMP) REFERENCES EMPLEADOSVICTOR (DNI_NIF)," +
                    "FOREIGN KEY F_ASIG_PROY(NUM_PROY) REFERENCES PROYECTOSVICTOR (NUM_PROY));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ejercicio6(Statement s,Scanner sc,Connection c) {
        try {
            LectorDatosClientes ld = new LectorDatosClientes();
            String nombreTabla;
            String nombreFichero, separadorCampos = "";
            int separadorNum;
            System.out.println("Que nombre le damos a la tabla ?");
            nombreTabla = sc.nextLine();
           String sql = "CREATE TABLE IF NOT EXISTS " + nombreTabla + " (DNI CHAR(9) NOT NULL, " + "APELLIDOS VARCHAR(32) NOT NULL, CP CHAR(5), " + "PRIMARY KEY(DNI));";

            s.executeUpdate(sql);
            System.out.println("Que nombre le damos al fichero csv?");
            nombreFichero = sc.nextLine();

            do {
                System.out.println("que separador de campos quieres, 1--> ; o 2 --> |");
                separadorNum = sc.nextInt();
                if (separadorNum == 1){
                    separadorCampos = ";";
                }else if (separadorNum == 2){
                    separadorCampos = "|";
                }else {
                    System.out.println("has elegido mal");
                }
            }while (separadorNum!= 1 && separadorNum!= 2);
           ld.sertaDatosClientes(nombreFichero,  nombreTabla,  separadorCampos,  sc, s,c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
