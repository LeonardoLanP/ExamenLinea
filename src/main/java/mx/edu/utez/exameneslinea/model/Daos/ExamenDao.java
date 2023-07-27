package mx.edu.utez.exameneslinea.model.Daos;

import mx.edu.utez.exameneslinea.model.*;
import mx.edu.utez.exameneslinea.utils.MysqlConector;

import javax.sound.midi.Soundbank;
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

    public List findAllExam(int idsub, int userid) {
        List<Exam> lista = new ArrayList<>();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.exam inner join user_sub on user_sub_id = id_user_sub" +
                            " inner join subject on sub_id = id_sub where id_sub=? and user_id = ?");
            stmt.setInt(1,idsub);
            stmt.setInt(2,userid);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Exam exam = new Exam();
                exam.setCode(res.getString("code"));
                exam.setGrade(res.getString("grade"));
                exam.setStatusex(res.getString("statusex"));
                exam.setId_exam(res.getInt("id_exam"));
                exam.setDateex(res.getString("dateex"));
                exam.setUser_sub_id(res.getInt("user_sub_id"));
                exam.setNamex(res.getString("namex"));
                lista.add(exam);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List findAllExamStudent(String codigo) {
        List<Question> lista = new ArrayList<>();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from sugel.exam " +
                            "inner join sugel.exam_question_answer on id_exam = exam_id " +
                            "inner join sugel.question on ques_id = id_ques " +
                            "where code = ?");
            stmt.setString(1,codigo);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
               /* Exam exam = new Exam();
                exam.setCode(res.getString("code"));
                exam.setGrade(res.getString("grade"));
                exam.setStatusex(res.getString("statusex"));
                exam.setId_exam(res.getInt("id_exam"));
                exam.setDateex(res.getString("dateex"));
                exam.setUser_sub_id(res.getInt("user_sub_id"));
                exam.setNamex(res.getString("namex"));
                lista.add(exam);*/
                Question quest = new Question();
                quest.setId_exam_question(res.getInt("id_exam_question"));
                quest.setExam_id(res.getInt("exam_id"));
                quest.setQues_id(res.getInt("ques_id"));
                quest.setAnswer_id(res.getInt("answer_id"));
                quest.setOpen_Answer(res.getString("open_Answer"));
                quest.setQuestion(res.getString("question"));
                lista.add(quest);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public List findQuestion(int EQA) {
        List<Question> lista = new ArrayList<>();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.exam_question_answer inner join sugel.question on ques_id = id_ques where exam_id = ?");
            stmt.setInt(1,EQA);
            ResultSet res = stmt.executeQuery();
            String listass;
            while(res.next()) {
                Question quest = new Question();
                quest.setId_exam_question(res.getInt("id_exam_question"));
                quest.setExam_id(res.getInt("exam_id"));
                quest.setQues_id(res.getInt("ques_id"));
                quest.setAnswer_id(res.getInt("answer_id"));
                quest.setOpen_Answer(res.getString("open_Answer"));
                quest.setQuestion(res.getString("question"));
                if (quest.getQues_id() != 1) {
                    lista.add(0, quest);

                } else {
                    lista.add(quest);
                }
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
                    con.prepareStatement("select * from sugel.exam where code = ?");
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


    public Object findOneEXAMUSI(int USI, String name) {
        Exam exm = new Exam();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {

            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.exam where user_sub_id = ? and namex = ?");
            stmt.setInt(1,USI);
            stmt.setString(2,name);

            ResultSet res = stmt.executeQuery();
            if(res.next()){
                exm.setId_exam(res.getInt("id_exam"));
                exm.setCode(res.getString("code"));
                exm.setGrade(res.getString("grade"));
                exm.setStatusex(res.getString("statusex"));
                exm.setDateex(res.getString("dateex"));
                exm.setUser_sub_id(res.getInt("user_sub_id"));
                exm.setNamex(res.getString("namex"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exm;
    }

    public Object findOneUserSub(int user, int subject) {
        User_sub us = new User_sub();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {

            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.user_sub where user_id = ? AND sub_id = ?");
            stmt.setInt(1,user);
            stmt.setInt(2,subject);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                us.setId_user_sub(res.getInt("id_user_sub"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return us;
    }

    public Object findQues(String ques, String typeQues) {
        Question QUES = new Question();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.question where question = ? AND type_question = ?");
            stmt.setString(1,ques);
            stmt.setString(2,typeQues);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                QUES.setId_ques(res.getInt("id_ques"));
                System.out.println("Asigno la id de la pregunta Nueva");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return QUES;
    }

    public Object findEQA(int idExm, int quess) {
        Question QUES = new Question();
        MysqlConector conector = new MysqlConector();
        Connection con = conector.connect();
        try {
            PreparedStatement stmt =
                    con.prepareStatement("select * from sugel.exam_question_answer" +
                            " where exam_id = ? AND ques_id = ?");
            stmt.setInt(1,idExm);
            stmt.setInt(2,quess);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                QUES.setId_exam_question(res.getInt("id_exam_question"));
                System.out.println(QUES.getId_exam_question() + " ID de la primer pregunta encontrada");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return QUES;
    }
    @Override
    public boolean update(int id, Object object) {
        return false;
    }

    public boolean updateEQA(int quesid, int idExm, int idEQ) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "update sugel.exam_question_answer set ques_id = ? " +
                            "where exam_id = ? AND id_exam_question = ?");
            stmt.setInt(1,quesid);
            stmt.setInt(2,idExm);
            stmt.setInt(3,idEQ);
            System.out.println("LLEGO al final del update");
            estado = stmt.executeUpdate() > 0 ? true:false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estado;
    }

    public boolean updateQues(int quesid, String quess) {
        boolean estado = false;
        Connection con = new MysqlConector().connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "update sugel.question set question= ?" +
                            "where id_ques = ?"
            );
            stmt.setString(1,quess);
            stmt.setInt(2,quesid);
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

    public boolean insertExam(int statusex, int user_sub_id, String namex) {
        MysqlConector connection = new MysqlConector();
        Connection con = connection.connect();
        try {
            PreparedStatement stmt = con.prepareStatement("insert into sugel.exam(statusex,user_sub_id,namex) " +
                    "values(?,?,?)");
            stmt.setInt(1, statusex);
            stmt.setInt(2, user_sub_id);
            stmt.setString(3,namex);
            if(stmt.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean insertEQA(int examid, int quesid, int answer, String openAns) {
        MysqlConector connection = new MysqlConector();
        Connection con = connection.connect();
        try {
            PreparedStatement stmt = con.prepareStatement(
                    "insert into sugel.exam_question_answer(exam_id,ques_id,answer_id,open_Answer) " +
                    "values(?,?,?,?)");
            stmt.setInt(1, examid);
            stmt.setInt(2, quesid);
            stmt.setInt(3, answer);
            stmt.setString(4,openAns);
            if(stmt.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean insertQUES(String question, String typeQues) {
        MysqlConector connection = new MysqlConector();
        Connection con = connection.connect();
        try {
            PreparedStatement stmt = con.prepareStatement("call InsertQuestion (?,?)");
            stmt.setString(1,question);
            stmt.setString(2,typeQues);
            if(stmt.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }



}
