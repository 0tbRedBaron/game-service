package com.myproject.gameapp.dao.utils;

import com.myproject.gameapp.entity.Game;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Champion on 06.06.2017.
 */
public class ResultSetToGames {

    public static Game resultSetToGame(ResultSet resultSet) throws SQLException {
        Game game = new Game();
        game.setId(resultSet.getInt("id"));
        game.setTitle(resultSet.getString("title"));
        game.setDescription(resultSet.getString("description"));
        game.setPublisher(resultSet.getString("publisher"));
        game.setAvg_rating(resultSet.getDouble("average_rating"));
        game.setVote_count(resultSet.getInt("vote_count"));
        return game;
    }

    public static Game resultSetToGameInfo(ResultSet resultSet) throws SQLException {
        Game gameInfo = new Game();
        gameInfo.setUser_name(resultSet.getString("first_name"));
        gameInfo.setRating(resultSet.getInt("rating"));
        gameInfo.setReview(resultSet.getString("review"));
        gameInfo.setCreated_timestamp(resultSet.getTimestamp("created_timestamp"));
        gameInfo.setUpdated_timestamp(resultSet.getTimestamp("updated_timestamp"));
        return  gameInfo;
    }

    public static Game resultSetToUserGames(ResultSet resultSet) throws SQLException {
        Game gameInfo = new Game();
        gameInfo.setGame_id(resultSet.getInt("game_id"));
        gameInfo.setTitle(resultSet.getString("title"));
        gameInfo.setPublisher(resultSet.getString("publisher"));
        gameInfo.setDescription(resultSet.getString("description"));
        gameInfo.setReview(resultSet.getString("review"));
        gameInfo.setRating(resultSet.getInt("rating"));
        return gameInfo;
    }
}
