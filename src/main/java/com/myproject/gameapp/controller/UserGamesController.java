package com.myproject.gameapp.controller;

import com.myproject.gameapp.dao.GameDao;
import com.myproject.gameapp.dao.postgreSql.PostgreSqlGameDao;
import com.myproject.gameapp.entity.Game;
import com.myproject.gameapp.entity.User;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Created by Champion on 06.06.2017.
 */

@WebServlet(urlPatterns = {"/listGamesForUser",
        "/userCollection",
        "/addGame",
        "/deleteGame",
        "/updateGame",
        "/loadGameToForm"
})
public class UserGamesController extends HttpServlet {

    private GameDao postgreSqlGameDao;

    @Resource(lookup = "java:/PostgresDS1")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            postgreSqlGameDao = new PostgreSqlGameDao(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        List<Game> games;

        String idString = request.getParameter("gameId");

        if (userPath.equals("/listGamesForUser")) {
            // get games from the PostgreSqlGameDao
            games = postgreSqlGameDao.getAllGames();

            // add games to the request
            request.setAttribute("GAME_LIST", games);
            userPath = "/user-game-catalog";

        } else if (userPath.equals("/userCollection")) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("myUser");

            // get games from the PostgreSqlGameDao
            games = postgreSqlGameDao.readGamesByUserId(user.getId());

            // add games to the request
            request.setAttribute("GAME_LIST", games);
            userPath = "/user-games";

        } else if (userPath.equals("/addGame")) {
            // read game id parameter from the "Add" link
            //String idString = request.getParameter("gameId");
            int gameId = Integer.parseInt(idString);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("myUser");

            postgreSqlGameDao.addGame(user.getId(), gameId);
            response.sendRedirect("/gameapp/listGamesForUser");
            return;

        } else if (userPath.equals("/deleteGame")) {
            // read game id parameter from the "Delete" link

            int gameId = Integer.parseInt(idString);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("myUser");

            postgreSqlGameDao.userDeleteGame(user.getId(), gameId);
            response.sendRedirect("/gameapp/userCollection");
            return;
        } else if (userPath.equals("/loadGameToForm")) {
            int gameId = Integer.parseInt(idString);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("myUser");

            Game singleGame = postgreSqlGameDao.getUserGameById(user.getId(), gameId);

            // place game object in the request attribute so the JSP can use it to pre-populate the form
            request.setAttribute("THE_GAME", singleGame);
            userPath = "/user-update-game";
        }

        // use RequestDispatcher to forward request internally
        String url = "/user" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPath = request.getServletPath();

        if (userPath.equals("/updateGame")) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("myUser");
            Game theGame = new Game();

            theGame.setUser_id(user.getId());
            theGame.setGame_id(Integer.parseInt(request.getParameter("gameId")));
            theGame.setRating(Integer.parseInt(request.getParameter("rating")));
            theGame.setReview(request.getParameter("review"));

            postgreSqlGameDao.updateUserGame(theGame);
            response.sendRedirect("/gameapp/userCollection");
        }

    }
}