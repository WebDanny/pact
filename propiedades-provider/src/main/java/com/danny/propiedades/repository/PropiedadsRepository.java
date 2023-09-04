package com.danny.propiedades.repository;

import com.danny.propiedades.model.Propiedades;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class PropiedadsRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Propiedades> getPropiedades() throws IOException {
        URL resource = getClass().getClassLoader().getResource("propiedades.json");
        return objectMapper.readValue(resource, new TypeReference<>() {
        });
    }
}
