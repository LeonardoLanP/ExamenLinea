package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;
import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet(name = "AdministradorServlet", value = "/admin/*")
public class AdministradorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getPathInfo();
        UsuarioDao dao = new UsuarioDao();

        switch (action) {
            case "/buscar-datos":
                String ROL = req.getParameter("userId");
                int id_rol = Integer.parseInt(ROL);

                Person usr = (Person) dao.findOne(id_rol);

                resp.setContentType("text/plain");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter out = resp.getWriter();
                out.println(usr.getName());

                out.println(usr.getLastname1());
                if (usr.getLastname2() != null) {
                    out.println(usr.getLastname2());
                } else {
                    out.println("");
                }
                out.println(usr.getCurp());
                out.println(usr.getEmail());
                out.println(usr.getUser());
                out.println(usr.getRol_id());
                out.println(usr.getId_person());
                out.flush();

                break;
            case "/actualizar-user":
                String name = req.getParameter("nombre");
                String last1 = req.getParameter("ap1");
                String last2 = req.getParameter("ape2");
                String CURP = req.getParameter("CURP");
                String email = req.getParameter("correo");
                String usuario = req.getParameter("usuario");
                String pass = req.getParameter("pass");
                int userid = Integer.parseInt(req.getParameter("id_user").trim());

                Person user = new Person();
                Person user1 = (Person) dao.findOne(userid);

                user.setName(name);
                user.setUser(usuario);
                user.setCurp(CURP);
                user.setLastname1(last1);
                user.setLastname2(last2);
                user.setEmail(email);

                if(!pass.equals("")){
                    dao.updatepass(user1.getID_user(),pass,user1.getRol_id());
                }

                dao.updateUser(user1.getUser_id(),user);
                dao.updatePerson(userid,user);
                if(user1.getRol_id()==3){
                    req.getSession().setAttribute("personType", "estudiante");
                    resp.sendRedirect(req.getContextPath() + "/admin/gestion-docente-alumno?id=estudiante");
                }else{
                    req.getSession().setAttribute("personType", "docente");
                    resp.sendRedirect(req.getContextPath() + "/admin/gestion-docente-alumno?id=docente");
                }
                break;
            case "/registro-user":
                String nameR = req.getParameter("nombre");
                String last1R = req.getParameter("apellido1");
                String last2R = req.getParameter("apellido2");
                String curpR = req.getParameter("CURP");
                String emailR = req.getParameter("correo");
                String rolR = req.getParameter("rol");
                String matriculaR = req.getParameter("matricula");

                String[] nombres = nameR.split(" ");
                String firstname = nombres[0];

                String userR = "";
                String passR = "";
                int id_rolR = 0;
                final String CURPASS = curpR.substring(curpR.length() - 3);
                if(rolR.equals("estudiante")){
                    id_rolR = 3;
                    userR = matriculaR;
                    passR = CURPASS;
                }else{
                    id_rolR = 2;
                    userR = firstname+"_"+last1R;
                    passR = firstname + CURPASS;
                }

                dao.insertu(new User(0,userR,emailR.toLowerCase(),passR,1,id_rolR));
                User USR = (User) dao.findOneUSU(userR,emailR,id_rolR,passR);
                dao.insertp(new Person(0,nameR,last1R,last2R,curpR,USR.getID_user()));

                if(id_rolR==3){
                    req.getSession().setAttribute("personType", "estudiante");
                    resp.sendRedirect(req.getContextPath() + "/admin/gestion-docente-alumno?id=estudiante");
                }else{
                    req.getSession().setAttribute("personType", "docente");
                    resp.sendRedirect(req.getContextPath() + "/admin/gestion-docente-alumno?id=docente");
                }
                break;

            case "/cambio-status":
                int personId = Integer.parseInt(req.getParameter("personId"));
                int estado = Integer.parseInt(req.getParameter("estado"));

                Person users = (Person) dao.findOne(personId);

                    dao.updateStatus(users.getID_user(), estado, users.getRol_id());
                    resp.setContentType("text/plain");
                    resp.setCharacterEncoding("UTF-8");
                    resp.setStatus(HttpServletResponse.SC_OK);

                break;
            case "/verificar-duplicados":
                String curp = req.getParameter("curp");
                String emailC = req.getParameter("email");
                String userC = req.getParameter("user");

                String duplicado = dao.findDuplicado(curp, emailC, userC);

                if (duplicado != null) {
                    resp.setContentType("text/plain"); // Establecer el tipo de contenido como texto plano
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write(duplicado);
                } else {
                    resp.setContentType("text/plain"); // Establecer el tipo de contenido como texto plano
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().write("no_duplicado");
                }
                break;
            default:

            resp.getWriter().println("Acción POST inválida");
            break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getPathInfo();

        UsuarioDao dao = new UsuarioDao();

        switch (action) {
            case "/gestion-docente-alumno":
                String id = req.getParameter("id");
                int idRol = 0;

                if (id.equals("docente")) {
                    idRol = 2;
                    req.getSession().setAttribute("personType", "docente");
                } else if (id.equals("estudiante")) {
                    idRol = 3;
                    req.getSession().setAttribute("personType", "estudiante");
                }

                List<Person> lista = null;
                if (idRol > 0) {
                    lista = dao.findAll(idRol);
                }

                req.getSession().setAttribute("personList", lista);
                resp.sendRedirect(req.getContextPath() + "/Administrador/inicio.jsp");
                break;

            default:
                resp.getWriter().println("Acción inválida");
                break;
        }



    }
}
