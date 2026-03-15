
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.Proveedor;

public class ProveedorDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn;
    Connection c;
    
    public ProveedorDao(){
    cn= new Conexion();
    c=cn.conectar();
    }
    
    public boolean guardarProveedor(Proveedor pr){
    
        String sql="insert into Proveedor(ruc, nombre, direccion, telefono) values(?,?,?,?)";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getDireccion());
            ps.setString(4, pr.getTelefono());
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
        cn.desconectar();
        }
    }
    
    public List listarProveedor(){
    List<Proveedor>listar= new ArrayList();
    String sql="select * from Proveedor";
        try {
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
            Proveedor pr= new Proveedor();
            pr.setId(rs.getLong("id"));
            pr.setRuc(rs.getString("ruc"));
            pr.setNombre(rs.getString("nombre"));
            pr.setDireccion(rs.getString("direccion"));
            pr.setTelefono(rs.getString("telefono"));
            listar.add(pr);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listar;
    }
    
    public boolean actualizarProveedor(Proveedor pr){
    
        String sql="update Proveedor set ruc=?, nombre=?, direccion=?, telefono=? where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getDireccion());
            ps.setString(4, pr.getTelefono());
            ps.setLong(5, pr.getId());
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean eliminarProveedor(Long id){
    
        String sql="delete from Proveedor where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public List buscarProveedor(String nombre){
    List<Proveedor>buscar= new ArrayList();
    String sql="select * from Proveedor where nombre like ?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, "%"+ nombre + "%");
            rs=ps.executeQuery();
            
            while(rs.next()){
            Proveedor pr= new Proveedor();
            pr.setId(rs.getLong("id"));
            pr.setRuc(rs.getString("ruc"));
            pr.setNombre(rs.getString("nombre"));
            pr.setDireccion(rs.getString("direccion"));
            pr.setTelefono(rs.getString("telefono"));
            buscar.add(pr);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return buscar;
    }
    
}
