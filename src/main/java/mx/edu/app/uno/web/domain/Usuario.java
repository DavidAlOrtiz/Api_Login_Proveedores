package mx.edu.app.uno.web.domain;

public class Usuario {
    private String nombre;
    private String email;
    private String pasword;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String password) {
        this.pasword = password;
    }

    @Override
    public String toString() {
        return "Usuario [email=" + email + ", id=" + id + ", nombre=" + nombre + ", pasword=" + pasword + "]";
    }

}
