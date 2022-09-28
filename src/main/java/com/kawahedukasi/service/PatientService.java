package com.kawahedukasi.service;

import com.google.common.base.Strings;
import com.kawahedukasi.model.Nurse;
import com.kawahedukasi.model.Patient;
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
public class PatientService {
    @Inject
    EntityManager em;

    @Transactional
    public Response create(JsonObject request) throws ParseException {

        String fullName = request.getString("fullName");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phoneNumber");
        String address =request.getString("address");
        String status = request.getString("status");
        String isCoverBPJS = request.getString("isCoverBPJS");

        if(!FormatUtil.isStandardNameInput(fullName) || !FormatUtil.isGenderCodeInput(gender) ||
        !FormatUtil.isStandardEmail(email) || !FormatUtil.isStandardPhoneNumber(phoneNumber) ||
        !FormatUtil.isStandardAlphabetInput(address) || !FormatUtil.isStandardAlphabetInput(status) ||
        !FormatUtil.isStandardBoolean(isCoverBPJS)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Patient patient = new Patient();
        patient.setFullName(fullName);
        patient.setGender(gender);
        patient.setEmail(email);
        patient.setPhoneNumber(phoneNumber);
        patient.setAddress(address);
        patient.setStatus(status);
        patient.setIsCoverBPJS(Boolean.valueOf(isCoverBPJS));

        patient.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", patient);

        return Response.ok().entity(responses).build();
    }

    public Response all(Integer page) {
        List<Patient> data = new ArrayList<>();
        data = Patient.findAll().page(page, 10).list();

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
        sb.append(" SELECT * FROM patient ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(name)) {
            sb.append(" AND full_name ILIKE :name ");
        } if (!Strings.isNullOrEmpty(email)) {
            sb.append(" AND email ILIKE :email");
        } if (!Strings.isNullOrEmpty(phoneNumber)) {
            sb.append(" AND phone_number ILIKE :phoneNumber");
        }

        Query query = em.createNativeQuery(sb.toString(), Patient.class);

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
        List<Patient> resultList = query.getResultList();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "success");
        responses.put("data", resultList);

        return Response.ok().entity(responses).build();
    }

    public Response update(Long id, JsonObject request) throws ParseException {

        Optional<Patient> patientOptional = Patient.findByIdOptional(id);
        if(patientOptional.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("PATIENT_ALREADY_DELETED").build();
        }

        String fullName = request.getString("fullName");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phoneNumber");
        String address =request.getString("address");
        String status = request.getString("status");
        String isCoverBPJS = request.getString("isCoverBPJS");

        if(!FormatUtil.isStandardNameInput(fullName) || !FormatUtil.isGenderCodeInput(gender) ||
                !FormatUtil.isStandardEmail(email) || !FormatUtil.isStandardPhoneNumber(phoneNumber) ||
                !FormatUtil.isStandardAlphabetInput(address) || !FormatUtil.isStandardAlphabetInput(status) ||
                !FormatUtil.isStandardBoolean(isCoverBPJS)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Patient patient = new Patient();
        patient.setFullName(fullName);
        patient.setGender(gender);
        patient.setEmail(email);
        patient.setPhoneNumber(phoneNumber);
        patient.setAddress(address);
        patient.setStatus(status);
        patient.setIsCoverBPJS(Boolean.valueOf(isCoverBPJS));

        patient.persist();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "update success");
        responses.put("data", patient);

        return Response.ok().entity(responses).build();
    }

    public Response delete(Long id) {

        Optional<Patient> patientOptional = Patient.findByIdOptional(id);
        if(patientOptional.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("PATIENT_ALREADY_DELETED").build();
        }

        Patient patient = patientOptional.get();
        patient.delete();

        JsonObject responses = new JsonObject();
        responses.put("code", "200");
        responses.put("status", "delete success");

        return Response.ok().entity(responses).build();
    }

}
