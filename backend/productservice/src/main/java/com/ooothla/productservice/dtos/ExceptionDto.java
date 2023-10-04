package com.ooothla.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class ExceptionDto {
    private HttpStatusCode httpStatusCode;
    private String message;

    public ExceptionDto(HttpStatusCode httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
