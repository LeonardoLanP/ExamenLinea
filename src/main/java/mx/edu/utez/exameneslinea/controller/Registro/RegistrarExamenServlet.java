package mx.edu.utez.exameneslinea.controller.Registro;

import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;
import mx.edu.utez.exameneslinea.model.Exam;
import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.User;
import mx.edu.utez.exameneslinea.model.User_sub;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrarExamenServlet", value = "/reg-examen")
public class RegistrarExamenServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String examen = req.getParameter("nombreex");
        int numQues = Integer.parseInt(req.getParameter("numberex"))*2;
        String typeQues = req.getParameter("tipo");
        Person per =(Person) req.getSession().getAttribute("sesion");
        int idSub = (int) req.getSession().getAttribute("idsub");

        System.out.println("Nombre Examen:" +examen +
                "\nNumero de preguntas:" + numQues +
                "\nTipo de Pregunta:" + typeQues +
                "\nPersona que lo crea" + per.getName() +" ID del usuario"+per.getID_user() +
                "idMateria:" + idSub);

        ExamenDao daoex = new ExamenDao();
        User_sub usersub = (User_sub) daoex.findOneUserSub(per.getID_user(),idSub);
        daoex.insertExam(1,usersub.getId_user_sub(),examen);
        Exam exm = (Exam) daoex.findOneEXAMUSI(usersub.getId_user_sub(),examen);
        for (int i = 0; i < numQues; i++){
            if(typeQues.equals("Abierta")){
                daoex.insertEQA(exm.getId_exam(),1,1,"");
            }else if (typeQues.equals("Multiple")){
                daoex.insertEQA(exm.getId_exam(),1,2,"");

            }
        }

        resp.sendRedirect("./ser-mater");

    }
}
