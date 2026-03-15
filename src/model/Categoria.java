
package model;


public class Categoria {
    
    private int id;
    private String nombre_categoria;

    public Categoria() {
    }

    public Categoria(int id, String nombre_categoria) {
        this.id = id;
        this.nombre_categoria = nombre_categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
    
    
    
}
