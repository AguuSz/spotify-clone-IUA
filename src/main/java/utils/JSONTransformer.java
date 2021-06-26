package utils;

import com.google.gson.Gson;

public class JSONTransformer {
    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
