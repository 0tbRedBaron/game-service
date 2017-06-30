package com.myproject.gameapp.entity;


import java.sql.Timestamp;

/**
 * Created by Champion on 27.04.2017.
 */
public class GameInfo {

    private int id;
    private int user_id;
    private int game_id;
    private String title;
    private String user_name;
    private String publisher;
    private String description;
    private int rating;
    private String review;
    private Timestamp created_timestamp;
    private Timestamp updated_timestamp;
    private Boolean game_saved;

    public GameInfo() {

    }

    public GameInfo(int id, String user_name, int rating, String review, Timestamp created_timestamp, Timestamp updated_timestamp, Boolean game_saved) {
        this.id = id;
        this.user_name = user_name;
        this.rating = rating;
        this.review = review;
        this.created_timestamp = created_timestamp;
        this.updated_timestamp = updated_timestamp;
        this.game_saved = game_saved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Timestamp getCreated_timestamp() {
        return created_timestamp;
    }

    public void setCreated_timestamp(Timestamp created_timestamp) {
        this.created_timestamp = created_timestamp;
    }

    public Timestamp getUpdated_timestamp() {
        return updated_timestamp;
    }

    public void setUpdated_timestamp(Timestamp updated_timestamp) {
        this.updated_timestamp = updated_timestamp;
    }

    public Boolean getGame_saved() {
        return game_saved;
    }

    public void setGame_saved(Boolean game_saved) {
        this.game_saved = game_saved;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", game_id=" + game_id +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", created_timestamp=" + created_timestamp +
                ", updated_timestamp=" + updated_timestamp +
                ", game_saved=" + game_saved +
                '}';
    }
}
