package com.kawahedukasi.service;

import com.google.common.base.Strings;
import com.kawahedukasi.model.Doctor;
import com.kawahedukasi.util.BasicUtil;
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

@ApplicationScoped
public class DoctorService {

    @Inject
    EntityManager em;

    @Transactional
    public Response create(JsonObject request) throws ParseException {

        String fullName = request.getString("fullName");
        String email = request.getString("email");
        String phoneNumber = request.getString("phoneNumber");
        String specialistName = request.getString("specialistName");
        String status = request.getString("status");
        String salary = request.getString("salary");

        if(!FormatUtil.isStandardNameInput(fullName) || !FormatUtil.isStandardEmail(email) ||
        !FormatUtil.isStandardPhoneNumber(phoneNumber) || !FormatUtil.isStandardAlphabetInput(specialistName) ||
        !FormatUtil.isStandardAlphabetInput(status) || !FormatUtil.isNumericInput(salary)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Doctor doctor = new Doctor();
        doctor.setFullName(fullName);
        doctor.setEmail(email);
        doctor.setPhoneNumber(phoneNumber);
        if(specialistName == null || specialistName.equals("")){
            doctor.setSpecialist(false);
        } else {
            doctor.setSpecialist(true);
        }
        doctor.setSpecialistName(specialistName);
        doctor.setStatus(status);
        doctor.setSalary(Long.valueOf(salary));

        doctor.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", doctor);

        return Response.ok().entity(responses).build();
    }

    public Response all(Integer page) {
        List<Doctor> data = new ArrayList<>();
        data = Doctor.findAll().page(page, 10).list();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", data);
        responses.put("total", Doctor.count());
        responses.put("totalPage", BasicUtil.roundUp(Doctor.count(), 10));

        return Response.ok().entity(responses).build();
    }

    public Response list(JsonObject request) {
        // limit & offset
        Integer limit = request.getInteger("limit");
        Integer offset = request.getInteger("offset");

        // filter name, specialist, email, phone number
        String name = request.getString("name");
        String specialistName = request.getString("specialist");
        String email = request.getString("email");
        String phoneNumber = request.getString("phoneNumber");
        
        // implement sql
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM doctor ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(name)) {
            sb.append(" AND full_name ILIKE :name ");
        } if (!Strings.isNullOrEmpty(specialistName)) {
            sb.append(" AND specialist_name ILIKE :specialistName");
        } if (!Strings.isNullOrEmpty(email)) {
            sb.append(" AND email ILIKE :email");
        } if (!Strings.isNullOrEmpty(phoneNumber)) {
            sb.append(" AND phone_number ILIKE :phoneNumber");
        }

        Query query = em.createNativeQuery(sb.toString(), Doctor.class);

        if(!Strings.isNullOrEmpty(name)){
            query.setParameter("name", "%" + name + "%");
        } if(!Strings.isNullOrEmpty(specialistName)){
            query.setParameter("specialistName", specialistName);
        } if(!Strings.isNullOrEmpty(email)){
            query.setParameter("email", "%" + email + "%");
        } if(!Strings.isNullOrEmpty(phoneNumber)){
            query.setParameter("phoneNumber", "%" + phoneNumber + "%");
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        // response
        List<Doctor> resultList = query.getResultList();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", resultList);

        return Response.ok().entity(responses).build();
    }
}
