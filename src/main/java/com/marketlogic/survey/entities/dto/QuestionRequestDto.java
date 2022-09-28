package com.marketlogic.survey.entities.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDto {

    @NotBlank
    private String title;

    @NotNull
    @NotEmpty
    @Setter(AccessLevel.NONE)
    private List<String> choices = new ArrayList<>();

    public QuestionRequestDto(String title) {
        this.title = title;
    }
}