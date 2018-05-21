package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Agenda;
import models.User;
import play.libs.Json;
import play.mvc.*;
import services.AgendaService;
import services.UserService;

import javax.inject.Inject;


public class AgendaController extends Controller {
    private final AgendaService agendaService;

    @Inject
    public AgendaController(AgendaService agendaService){

       this.agendaService = agendaService;
    }

    //GET Agendas
    public Result getAgenda(int id) {
        Agenda agenda = agendaService.find(id);
        return ok(Json.toJson(agenda));
    }
}
