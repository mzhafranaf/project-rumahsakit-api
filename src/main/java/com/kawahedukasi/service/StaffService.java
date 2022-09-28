package com.kawahedukasi.service;

import com.google.common.base.Strings;
import com.kawahedukasi.model.Nurse;
import com.kawahedukasi.model.Staff;
import com.kawahedukasi.util.FormatUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StaffService {

    @Inject
    EntityManager em;

    @Transactional
    public Response create(JsonObject request) throws ParseException {

        String fullName = request.getString("fullName");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phoneNumber");
        String position =request.getString("position");
        String status = request.getString("status");
        String salary = request.getString("salary");

        if(!FormatUtil.isStandardNameInput(fullName) || !FormatUtil.isGenderCodeInput(gender) ||
        !FormatUtil.isStandardEmail(email) || !FormatUtil.isStandardPhoneNumber(phoneNumber) ||
        !FormatUtil.isStandardAlphabetInput(position) || !FormatUtil.isStandardAlphabetInput(status) ||
        !FormatUtil.isNumericInput(salary)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Staff staff = new Staff();
        staff.setFullName(fullName);
        staff.setGender(gender);
        staff.setEmail(email);
        staff.setPhoneNumber(phoneNumber);
        staff.setPosition(position);
        staff.setStatus(status);
        staff.setSalary(Long.valueOf(salary));

        staff.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", staff);

        return Response.ok().entity(responses).build();

    }

    public Response all(Integer page) {

        List<Staff> data = new ArrayList<>();
        data = Staff.findAll().page(page, 10).list();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", data);

        return Response.ok().entity(responses).build();

    }

    public Response list(JsonObject request) {

        Integer limit = request.getInteger("limit");
        Integer offset = request.getInteger("offset");

        // filter name, email, phone number
        String name = request.getString("name");
        String email = request.getString("email");
        String phoneNumber = request.getString("phoneNumber");

        // implement sql
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM staff ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(name)) {
            sb.append(" AND full_name ILIKE :name ");
        } if (!Strings.isNullOrEmpty(email)) {
            sb.append(" AND email ILIKE :email");
        } if (!Strings.isNullOrEmpty(phoneNumber)) {
            sb.append(" AND phone_number ILIKE :phoneNumber");
        }

        Query query = em.createNativeQuery(sb.toString(), Staff.class);

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
        List<Staff> resultList = query.getResultList();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", resultList);

        return Response.ok().entity(responses).build();

    }

    @Transactional
    public Response delete(Long id) {

        Optional<Staff> staffOptional = Staff.findByIdOptional(id);

        if(staffOptional.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("STAFF_ALREADY_DELETED").build();
        }

        Staff staff = staffOptional.get();
        staff.delete();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "delete success");

        return Response.ok().entity(responses).build();

    }
}
