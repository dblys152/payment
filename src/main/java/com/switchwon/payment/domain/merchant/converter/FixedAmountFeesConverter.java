package com.switchwon.payment.domain.merchant.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.switchwon.payment.domain.merchant.FixedAmountFees;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FixedAmountFeesConverter implements AttributeConverter<FixedAmountFees, String> {
    private final ObjectMapper objectMapper;

    public FixedAmountFeesConverter() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public String convertToDatabaseColumn(FixedAmountFees attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public FixedAmountFees convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, FixedAmountFees.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing fixedAmountFees DB data", e);
        }
    }
}
