package com.kawahedukasi.controller;

import com.kawahedukasi.model.oas.CreateDoctorOAS;
import com.kawahedukasi.service.DoctorService;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/rs-kawahedukasi/doctor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorController {

    @Inject
    DoctorService doctorService;

    @POST
    @RolesAllowed("Admin")
    @Operation(summary = "Create new Doctor",
            description = "This can only be done by the logged in user.")
    @RequestBody(required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = CreateDoctorOAS.Body.class)))
    @APIResponses({
            @APIResponse(responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateDoctorOAS.Body.class))),
            @APIResponse(responseCode = "400",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN,
                    schema = @Schema(example = "BAD_REQUEST")))
    })
    public Response create(JsonObject request) throws ParseException {
        return doctorService.create(request);
    }

    @POST
    @Path("/list")
    @RolesAllowed("Admin")
    public Response list(JsonObject request) throws ParseException {
        return doctorService.list(request);
    }

    @GET
    @Path("/all")
    @RolesAllowed("Admin")
    public Response read(@QueryParam("page") Integer page) {
        return doctorService.all(page);
    }
}
