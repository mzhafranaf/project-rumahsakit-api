package com.kawahedukasi.controller;

import com.kawahedukasi.service.NurseService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/rs-kawahedukasi/nurse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NurseController {

    @Inject
    NurseService nurseService;

    @POST
    public Response create(JsonObject request) throws ParseException {
        return nurseService.create(request);
    }

    @POST
    @Path("/list")
    public Response list(JsonObject request) throws ParseException {
        return nurseService.list(request);
    }

    @GET
    @Path("/all")
    public Response read(@QueryParam("page") Integer page) {
        return nurseService.all(page);
    }
}
