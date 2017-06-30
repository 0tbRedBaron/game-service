package com.myproject.gameapp.dao;

import com.myproject.gameapp.entity.User;

/**
 * Created by Champion on 01.05.2017.
 */
public interface UserDao {

    void create(User user);

    User read(String login);

    void update(User user);

}
