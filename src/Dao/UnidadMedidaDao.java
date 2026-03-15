
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.UnidadMedida;

public class UnidadMedidaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection c;
    Conexion cn;

    public UnidadMedidaDao() {
        cn=new Conexion();
        c=cn.conectar();
    }
    
    public boolean guardarMedida(UnidadMedida um){
    
        String sql="insert into unidad_medida(nombre, abreviacion) values (?,?)";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, um.getNombre());
            ps.setString(2, um.getAbreviacion());
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean actualizarMedida(UnidadMedida um){
    
        String sql="update unidad_medida set nombre=?, abreviacion=? where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, um.getNombre());
            ps.setString(2, um.getAbreviacion());
            ps.setByte(3, um.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List listarMedida(){
    
        List<UnidadMedida> listar= new ArrayList();
        String sql= "select * from unidad_medida";
        try {
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
            UnidadMedida um = new UnidadMedida();
            um.setId(rs.getByte("id"));
            um.setNombre(rs.getString("nombre"));
            um.setAbreviacion(rs.getString("abreviacion"));
            listar.add(um);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listar;
    }
    
    public boolean eliminarMedida(Byte id){
    
        String sql="delete from unidad_medida where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setByte(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List buscarMedida(String nombre, String abreviacion){
    
        List<UnidadMedida>buscar= new ArrayList();
        String sql="select * from unidad_medida where nombre like ? or abreviacion like ?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, "%"+nombre+"%");
            ps.setString(2, "%"+abreviacion+"%");
            rs=ps.executeQuery();
            
            while(rs.next()){
                UnidadMedida um = new UnidadMedida();
                um.setId(rs.getByte("id"));
                um.setNombre(rs.getString("nombre"));
                um.setAbreviacion(rs.getString("abreviacion"));
                buscar.add(um);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadMedidaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return buscar;
    }
    
    
}
