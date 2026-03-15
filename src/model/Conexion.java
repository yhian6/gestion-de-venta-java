
package model;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
   final private String url="jdbc:mysql://localhost:3306/GestionVenta";
   final private String username="root";
   final private String password="root";
   private Connection cn;
   
   public Connection conectar(){
   
       try {
           cn=DriverManager.getConnection(url, username, password);
           return cn;
       } catch (SQLException ex) {
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return null;
   }
   
   public void desconectar(){
   
       try {
           if(conectar()!=null && !conectar().isClosed()){
               try {
                   conectar().close();
               } catch (SQLException ex) {
                   Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       } catch (SQLException ex) {
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
}
