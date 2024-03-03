package com.switchwon.payment.domain.core.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.switchwon.payment.domain.core.entity.Fees;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FeesConverter implements AttributeConverter<Fees, String> {
    private final ObjectMapper objectMapper;

    public FeesConverter() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public String convertToDatabaseColumn(Fees attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Fees convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Fees.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing fees DB data", e);
        }
    }
}
