package com.switchwon.payment.domain.core.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.switchwon.payment.domain.core.entity.PaymentDetails;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DetailsConverter implements AttributeConverter<PaymentDetails, String> {
    private final ObjectMapper objectMapper;

    public DetailsConverter() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public String convertToDatabaseColumn(PaymentDetails attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public PaymentDetails convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, PaymentDetails.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error parsing details DB data", e);
        }
    }
}
