package com.marketlogic.survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    @NotNull
    @NotEmpty
    private List<Choice> choices = new ArrayList<>();

    @NotNull
    private Integer status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id")
    @NotNull
    private Survey survey;

    public Question(String title, Integer status, Survey survey) {
        this.title = title;
        this.status = status;
        this.survey = survey;
    }

    public QuestionStatus getStatus() {
        return QuestionStatus.valueOf(status);
    }
}
