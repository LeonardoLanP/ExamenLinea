package mx.edu.utez.exameneslinea.model;

import mx.edu.utez.exameneslinea.utils.MysqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExamenDao implements DaoRepository{
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object findOne(int id) {
        return null;
    }

    public Object findOne(String codigo) {
        Examen exm = new Examen();
        MysqlConector conector = new MysqlConector();
        try {
            Connection con = conector.connect();
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
}
