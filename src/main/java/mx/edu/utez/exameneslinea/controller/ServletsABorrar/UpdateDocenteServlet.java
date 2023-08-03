package mx.edu.utez.exameneslinea.controller.ServletsABorrar;

import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        UsuarioDao dao = new UsuarioDao();
        Person usr = new Person();
        Person user1 = (Person) dao.findOne(userid);


        usr.setName(name);
        usr.setCurp(CURP);
        usr.setLastname1(last1);
        usr.setLastname2(last2);

        if(!pass.equals("")){
            System.out.println("Se va actualizar la contraseña del usuario " + user1.getRol_id());
            dao.updatepass(user1.getID_user(),pass,user1.getRol_id());
        }else {
            System.out.println("Contraseña vacia no se actualiza");
        }
        dao.updatePerson(userid,usr);
        System.out.println("Rol del Ususario Redirec" + user1.getRol_id());
            resp.sendRedirect(req.getContextPath() + "/Docente/materias.jsp");



    }
}

