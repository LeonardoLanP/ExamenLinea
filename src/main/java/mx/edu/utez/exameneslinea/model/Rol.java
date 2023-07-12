package mx.edu.utez.exameneslinea.model;

public class Rol {
    private int id_rol;
    private String tipo_usuario;

    public Rol() {
    }

    public Rol(int id_rol, String tipo_usuario) {
        this.id_rol = id_rol;
        this.tipo_usuario = tipo_usuario;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
