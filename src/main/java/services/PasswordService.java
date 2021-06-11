package main.java.services;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {
    private final static int workload = 12;

    public static String hashPassword(String plainTextPassword) {
        String salt = BCrypt.gensalt(workload);

        return BCrypt.hashpw(plainTextPassword, salt);
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        boolean didPasswordVerify = false;

        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) throw new IllegalArgumentException("Invalid hash provided for comparison");

        didPasswordVerify = BCrypt.checkpw(plainTextPassword, hashedPassword);

        return didPasswordVerify;
    }
}
