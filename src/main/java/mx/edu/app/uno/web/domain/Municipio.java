package mx.edu.app.uno.web.domain;

import lombok.Data;

@Data
public class Municipio {
    private String id;
    private String entidad;
    private String nombre;

    @Override
    public String toString() {
        return "Municipio [entidad=" + entidad + ", nombre=" + nombre + "]";
    }

}
