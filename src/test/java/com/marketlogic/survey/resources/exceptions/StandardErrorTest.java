package com.marketlogic.survey.resources.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class StandardErrorTest {

    private StandardError error;

    @BeforeEach
    void setUp() {
        String description = "Handled error description";
        String message = "Exception message";
        String requestURI = "requestURI";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        error = new StandardError(Instant.now(), status.value(), description, message, requestURI);
    }

    @Test
    void getTimestamp() {
        assertInstanceOf(Instant.class, error.getTimestamp());
    }

    @Test
    void setTimestamp() {
        Instant now = Instant.now();
        error.setTimestamp(now);
        assertEquals(now, error.getTimestamp());
    }

    @Test
    void getStatus() {
        assertEquals(400, error.getStatus());
    }

    @Test
    void setStatus() {
        error.setStatus(HttpStatus.NOT_FOUND.value());
        assertEquals(404, error.getStatus());
    }

    @Test
    void getError() {
        assertEquals("Handled error description", error.getError());
    }

    @Test
    void setError() {
        error.setError("Handled error description changed");
        assertEquals("Handled error description changed", error.getError());
    }

    @Test
    void getMessage() {
        assertEquals("Exception message", error.getMessage());
    }

    @Test
    void setMessage() {
        error.setMessage("New exception message");
        assertEquals("New exception message", error.getMessage());
    }

    @Test
    void getPath() {
        assertEquals("requestURI", error.getPath());
    }

    @Test
    void setPath() {
        error.setPath("new requestURI");
        assertEquals("new requestURI", error.getPath());
    }
}