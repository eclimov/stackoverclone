package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "name")
    private String name;

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Question> questions = new HashSet<>();

    public User addQuestion(Question question) {
        this.questions.add(question);
        return this;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Answer> answers = new HashSet<>();

    public User addAnswer(Answer answer) {
        this.answers.add(answer);
        return this;
    }
}
