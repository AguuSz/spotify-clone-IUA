package main.java.interfaces;

import main.java.models.Country;

import java.sql.SQLException;
import java.util.List;

public interface ICountryService {

    public Country findOne(int id) throws SQLException;
    public List<Country> findByName(String name) throws SQLException;
}
