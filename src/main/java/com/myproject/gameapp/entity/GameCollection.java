package com.myproject.gameapp.entity;

/**
 * Created by Champion on 27.04.2017.
 */
public class GameCollection {
    private int user_id;
    private int game_count;

    public GameCollection(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGame_count() {
        return game_count;
    }

    public void setGame_count(int game_count) {
        this.game_count = game_count;
    }

    @Override
    public String toString() {
        return "GameCollection{" +
                "user_id=" + user_id +
                ", game_count=" + game_count +
                '}';
    }
}
