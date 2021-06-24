package interfaces;

import exception.ValidationException;
import models.Country;

import java.sql.SQLException;
import java.util.List;

public interface ICountryService {
    public Country findOne(int id) throws ValidationException, SQLException;
    public List<Country> findByName(String name) throws ValidationException, SQLException;
}
