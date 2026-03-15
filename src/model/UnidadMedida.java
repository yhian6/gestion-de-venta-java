
package model;

public class UnidadMedida {
    
    private Byte id;
    private String nombre;
    private String abreviacion;

    public UnidadMedida() {
    }

    public UnidadMedida(Byte id, String nombre, String abreviacion) {
        this.id = id;
        this.nombre = nombre;
        this.abreviacion = abreviacion;
    }

    
    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }
    
    
}
