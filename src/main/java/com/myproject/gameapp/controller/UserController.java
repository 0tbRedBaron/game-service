package com.myproject.gameapp.controller;

import com.myproject.gameapp.dao.UserDao;
import com.myproject.gameapp.dao.postgreSql.PostgreSqlUserDao;
import com.myproject.gameapp.entity.User;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/userLogin")
public class UserController extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("register") != null){
            getServletContext().getRequestDispatcher("/registration-user.jsp").forward(req, resp);
            return;
        }

        //noinspection Since15
        if (null == req.getParameter("login") || null == req.getParameter("password")
                || req.getParameter("login").isEmpty()
                || req.getParameter("password").isEmpty()) {
            getServletContext().getRequestDispatcher("/userIndex.jsp").forward(req, resp);
            return;
        }

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = postgreSqlUserDao.read(login);

        if(user == null) {
            System.out.println("no such login");
            getServletContext().getRequestDispatcher("/loginError.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("myUser", user);

        if (password.equals(user.getPassword()) && login.equalsIgnoreCase("admin")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/listGames");
            dispatcher.forward(req, resp);
        } else if (password.equals(user.getPassword())) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/userCollection");
            dispatcher.forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/loginError.jsp").forward(req, resp);
        }
    }

}
