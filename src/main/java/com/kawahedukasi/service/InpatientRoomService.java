package com.kawahedukasi.service;

import com.kawahedukasi.model.InpatientRoom;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InpatientRoomService {

    @Inject
    EntityManager em;

    @Transactional
    public Response create(JsonObject request) throws ParseException {

        String roomPrefix = request.getString("roomPrefix");
        String roomNumber = request.getString("roomNumber");
        String roomCategory = request.getString("roomCategory");
        String isEmpty = request.getString("isEmpty");

        InpatientRoom inpatientRoom = new InpatientRoom();
        inpatientRoom.setRoomPrefix(roomPrefix);
        inpatientRoom.setRoomNumber(roomNumber);
        inpatientRoom.setRoomCategory(roomCategory);
        inpatientRoom.setIsEmpty(Boolean.valueOf(isEmpty));

        inpatientRoom.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", inpatientRoom);

        return Response.ok().entity(responses).build();

    }

    public Response all(Integer page) {
        List<InpatientRoom> data = new ArrayList<>();
        data = InpatientRoom.findAll().page(page, 10).list();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", data);

        return Response.ok().entity(responses).build();
    }

    @Transactional
    public Response update(Long id, JsonObject request) throws ParseException{
        // check by id
        Optional<InpatientRoom> inpatientRoomOptional = InpatientRoom.findByIdOptional(id);
        if(inpatientRoomOptional.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("ROOM_ALREADY_DELETED").build();
        }

        String roomPrefix = request.getString("roomPrefix");
        String roomNumber = request.getString("roomNumber");
        String roomCategory = request.getString("roomCategory");
        String isEmpty = request.getString("isEmpty");

        InpatientRoom inpatientRoom = new InpatientRoom();
        inpatientRoom.setRoomPrefix(roomPrefix);
        inpatientRoom.setRoomNumber(roomNumber);
        inpatientRoom.setRoomCategory(roomCategory);
        inpatientRoom.setIsEmpty(Boolean.valueOf(isEmpty));

        inpatientRoom.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", inpatientRoom);

        return Response.ok().entity(responses).build();

    }

    public Response delete(Long id) {
        // check by id
        Optional<InpatientRoom> inpatientRoomOptional = InpatientRoom.findByIdOptional(id);
        if(inpatientRoomOptional.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("ROOM_ALREADY_DELETED").build();
        }

        InpatientRoom inpatientRoom = inpatientRoomOptional.get();
        inpatientRoom.delete();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "delete success");

        return Response.ok().entity(responses).build();

    }
}
