package com.switchwon.payment.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseModel<T> {
    int status;
    T data;
    String errorMessage;

    public static <T> ApiResponseModel<T> success(int status, T data) {
        return new ApiResponseModel<>(status, data, null);
    }

    public static <T> ApiResponseModel<T> error(int status, String errorMessage) {
        return new ApiResponseModel<>(status, null, errorMessage);
    }
}
