package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DataConverter implements IDataConverter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * @param json Cadena en formato JSON
     * @param tClass Clase destino que se desea convertir el JSON
     * @return Objeto del tipo especificado, mapeado desde el JSON proporcionado
     * @param <T> Tipo genérico que representa la clase a devolver
     */
    @Override
    public <T> T dataConverter(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (IOException e) {
            throw new RuntimeException("Error al convertir el JSON");
        }
    }
}
