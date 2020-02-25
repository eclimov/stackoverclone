package com.roadmap.stackoverclone.model.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Question> questions = new HashSet<>();

    public User addQuestion(Question question) {
        this.questions.add(question);
        return this;
    }
}
