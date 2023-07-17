package mx.edu.utez.exameneslinea.model;

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
        List<Persona> listaUsuarios = new ArrayList<>();
        MysqlConector conn = new MysqlConector();
        Connection con = conn.connect();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from sugel.person inner join sugel.user on person.User_id = user.id_user where rol_id = ?");
            stmt.setInt(1,idRol);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Persona usr = new Persona();
                usr.setId_person(res.getInt("ID_person"));
                usr.setFirstname(res.getString("firstname"));
                usr.setSecondname(res.getString("secondname"));
                usr.setLastname1(res.getString("lastname1"));
                usr.setLastname2(res.getString("lastname2"));
                usr.setCurp(res.getString("curp"));
                usr.setUser_id(res.getInt("User_id"));
                usr.setId_user(res.getInt("id_user"));
                usr.setUser(res.getString("user"));
                usr.setEmail(res.getString("email"));
                usr.setPass(res.getString("pass"));
                usr.setRol_id(res.getInt("rol_id"));
                usr.setStatus(res.getInt("status"));
                listaUsuarios.add(usr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;


    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    public Object findOneUSU(String usuario,String contrasena, int idRol) {
        Usuario usr = new Usuario();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "select * from sugel.user  " +
                            "where user = ? AND pass = ? AND rol_id = ?");
            stmt.setString(1,usuario);
            stmt.setString(2,contrasena);
            stmt.setInt(3,idRol);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                usr.setId_user(res.getInt("id_user"));
                usr.setUser(res.getString("user"));
                usr.setEmail(res.getString("email"));
                usr.setPass(res.getString("pass"));
                usr.setRol_id(res.getInt("rol_id"));
                usr.setStatus(res.getInt("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usr;
    }


    public Object findOne(String usuario,String contrasena, int idRol) {
        Persona usr = new Persona();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "select * from sugel.person inner join sugel.user on person.User_id = user.id_user " +
                            "where user = ? AND pass = ? AND rol_id = ?");
            stmt.setString(1,usuario);
            stmt.setString(2,contrasena);
            stmt.setInt(3,idRol);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                usr.setId_person(res.getInt("ID_person"));
                usr.setFirstname(res.getString("firstname"));
                usr.setSecondname(res.getString("secondname"));
                usr.setLastname1(res.getString("lastname1"));
                usr.setLastname2(res.getString("lastname2"));
                usr.setCurp(res.getString("curp"));
                usr.setUser_id(res.getInt("User_id"));
                usr.setId_user(res.getInt("id_user"));
                usr.setUser(res.getString("user"));
                usr.setEmail(res.getString("email"));
                usr.setPass(res.getString("pass"));
                usr.setRol_id(res.getInt("rol_id"));
                usr.setStatus(res.getInt("status"));
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

    @Override
    public boolean delete(int id) {
        return false;
    }

    public boolean insertp(Persona person){
        MysqlConector connection = new MysqlConector();
        Connection con = connection.connect();
            try {
                PreparedStatement stmt = con.prepareStatement(
                        "insert into person(firstname, secondname, lastname1, lastname2, curp, User_id) " +
                                "values(?,?,?,?,?,?)"
                );
                stmt.setString(1,person.getFirstname());
                stmt.setString(2,person.getSecondname());
                stmt.setString(3,person.getLastname1());
                stmt.setString(4,person.getLastname2());
                stmt.setString(5,person.getCurp());
                stmt.setInt(6,person.getUser_id());
                if(stmt.executeUpdate() > 0){
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
        public  boolean insertu(Usuario usr) {
            MysqlConector connection = new MysqlConector();
            Connection con = connection.connect();
            try {
                PreparedStatement stmt = con.prepareStatement("insert into sugel.user(user,email,pass,rol_id,status) values(?,?,?,?,?)");
                stmt.setString(1, usr.getUser());
                stmt.setString(2,usr.getEmail());
                stmt.setString(3,usr.getPass());
                stmt.setInt(4,usr.getRol_id());
                stmt.setInt(5,usr.getStatus());

                if(stmt.executeUpdate() > 0){
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
    }



