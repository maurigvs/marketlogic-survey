package com.marketlogic.survey.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "choices")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Choice() {
    }

    public Choice(Integer id, String title, Question question) {
        this.id = id;
        this.title = title;
        this.question = question;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Choice)) return false;
        Choice question = (Choice) o;
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
