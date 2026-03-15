
package model;


public class Rol {
    
    private Byte id;
    private String nombre_rol;

    public Rol() {
    }

    public Rol(Byte id, String nombre_rol) {
        this.id = id;
        this.nombre_rol = nombre_rol;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }
    
    
    
}
