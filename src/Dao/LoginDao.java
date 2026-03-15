
package Dao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Empleado;
import model.Rol;
import model.Usuario;
public class LoginDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection c;
    Conexion cn;

    public LoginDao() {
      cn = new Conexion();
      c=cn.conectar();
    }
    
    
    
    
    
    public Usuario iniciarSesion(String usuario, String password){
    
        String sql = "SELECT u.usuario, u.contraseña, u.estado, "
               + "e.id AS emp_id, e.nombre AS emp_nombre, "
               + "r.id AS rol_id, r.nombre_rol "
               + "FROM usuarios u "
               + "INNER JOIN empleados e ON u.id_empleado = e.id "
               + "INNER JOIN roles r ON e.id_rol = r.id "
               + "WHERE u.usuario = ? AND u.contraseña = ?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setUsuario(rs.getString("usuario"));
                u.setContrasenia(rs.getString("contraseña"));
                u.setEstado(rs.getString("estado"));

                // Verificar empleado
                Empleado e = new Empleado();
                e.setId(rs.getLong("emp_id"));
                e.setNombre(rs.getString("emp_nombre"));
                
                Rol rol = new Rol();
                rol.setId(rs.getByte("rol_id"));
                rol.setNombre_rol(rs.getString("nombre_rol"));

                e.setRol(rol);
                u.setEmpleado(e);
                

                return u;
            } else {
                System.out.println("No se encontró el usuario o la contraseña.");
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    
}
