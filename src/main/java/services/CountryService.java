package main.java.services;

import main.java.dao.CountryDAO;
import main.java.exception.ValidationException;
import main.java.interfaces.ICountryService;
import main.java.models.Country;
import main.java.utils.Validate;

import java.sql.SQLException;
import java.util.List;

public class CountryService implements ICountryService {
    CountryDAO dao = new CountryDAO();
    @Override
    public Country findOne(int id) throws ValidationException, SQLException {
        Validate.validateId(id);
        return dao.findOne(id);
    }

    @Override
    public List<Country> findByName(String name) throws ValidationException, SQLException {
        name = Validate.validateString(name);
        return dao.findByName(name);
    }
}
