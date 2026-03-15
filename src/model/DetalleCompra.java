
package model;

import java.util.Date;


public class DetalleCompra {
    
    private Long id;
    private Compra compra;
    private Producto producto;
    private int cantidad;
    private double precio_unitario;
    private Date fecha;

    public DetalleCompra() {
    }

    public DetalleCompra(Long id, Compra compra, Producto producto, int cantidad, double precio_unitario, Date fecha) {
        this.id = id;
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
    
}
