package com.myproject.gameapp.dao.postgreSql;

import com.myproject.gameapp.dao.GameDao;
import com.myproject.gameapp.dao.utils.JdbcUtils;
import com.myproject.gameapp.dao.utils.ResultSetToGames;
import com.myproject.gameapp.entity.Game;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Champion on 27.04.2017.
 */
public class PostgreSqlGameDao implements GameDao {

    private DataSource dataSource;

    public PostgreSqlGameDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Game> getAllGames() {

        String sql = "SELECT * FROM games ORDER BY id\n" + "ASC";

        List<Game> gameList = new ArrayList();

        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet resultSet = null;
        try {
            // get a connection
            myConnection = dataSource.getConnection();
            myStatement = myConnection.createStatement();
            resultSet = myStatement.executeQuery(sql);

            while (resultSet.next()) {
                Game tempGame = ResultSetToGames.resultSetToGame(resultSet);
                gameList.add(tempGame);
            }
        } catch (SQLException e) {
            System.out.println("Games select exception");
        }
        finally {
            // close JDBC objects
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(myStatement);
            JdbcUtils.closeQuietly(myConnection);
        }
        return gameList;
    }

    public List<Game> searchGame(String gameName) {

        String sql = "SELECT * FROM games WHERE LOWER(title) like LOWER(?) OR LOWER(description) LIKE LOWER(?) " +
                "OR LOWER(publisher) LIKE LOWER(?);";

        String ename = "%" + gameName + "%";

        List<Game> gameList = new ArrayList<Game>();
        Connection myConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            myConnection = dataSource.getConnection();
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setString(1, ename);
            preparedStatement.setString(2, ename);
            preparedStatement.setString(3, ename);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game tempGame = ResultSetToGames.resultSetToGame(resultSet);
                gameList.add(tempGame);
            }

        } catch (SQLException e) {
            System.out.println("Game search exception");
        }
        finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(myConnection);
        }
        return gameList;
    }

    public Game getGameById(int id) {

        String sql = "SELECT * FROM games WHERE id = ?";

        Game game = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                game = ResultSetToGames.resultSetToGame(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Game select exception");
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        return game;
    }

    public void createGame(Game newGame) {

        String sql = "INSERT INTO games (title, description, publisher) " +
                "VALUES (?, ?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newGame.getTitle());
            preparedStatement.setString(2, newGame.getDescription());
            preparedStatement.setString(3, newGame.getPublisher());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Game insert exception");
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }

    public void updateGame(Game theGame) {

        String sql = "UPDATE games "
                + "SET title=?, description=?, publisher=? "
                + "WHERE id=?;";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, theGame.getTitle());
            preparedStatement.setString(2, theGame.getDescription());
            preparedStatement.setString(3, theGame.getPublisher());
            preparedStatement.setInt(4, theGame.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Game update exception");
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
    }

    @Override
    public void updateUserGame(Game theGame) {
        String sql = "UPDATE game_info " +
                "SET rating=?, review=? " +
                "WHERE user_id=? AND game_id=?";

        Connection myConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            myConnection = dataSource.getConnection();
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setInt(1, theGame.getRating());
            preparedStatement.setString(2, theGame.getReview());
            preparedStatement.setInt(3, theGame.getUser_id());
            preparedStatement.setInt(4, theGame.getGame_id());

            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("Update User Game exception");
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(myConnection);
        }
    }


    public void deleteGame(int id) {

        String sql = "DELETE FROM games WHERE id=?;";
        Connection myConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            myConnection = dataSource.getConnection();

            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Game delete exception");
        }
        finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(myConnection);
        }
    }

    @Override
    public void addGame(int userId, int gameId) {

        String sql ="INSERT INTO game_info(user_id, game_id, game_saved) " +
                "VALUES(?, ?, true) " +
                "ON CONFLICT (user_id, game_id)" +
                "DO UPDATE SET game_saved = EXCLUDED.game_saved";

        Connection myConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            myConnection = dataSource.getConnection();
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("Game add exception");
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(myConnection);
        }
    }

    @Override
    public void userDeleteGame(int userId, int gameId) {
        String sql = "UPDATE game_info " +
                "SET game_saved = false " +
                "WHERE user_id = ? AND game_id = ?";

        Connection myConnection = null;
        PreparedStatement preparedStatement = null;
        try {
            myConnection = dataSource.getConnection();
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println("Game delete exception");
        } finally {
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(myConnection);
        }
    }

    public List<Game> readGamesByUserId(int userId) {

        String sql = "SELECT g.title, g.publisher, g.description, gi.game_id, gi.review, gi.rating, gi.game_saved " +
                     "FROM game_info gi " +
                     "LEFT JOIN games g ON gi.game_id = g.id " +
                     "WHERE user_id = ?";

        List<Game> userGames = new ArrayList<>();

        Connection myConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            myConnection = dataSource.getConnection();
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean("game_saved") == true) {
                Game tempGameInfo = ResultSetToGames.resultSetToUserGames(resultSet);
                userGames.add(tempGameInfo);
                }
            }
        } catch (SQLException e) {
            System.out.println("User GameInfo select exception");
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(myConnection);
        }

        return userGames;
    }

    public Game getUserGameById (int userID, int gameId) {
        String sql = "SELECT gi.user_id, gi.game_id, gi.rating, gi.review, g.title " +
                "FROM game_info gi " +
                "INNER JOIN games g ON gi.game_id = g.id " +
                "WHERE user_id = ? AND game_id = ?";

        Game game = new Game();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, gameId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                game.setUser_id(resultSet.getInt("user_id"));
                game.setGame_id(resultSet.getInt("game_id"));
                game.setRating(resultSet.getInt("rating"));
                game.setReview(resultSet.getString("review"));
                game.setTitle(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            System.out.println("User Game select exception");
        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(connection);
        }
        return game;
    }

    public List<Game> gameInfoById(int gameId) {

        String sql = "SELECT u.first_name, g.rating, g.review, g.created_timestamp, g.updated_timestamp " +
                "FROM game_info g " +
                "INNER JOIN users u ON g.user_id = u.id " +
                "WHERE game_id = ?";

        List<Game> gameInfoList = new ArrayList<>();

        Connection myConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            myConnection = dataSource.getConnection();
            preparedStatement = myConnection.prepareStatement(sql);
            preparedStatement.setInt(1, gameId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Game tempGameInfo = ResultSetToGames.resultSetToGameInfo(resultSet);
                gameInfoList.add(tempGameInfo);
            }

        } catch (SQLException ex) {
            System.out.println("GameInfo select exception");

        } finally {
            JdbcUtils.closeQuietly(resultSet);
            JdbcUtils.closeQuietly(preparedStatement);
            JdbcUtils.closeQuietly(myConnection);
        }

        return gameInfoList;
    }
}
