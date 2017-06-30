package com.myproject.gameapp.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Champion on 27.04.2017.
 */
public class Game {
    private int id;
    private String title;
    private String description;
    private String publisher;
    private double avg_rating;
    private int vote_count;
    private int user_id;
    private int game_id;
    private String user_name;
    private int rating;
    private String review;
    private Timestamp created_timestamp;
    private Timestamp updated_timestamp;
    private Boolean game_saved;

    public Game() {
    }


    public Game(String title, String description, String publisher, double avg_rating, int vote_count) {
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.avg_rating = avg_rating;
        this.vote_count = vote_count;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(double avg_rating) {
        this.avg_rating = avg_rating;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", avg_rating=" + avg_rating +
                ", vote_count=" + vote_count +
                '}';
    }
}
