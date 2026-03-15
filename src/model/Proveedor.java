
package model;


public class Proveedor {
    
    private Long id;
    private String ruc;
    private String nombre;
    private String direccion;
    private String telefono;
    
    public Proveedor(){
    
    }

    public Proveedor(Long id, String ruc, String nombre, String direccion, String telefono) {
        this.id = id;
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    

    
}
