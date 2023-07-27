package mx.edu.utez.exameneslinea.controller;

import mx.edu.utez.exameneslinea.model.Daos.ExamenDao;
import mx.edu.utez.exameneslinea.model.Exam;
import mx.edu.utez.exameneslinea.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "LoginExamenServlet", value = "/ques_reload")
public class LoginExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigo = req.getParameter("codigo");
        ExamenDao dao = new ExamenDao();
        Exam exm = (Exam) dao.findOne(codigo);


        if (exm.getCode().equals(codigo)) {
            System.out.println("ya entro en la configuaracion");
            if (exm.getStatusex().equals("1")) {
                System.out.println("Lo detecto activo");
                List<Question> questionList = dao.findAllExamStudent(codigo);
                int cantidadPreguntasSeleccionar = questionList.size() / 2;
                Set<Question> preguntasSeleccionadas = new HashSet<>();
                while (preguntasSeleccionadas.size() < cantidadPreguntasSeleccionar) {
                    int indiceAleatorio = (int) (Math.random() * questionList.size());
                    Question preguntaAleatoria = questionList.get(indiceAleatorio);
                    preguntasSeleccionadas.add(preguntaAleatoria);
                }
                List<Question> preguntasSeleccionadasList = new ArrayList<>(preguntasSeleccionadas);
                for (Question pregunta : preguntasSeleccionadasList) {
                    System.out.println(pregunta.getQuestion()); // Ejemplo de cómo obtener la pregunta de cada objeto
                }
                req.getSession().setAttribute("quests", preguntasSeleccionadasList);
                resp.sendRedirect("./Estudiante/examen.jsp");

            } else {
                System.out.println("Lo detecto desactivado");
                resp.sendRedirect("./Estudiante/acceso.jsp?"+"status=desactivado");

            }
        } else {
            System.out.println("Lo detecto que no existe");
            resp.sendRedirect("./Estudiante/acceso.jsp?"+"status=noRegistrado");
        }
    }
}
