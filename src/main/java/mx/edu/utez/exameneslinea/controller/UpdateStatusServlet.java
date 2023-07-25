package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;
import mx.edu.utez.exameneslinea.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateStatusServlet", value = "/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int personId = Integer.parseInt(req.getParameter("personId"));
        int estado = Integer.parseInt(req.getParameter("estado"));

        UsuarioDao dao = new UsuarioDao();
        Person user = (Person) dao.findOne(personId);
        if (user != null) {
            dao.updateStatus(user.getID_user(), estado, user.getRol_id());
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Actualización exitosa");
        } else {
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error: usuario no encontrado");
        }
    }

}


