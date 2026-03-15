
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Conexion;

public class ClienteDao {
    
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection c;
    private Conexion con;
    
    public ClienteDao(){
    con=new Conexion();
    c=con.conectar();
    }
    
    public boolean guardarCliente(Cliente cl){
    
        String sql="INSERT INTO Cliente (nombre, apellidos, dni) VALUES (?, ?, ?)";
        try {
           
            ps=c.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getApellidos());
            ps.setString(3, cl.getDni());
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, "error", ex);
            return false;
            
        }finally{
        con.desconectar();
        }
        
    }
    
    public List listarCliente(){
        List<Cliente> lista= new ArrayList();
        String sql="select * from Cliente";
        
        try {
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
            Cliente cl= new Cliente();
            cl.setId(rs.getLong("id"));
            cl.setNombre(rs.getString("nombre"));
            cl.setApellidos(rs.getString("apellidos"));
            cl.setDni(rs.getString("dni"));
            lista.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    public boolean eliminarCliente(Long id){
    String sql="delete from Cliente where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
    
    public boolean actualizarCliente(Cliente cl){
    String sql="update cliente set nombre=?, apellidos=?, dni=? where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getApellidos());
            ps.setString(3, cl.getDni());
            ps.setLong(4, cl.getId());
            ps.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
    
    public List buscarCliente(String nombre, String apellidos, String dni){
    List<Cliente> buscar= new ArrayList();
    String sql="select * from Cliente where nombre like ? or apellidos like ? or dni like ?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1,"%"+nombre+ "%");
            ps.setString(2,"%"+apellidos+ "%");
            ps.setString(3,"%"+dni+ "%");
            
            rs=ps.executeQuery();
            
            while(rs.next()){
                Cliente cl= new Cliente();
                cl.setId(rs.getLong("id"));
                cl.setNombre(rs.getString("nombre"));
                cl.setApellidos(rs.getString("apellidos"));
                cl.setDni(rs.getString("dni"));
                buscar.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return buscar;
    }
    
}
