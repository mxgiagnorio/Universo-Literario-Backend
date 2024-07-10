package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import service.UserService;
import spark.Route;
import spark.Filter;
import spark.Request;
import spark.Response;
import util.LocalDateTimeAdapter;
import util.JwtUtil;

import static spark.Spark.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
public class UserController {
    private final UserService userService;
    private final Gson gson;

    public UserController(UserService userService) {
        this.userService = userService;
        this.gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
        setupRoutes();
    }

 private void setupRoutes() {
    before("/users", (Filter) (request, response) -> requireAuth(request, response));
    post("/register", register());
    post("/login", login());
    get("/all-users", getAllUsers());
}

    private Route register() {
        return (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userService.registerUser(user);
            response.status(201);
            return gson.toJson(user);
        };
    }

private Route login() {
    return (request, response) -> {
        User credentials = gson.fromJson(request.body(), User.class);
        User user = userService.login(credentials.getUsername(), credentials.getPasswordHash()); // Aquí, passwordHash debería ser la contraseña en texto plano
        if (user != null) {
            String token = JwtUtil.generateToken(user);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("user", user);
            responseMap.put("token", token);
            return gson.toJson(responseMap);
        } else {
            response.status(401);
            return "Invalid credentials";
        }
    };
}

    private Route getAllUsers() {
        return (request, response) -> {
            response.type("application/json");
            return gson.toJson(userService.getAllUsers());
        };
    }

private void requireAuth(Request request, Response response) {
    String token = request.headers("Authorization");
    if (token != null && token.startsWith("Bearer ")) {
        token = token.substring(7);
        if (JwtUtil.verifyToken(token) == null) {
            halt(401, "Invalid or expired token");
        }
    } else {
        halt(401, "Authentication token required");
    }
}
}