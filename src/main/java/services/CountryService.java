package services;

import dao.CountryDAO;
import exception.ValidationException;
import interfaces.ICountryService;
import models.Country;
import utils.Validate;

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
