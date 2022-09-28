package com.marketlogic.survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marketlogic.survey.entities.enums.QuestionStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey_questions")
@Data
@NoArgsConstructor
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<Choice> choices = new ArrayList<>();

    @JsonIgnore
    private Integer status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id")
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
