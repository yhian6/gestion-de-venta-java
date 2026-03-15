
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Compra;
import model.Conexion;
import model.Proveedor;
import model.Usuario;

public class CompraDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection c;
    Conexion cn;

    public CompraDao() {
        
        cn = new Conexion();
        c = cn.conectar();
        
    }
    
    public Boolean guardarCompra(String nombreProveedor, String usuario, double total){
    
        String sqlUsuario = " select id from usuarios where usuario = ?";
        Byte id_usuario = null;
        Long id_proveedor = null;
        try {
            ps = c.prepareStatement(sqlUsuario);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            
            if (rs.next()) {

                id_usuario = rs.getByte("id");
            }
            
            String sql_id_proveedor = " select id from proveedor where nombre = ?";
           
            
                ps = c.prepareStatement(sql_id_proveedor);
                ps.setString(1, nombreProveedor);
                rs = ps.executeQuery();

                if (rs.next()) {

                id_proveedor = rs.getLong("id");
                }

            String sql = "insert into compras (id_proveedor, id_usuario, total) values (?,?,?)";
            ps = c.prepareStatement(sql);
            if (id_proveedor == null) {
                ps.setNull(1, java.sql.Types.BIGINT);
            } else {
                ps.setLong(1, id_proveedor);
            }

            ps.setByte(2, id_usuario);
            ps.setDouble(3, total);
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
    
    public void actualizarStock(String codigo, int cantidad){
    
       String sql_stock_actual = "select stock from productos where codigo=?";
       String sql="update productos set stock =? where codigo =?";
       
        try {
            ps=c.prepareStatement(sql_stock_actual);
            ps.setString(1, codigo);
            rs=ps.executeQuery();
            if(rs.next()){
           int stockObtenido = rs.getInt("stock");
           int stockNuevo = stockObtenido+cantidad;
           
           ps=c.prepareStatement(sql);
           ps.setInt(1, stockNuevo);
           ps.setString(2, codigo);
           ps.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public List listarCompras (){
    
        List<Compra> listar = new ArrayList();
        String sql = """
                     select c.id_compra, c.fecha, p.id as id_proveedor, p.nombre as nombre_proveedor, u.id as id_usuario, u.usuario, c.total
                                     from compras c
                                     left join proveedor p on c.id_proveedor = p.id 
                                     inner join usuarios u on c.id_usuario = u.id
                     """;
                
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Compra compra = new Compra();
                compra.setId(rs.getLong("id_compra"));
                compra.setFecha(rs.getDate("fecha"));
                
                Proveedor pr = new Proveedor();
                pr.setId(rs.getLong("id_proveedor"));
                pr.setNombre(rs.getString("nombre_proveedor"));
                compra.setProveedor(pr);
                
                Usuario u = new Usuario();
                u.setId(rs.getByte("id_usuario"));
                u.setUsuario(rs.getString("usuario"));
                compra.setUsuario(u);
                
                compra.setTotal(rs.getDouble("total"));
                
                listar.add(compra);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listar;
    }
    
    public List buscarCompra(String nombreProveedor){
    
        List<Compra> buscar = new ArrayList();
        String sql = """
                     select c.id_compra, c.fecha, p.id as id_proveedor, p.nombre as nombre_proveedor, u.id as id_usuario, u.usuario, c.total
                     from compras c
                     left join proveedor p on c.id_proveedor = p.id 
                     inner join usuarios u on c.id_usuario = u.id
                     where p.nombre like ?
                     """;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nombreProveedor + "%");
            rs = ps.executeQuery();
            
            while(rs.next()){
            Compra compra = new Compra();
                compra.setId(rs.getLong("id_compra"));
                compra.setFecha(rs.getDate("fecha"));
                
                Proveedor pr = new Proveedor();
                pr.setId(rs.getLong("id_proveedor"));
                pr.setNombre(rs.getString("nombre_proveedor"));
                compra.setProveedor(pr);
                
                Usuario u = new Usuario();
                u.setId(rs.getByte("id_usuario"));
                u.setUsuario(rs.getString("usuario"));
                compra.setUsuario(u);
                
                compra.setTotal(rs.getDouble("total"));
                
                buscar.add(compra);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return buscar;
    }
    
    public Long idCompra(String usuario){
    
        Long id = null;
        Long idCompra = null;
        String sqlUsuario = "select id from usuarios where usuario=?";
        try {
            ps=c.prepareStatement(sqlUsuario);
            ps.setString(1, usuario);
            rs=ps.executeQuery();
            while(rs.next()){     
                id = rs.getLong("id");
               
            }
            
            String sql = "select id_compra from compras where id_usuario=?";
            ps=c.prepareStatement(sql);
            ps.setLong(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
               idCompra = rs.getLong("id_compra");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idCompra;
    }
    
    public Long idProducto(String codigo){
    
        String sql= "select id from productos where codigo=?";
        try {
            ps=c.prepareStatement(sql);
            ps.setString(1, codigo);
            rs=ps.executeQuery();
            while(rs.next()){
                long id = 0;
                id = rs.getLong("id");
           return id;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public List<Compra> buscarCompraFecha(Date fecha) {
    List<Compra> listar = new ArrayList<>();
    String sql = """
                 SELECT c.id_compra, c.fecha, p.id AS id_proveedor, p.nombre AS nombre_proveedor, u.id AS id_usuario, u.usuario, c.total
                 FROM compras c
                 LEFT JOIN proveedor p ON c.id_proveedor = p.id
                 INNER JOIN usuarios u ON c.id_usuario = u.id
                 WHERE DATE(c.fecha) = ?
                 """;
    
    try {
        ps = c.prepareStatement(sql);
        // Convertimos java.util.Date a java.sql.Date
        ps.setDate(1, new java.sql.Date(fecha.getTime()));
        
        rs = ps.executeQuery();
        
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setId(rs.getLong("id_compra"));
            compra.setFecha(rs.getDate("fecha"));
            
            Proveedor pr = new Proveedor();
            pr.setId(rs.getLong("id_proveedor"));
            pr.setNombre(rs.getString("nombre_proveedor"));
            compra.setProveedor(pr);
            
            Usuario u = new Usuario();
            u.setId(rs.getByte("id_usuario"));
            u.setUsuario(rs.getString("usuario"));
            compra.setUsuario(u);
            
            compra.setTotal(rs.getDouble("total"));
            
            listar.add(compra);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return listar;
}

    public List<String[]> obtenerProductosPorVencer() {
    List<String[]> lista = new ArrayList<>();
    String sql = """
        SELECT 
                    p.nombre AS producto,
                    dc.fecha_vencimiento,
                    dc.cantidad,
                    pr.nombre
                FROM detalle_compra dc
                JOIN productos p ON dc.id_producto = p.id
                JOIN compras c ON dc.id_compra = c.id_compra
                JOIN proveedor pr ON c.id_proveedor = pr.id
                WHERE DATE(dc.fecha_vencimiento) BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 1 MONTH)
        
                ORDER BY dc.fecha_vencimiento ASC;
    """;

    try {
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            String[] fila = new String[4];
            fila[0] = rs.getString("producto");
            fila[1] = rs.getString("fecha_vencimiento");
            fila[2] = rs.getString("cantidad");
            fila[3] = rs.getString("nombre");
            lista.add(fila);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener productos por vencer: " + e.getMessage());
    }

    return lista;
}

    
}
