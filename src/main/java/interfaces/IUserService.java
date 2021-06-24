package interfaces;

import dto.UserDTO;
import exception.ValidationException;
import models.Activity;
import models.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    public User findOne(int id) throws ValidationException, SQLException;
    public User findOneByEmail(String email) throws ValidationException, SQLException;
    public List<User> findByName(String name) throws SQLException, ValidationException;
    public User updateOne(UserDTO dto) throws ValidationException, SQLException;
    public User deleteOne(int id) throws ValidationException, SQLException;
    public User addFriend(int userId, int friendId) throws ValidationException, SQLException;
    public List<User> getFriendsList(int id) throws ValidationException, SQLException;
    public User deleteFriend(int userId, int friendId) throws ValidationException, SQLException;
    public List<Activity> getActivity (int id) throws ValidationException, SQLException;
}
