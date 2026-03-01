package com.aluracursos.literalura.service;

import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        return objectMapper.readValue(json, clase);
    }
}
