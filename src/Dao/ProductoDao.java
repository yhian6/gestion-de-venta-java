
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import model.Categoria;
import model.Conexion;
import model.Producto;
import model.UnidadMedida;

public class ProductoDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection c;
    Conexion cn;

    public ProductoDao() {
        cn=new Conexion();
        c=cn.conectar();
       
    }
    
   public List<Producto> listarProductos(boolean soloActivos) {
    List<Producto> listar = new ArrayList<>();

    String sql = """
        SELECT p.id AS id_producto,
               p.codigo,
               p.nombre AS nombre_producto,
               p.descripcion,
               c.id AS id_categoria,
               c.nombre_categoria,
               um.id AS id_unidad,
               um.nombre AS nombre_unidad,
               p.precioCompra,
               p.precioVenta,
               p.stock,
               p.ubicacion,
               p.estado
        FROM productos p
        INNER JOIN categorias c ON p.id_categoria = c.id
        INNER JOIN unidad_medida um ON p.id_unidad_medida = um.id
    """;

    if (soloActivos) {
        sql += " WHERE p.estado = 'Activo'";
    }

    try {
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Producto pr = new Producto();
            pr.setId(rs.getLong("id_producto"));
            pr.setCodigo(rs.getString("codigo"));
            pr.setNombre(rs.getString("nombre_producto"));
            pr.setDescripcion(rs.getString("descripcion"));

            Categoria ca = new Categoria();
            ca.setId(rs.getInt("id_categoria"));
            ca.setNombre_categoria(rs.getString("nombre_categoria"));
            pr.setCategoria(ca);

            UnidadMedida um = new UnidadMedida();
            um.setId(rs.getByte("id_unidad"));
            um.setNombre(rs.getString("nombre_unidad"));
            pr.setUm(um);

            pr.setPrecioCompra(rs.getDouble("precioCompra"));
            pr.setPrecioVenta(rs.getDouble("precioVenta"));
            pr.setStock(rs.getInt("stock"));
            pr.setUbicacion(rs.getString("ubicacion"));
            pr.setEstado(rs.getString("estado"));

            listar.add(pr);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
    }

    return listar;
}

   
    
    public List listarCategoria(){
        List<String> listarCa = new ArrayList();
        String sql="select * from categorias";
        try {
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
            Categoria ca = new Categoria();
            String nombre= rs.getString("nombre_categoria");
            listarCa.add(nombre);
            };
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listarCa;
    
    }
    
    public List listarUm(){
    List<String> listarUm = new ArrayList();
        String sql="select * from unidad_medida";
        try {
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
            UnidadMedida um = new UnidadMedida();
            String nombre= rs.getString("nombre");
            listarUm.add(nombre);
            };
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listarUm;
    }
    
    public boolean guardarProducto(String categoria, String um, Producto pr){
    
         byte idUm = 0;
        String sqlCategoria="select id from categorias where nombre_categoria=?";
        String sqlUm="select id from unidad_medida where nombre=?";
        String sql = """
                     insert into productos(codigo, nombre, descripcion, id_categoria, id_unidad_medida, precioCompra, precioVenta, stock, ubicacion, estado)
                     values(?,?,?,?,?,?,?,?,?,?)
                     """;
        
        try {
            ps=c.prepareStatement(sqlCategoria);
            ps.setString(1, categoria);
            rs=ps.executeQuery();
            if(rs.next()){
            int idC= rs.getInt("id");
            
            ps=c.prepareStatement(sqlUm);
            ps.setString(1,um );
            rs=ps.executeQuery();
            if(rs.next()){
                 idUm=   rs.getByte("id");
            }
            
            ps=c.prepareStatement(sql);
            ps.setString(1, pr.getCodigo());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getDescripcion());
            ps.setInt(4, idC);
            ps.setByte(5, idUm);
            ps.setDouble(6, pr.getPrecioCompra());
            ps.setDouble(7, pr.getPrecioVenta());
            ps.setInt(8, pr.getStock());
            ps.setString(9, pr.getUbicacion());
            ps.setString(10, pr.getEstado());
            ps.execute();
            return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean actualizarProducto(String categoria, String um, Producto pr){
    
        byte idUm = 0;
        String sqlCategoria="select id from categorias where nombre_categoria=?";
        String sqlUm="select id from unidad_medida where nombre=?";
        String sql = """
                     update productos set codigo=?, nombre=?, descripcion=?, id_categoria=?, id_unidad_medida=?, precioCompra=?, precioVenta=?, ubicacion=?, estado=?
                     where id=?
                     """;
        
        try {
            ps=c.prepareStatement(sqlCategoria);
            ps.setString(1, categoria);
            rs=ps.executeQuery();
            if(rs.next()){
            int idC= rs.getInt("id");
            
            ps=c.prepareStatement(sqlUm);
            ps.setString(1,um );
            rs=ps.executeQuery();
            if(rs.next()){
                 idUm=   rs.getByte("id");
            }
            
            ps=c.prepareStatement(sql);
            ps.setString(1, pr.getCodigo());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getDescripcion());
            ps.setInt(4, idC);
            ps.setByte(5, idUm);
            ps.setDouble(6, pr.getPrecioCompra());
            ps.setDouble(7, pr.getPrecioVenta());
            ps.setString(8, pr.getUbicacion());
            ps.setString(9, pr.getEstado());
            ps.setLong(10, pr.getId());
            ps.execute();
            return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean eliminarProducto(Long id){
    
        String sql= "delete from productos where id=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public List buscarProducto(String nombre, String descripcion, String categoria){
        
        List<Producto> buscar = new ArrayList();
        String sql = """
        SELECT p.id AS id_producto,
               p.codigo,
               p.nombre AS nombre_producto,
               p.descripcion,
               c.id AS id_categoria,
               c.nombre_categoria,
               um.id AS id_unidad,
               um.nombre AS nombre_unidad,
               p.precioCompra,
               p.precioVenta,
               p.stock,
               p.ubicacion,
               p.estado
        FROM productos p
        INNER JOIN categorias c ON p.id_categoria = c.id
        INNER JOIN unidad_medida um ON p.id_unidad_medida = um.id 
                     where p.nombre like ? or p.descripcion like ?
                     or c.nombre_categoria like ?
    """;
        try {
           
            ps=c.prepareStatement(sql);
            ps.setString(1, "%"+nombre+"%");
            ps.setString(2, "%"+descripcion+"%"); 
            ps.setString(3, "%"+categoria+"%");
            rs=ps.executeQuery();
            while(rs.next()){
            Producto pr = new Producto();
            pr.setId(rs.getLong("id_producto"));
            pr.setCodigo(rs.getString("codigo"));
            pr.setNombre(rs.getString("nombre_producto"));
            pr.setDescripcion(rs.getString("descripcion"));

            Categoria ca = new Categoria();
            ca.setId(rs.getInt("id_categoria"));
            ca.setNombre_categoria(rs.getString("nombre_categoria"));
            pr.setCategoria(ca);

            UnidadMedida um = new UnidadMedida();
            um.setId(rs.getByte("id_unidad"));
            um.setNombre(rs.getString("nombre_unidad"));
            pr.setUm(um);

            pr.setPrecioCompra(rs.getDouble("precioCompra"));
            pr.setPrecioVenta(rs.getDouble("precioVenta"));
            pr.setStock(rs.getInt("stock"));
            pr.setUbicacion(rs.getString("ubicacion"));
            pr.setEstado(rs.getString("estado"));

            buscar.add(pr);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return buscar;
        
    }
    
    public int getStock(String codigo){
    
        int stock=0;
        String sql = "select stock from productos where codigo=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, codigo);
            rs=ps.executeQuery();
            
            if(rs.next()){
            stock = rs.getInt("stock");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return stock;
    }
    
   



    public Map<String, Integer> obtenerProductosBajoStock() {
        Map<String, Integer> datos = new HashMap<>();
        String sql = "SELECT nombre, stock FROM productos WHERE stock <= 5";

       
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                datos.put(rs.getString("nombre"), rs.getInt("stock"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
           
                     
        return datos;
    }


}
