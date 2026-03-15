
package model;

import java.util.Date;


public class Compra {
    
    private Long id;
    private Date fecha;
    private Proveedor proveedor;
    private Usuario usuario;
    private double total;

    public Compra() {
    }

    public Compra(Long id, Date fecha, Proveedor proveedor, Usuario usuario, double total) {
        this.id = id;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.total = total;
    }
    
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
