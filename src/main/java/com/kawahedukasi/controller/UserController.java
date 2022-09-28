package com.kawahedukasi.controller;

import com.kawahedukasi.service.UserService;
import io.vertx.core.json.JsonObject;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/rs-kawahedukasi/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    @RolesAllowed("admin")
    @Path("/admin")
    public String admin(@Context SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }

    @POST
    @Path("/registration")
    @PermitAll
    public Response registration(JsonObject request) {
        return  userService.registration(request);
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response login(JsonObject request) throws Exception {
        return userService.login(request);
    }
}
