package dao;

import model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void createUser(User user) throws SQLException;
    User getUserById(int id) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
}