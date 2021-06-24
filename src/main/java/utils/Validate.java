package utils;

import exception.ValidationException;

import java.sql.Date;
import java.sql.Timestamp;
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

    public static String validateMacAddress (String macAddress) throws ValidationException {
        macAddress = validateString(macAddress);
        Pattern pattern = Pattern.compile("[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}");
        Matcher matcher = pattern.matcher(macAddress);
        if(!matcher.find())
            throw new ValidationException("MacAddress is incorrect");
        return macAddress;
    }
}
