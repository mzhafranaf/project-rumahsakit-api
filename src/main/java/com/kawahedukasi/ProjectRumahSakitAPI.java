package com.kawahedukasi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name="widget", description="Widget operations."),
                @Tag(name="gasket", description="Operations related to gaskets")
        },
        info = @Info(
                title="Project Rumah Sakit Kawah Edukasi",
                version = "1.0.1",
                contact = @Contact(
                        name = "Muhammad Zhafran Abiyu Fadhilah",
                        url = "https://www.linkedin.com/in/muhammad-zhafran-abiyu-fadhilah-4203a816a/",
                        email = "mzhafranaf24@gmail.com"),
                license = @License(
                        name = "Kawah Edukasi",
                        url = "http://kawahedukasi.com"))
)

public class ProjectRumahSakitAPI {
}
