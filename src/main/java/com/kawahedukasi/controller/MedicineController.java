package com.kawahedukasi.controller;

import com.kawahedukasi.service.MedicineService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/rs-kawahedukasi/medicine")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicineController {

    @Inject
    MedicineService medicineService;

    @POST
    public Response create(JsonObject request) throws ParseException {
        return medicineService.create(request);
    }

    @POST
    @Path("/list")
    public Response list(JsonObject request) {
        return medicineService.list(request);
    }

    @GET
    @Path("/all")
    public Response read(@QueryParam("page") Integer page) {
        return medicineService.all(page);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, JsonObject request) throws ParseException {
        return medicineService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response detele(@PathParam("id") Long id) {
        return medicineService.delete(id);
    }
}
