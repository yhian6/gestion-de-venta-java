
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categoria;
import model.Conexion;

public class CategoriaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn;
    Connection c;

    public CategoriaDao() {
        cn= new Conexion();
        c=cn.conectar();
    }
    
    public boolean guardarCategoria(Categoria ca){
    
        String sql="insert into categorias(nombre_categoria) values(?)";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, ca.getNombre_categoria());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean actualizarCategoria(Categoria ca){
    
        String sql="update categorias set nombre_categoria=? where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, ca.getNombre_categoria());
            ps.setInt(2, ca.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List listarCategoria(){
    
        List<Categoria>listar= new ArrayList();
        String sql="select * from categorias";
        try {
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
            Categoria ca= new Categoria();
            ca.setId(rs.getInt("id"));
            ca.setNombre_categoria(rs.getString("nombre_categoria"));
            listar.add(ca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listar;
    }
    
    public boolean eliminarCategoria(int id){
    
        String sql="delete from categorias where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List buscarCategoria(String nombre_categoria){
    
        List<Categoria> listar= new ArrayList();
        String sql="select * from categorias where nombre_categoria like ?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, "%"+nombre_categoria+"%");
            rs=ps.executeQuery();
            
            while(rs.next()){
            Categoria ca= new Categoria();
            ca.setId(rs.getInt("id"));
            ca.setNombre_categoria(rs.getString("nombre_categoria"));
            listar.add(ca);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listar;
    }
}
