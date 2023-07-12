package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Examen;
import mx.edu.utez.exameneslinea.model.ExamenDao;

import javax.servlet.RequestDispatcher;
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
        Examen exm = (Examen) dao.findOne(codigo);

        if (exm.getId_exam() != 0) {
            if (exm.getStatus().equals("activo")) {
                resp.sendRedirect("./Estudiante/examen.jsp");
            } else {
                resp.sendRedirect("./Estudiante/acceso.jsp?"+"status=desactivado");

            }
        } else {
            resp.sendRedirect("./Estudiante/acceso.jsp?"+"status=noRegistrado");
        }
    }
}