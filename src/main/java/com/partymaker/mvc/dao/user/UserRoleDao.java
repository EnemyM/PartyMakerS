package com.partymaker.mvc.dao.user;


import java.io.Serializable;
import java.util.Set;

/**
 * Created by anton on 10/10/16.
 */
public interface UserRoleDao<T, PK extends Serializable> {
    Set<T> findAllRoles();

    T findById(PK id);

    T findByField(String nameField, Object value);
}
