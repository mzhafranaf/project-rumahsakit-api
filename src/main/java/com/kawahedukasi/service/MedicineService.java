package com.kawahedukasi.service;

import com.google.common.base.Strings;
import com.kawahedukasi.model.Medicine;
import com.kawahedukasi.model.Patient;
import com.kawahedukasi.util.FormatUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MedicineService {

    @Inject
    EntityManager em;

    @Transactional
    public Response create(JsonObject request) throws ParseException{

        String medicineName = request.getString("medicineName");
        String production = request.getString("production");
        String category = request.getString("category");
        String description = request.getString("description");

        if(!FormatUtil.isStandardAlphabetInput(medicineName) || !FormatUtil.isStandardAlphabetInput(production) ||
        !FormatUtil.isStandardAlphabetInput(category) || !FormatUtil.isStandardAlphabetInput(description)){
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Medicine medicine = new Medicine();
        medicine.setMedicineName(medicineName);
        medicine.setProduction(production);
        medicine.setCategory(category);
        medicine.setDescription(description);

        medicine.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", medicine);

        return Response.ok().entity(responses).build();
    }

    public Response all(Integer page) {
        List<Medicine> data = new ArrayList<>();
        data = Medicine.findAll().page(page, 10).list();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", data);

        return Response.ok().entity(responses).build();
    }

    public Response list(JsonObject request) {
        // limit & offset
        Integer limit = request.getInteger("limit");
        Integer offset = request.getInteger("offset");

        // filter name, email, phone number
        String name = request.getString("name");
        String production = request.getString("production");
        String category = request.getString("category");

        // implement sql
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM medicine ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(name)) {
            sb.append(" AND full_name ILIKE :name ");
        } if (!Strings.isNullOrEmpty(production)) {
            sb.append(" AND email ILIKE :production");
        } if (!Strings.isNullOrEmpty(category)) {
            sb.append(" AND phone_number ILIKE :category");
        }

        Query query = em.createNativeQuery(sb.toString(), Medicine.class);

        if(!Strings.isNullOrEmpty(name)){
            query.setParameter("name", "%" + name + "%");
        } if(!Strings.isNullOrEmpty(production)){
            query.setParameter("email", "%" + production + "%");
        } if(!Strings.isNullOrEmpty(category)){
            query.setParameter("phoneNumber", "%" + category + "%");
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        // response
        List<Patient> resultList = query.getResultList();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", resultList);

        return Response.ok().entity(responses).build();

    }

    @Transactional
    public Response update(Long id, JsonObject request) throws ParseException {
        // serach by id
        Optional<Medicine> medicineOptional = Medicine.findByIdOptional(id);
        if(medicineOptional.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("MEDICINE_ALREADY_DELETED").build();
        }

        String medicineName = request.getString("medicineName");
        String production = request.getString("production");
        String category = request.getString("category");
        String description = request.getString("description");

        if(!FormatUtil.isStandardAlphabetInput(medicineName) || !FormatUtil.isStandardAlphabetInput(production) ||
                !FormatUtil.isStandardAlphabetInput(category) || !FormatUtil.isStandardAlphabetInput(description)){
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Medicine medicine = new Medicine();
        medicine.setMedicineName(medicineName);
        medicine.setProduction(production);
        medicine.setCategory(category);
        medicine.setDescription(description);

        medicine.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "update success");
        responses.put("data", medicine);

        return Response.ok().entity(responses).build();
    }

    public Response delete(Long id) {
        // serach by id
        Optional<Medicine> medicineOptional = Medicine.findByIdOptional(id);
        if(medicineOptional.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("MEDICINE_ALREADY_DELETED").build();
        }

        Medicine medicine = medicineOptional.get();
        medicine.delete();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "delete success");

        return Response.ok().entity(responses).build();
    }
}
