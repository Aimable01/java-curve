package org.aimable.nestfitmvc.DAOImpl;

import org.aimable.nestfitmvc.DAO.UserDAO;
import org.aimable.nestfitmvc.model.User;
import org.aimable.nestfitmvc.utility.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    // the database connection
    Connection conn = DbConnection.getConnection();

    public UserDAOImpl() throws SQLException {
    }

    @Override
    public void registerUser(User user) throws SQLException {
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void login(String email, String password) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
