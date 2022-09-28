package com.kawahedukasi.controller;

import com.kawahedukasi.service.StaffService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("rs-kawahedukasi/staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffController {

    @Inject
    StaffService staffService;

    @POST
    public Response create(JsonObject request) throws ParseException {
        return staffService.create(request);
    }

    @POST
    @Path("/list")
    public Response list(JsonObject request) throws ParseException {
        return staffService.list(request);
    }

    @GET
    @Path("/all")
    public Response read(@QueryParam("page") Integer page) {
        return staffService.all(page);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return staffService.delete(id);
    }
}
