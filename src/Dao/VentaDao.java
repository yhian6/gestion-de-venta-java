
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Conexion;
import model.Usuario;
import model.Venta;

public class VentaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection c;
    Conexion cn;

    public VentaDao() {
        cn= new Conexion();
        c=cn.conectar();
    }
    
    public boolean guardarVenta(String usuario, String dni, Double total){
        
        String sql_id_usuario="select id from usuarios where usuario=?";
        String sql_id_cliente="select id from cliente where dni=?";
        String sql="insert into ventas(id_cliente, id_usuario, total) values (?,?,?)";
        long id_cliente=0;
        byte id_usuario=0;
        try {
            ps=c.prepareStatement(sql_id_usuario);
            ps.setString(1, usuario);
            rs=ps.executeQuery();
            if(rs.next()){
            id_usuario=rs.getByte("id");
            }
            
            ps=c.prepareStatement(sql_id_cliente);
            ps.setString(1, dni);
            rs=ps.executeQuery();
            if(rs.next()){
            id_cliente = rs.getLong("id");
            }
            
            
            
            ps=c.prepareStatement(sql);
            
            
            if (id_cliente == 0) {
                ps.setNull(1, java.sql.Types.BIGINT);
            } else {
                ps.setLong(1, id_cliente);
            }
            ps.setByte(2, id_usuario);
            ps.setDouble(3, total);
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
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
           int stockNuevo = stockObtenido-cantidad;
           
           ps=c.prepareStatement(sql);
           ps.setInt(1, stockNuevo);
           ps.setString(2, codigo);
           ps.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public Long idVenta(String usuario){
    
        Long id = null;
        Long idVenta = null;
        String sqlUsuario = "select id from usuarios where usuario=?";
        try {
            ps=c.prepareStatement(sqlUsuario);
            ps.setString(1, usuario);
            rs=ps.executeQuery();
            while(rs.next()){     
                id = rs.getLong("id");
               
            }
            
            String sql = "select id_venta from ventas where id_usuario=?";
            ps=c.prepareStatement(sql);
            ps.setLong(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
               idVenta = rs.getLong("id_venta");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idVenta;
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
    
   public List<Venta> listarVentas(String nombreUsuario, String rol) {
    List<Venta> listar = new ArrayList<>();
    StringBuilder sql = new StringBuilder(
        "SELECT v.id_venta, v.fecha, c.id AS id_cliente, c.nombre AS nombre_cliente, " +
        "u.id AS id_usuario, u.usuario, v.total " +
        "FROM ventas v " +
        "LEFT JOIN cliente c ON v.id_cliente = c.id " +
        "INNER JOIN usuarios u ON v.id_usuario = u.id "
    );

    try {
        if (!rol.equalsIgnoreCase("Administrador")) {
            sql.append("WHERE u.usuario = ?");
            ps = c.prepareStatement(sql.toString());
            ps.setString(1, nombreUsuario);
        } else {
            ps = c.prepareStatement(sql.toString());
        }

        rs = ps.executeQuery();

        while (rs.next()) {
            Venta v = new Venta();
            v.setId(rs.getLong("id_venta"));
            v.setFecha(rs.getDate("fecha"));

            Cliente cliente = new Cliente();
            Long idCliente = rs.getLong("id_cliente");
            if (rs.wasNull()) {
                cliente.setId(null);
                cliente.setNombre("Sin cliente");
            } else {
                cliente.setId(idCliente);
                cliente.setNombre(rs.getString("nombre_cliente"));
            }
            v.setCliente(cliente);

            Usuario usuario = new Usuario();
            usuario.setId(rs.getByte("id_usuario"));
            usuario.setUsuario(rs.getString("usuario"));
            v.setUsuario(usuario);

            v.setTotal(rs.getDouble("total"));
            listar.add(v);
        }

    } catch (SQLException ex) {
        Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
    }

    return listar;
}
   
   public List<Venta> buscarVentaUsuario(String nombreUsuario, String rol, java.sql.Date fecha) {
    List<Venta> buscar = new ArrayList<>();
    StringBuilder sql = new StringBuilder(
        "SELECT v.id_venta, v.fecha, c.id AS id_cliente, c.nombre, " +
        "u.id AS id_usuario, u.usuario, v.total " +
        "FROM ventas v " +
        "LEFT JOIN cliente c ON v.id_cliente = c.id " +
        "INNER JOIN usuarios u ON v.id_usuario = u.id "
    );

    try {
        if (rol.equalsIgnoreCase("Administrador")) {
            sql.append(" WHERE u.usuario LIKE ?");
            if (fecha != null) {
                sql.append(" AND DATE(v.fecha) = ?");
            }
            sql.append(" ORDER BY v.id_venta ASC");

            ps = c.prepareStatement(sql.toString());
            ps.setString(1, "%" + nombreUsuario + "%");
            if (fecha != null) {
                ps.setDate(2, fecha);
            }
        }

        rs = ps.executeQuery();

        while (rs.next()) {
            Venta v = new Venta();
            v.setId(rs.getLong("id_venta"));
            v.setFecha(rs.getDate("fecha"));

            Cliente cliente = new Cliente();
            Long idCliente = rs.getLong("id_cliente");
            if (rs.wasNull()) {
                cliente.setId(null);
                cliente.setNombre("Sin cliente");
            } else {
                cliente.setId(idCliente);
                cliente.setNombre(rs.getString("nombre"));
            }
            v.setCliente(cliente);

            Usuario usuario = new Usuario();
            usuario.setId(rs.getByte("id_usuario"));
            usuario.setUsuario(rs.getString("usuario"));
            v.setUsuario(usuario);

            v.setTotal(rs.getDouble("total"));
            buscar.add(v);
        }

    } catch (SQLException ex) {
        Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
    }

    return buscar;
}
   
  public List<Venta> buscarVentaCliente(String nombreCliente, String rol, String usu, java.sql.Date fecha) {
    List<Venta> buscar = new ArrayList<>();
    StringBuilder sql = new StringBuilder(
        "SELECT v.id_venta, v.fecha, c.id AS id_cliente, c.nombre, " +
        "u.id AS id_usuario, u.usuario, v.total " +
        "FROM ventas v " +
        "LEFT JOIN cliente c ON v.id_cliente = c.id " +
        "INNER JOIN usuarios u ON v.id_usuario = u.id "
    );

    boolean hayNombre = nombreCliente != null && !nombreCliente.trim().isEmpty();
    boolean hayFecha = fecha != null;

    try {
        if (rol.equalsIgnoreCase("Vendedor")) {
            sql.append(" WHERE u.usuario = ?");

            if (hayNombre) {
                sql.append(" AND c.nombre LIKE ?"); // sin IS NULL
            }

            if (hayFecha) {
                sql.append(" AND DATE(v.fecha) = ?");
            }

            sql.append(" ORDER BY v.id_venta ASC");

            ps = c.prepareStatement(sql.toString());

            int index = 1;
            ps.setString(index++, usu);
            if (hayNombre) {
                ps.setString(index++, "%" + nombreCliente + "%");
            }
            if (hayFecha) {
                ps.setDate(index++, fecha);
            }
        }

        rs = ps.executeQuery();

        while (rs.next()) {
            Venta v = new Venta();
            v.setId(rs.getLong("id_venta"));
            v.setFecha(rs.getDate("fecha"));

            Cliente cliente = new Cliente();
            Long idCliente = rs.getLong("id_cliente");
            if (rs.wasNull()) {
                cliente.setId(null);
                cliente.setNombre("Sin cliente");
            } else {
                cliente.setId(idCliente);
                cliente.setNombre(rs.getString("nombre"));
            }
            v.setCliente(cliente);

            Usuario usuario = new Usuario();
            usuario.setId(rs.getByte("id_usuario"));
            usuario.setUsuario(rs.getString("usuario"));
            v.setUsuario(usuario);

            v.setTotal(rs.getDouble("total"));
            buscar.add(v);
        }

    } catch (SQLException ex) {
        Logger.getLogger(VentaDao.class.getName()).log(Level.SEVERE, null, ex);
    }

    return buscar;
}
  
  public int obtenerVentasDeHoy() {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total_ventas FROM ventas WHERE DATE(fecha) = CURDATE()";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total_ventas");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas de hoy: " + e.getMessage());
        }
        return total;
    }

}
