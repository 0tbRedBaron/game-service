package com.myproject.gameapp.controller;

import com.myproject.gameapp.dao.UserDao;
import com.myproject.gameapp.entity.User;
import com.myproject.gameapp.dao.postgreSql.PostgreSqlUserDao;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Champion on 01.05.2017.
 */

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Resource(lookup = "java:/PostgresDS1")
    private DataSource dataSource;

    private UserDao postgreSqlUserDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            postgreSqlUserDao = new PostgreSqlUserDao(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");

        //noinspection Since15
        if (null == login || null == password || null == email || null == name
                || login.isEmpty() || password.isEmpty() || email.isEmpty()
                || name.isEmpty() || !password.equals(confirmPassword)) {
            getServletContext().getRequestDispatcher("/registration-user.jsp").forward(req, resp);
        }

        User newUser = new User(name, login, password, email);
        postgreSqlUserDao.create(newUser);


        HttpSession session = req.getSession();
        session.setAttribute("myUser", newUser);
        //req.login(login, password);

        resp.sendRedirect("/gameapp/registrationSuccess.jsp");

        //getServletContext().getRequestDispatcher("/user-page.jsp").forward(req, resp);
    }
}