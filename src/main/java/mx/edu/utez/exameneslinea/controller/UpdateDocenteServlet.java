package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Persona;
import mx.edu.utez.exameneslinea.model.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateDocenteServlet", value = "/up-doce")
public class UpdateDocenteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nombre");
        String last1 = req.getParameter("ap1");
        String last2 = req.getParameter("ape2");
        String CURP = req.getParameter("CURP");
        String pass = req.getParameter("pass");
        int userid = Integer.parseInt(req.getParameter("id_user").trim());
        System.out.println("id del usuario update"+userid);

        String[] names = name.split(" ");
        String firstname = "";
        String secondname = null;

        if (names.length >= 2) {
            firstname = names[0];
            secondname = names[1];
        } else if (names.length == 1) {
            firstname = names[0];
        }
        UsuarioDao dao = new UsuarioDao();
        Persona usr = new Persona();
        Persona user1 = (Persona) dao.findOne(userid);


        usr.setFirstname(firstname);
        usr.setSecondname(secondname);
        usr.setCurp(CURP);
        usr.setLastname1(last1);
        usr.setLastname2(last2);

        dao.updatepass(userid,pass);
        dao.updatePerson(userid,usr);
        System.out.println("Rol del Ususario Redirec" + user1.getRol_id());
            resp.sendRedirect(req.getContextPath() + "/Docente/materias.jsp");



    }
}

