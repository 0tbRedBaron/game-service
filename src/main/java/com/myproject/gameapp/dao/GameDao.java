package com.myproject.gameapp.dao;

import com.myproject.gameapp.entity.Game;

import java.util.List;

/**
 * Created by Champion on 27.04.2017.
 */
public interface GameDao {
    List<Game> getAllGames();
    List<Game> searchGame(String gameName);
    Game getGameById(int id);
    void createGame(Game newGame);
    void updateGame(Game theGame);
    void deleteGame(int id);
    List<Game> gameInfoById(int id);
    List<Game> readGamesByUserId(int userId);
    void addGame(int userId, int gameId);
    void userDeleteGame(int userId, int gameId);
    void updateUserGame(Game theGame);
    Game getUserGameById (int gameId, int userID);

}
