package controllers;

import services.*;
import javax.inject.Inject;
import javax.inject.Singleton;
import views.html.*;
import models.*;
import play.libs.Json;
import play.api.data.*;
import play.api.data.Forms.*;
import play.api.data.validation.Constraints.*;
import play.data.FormFactory;
import play.*;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;


public class AlexController extends Controller {
    @Inject FormFactory formFactory;
    private UserService userService = new UserService();
    public Result alex() {
        return ok(alex.render(userService.find(1)));
    }
    //POST add User
    public Result addUser() {
        User user = new User();
        JsonNode json = request().body().asJson();
        user.setName(json.findPath("name").textValue());
        userService.create(user);
        return ok(addUser.render(user));
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
        return ok(Json.toJson(userService.readAll()));
    }

}
