package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Exam;
import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExamenServlet", value = "/login-examen")

public class ExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigo = req.getParameter("codigo");

        ExamenDao dao = new ExamenDao();
        Exam exm = (Exam) dao.findOne(codigo);

        if (exm.getId_exam() != 0) {
            if (exm.getStatusex().equals("activo")) {
                resp.sendRedirect("./Estudiante/examen.jsp");
            } else {
                resp.sendRedirect("./Estudiante/acceso.jsp?"+"status=desactivado");

            }
        } else {
            resp.sendRedirect("./Estudiante/acceso.jsp?"+"status=noRegistrado");
        }
    }
}
