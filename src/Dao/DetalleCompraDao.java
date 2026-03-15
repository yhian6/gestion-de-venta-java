
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Compra;
import model.Conexion;
import model.DetalleCompra;
import model.Producto;
import model.Venta;

public class DetalleCompraDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection c;
    Conexion cn;

    public DetalleCompraDao() {
        cn=new Conexion();
        c=cn.conectar();
    }
    
    public List listarDetalle(Long id) {
        List<DetalleCompra> listar = new ArrayList<>();
        String sql = "SELECT c.id_compra, p.id as id_producto, p.nombre AS nombre_producto, dc.cantidad, dc.precio_unitario, dc.fecha_vencimiento "
                + "FROM compras c "
                + "INNER JOIN detalle_compra dc ON c.id_compra = dc.id_compra "
                + "INNER JOIN productos p ON dc.id_producto = p.id "
                + "WHERE dc.id_compra = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleCompra detalle = new DetalleCompra();
                Compra co = new Compra();
                co.setId(rs.getLong("id_compra"));
                detalle.setCompra(co);

                Producto p = new Producto();
                p.setId(rs.getLong("id_producto")); // Asocia el producto
                p.setNombre(rs.getString("nombre_producto")); // Nombre del producto
                detalle.setProducto(p);

                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio_unitario(rs.getDouble("precio_unitario"));
                detalle.setFecha(rs.getDate("fecha_vencimiento"));
                listar.add(detalle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleVentaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listar;
    }

   public void guardarDetalle(Long id_compra, Long id_producto, int cantidad, Double precio_unitario, Date fecha_vencimiento) {
    String sql = "insert into detalle_compra(id_compra, id_producto, cantidad, precio_unitario, fecha_vencimiento) values(?,?,?,?,?)";
    try {
        ps = c.prepareStatement(sql);
        ps.setLong(1, id_compra);
        ps.setLong(2, id_producto);
        ps.setInt(3, cantidad);
        ps.setDouble(4, precio_unitario);
        ps.setDate(5, new java.sql.Date(fecha_vencimiento.getTime()));  // 👈 aquí
        ps.execute();
    } catch (SQLException ex) {
        Logger.getLogger(DetalleVentaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
