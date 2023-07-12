package mx.edu.utez.exameneslinea.model;

public class Usuario {
    protected int id_user;
    protected String user;
    protected String email;
    protected String pass;
    protected int rol_id;

    protected int status;

    public Usuario() {
    }

    public Usuario(int id_user, String user, String email, String pass, int rol_id, int status) {
        this.id_user = id_user;
        this.user = user;
        this.email = email;
        this.pass = pass;
        this.rol_id = rol_id;
        this.status = status;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
