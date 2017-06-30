package com.myproject.gameapp.controller;

import com.myproject.gameapp.dao.GameDao;
import com.myproject.gameapp.dao.postgreSql.PostgreSqlGameDao;
import com.myproject.gameapp.entity.Game;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Created by Champion on 27.04.2017.
 */

@WebServlet(urlPatterns = {"/admin/",
        "/admin/searchGames",
        "/admin/listGames",
        "/admin/loadGameToForm",
        "/admin/createGame",
        "/admin/updateGame",
        "/admin/deleteGame",
        "/admin/gamesForUser",
        "/admin/viewGameInfo"
})

public class GameController extends HttpServlet {

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

        if (userPath.equals("/admin/listGames")) {

            // get games from the PostgreSqlGameDao
            games = postgreSqlGameDao.getAllGames();

            // add games to the request
            request.setAttribute("GAME_LIST", games);
            userPath = "/view-games";

        } else if (userPath.equals("/admin/searchGames")) {
            String nameString = request.getParameter("gameName");

            if (nameString != null) {
                games = postgreSqlGameDao.searchGame(nameString);
                request.setAttribute("GAMES", games);
            }
            userPath = "/search-games";

        } else if (userPath.equals("/admin/loadGameToForm")) {

            // read game id parameter from the "Update" link
            //String idString = request.getParameter("gameId");
            int id = Integer.parseInt(idString);
            Game theGame = postgreSqlGameDao.getGameById(id);

            // place game object in the request attribute so the JSP can use it to pre-populate the form
            request.setAttribute("THE_GAME", theGame);
            userPath = "/update-game-form";

        } else if (userPath.equals("/admin/deleteGame")) {
            // read game id parameter from the "Delete" link
            //String idString = request.getParameter("gameId");
            int id = Integer.parseInt(idString);

            postgreSqlGameDao.deleteGame(id);
            response.sendRedirect("/gameapp/admin/listGames");

        } else if (userPath.equals("/admin/viewGameInfo")) {
            // read game id parameter from the "gameInfoLink"
            //String idString = request.getParameter("gameId");
            int id = Integer.parseInt(idString);
            games = postgreSqlGameDao.gameInfoById(id);
            request.setAttribute("GAME_INFO", games);
            userPath = "/player-game-info";
        }

        // use RequestDispatcher to forward request internally
        String url = "/admin" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        if (userPath.equals("/admin/updateGame")) {
            Game theGame = new Game();
            theGame.setId(Integer.parseInt(request.getParameter("gameId")));
            theGame.setTitle(request.getParameter("title"));
            theGame.setDescription(request.getParameter("description"));
            theGame.setPublisher(request.getParameter("publisher"));

            postgreSqlGameDao.updateGame(theGame);

            response.sendRedirect("/gameapp/admin/listGames");

        } else if (userPath.equals("/admin/createGame")) {
            Game theGame = new Game();
            theGame.setTitle(request.getParameter("title"));
            theGame.setDescription(request.getParameter("description"));
            theGame.setPublisher(request.getParameter("publisher"));

            postgreSqlGameDao.createGame(theGame);

            response.sendRedirect("/gameapp/admin/listGames");
        }

    }



}
