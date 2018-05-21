package controllers;

import play.mvc.*;
import play.libs.Json;
import models.User;
import play.mvc.Http.RequestBody;
import javax.inject.Inject;
import play.libs.Json;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import services.UserService;


public class UserController extends Controller {
    private final UserService userService;

    @Inject
    public UserController(UserService userService){
        this.userService = userService;
    }

    //POST add User
    public Result addUser() {
        User user = new User();
        JsonNode json = request().body().asJson();
        user.setName(json.findPath("name").textValue());
        userService.create(user);
        return ok(Json.toJson(user));
    }
    //DELETE User
    public Result deleteUser(int id) {
        userService.delete(id);
        return ok(id + " user deleted");
    }
    //GET User
    public Result getUser(int id) {
        User user = userService.find(id);

        return ok(Json.toJson(user));
    }

    //GET Users
    public Result getUsers() {
        return ok(Json.toJson(userService.findAll()));
    }

}
