package mx.edu.app.uno.web.domain;

import lombok.Data;

@Data
public class Entidad {
    private String id;
    private String nombre;

    @Override
    public String toString() {
        return "Entidad [id=" + id + ", nombre=" + nombre + "]";
    }

}
