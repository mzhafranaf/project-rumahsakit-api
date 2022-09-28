package com.kawahedukasi.model.oas;

import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

public class CreateDoctorOAS {
    public class Body{
        @Schema(example = "Muhammad Zhafran Abiyu Fadhilah")
        public String fullName;

        @Schema(example = "mzhafranaf24@gmail.com")
        public String email;

        @Schema(example = "6289523347800")
        public String phoneNumber;

        @Schema(example = "Cardiology")
        public String specialistName;

        @Schema(example = "attending")
        public String status;

        @Schema(example = "50000000")
        public String salary;
    }

}
