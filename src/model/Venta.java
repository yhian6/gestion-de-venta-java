
package model;

import java.util.Date;


public class Venta {
    
    private Long id;
    private Date fecha;
    private Cliente cliente;
    private Usuario usuario;
    private Double total;
    
    public Venta() {
    }

    public Venta(Long id, Date fecha, Cliente cliente, Usuario usuario, Double total) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
    
}
