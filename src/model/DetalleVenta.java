
package model;

public class DetalleVenta {
    
     private Long id_detalle;
     private Venta venta;
     private Producto producto;
     private int cantidad;
     private Double precio_unitario;

    public DetalleVenta() {
    }
     
     

    public DetalleVenta(Long id_detalle, Venta venta, Producto producto, int cantidad, Double precio_unitario) {
        this.id_detalle = id_detalle;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public Long getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(Long id_detalle) {
        this.id_detalle = id_detalle;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
     
     
    
    
}
