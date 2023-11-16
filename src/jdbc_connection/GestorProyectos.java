package jdbc_connection;

import java.sql.*;
import java.util.Scanner;

public class GestorProyectos {
    public boolean nuevoEmpleado(Statement s) {
        try {

            // Crea una sentencia SQL para insertar un nuevo empleado
            s.executeUpdate( "INSERT INTO EMPLEADOSVICTOR (DNI_NIF, NOMBRE) VALUES ('12345a','VICTOR')");
            s.executeUpdate( "INSERT INTO EMPLEADOSVICTOR (DNI_NIF, NOMBRE) VALUES ('22222d','PCO')");
            s.executeUpdate( "INSERT INTO EMPLEADOSVICTOR (DNI_NIF, NOMBRE) VALUES ('33333v','Alberto')");
            s.executeUpdate( "INSERT INTO EMPLEADOSVICTOR (DNI_NIF, NOMBRE) VALUES ('44444c','bicho')");
            s.executeUpdate( "INSERT INTO EMPLEADOSVICTOR (DNI_NIF, NOMBRE) VALUES ('55555g','Jesus')");
            s.executeUpdate( "INSERT INTO EMPLEADOSVICTOR (DNI_NIF, NOMBRE) VALUES ('66666l','Fernando')");

            return true;
        }catch (SQLException e ){
            e.printStackTrace();
            System.out.println("SQL mensaje: " + e.getMessage());
            System.out.println("SQL Estado: " + e.getSQLState());
            System.out.println("SQL código específico: " + e.getErrorCode());

            return false;


        }



    }

    /*

    public int nuevoproyecto(Statement s,ResultSet rs3){
        try {
            s.executeUpdate("INSERT INTO PROYECTOSVICTOR (NUM_PROY , NOMBRE, DNI_NIF_JEFE_PROY , F_INICIO,  F_FIN) VALUES " +
                    "default,'XIAOMI','123453a','2018-08-18','2018-08-18'");
            System.out.println(rs3.getString("NUM_PROY"));
            s.executeUpdate("INSERT INTO PROYECTOSVICTOR (NUM_PROY , NOMBRE, DNI_NIF_JEFE_PROY , F_INICIO,  F_FIN) VALUES " +
                    "default,'APPLE','555552g','2018-08-18','2024-05-16'");
            System.out.println(rs3.getString("NUM_PROY"));
            s.executeUpdate("INSERT INTO PROYECTOSVICTOR (NUM_PROY , NOMBRE, DNI_NIF_JEFE_PROY , F_INICIO,  F_FIN) VALUES " +
                    "default,'SAMSUNG','333332v','2017-06-15','2027-06-15'");
            System.out.println(rs3.getString("NUM_PROY"));


        }catch (SQLException e){

            e.printStackTrace();
        }


    return 0;
    }
    */
    public int nuevoproyecto(Statement s, ResultSet rs3) {
        try {
            String si = null;
            // Insertar la primera fila
            if (si == null){
                si = "now()";
            }
            s.executeUpdate("INSERT INTO PROYECTOSVICTOR (NOMBRE, DNI_NIF_JEFE_PROY, F_INICIO, F_FIN) " +
                    "VALUES ('XIAOMI', '12345a', " + si + ", '2048-08-18')", Statement.RETURN_GENERATED_KEYS);


            // Obtener el valor generado para NUM_PROY de la primera fila
            ResultSet generatedKeys1 = s.getGeneratedKeys();
            if (generatedKeys1.next()) {
                System.out.println("NUM_PROY de la primera fila: " + generatedKeys1.getInt(1));
            }

            // Insertar la segunda fila
            s.executeUpdate("INSERT INTO PROYECTOSVICTOR (NOMBRE, DNI_NIF_JEFE_PROY, F_INICIO, F_FIN) " +
                    "VALUES ('APPLE', '55555g', '2018-08-18', null)", Statement.RETURN_GENERATED_KEYS);
            // Obtener el valor generado para NUM_PROY de la segunda fila
            ResultSet generatedKeys2 = s.getGeneratedKeys();
            if (generatedKeys2.next()) {
                System.out.println("NUM_PROY de la segunda fila: " + generatedKeys2.getInt(1));
            }

            // Insertar la tercera fila
            s.executeUpdate("INSERT INTO PROYECTOSVICTOR (NOMBRE, DNI_NIF_JEFE_PROY, F_INICIO, F_FIN) " +
                    "VALUES ('SAMSUNG', '33333v', '2017-06-15', '2027-06-15')", Statement.RETURN_GENERATED_KEYS);
            // Obtener el valor generado para NUM_PROY de la tercera fila
            ResultSet generatedKeys3 = s.getGeneratedKeys();
            if (generatedKeys3.next()) {
                System.out.println("NUM_PROY de la tercera fila: " + generatedKeys3.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void asignaEmpAproyecto() {



    }
}
