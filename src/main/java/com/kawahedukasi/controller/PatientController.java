package com.kawahedukasi.controller;

import com.kawahedukasi.service.PatientService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/rs-kawahedukasi/patient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientController {

    @Inject
    PatientService patientService;

    @POST
    public Response create(JsonObject request) throws ParseException {
        return patientService.create(request);
    }

    @POST
    @Path("/list")
    public Response list(JsonObject request) {
        return patientService.list(request);
    }

    @GET
    @Path("/all")
    public Response read(@QueryParam("page") Integer page) {
        return patientService.all(page);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, JsonObject request) throws ParseException {
        return patientService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return patientService.delete(id);
    }
}
