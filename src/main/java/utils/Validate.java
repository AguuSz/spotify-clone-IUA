package main.java.utils;

import main.java.exception.ValidationException;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {


    public static String validateString(String string) throws ValidationException {
        if (string == null || string.isBlank())
            throw new ValidationException("The string is null");
        else
            return string.trim();

    }

    public static Integer validateId(int id) throws ValidationException {
        if (id <= 0)
            throw new ValidationException("The id must be positive");
        else
            return id;
    }

    public static String validateEmail(String email) throws ValidationException {
        email = validateString(email);
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z{2,}])$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find())
            throw new ValidationException("The email is incorrect");
        return email;
    }

    public static String validatePassword(String password) throws ValidationException {
        password = validateString(password);
        if(password.length() < 5)
            throw new ValidationException("Password must contain at least 5 characters");

        return password;
    }

    public static Date validateDate(Date date) throws ValidationException {
        if (date == null)
            throw new ValidationException("Date is null");
        return date;
    }

    public static String validateMacAddress (String macAddress) throws ValidationException {
        macAddress = validateString(macAddress);
        Pattern pattern = Pattern.compile("[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}");
        Matcher matcher = pattern.matcher(macAddress);
        if(!matcher.find())
            throw new ValidationException("MacAddress is incorrect");
        return macAddress;
    }
}
