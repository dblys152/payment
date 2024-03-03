package com.switchwon.payment.domain.core.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchwon.payment.domain.core.entity.Fees;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter(autoApply = true)
@RequiredArgsConstructor
public class FeesConverter implements AttributeConverter<Fees, String> {
    private final ObjectMapper objectMapper;

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
