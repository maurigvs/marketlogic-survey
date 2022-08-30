package com.marketlogic.survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marketlogic.survey.entities.enums.QuestionStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToMany(mappedBy = "question")
    private List<Choice> choices = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private Integer status;

    public Question() {
    }

    public Question(Integer id, String title, QuestionStatus status, List<Choice> choices, Survey survey) {
        this.id = id;
        this.title = title;
        this.choices = choices;
        this.survey = survey;
        setStatus(status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public QuestionStatus getStatus() {
        return QuestionStatus.valueOf(status);
    }

    public void setStatus(QuestionStatus status) {
        this.status = status.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", choices=" + choices +
                ", status=" + getStatus() +
                '}';
    }
}
