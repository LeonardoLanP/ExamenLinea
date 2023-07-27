package mx.edu.utez.exameneslinea.controller.Updates;

import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUsuarioServlet", value = "/up-usr")
public class UpdateUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("nombre");
        String last1 = req.getParameter("ap1");
        String last2 = req.getParameter("ape2");
        String CURP = req.getParameter("CURP");
        String email = req.getParameter("correo");
        String usuario = req.getParameter("usuario");
        String pass = req.getParameter("pass");
        int userid = Integer.parseInt(req.getParameter("id_user").trim());


        UsuarioDao dao = new UsuarioDao();
        Person usr = new Person();
        Person user1 = (Person) dao.findOne(userid);


        usr.setName(name);
        usr.setUser(usuario);
        usr.setCurp(CURP);
        usr.setLastname1(last1);
        usr.setLastname2(last2);
        usr.setEmail(email);

        if(!pass.equals("")){
            System.out.println("Se va actualizar la contraseña del usuario " + user1.getRol_id());
            dao.updatepass(user1.getID_user(),pass,user1.getRol_id());
        }else {
            System.out.println("Contraseña vacia no se actualiza");
        }

        dao.updateUser(user1.getUser_id(),usr);
        dao.updatePerson(userid,usr);
        if(user1.getRol_id()==3){
            req.getSession().setAttribute("personType", "estudiante");
            resp.sendRedirect(req.getContextPath() + "/person?id=estudiante");
        }else{
            req.getSession().setAttribute("personType", "docente");
            resp.sendRedirect(req.getContextPath() + "/person?id=docente");
        }



    }
}
