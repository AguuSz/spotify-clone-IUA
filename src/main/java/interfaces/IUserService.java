package main.java.interfaces;

import main.java.dto.UserDTO;
import main.java.exception.ValidationException;
import main.java.models.Activity;
import main.java.models.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    public User findOne(int id) throws ValidationException, SQLException;
    public User findOneByEmail(String email) throws ValidationException, SQLException;
    public List<User> findByName(String name) throws SQLException, ValidationException;
    public User updateOne(UserDTO dto) throws ValidationException, SQLException;
    public User createOne(UserDTO dto) throws SQLException;
    public User deleteOne(int id) throws ValidationException, SQLException;
    public User addFriend(int userId, int friendId) throws ValidationException, SQLException;
    public List<User> getFriendsList(int id) throws ValidationException, SQLException;
    public User deleteFriend(int userId, int friendId) throws ValidationException, SQLException;
    public List<Activity> getActivity (int id) throws ValidationException, SQLException;
}
