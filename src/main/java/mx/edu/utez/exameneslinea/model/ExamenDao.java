package mx.edu.utez.exameneslinea.model;

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
        List<Examen> lista = new ArrayList<>();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.exam inner join user_sub on user_sub_id = id_user_sub\n" +
                            "inner join subject on sub_id = id_sub where id_sub=?;");
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Examen exam = new Examen();
                exam.setCode(res.getString("code"));
                exam.setGrade(res.getString("grade"));
                exam.setStatus(res.getString("status"));
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
        List<Materia> lista = new ArrayList<>();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select * from subject inner join user_sub on id_sub = sub_id where user_id = ?");
            stmt.setInt(1,id_user);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Materia m = new Materia();
                m.setId_matera(res.getInt("id_sub"));
                m.setGrado(res.getInt("grade"));
                m.setGupo(res.getString("groupp"));
                m.setNombre_materia(res.getString("subname"));
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
        Materia mater = new Materia();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {

            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.subject where grade = ? AND groupp = ? AND subname = ?");
            stmt.setInt(1,grade);
            stmt.setString(2,group);
            stmt.setString(3,subname);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                mater.setId_matera(res.getInt("id_sub"));
                mater.setGrado(res.getInt("grade"));
                mater.setGupo(res.getString("groupp"));
                mater.setNombre_materia(res.getString("subname"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mater;
    }

    public Object findOne(String codigo) {
        Examen exm = new Examen();
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
                exm.setStatus(res.getString("status"));
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


    public boolean insertMateria(Materia mater) {
        MysqlConector connection = new MysqlConector();
        Connection con = connection.connect();
        try {
            PreparedStatement stmt = con.prepareStatement("insert into sugel.subject(grade,groupp,subname) values(?,?,?)");
            stmt.setInt(1, mater.getGrado());
            stmt.setString(2,mater.getGupo());
            stmt.setString(3,mater.getNombre_materia());
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
