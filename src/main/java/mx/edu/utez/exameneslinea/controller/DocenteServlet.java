package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Daos.UsuarioDao;
import mx.edu.utez.exameneslinea.model.Exam;
import mx.edu.utez.exameneslinea.model.Person;
import mx.edu.utez.exameneslinea.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DocenteServlet", value = "/docente/*")
public class DocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getPathInfo();

        ExamenDao daoex = new ExamenDao();
        List<Exam> lista = null;
        switch (action){
            case "/buscar-examenes":
                int materiaId = Integer.parseInt(req.getParameter("materiaId"));
                Person per =(Person) req.getSession().getAttribute("sesion");
                System.out.println("id del usuario"+per.getID_user() + "id de la materia: " + materiaId);


                lista = daoex.findAllExam(materiaId,per.getID_user());

                req.getSession().setAttribute("exam", lista);
                req.getSession().setAttribute("idsub",materiaId);
                resp.sendRedirect(req.getContextPath() + "/Docente/examenes.jsp");
                break;
            case"/buscar-materias":
                Person pers =(Person) req.getSession().getAttribute("sesion");
                lista = daoex.findAllMa(pers.getID_user());
                req.getSession().setAttribute("subjectlista", lista);
                resp.sendRedirect(req.getContextPath() + "/Docente/materias.jsp");
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getPathInfo();

        UsuarioDao daou = new UsuarioDao();
        ExamenDao dao = new ExamenDao();
        List<Person> lista = null;

        switch (action){
            case "/actualizar-datos-docente":
                String name = req.getParameter("nombre");
                String last1 = req.getParameter("ap1");
                String last2 = req.getParameter("ape2");
                String CURP = req.getParameter("CURP");
                String pass = req.getParameter("pass");
                int userid = Integer.parseInt(req.getParameter("id_user").trim());
                System.out.println("id del usuario update"+userid);

                Person usr = new Person();
                Person user1 = (Person) daou.findOne(userid);

                usr.setName(name);
                usr.setCurp(CURP);
                usr.setLastname1(last1);
                usr.setLastname2(last2);

                if(!pass.equals("")){
                    daou.updatepass(user1.getID_user(),pass,user1.getRol_id());
                }

                daou.updatePerson(userid,usr);
                resp.sendRedirect(req.getContextPath() + "/Docente/materias.jsp");
                break;
            case "/resgitra-materia":
                String nameS = req.getParameter("nombre");
                int grade = Integer.parseInt(req.getParameter("grado"));
                String group = req.getParameter("grupo");
                Person per =(Person) req.getSession().getAttribute("sesion");


                dao.insertMateria(new Subject(0,grade,group,nameS,1));
                Subject mater = (Subject) dao.findMateria(grade,group,nameS);
                dao.insertMateriaUsuario(per.getID_user(),mater.getId_sub());

                req.getSession().setAttribute("materias", mater);
                resp.sendRedirect(req.getContextPath() + "/docente/buscar-materias");
                break;
            default:
        }
    }
}
