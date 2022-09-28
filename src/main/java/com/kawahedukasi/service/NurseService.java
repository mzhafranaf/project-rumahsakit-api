package com.kawahedukasi.service;

import com.google.common.base.Strings;
import com.kawahedukasi.model.Nurse;
import com.kawahedukasi.util.FormatUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.text.Format;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NurseService {

    @Inject
    EntityManager em;

    @Transactional
    public Response create(JsonObject request) throws ParseException {

        String fullName = request.getString("fullName");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phoneNumber");
        String status = request.getString("status");
        String salary = request.getString("salary");

        if(!FormatUtil.isStandardNameInput(fullName) || !FormatUtil.isGenderCodeInput(gender) ||
        !FormatUtil.isStandardEmail(email) || !FormatUtil.isStandardPhoneNumber(phoneNumber) ||
        !FormatUtil.isStandardAlphabetInput(status) || !FormatUtil.isNumericInput(salary)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Nurse nurse = new Nurse();
        nurse.setFullName(fullName);
        nurse.setGender(gender);
        nurse.setEmail(fullName);
        nurse.setPhoneNumber(phoneNumber);
        nurse.setStatus(status);
        nurse.setSalary(Long.valueOf(salary));

        nurse.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", nurse);

        return Response.ok().entity(responses).build();
    }

    public Response all(Integer page) {
        List<Nurse> data = new ArrayList<>();
        data = Nurse.findAll().page(page, 10).list();

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
        String email = request.getString("email");
        String phoneNumber = request.getString("phoneNumber");

        // implement sql
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM nurse ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(name)) {
            sb.append(" AND full_name ILIKE :name ");
        } if (!Strings.isNullOrEmpty(email)) {
            sb.append(" AND email ILIKE :email");
        } if (!Strings.isNullOrEmpty(phoneNumber)) {
            sb.append(" AND phone_number ILIKE :phoneNumber");
        }

        Query query = em.createNativeQuery(sb.toString(), Nurse.class);

        if(!Strings.isNullOrEmpty(name)){
            query.setParameter("name", "%" + name + "%");
        } if(!Strings.isNullOrEmpty(email)){
            query.setParameter("email", "%" + email + "%");
        } if(!Strings.isNullOrEmpty(phoneNumber)){
            query.setParameter("phoneNumber", "%" + phoneNumber + "%");
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        // response
        List<Nurse> resultList = query.getResultList();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", resultList);

        return Response.ok().entity(responses).build();
    }
}
