
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Empleado;
import model.Rol;

public class EmpleadoDao {
    
    private PreparedStatement ps;
    private ResultSet rs;
    Connection c;
    Conexion cn;
    
    public EmpleadoDao(){
    
        cn=new Conexion();
        c=cn.conectar();
    }
    
    public List listarEmpleado(){
    
        List<Empleado> listar= new ArrayList();
        String sql = """
                     SELECT e.id, e.nombre, e.apellidos, e.telefono, e.direccion, e.dni, r.nombre_rol
                     FROM empleados e
                     JOIN roles r ON e.id_rol = r.id ORDER BY e.id ASC;""";
        try {
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
            Empleado em= new Empleado();
            em.setId(rs.getLong("id"));
            em.setNombre(rs.getString("nombre"));
            em.setApellidos(rs.getString("apellidos"));
            em.setTelefono(rs.getString("telefono"));
            em.setDireccion(rs.getString("direccion"));
            em.setDni(rs.getString("dni"));
            
            Rol rol= new Rol();
            rol.setId(rs.getByte("id"));
            rol.setNombre_rol(rs.getString("nombre_rol"));
            em.setRol(rol);
            listar.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listar;
    }
    
    public Byte obtenerIdRol(String nombre) {

        String sql = "select id from roles where nombre_rol=? ";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                Byte id= rs.getByte("id");
                return id;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return null;
        
    }
    
    public Boolean guardarEmpleado(Byte idRol,Empleado em){
    
        String sql = "INSERT INTO empleados (nombre, apellidos, telefono, direccion, dni, id_rol) VALUES (?, ?, ?, ?, ?, ?)";

        try {     
            ps=c.prepareStatement(sql);
            ps.setString(1, em.getNombre());
            ps.setString(2, em.getApellidos());
            ps.setString(3, em.getTelefono());
            ps.setString(4, em.getDireccion());
            ps.setString(5, em.getDni());
            ps.setByte(6, idRol);
            ps.execute();
            
            return true;
         
            
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    public boolean actualizarEmpleado(Byte idRol,Empleado em){
    
        String sql= "update empleados set nombre=?, apellidos=?, telefono=?, direccion=?, dni=?, id_rol=? where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, em.getNombre());
            ps.setString(2, em.getApellidos());
            ps.setString(3, em.getTelefono());
            ps.setString(4, em.getDireccion());
            ps.setString(5, em.getDni());
            ps.setByte(6, idRol);
            ps.setLong(7, em.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean EliminarEmpleado(Long id){
    
        String sql="delete from empleados where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List buscarEmpleado(String nombre){
    
        List<Empleado>lista= new ArrayList();
         String sql = """
                 SELECT e.id, e.nombre, e.apellidos, e.telefono, e.direccion, e.dni, r.nombre_rol
                 FROM empleados e
                 JOIN roles r ON e.id_rol = r.id
                 WHERE e.nombre LIKE ? ORDER BY e.id ASC;
                 """;
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, "%"+nombre+"%");
            rs=ps.executeQuery();
            
            while(rs.next()){
            Empleado em= new Empleado();
            em.setId(rs.getLong("id"));
            em.setNombre(rs.getString("nombre"));
            em.setApellidos(rs.getString("apellidos"));
            em.setDireccion(rs.getString("direccion"));
            em.setTelefono(rs.getString("telefono"));
            em.setDni(rs.getString("dni"));
            Rol rol= new Rol();
            rol.setId(rs.getByte("id"));
            rol.setNombre_rol(rs.getString("nombre_rol"));
            em.setRol(rol);
            lista.add(em);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
}
