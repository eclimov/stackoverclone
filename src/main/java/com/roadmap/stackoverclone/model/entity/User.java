package com.roadmap.stackoverclone.model.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
