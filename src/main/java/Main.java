import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        port(port);
        System.out.println("Servidor escuchando en: localhost:" + port);

        get("/", (req, res) -> "Hello World");
    }
}
