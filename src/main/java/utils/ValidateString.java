package main.java.utils;

import main.java.exception.ValidationException;

public class ValidateString {


    public static String validate(String string) throws ValidationException {

        if(string == null || string.isBlank()){

            throw new ValidationException("The string is null");

        }else{
            return string.trim();
        }

    }

}
