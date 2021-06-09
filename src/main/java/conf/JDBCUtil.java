package main.java.conf;

public class JDBCUtil {
    private static final String user = "root";
    private static final String password = Password.personalPassword; //TOCAR PERSONALMENTE
    private static final String host = "localhost";
    private static final String database = "spotify-clone";
    private static final String port = "3306";

    public static String getURL() {
        return "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
