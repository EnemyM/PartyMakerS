package com.partymaker.mvc.service;

import com.partymaker.mvc.dao.user.UserRoleDao;
import com.partymaker.mvc.model.user.role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by anton on 11/10/16.
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao dao;

    @Override
    public UserRole findUserRoleByName(String roleName) {
        return (UserRole) dao.findByField("role", roleName);
    }

    @Override
    public UserRole findUserRoleById(Integer id) {
        return (UserRole) dao.findById(id);
    }
}
