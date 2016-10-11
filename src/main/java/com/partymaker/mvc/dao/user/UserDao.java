package com.partymaker.mvc.dao.user;


import com.partymaker.mvc.model.user.User;

import java.io.Serializable;
import java.util.List;

public interface UserDao<T, PK extends Serializable> {

    T findById(PK id);

    List<T> findAll();

    void deleteUser(T user);

    void save(T user);

    T findByField(String nameField, String value);
}
