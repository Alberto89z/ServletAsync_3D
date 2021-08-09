package mx.edu.utez.controller;

import mx.edu.utez.user.BeanUser;
import mx.edu.utez.user.DaoUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletSession", urlPatterns = {"/login", "/logout"})
public class ServletSession extends HttpServlet {
    /**
     * Cierra la sesión del usuario logeado
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obteninendo la sesión ¡
        HttpSession session = request.getSession();
        //Matando la sesión
        session.setAttribute("session", null);
        session.invalidate();
        //redirigiendo a "/"
        request.getRequestDispatcher("/").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BeanUser beanUser = new BeanUser();
        DaoUser daoUser = new DaoUser();

        beanUser.setEmail(request.getParameter("email"));
        beanUser.setPassword(request.getParameter("password"));

        boolean flag = daoUser.createSession(
            beanUser.getEmail(),
            beanUser.getPassword()
        );
        if(flag){
            session.setAttribute("session",beanUser);
        }else {
            request.getRequestDispatcher("/").forward(request, response);
        }
    }
}
