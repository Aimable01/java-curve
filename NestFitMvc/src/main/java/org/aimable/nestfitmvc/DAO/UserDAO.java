package org.aimable.nestfitmvc.DAO;

import org.aimable.nestfitmvc.model.User;

import java.sql.SQLException;

public interface UserDAO {
    void registerUser(User user) throws SQLException;
    User login(String email, String password) throws SQLException;
}
