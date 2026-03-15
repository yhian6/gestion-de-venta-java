
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Conexion;
import model.Empleado;
import model.Usuario;

public class UsuarioDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection c;
    Conexion cn;

    public UsuarioDao() {
        cn= new Conexion();
        c=cn.conectar();
    }
    
    public List listarUsuario(){
    
        List<Usuario>lista= new ArrayList();
        String sql=""" 
                   select u.id, e.nombre, e.apellidos, u.usuario, u.contraseña, u.estado
                   from usuarios u 
                   join empleados e on u.id_empleado = e.id ORDER BY u.id ASC;
                   """;
        
        try {
            ps=c.prepareStatement(sql); 
            rs=ps.executeQuery();
            
            while(rs.next()){
            Usuario u= new Usuario();
            u.setId(rs.getByte("id"));
            Empleado e= new Empleado();
            e.setId(rs.getLong("id"));
            e.setNombre(rs.getString("nombre"));
            e.setApellidos(rs.getString("apellidos"));
            u.setEmpleado(e);
            u.setUsuario(rs.getString("usuario"));
            u.setContrasenia(rs.getString("contraseña"));
            u.setEstado(rs.getString("estado"));
            lista.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    public Long obtenerIdEmpleado(String nombre, String apellidos){
    
        String sql="select id from empleados where nombre=? and apellidos=? ";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            rs=ps.executeQuery();
            
            if(rs.next()){
            Long id=rs.getLong("id");
            return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return null;
    }
    
  public boolean guardarUsuario(Long id, Usuario u) {
    String sqlBuscarEmpleado = "SELECT nombre, apellidos FROM empleados WHERE id = ?";
   // String sqlValidarNombreApellido = "SELECT COUNT(*) FROM empleados WHERE nombre = ? AND apellidos = ?";
    String sqlValidarUsuario = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
    String sqlInsert = "INSERT INTO usuarios (id_empleado, usuario, contraseña, estado) VALUES (?,?,?,?)";

    try {
        // Paso 1: Obtener nombre y apellidos del empleado usando su ID
        ps = c.prepareStatement(sqlBuscarEmpleado);
        ps.setLong(1, id);
        rs = ps.executeQuery();

        String nombre = null;
        String apellidos = null;

        if (rs.next()) {
            nombre = rs.getString("nombre");
            apellidos = rs.getString("apellidos");
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
            return false;
        }


        // Paso 3: Validar si ya existe el nombre de usuario
        ps = c.prepareStatement(sqlValidarUsuario);
        ps.setString(1, u.getUsuario());
        rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya está en uso. Elige otro.");
            return false;
        }

        // Paso 4: Insertar nuevo usuario
        ps = c.prepareStatement(sqlInsert);
        ps.setLong(1, id);
        ps.setString(2, u.getUsuario());
        ps.setString(3, u.getContraseña());
        ps.setString(4, u.getEstado());
        ps.execute();
        return true;

    } catch (SQLException ex) {
        Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}



    
    public boolean actualizarUsuario(Long id, Usuario u){
    
        String sql="update usuarios set id_empleado=?, usuario=?, contraseña=?, estado=? where id=?";
        try {
            ps=c.prepareStatement(sql);
             ps.setLong(1, id);
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getContraseña());
            ps.setString(4, u.getEstado());
            ps.setByte(5, u.getId());
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    };
    
    public boolean eliminarUsuario(Byte id){
    
        String sql="delete from usuarios where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setByte(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List buscarUsuario(String nombre, String apellidos, String usuario) {
        List<Usuario> buscar = new ArrayList();
        String sql = """
        SELECT u.id, e.nombre, e.apellidos, u.usuario, u.contraseña, u.estado
        FROM usuarios u
        JOIN empleados e ON u.id_empleado = e.id
        WHERE e.nombre LIKE ? OR e.apellidos LIKE ? OR u.usuario LIKE ?
        ORDER BY u.id ASC """;
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, "%"+nombre+"%");
            ps.setString(2, "%"+apellidos+"%");
            ps.setString(3, "%"+usuario+"%");
            rs=ps.executeQuery();
            
            while(rs.next()){
                Usuario u = new Usuario();
                Empleado e = new Empleado();
                e.setId(rs.getLong("id"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidos(rs.getString("apellidos"));
                u.setEmpleado(e);
                u.setUsuario(rs.getString("usuario"));
                u.setContrasenia(rs.getString("apellidos"));
                u.setEstado(rs.getString("estado"));
                buscar.add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return buscar;
    }
    
    
}
