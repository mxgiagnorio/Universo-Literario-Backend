package service;

import dao.UserDAO;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

   public User login(String username, String password) throws SQLException {
    User user = userDAO.getUserByUsername(username);
    System.out.println("Attempting login for user: " + username);
    if (user != null) {
        System.out.println("User found in database");
        if (BCrypt.checkpw(password, user.getPasswordHash())) {
            System.out.println("Password match successful");
            return user;
        } else {
            System.out.println("Password match failed");
        }
    } else {
        System.out.println("User not found in database");
    }
    return null;
}

    public void registerUser(User user) throws SQLException {
        user.setPasswordHash(BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt()));
        userDAO.createUser(user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }
}