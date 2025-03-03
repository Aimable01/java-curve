package org.aimable.nestfitmvc.service;

import org.aimable.nestfitmvc.model.User;

import java.sql.SQLException;

public interface UserService {
    void registerUser(User user) throws SQLException;
    void login(String email, String password) throws SQLException;
}
