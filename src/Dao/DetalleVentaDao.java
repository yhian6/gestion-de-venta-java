
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexion;
import model.DetalleVenta;
import model.Producto;
import model.Venta;


public class DetalleVentaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection c;
    Conexion cn;

    public DetalleVentaDao() {
        cn=new Conexion();
        c=cn.conectar();
    }
    
    
    public List<DetalleVenta> listarDetalle(Long id) {
    List<DetalleVenta> listar = new ArrayList<>();
    String sql = "SELECT v.id_venta, p.id as id_producto, p.nombre AS nombre_producto, dv.cantidad, dv.precio_unitario "
                + "FROM ventas v "
                + "INNER JOIN detalle_venta dv ON v.id_venta = dv.id_venta "
                + "INNER JOIN productos p ON dv.id_producto = p.id "
                + "WHERE dv.id_venta = ?"; // Asegúrate de que la cláusula WHERE sea correcta

    try {
        ps = c.prepareStatement(sql);
        ps.setLong(1, id); // Establece el ID de la venta
        rs = ps.executeQuery(); // Ejecuta la consulta y asigna el resultado a 'rs'

        while (rs.next()) {
            DetalleVenta detalle = new DetalleVenta();
            Venta v = new Venta();
            v.setId(rs.getLong("id_venta"));
            detalle.setVenta(v); // Asocia la venta al detalle

            Producto p = new Producto();
            p.setId(rs.getLong("id_producto")); // Asocia el producto
            p.setNombre(rs.getString("nombre_producto")); // Nombre del producto
            detalle.setProducto(p);

            detalle.setCantidad(rs.getInt("cantidad"));
            detalle.setPrecio_unitario(rs.getDouble("precio_unitario"));

            listar.add(detalle);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DetalleVentaDao.class.getName()).log(Level.SEVERE, null, ex);
    }

    return listar;
}

    
    public void guardarDetalle(Long id_venta, Long id_producto, int cantidad, Double precio_unitario){
    
        String sql="insert into detalle_venta(id_venta, id_producto, cantidad, precio_unitario) values(?,?,?,?)";
        try {
            ps=c.prepareStatement(sql);
            ps.setLong(1,id_venta );
            ps.setLong(2,id_producto );
            ps.setLong(3,cantidad );
            ps.setDouble(4, precio_unitario);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DetalleVentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
