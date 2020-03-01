package com.roadmap.stackoverclone.model.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "name")
    private String name; // TODO: make this field unique

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Question> questions = new HashSet<>();

    public User addQuestion(Question question) {
        this.questions.add(question);
        return this;
    }

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Answer> answers = new HashSet<>();

    public User addAnswer(Answer answer) {
        this.answers.add(answer);
        return this;
    }
}
