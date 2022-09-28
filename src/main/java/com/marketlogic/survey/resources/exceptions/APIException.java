package com.marketlogic.survey.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class APIException implements Serializable {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T' HH:mm:ss'Z'")
    private ZonedDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String path;
}
