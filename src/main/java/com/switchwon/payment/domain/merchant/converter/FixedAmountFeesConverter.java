package com.switchwon.payment.domain.merchant.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchwon.payment.domain.merchant.FixedAmountFees;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter(autoApply = true)
@RequiredArgsConstructor
public class FixedAmountFeesConverter implements AttributeConverter<FixedAmountFees, String> {
    private final ObjectMapper objectMapper;

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
