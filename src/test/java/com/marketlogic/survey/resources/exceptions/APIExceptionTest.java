package com.marketlogic.survey.resources.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class APIExceptionTest {

    private APIException error;

    @BeforeEach
    void setUp() {
        error = new APIException(ZonedDateTime.now(), HttpStatus.BAD_REQUEST, "Exception Message", "/marketlogic-survey/survey/1");
    }

    @Test
    void testGetTimestamp() {
        assertInstanceOf(ZonedDateTime.class, error.getTimestamp());
    }

    @Test
    void testGetStatus() {
        assertInstanceOf(HttpStatus.class, error.getStatus());
    }

    @Test
    void getMessage() {
        assertInstanceOf(String.class, error.getMessage());
        assertEquals("Exception Message", error.getMessage());
        assertNotNull(error.getMessage());
    }

    @Test
    void testGetPath() {
        assertInstanceOf(String.class, error.getPath());
        assertEquals("/marketlogic-survey/survey/1", error.getPath());
        assertNotNull(error.getPath());
    }

    @Test
    void testSetTimestamp() {
        ZonedDateTime oldTime = error.getTimestamp();
        ZonedDateTime nowTime = ZonedDateTime.now();
        error.setTimestamp(nowTime);
        assertInstanceOf(ZonedDateTime.class, error.getTimestamp());
        assertNotEquals(oldTime, error.getTimestamp());
    }

    @Test
    void testSetStatus() {
        HttpStatus oldStatus = error.getStatus();
        error.setStatus(HttpStatus.NOT_FOUND);
        assertInstanceOf(HttpStatus.class, error.getStatus());
        assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        assertNotEquals(oldStatus, error.getStatus());
    }

    @Test
    void setMessages() {
        String oldMessage = error.getMessage();
        error.setMessage("Another Exception Message");
        assertInstanceOf(String.class, error.getMessage());
        assertEquals("Another Exception Message", error.getMessage());
        assertNotEquals(oldMessage, error.getMessage());
    }

    @Test
    void testSetPath() {
        String oldPath = error.getPath();
        error.setPath("/marketlogic-survey/survey/question/2/disable");
        assertInstanceOf(String.class, error.getPath());
        assertEquals("/marketlogic-survey/survey/question/2/disable", error.getPath());
        assertNotEquals(oldPath, error.getPath());
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}