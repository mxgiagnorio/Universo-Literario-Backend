package main;

import dao.UserDAO;
import dao.UserDAOImpl;
import service.UserService;
import controller.UserController;
import static spark.Spark.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String portStr = props.getProperty("SERVER_PORT");
        int port = portStr != null ? Integer.parseInt(portStr) : 4567;
        port(port);

        UserDAO userDAO = new UserDAOImpl();
        UserService userService = new UserService(userDAO);
        new UserController(userService);

        // Configurar CORS para permitir solicitudes desde cualquier origen
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        });
    }
}
