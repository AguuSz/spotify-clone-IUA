package main.java.services;

import main.java.interfaces.ICountryService;
import main.java.models.Country;

import java.sql.SQLException;
import java.util.List;

public class CountryService implements ICountryService {
    @Override
    public Country findOne(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Country> findByName(String name) throws SQLException {
        return null;
    }
}
