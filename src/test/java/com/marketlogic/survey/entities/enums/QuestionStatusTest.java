package com.marketlogic.survey.entities.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionStatusTest {

    QuestionStatus enabled = QuestionStatus.ENABLED;
    QuestionStatus disabled = QuestionStatus.DISABLED;
    int enabledCode = 1;
    int disabledCode = 2;

    @Test
    void getCode() {
        assertEquals(enabledCode, enabled.getCode());
        assertEquals(disabledCode, disabled.getCode());
    }

    @Test
    void valueOf() {
        assertEquals(enabled, QuestionStatus.valueOf(enabledCode));
        assertEquals(disabled, QuestionStatus.valueOf(disabledCode));
    }

    @Test
    void values() {
        QuestionStatus[] statuses = QuestionStatus.values();
        assertEquals(statuses[0], enabled);
        assertEquals(statuses[1], disabled);
        assertEquals(statuses.length, 2);
    }

    @Test
    void testValueOf() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            QuestionStatus status = QuestionStatus.valueOf(3);
        });
        assertInstanceOf(IllegalArgumentException.class, exception);
        assertEquals("Invalid Question Status code!", exception.getMessage());
    }
}