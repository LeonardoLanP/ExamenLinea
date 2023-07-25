package mx.edu.utez.exameneslinea.model.Daos;

import mx.edu.utez.exameneslinea.model.Exam;
import mx.edu.utez.exameneslinea.model.Subject;
import mx.edu.utez.exameneslinea.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamenDao implements DaoRepository{
    @Override
    public List findAll() {
        return null;
    }

    public List findAllExam(int id) {
        List<Exam> lista = new ArrayList<>();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.exam inner join user_sub on user_sub_id = id_user_sub\n" +
                            "inner join subject on sub_id = id_sub where id_sub=?;");
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Exam exam = new Exam();
                exam.setCode(res.getString("code"));
                exam.setGrade(res.getString("grade"));
                exam.setStatusex(res.getString("status"));
                exam.setId_exam(res.getInt("id_exam"));
                exam.setDateex(res.getString("dateex"));
                exam.setUser_sub_id(res.getInt("user_sub_id"));
                lista.add(exam);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List findAllMa(int id_user) {
        List<Subject> lista = new ArrayList<>();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select * from subject inner join user_sub on id_sub = sub_id where user_id = ?");
            stmt.setInt(1,id_user);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Subject m = new Subject();
                m.setId_sub(res.getInt("id_sub"));
                m.setGrade(res.getInt("grade"));
                m.setGrouSub(res.getString("groupSub"));
                m.setSubname(res.getString("subname"));
                m.setStatusub(res.getInt("statusub"));
                lista.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    public Object findMateria(int grade,String group, String subname) {
        Subject mater = new Subject();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {

            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.subject where grade = ? AND groupSub = ? AND subname = ?");
            stmt.setInt(1,grade);
            stmt.setString(2,group);
            stmt.setString(3,subname);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                mater.setId_sub(res.getInt("id_sub"));
                mater.setGrade(res.getInt("grade"));
                mater.setGrouSub(res.getString("groupSub"));
                mater.setSubname(res.getString("subname"));
                mater.setStatusub(res.getInt("statusub"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mater;
    }

    public Object findOne(String codigo) {
        Exam exm = new Exam();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {

            PreparedStatement stmt =
                    con.prepareStatement("select * from exam where code = ?");
            stmt.setString(1,codigo);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                exm.setId_exam(res.getInt("id_exam"));
                exm.setCode(res.getString("code"));
                exm.setGrade(res.getString("grade"));
                exm.setStatusex(res.getString("statusex"));
                exm.setDateex(res.getString("dateex"));
                exm.setUser_sub_id(res.getInt("user_sub_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exm;
    }
    @Override
    public boolean update(int id, Object object) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    public boolean insertMateria(Subject mater) {
        MysqlConector connection = new MysqlConector();
        Connection con = connection.connect();
        try {
            PreparedStatement stmt = con.prepareStatement("insert into sugel.subject(grade,groupSub,subname,statusub) values(?,?,?,'1')");
            stmt.setInt(1, mater.getGrade());
            stmt.setString(2,mater.getGrouSub());
            stmt.setString(3,mater.getSubname());
            if(stmt.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean insertMateriaUsuario(int id_U, int id_M) {
        MysqlConector connection = new MysqlConector();
        Connection con = connection.connect();
        try {
            PreparedStatement stmt = con.prepareStatement("insert into sugel.user_sub(user_id,sub_id) values(?,?)");
            stmt.setInt(1, id_U);
            stmt.setInt(2,id_M);
            if(stmt.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
