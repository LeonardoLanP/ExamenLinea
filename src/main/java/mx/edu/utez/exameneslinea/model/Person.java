package mx.edu.utez.exameneslinea.model;

public class Person extends User {
    private int id_person;
    private String firstname;
    private String secondname;
    private String lastname1;
    private String lastname2;
    private String curp;
    private int User_id;

    public Person() {
    }

    public Person(int id_person, String firstname, String secondname, String lastname1, String lastname2, String curp, int User_id) {
        this.id_person = id_person;
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname1 = lastname1;
        this.lastname2 = lastname2;
        this.curp = curp;
        this.User_id = User_id;
    }

    public Person(int id_person, String firstname, String secondname, String lastname1, String lastname2, String curp, int user_id,
                  int id_user, String user, String email, String pass, int rol_id, int status) {
        super(id_user, user, email, pass, rol_id, status);
        this.id_person = id_person;
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname1 = lastname1;
        this.lastname2 = lastname2;
        this.curp = curp;
        this.User_id = User_id;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getLastname1() {
        return lastname1;
    }

    public void setLastname1(String lastname1) {
        this.lastname1 = lastname1;
    }

    public String getLastname2() {
        return lastname2;
    }

    public void setLastname2(String lastname2) {
        this.lastname2 = lastname2;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }
}
