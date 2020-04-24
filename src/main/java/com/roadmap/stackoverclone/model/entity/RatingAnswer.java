package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "rating_answer")
public class RatingAnswer extends BaseEntity {
    @Column(name = "value")
    private int value;

    @ManyToOne
    @JoinColumn(name="answer_id", nullable = false)
    private Answer answer;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public RatingAnswer setValue(int value) {
        this.value = value;
        return this;
    }

    public RatingAnswer setAnswer(Answer answer) {
        this.answer = answer;
        return this;
    }

    public RatingAnswer setUser(User user) {
        this.user = user;
        return this;
    }
}
