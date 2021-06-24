package services;

import dao.UserDAO;
import dto.UserDTO;
import exception.ValidationException;
import interfaces.IUserService;
import models.Activity;
import models.User;
import utils.Validate;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    UserDAO dao = new UserDAO();
    @Override
    public User findOne(int id) throws ValidationException, SQLException {
        Validate.validateId(id);
        return dao.findOne(id);
    }

    @Override
    public User findOneByEmail(String email) throws SQLException {
        return dao.findByEmail(email);
    }

    @Override
    public List<User> findByName(String name) throws ValidationException, SQLException {
        name = Validate.validateString(name);
        return dao.findByName(name);
    }

    @Override
    public User updateOne(UserDTO dto) throws SQLException {
        return dao.update(dto);
    }

    @Override
    public User deleteOne(int id) throws ValidationException, SQLException {
        Validate.validateId(id);
        return dao.delete(id);
    }

    @Override
    public User addFriend(int userId, int friendId) throws ValidationException, SQLException {
        if(Validate.validateId(userId) == Validate.validateId(friendId))
            throw new ValidationException("Id's must be different");
        return dao.addFriend(userId, friendId);
    }

    @Override
    public List<User> getFriendsList(int id) throws ValidationException, SQLException {
        Validate.validateId(id);
        return dao.getFriendsList(id);
    }

    @Override
    public User deleteFriend(int userId, int friendId) throws ValidationException, SQLException {
        if(Validate.validateId(userId) == Validate.validateId(friendId))
            throw new ValidationException("IDs must be different");
        return dao.deleteFriend(userId, friendId);
    }

    @Override
    public List<Activity> getActivity(int id) throws ValidationException, SQLException {
        Validate.validateId(id);
        return dao.getActivity(id);
    }
}
