package com.switchwon.payment.domain.wallet.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchwon.payment.domain.wallet.Balances;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter(autoApply = true)
@RequiredArgsConstructor
public class BalancesConverter implements AttributeConverter<Balances, String> {
    private final ObjectMapper objectMapper;

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
