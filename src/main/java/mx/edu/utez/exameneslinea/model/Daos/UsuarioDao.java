package mx.edu.utez.exameneslinea.model.Daos;

import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.User;
import mx.edu.utez.exameneslinea.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements DaoRepository{

    @Override
    public List findAll(){return null;}
    public List findAll(int idRol) {
        List<Person> listaUsuarios = new ArrayList<>();
        MysqlConector conn = new MysqlConector();
        Connection con = conn.connect();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from sugel.person inner join sugel.user on person.User_id = user.id_user where rol_id = ?  ORDER BY lastname1 ASC");
            stmt.setInt(1,idRol);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Person usr = new Person();
                usr.setId_person(res.getInt("ID_person"));
                usr.setName(res.getString("name"));
                usr.setLastname1(res.getString("lastname1"));
                usr.setLastname2(res.getString("lastname2"));
                usr.setCurp(res.getString("curp"));
                usr.setUser_id(res.getInt("User_id"));
                usr.setID_user(res.getInt("id_user"));
                usr.setUser(res.getString("user"));
                usr.setEmail(res.getString("email"));
                usr.setPass(res.getString("pass"));
                usr.setRol_id(res.getInt("rol_id"));
                usr.setUser_status(res.getInt("user_status"));
                listaUsuarios.add(usr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;


    }

    @Override
    public Object findOne(int idPerson) {
        Person usr = new Person();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "select * from sugel.person inner join sugel.user on person.User_id = user.id_user " +
                            "where  ID_person = ?");
            stmt.setInt(1,idPerson);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                usr.setId_person(res.getInt("ID_person"));
                usr.setName(res.getString("name"));
                usr.setLastname1(res.getString("lastname1"));
                usr.setLastname2(res.getString("lastname2"));
                usr.setCurp(res.getString("curp"));
                usr.setUser_id(res.getInt("User_id"));
                usr.setID_user(res.getInt("id_user"));
                usr.setUser(res.getString("user"));
                usr.setEmail(res.getString("email"));
                usr.setPass(res.getString("pass"));
                usr.setRol_id(res.getInt("rol_id"));
                usr.setUser_status(res.getInt("user_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }

    public Object findOneUSU(String usuario,String email, int idRol,String pass) {
        User usr = new User();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "select * from sugel.user  " +
                            "where user = ? AND email = ? AND Rol_id = ? AND pass=sha2(?,256)");
            stmt.setString(1,usuario);
            stmt.setString(2,email);
            stmt.setInt(3,idRol);
            stmt.setString(4,pass);

            ResultSet res = stmt.executeQuery();
            if(res.next()){
                usr.setID_user(res.getInt("ID_user"));
                usr.setUser(res.getString("user"));
                usr.setEmail(res.getString("email"));
                usr.setPass(res.getString("pass"));
                usr.setUser_status(res.getInt("user_status"));
                usr.setRol_id(res.getInt("Rol_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }


    public Object findOne(String usuario,String contrasena, int idRol) {
        Person usr = new Person();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "select * from sugel.person inner join sugel.user on person.User_id = user.id_user " +
                            "where user = ? AND pass = sha2(?,256) AND rol_id = ?");
            stmt.setString(1,usuario);
            stmt.setString(2,contrasena);
            stmt.setInt(3,idRol);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                usr.setId_person(res.getInt("ID_person"));
                usr.setName(res.getString("name"));
                usr.setLastname1(res.getString("lastname1"));
                usr.setLastname2(res.getString("lastname2"));
                usr.setCurp(res.getString("curp"));
                usr.setUser_id(res.getInt("User_id"));
                usr.setID_user(res.getInt("id_user"));
                usr.setUser(res.getString("user"));
                usr.setEmail(res.getString("email"));
                usr.setPass(res.getString("pass"));
                usr.setRol_id(res.getInt("rol_id"));
                usr.setUser_status(res.getInt("user_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }



    @Override
    public boolean update(int id, Object object) {
        return false;
    }

    public boolean updateUser(int id, Person usr) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "update sugel.user set user = ?, email = ?" +
                            "where id_user = ?"
            );
            stmt.setString(1,usr.getUser());
            stmt.setString(2,usr.getEmail());
            stmt.setInt(3,id);
            estado = stmt.executeUpdate() > 0 ? true:false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estado;
    }

    public boolean updatePerson(int id, Person usr) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "update person set name = ?," +
                            " lastname1 = ?," +
                            " lastname2 = ?," +
                            " curp = ?"+
                            "where ID_person = ?"
            );
            stmt.setString(1,usr.getName());
            stmt.setString(2,usr.getLastname1());
            stmt.setString(3,usr.getLastname2());
            stmt.setString(4,usr.getCurp());
            stmt.setInt(5,id);
            estado = stmt.executeUpdate() > 0 ? true:false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estado;
    }

    public boolean updatepass(int id, String pass,int rol) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "update sugel.user set pass = sha2(?,256) " +
                            "where ID_user = ? AND Rol_id =?"
            );
            stmt.setString(1,pass);
            stmt.setInt(2,id);
            stmt.setInt(3,rol);

            estado = stmt.executeUpdate() > 0 ? true:false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estado;
    }
    public boolean updateStatus(int id, int newStatus,int rol) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "update sugel.user set user_status = ? " +
                            "where ID_user = ? AND Rol_id =?"
            );
            stmt.setInt(1,newStatus);
            stmt.setInt(2,id);
            stmt.setInt(3,rol);

            estado = stmt.executeUpdate() > 0 ? true:false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estado;
    }


    @Override
    public boolean delete(int id) {
        return false;
    }

    public boolean insertp(Person person){
        MysqlConector connection = new MysqlConector();
        Connection con = connection.connect();
            try {
                PreparedStatement stmt = con.prepareStatement(
                        "insert into person(name, lastname1, lastname2, curp, User_id) " +
                                "values(?,?,?,?,?)"
                );
                stmt.setString(1,person.getName());
                stmt.setString(2,person.getLastname1());
                stmt.setString(3,person.getLastname2());
                stmt.setString(4,person.getCurp());
                stmt.setInt(5,person.getUser_id());
                if(stmt.executeUpdate() > 0){
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
        public  boolean insertu(User usr) {
            MysqlConector connection = new MysqlConector();
            Connection con = connection.connect();
            try {
                PreparedStatement stmt = con.prepareStatement("insert into sugel.user(user,email,pass,user_status,rol_id) values(?,?,sha2(?,256),?,?)");
                stmt.setString(1, usr.getUser());
                stmt.setString(2,usr.getEmail());
                stmt.setString(3,usr.getPass());
                stmt.setInt(4,usr.getUser_status());
                stmt.setInt(5,usr.getRol_id());

                if(stmt.executeUpdate() > 0){
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;
        }


    }



