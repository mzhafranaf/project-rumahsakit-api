package com.kawahedukasi.controller;

import com.kawahedukasi.service.InpatientRoomService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/rs-kawahedukasi/inpatient-room")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InpatientRoomController {

    @Inject
    InpatientRoomService inpatientRoomService;

    @POST
    public Response create(JsonObject request) throws ParseException {
        return inpatientRoomService.create(request);
    }

    @GET
    @Path("/all")
    public Response all(@QueryParam("page") Integer page) {
        return inpatientRoomService.all(page);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, JsonObject request) throws ParseException {
        return inpatientRoomService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return inpatientRoomService.delete(id);
    }
}
