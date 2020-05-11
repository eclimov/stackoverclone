package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "answers")
public class Answer extends BaseTextEntity {
    @OneToMany(mappedBy = "answer", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<RatingAnswer> ratings = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    public Answer setQuestion(Question question) {
        this.question = question;
        return this;
    }
}
