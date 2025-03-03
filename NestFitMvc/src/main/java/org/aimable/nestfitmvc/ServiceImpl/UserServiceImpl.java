package org.aimable.nestfitmvc.ServiceImpl;

import org.aimable.nestfitmvc.DAO.UserDAO;
import org.aimable.nestfitmvc.DAOImpl.UserDAOImpl;
import org.aimable.nestfitmvc.model.User;
import org.aimable.nestfitmvc.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    public UserServiceImpl() throws SQLException {
    }

    @Override
    public void registerUser(User user) throws SQLException {
        userDAO.registerUser(user);
    }

    @Override
    public void login(String email, String password) throws SQLException {
        userDAO.login(email, password);
    }
}
