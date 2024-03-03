package com.switchwon.payment.domain.wallet.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.switchwon.payment.domain.wallet.Balances;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BalancesConverter implements AttributeConverter<Balances, String> {
    private final ObjectMapper objectMapper;

    public BalancesConverter() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public String convertToDatabaseColumn(Balances attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Balances convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Balances.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing balances DB data", e);
        }
    }
}
