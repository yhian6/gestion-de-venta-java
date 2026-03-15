
package model;


public class Usuario {
    
    private Byte id;
    private Empleado empleado;
    private String usuario;
    private String contrasenia;
    private String estado;
    private Rol rol;
    
    public Usuario() {
    }

    public Usuario(Byte id, Empleado empleado, String usuario, String contrasenia, String estado) {
        this.id = id;
        this.empleado = empleado;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
