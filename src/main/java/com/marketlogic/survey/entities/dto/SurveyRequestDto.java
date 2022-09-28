package com.marketlogic.survey.entities.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SurveyRequestDto {

    @NotBlank
    private String title;

    @NotNull
    @NotEmpty
    @Setter(AccessLevel.NONE)
    private List<QuestionRequestDto> questions = new ArrayList<>();

    public SurveyRequestDto(String title) {
        this.title = title;
    }
}