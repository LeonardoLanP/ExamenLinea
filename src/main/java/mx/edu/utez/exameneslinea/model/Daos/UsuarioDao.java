package mx.edu.utez.exameneslinea.model.Daos;

import mx.edu.utez.exameneslinea.model.Exam;
import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.Subject;
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
    public List<Person> findAll(int idRol) {
        List<Person> listaUsuarios = new ArrayList<>();
        Connection con = new MysqlConector().connect();
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


    public List<Person> findAllgrades(int idRol) {
        List<Person> listaUsuarios = new ArrayList<>();
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement("select code,exam.grade,dateex from sugel.user " +
                    "inner join sugel.user_sub on user.ID_user = user_sub.user_id " +
                    "inner join sugel.subject on subject.id_sub = user_sub.sub_id " +
                    "inner join sugel.exam on exam.user_sub_id = user_sub.id_user_sub " +
                    "where user_id = ? and statusex = ?");
            stmt.setInt(1,idRol);
            stmt.setInt(2,2);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Person per = new Person();
                Exam ex = new Exam();
                ex.setCode(res.getString("code"));
                per.setDatex(res.getString("dateex"));
                per.setGrade(res.getString("grade"));
                PreparedStatement stmtdatex = con.prepareStatement("select name,lastname1,lastname2,subname,namex from sugel.user " +
                        "inner join sugel.person on person.User_id = user.ID_user " +
                        "inner join sugel.user_sub on user.ID_user = user_sub.user_id " +
                        "inner join sugel.subject on subject.id_sub = user_sub.sub_id " +
                        "inner join sugel.exam on exam.user_sub_id = user_sub.id_user_sub " +
                        "where code = ? order by exam.id_exam asc ");
                stmtdatex.setString(1,ex.getCode());
                ResultSet rest = stmtdatex.executeQuery();
                if (rest.next()) {
                    per.setName(rest.getString("name")+ " " +rest.getString("lastname1") + " " + (rest.getString("lastname2") == null || rest.getString("lastname2").isEmpty() ? "" : rest.getString("lastname2")));
                    per.setSubname(rest.getString("subname"));
                    per.setNamex(rest.getString("namex"));
                    listaUsuarios.add(per);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;


    }

    @Override
    public Object findOne(int idPerson) {
        Person usr = new Person();
        Connection con = new MysqlConector().connect();
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

    public boolean verificarestatus(int idUser) {
        Person usr = new Person();
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "select * from sugel.user where ID_user = ?");
            stmt.setInt(1,idUser);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                usr.setUser_status(res.getInt("user_status"));
                return usr.getUser_status() == 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<String> findDuplicados(String curp, String email, String user) {
        List<String> camposDuplicados = new ArrayList<>();
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM sugel.person INNER JOIN sugel.user ON person.User_id = user.id_user " +
                            "WHERE curp = ? OR email = ? OR user = ?");
            stmt.setString(1, curp);
            stmt.setString(2, email);
            stmt.setString(3, user);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                if (curp.equalsIgnoreCase(res.getString("curp"))) {
                    camposDuplicados.add(" CURP");
                }
                if (email.equalsIgnoreCase(res.getString("email"))) {
                    camposDuplicados.add(" Correo");
                }
                if (user.equalsIgnoreCase(res.getString("user"))) {
                    camposDuplicados.add(" Matricula");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return camposDuplicados;
    }



    public Object findOneUSU(String usuario,String email, int idRol,String pass) {
        User usr = new User();
        Connection con = new MysqlConector().connect();
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

    public int findOneUSUDoc(String usuario) {
        int numerouser = 0;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM sugel.user " +
                            "WHERE user LIKE ? OR REPLACE(user, SUBSTRING_INDEX(user, '_', -1), '') = ?");
            stmt.setString(1, usuario + "%");
            stmt.setString(2, usuario);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
               numerouser ++;
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numerouser;
    }


    public Object findOne(String usuario,String contrasena, int idRol) {
        Person usr = new Person();
        Connection con = new MysqlConector().connect();
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
                    "update person set name = ?, " +
                            " lastname1 = ?, " +
                            " lastname2 = ?, " +
                            " curp = ? "+
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

    public boolean updateDocente(int id, Person usr) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "update person set name = ?," +
                            " lastname1 = ?," +
                            " lastname2 = ? " +
                            "where ID_person = ?"
            );
            stmt.setString(1,usr.getName());
            stmt.setString(2,usr.getLastname1());
            stmt.setString(3,usr.getLastname2());
            stmt.setInt(4,id);
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
        Connection con = new MysqlConector().connect();
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
            Connection con = new MysqlConector().connect();
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

    public boolean updatedocente(int iduser) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();

        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM sugel.user " +
                            "INNER JOIN sugel.user_sub ON user_sub.user_id = user.ID_user " +
                            "INNER JOIN sugel.subject ON user_sub.sub_id = id_sub " +
                            "INNER JOIN sugel.exam ON exam.user_sub_id = id_user_sub " +
                            "WHERE user.ID_user = ?");

            stmt.setInt(1, iduser);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Exam ex = new Exam();
                ex.setId_exam(res.getInt("id_exam"));
                ex.setSub_id(res.getInt("id_sub"));

                PreparedStatement stmtsub = con.prepareStatement(
                        "UPDATE sugel.subject SET statusub = 0 " +
                                "WHERE id_sub = ?");

                stmtsub.setInt(1, ex.getSub_id());
                estado = stmtsub.executeUpdate() > 0 ? true : false;


                    PreparedStatement stmtex = con.prepareStatement(
                            "SELECT * FROM sugel.exam " +
                                    "WHERE id_exam = ?");

                    stmtex.setInt(1, ex.getId_exam());
                    ResultSet resex = stmtex.executeQuery();

                    while (resex.next()) {
                        ex.setGradeex(resex.getString("grade"));

                        if (ex.getGradeex().equals("AU")) {
                            PreparedStatement stmtgrade = con.prepareStatement(
                                    "UPDATE sugel.exam SET grade = 10, statusex = 0 " +
                                            "WHERE id_exam = ?");

                            stmtgrade.setInt(1, ex.getId_exam());
                            estado = stmtgrade.executeUpdate() > 0 ? true : false;
                        } else {
                            PreparedStatement stmtgrstatus = con.prepareStatement(
                                    "UPDATE sugel.exam SET statusex = 0 " +
                                            "WHERE id_exam = ?");
                            stmtgrstatus.setInt(1, ex.getId_exam());
                            estado = stmtgrstatus.executeUpdate() > 0 ? true : false;
                        }
                    }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return estado;
    }

    public boolean onlysub(int iduser) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM sugel.user " +
                            "INNER JOIN sugel.user_sub ON user_sub.user_id = user.ID_user " +
                            "INNER JOIN sugel.subject ON user_sub.sub_id = id_sub " +
                            "WHERE user.ID_user = ?");
            stmt.setInt(1,iduser);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Subject sub = new Subject();
                sub.setId_sub(res.getInt("id_sub"));
                PreparedStatement stmtsub = con.prepareStatement(
                        "UPDATE sugel.subject SET statusub = 0 " +
                                "WHERE id_sub = ?");
                stmtsub.setInt(1, sub.getId_sub());
                estado = stmtsub.executeUpdate() > 0 ? true : false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estado;
    }



}