package jdbc_connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.Collection;
import java.util.Scanner;
public class LectorDatosClientes {
    public void sertaDatosClientes(String nombreFichero, String nombreTabla, String separadorCampos, Scanner sc, Statement s, Connection c){
        int para = 0;
        String DNI,APELLIDOS,CP;
        do {
            System.out.println("Insertaras clientes hasta que pulses 0, para seguir otro numero");
         para =sc.nextInt();
         sc.nextLine();
        String query = "INSERT INTO " + nombreTabla + " (DNI,APELLIDOS,CP) VALUES (?,?,?)";


        try(PreparedStatement ps = s.getConnection().prepareStatement(query)) {
            System.out.println("Nombre");
            DNI = sc.nextLine();
            ps.setString(1, DNI);
            System.out.println("Apellido");
            APELLIDOS = sc.nextLine();
            ps.setString(2, APELLIDOS);
            System.out.println("CP");
            CP = sc.nextLine();
            ps.setString(3, CP);
            System.out.println("Introducidos Correctamente");
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo datos");
        }
        try {

            ResultSet rs6 = s.executeQuery("SELECT * FROM " + nombreTabla);



            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero))) {
                // Escribe los datos desde el ResultSet al archivo CSV sin incluir la cabecera
                while (rs6.next()) {
                    int columnCount = rs6.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        writer.write(rs6.getString(i));
                        if (i < columnCount) {
                            writer.write(separadorCampos);
                        }
                    }
                    writer.write(System.lineSeparator());
                }

                System.out.println("Datos exportados correctamente a CSV.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }




        }while (para!=7);


    }
}
