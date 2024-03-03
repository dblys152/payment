package com.switchwon.payment.domain.core.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchwon.payment.domain.core.entity.PaymentDetails;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter(autoApply = true)
@RequiredArgsConstructor
public class DetailsConverter implements AttributeConverter<PaymentDetails, String> {
    private final ObjectMapper objectMapper;

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
