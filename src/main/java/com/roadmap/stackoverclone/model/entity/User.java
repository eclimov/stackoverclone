package com.roadmap.stackoverclone.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username")
    private String username; // TODO: make this field unique

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "password")
    @JsonIgnore
    private String password;

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


    /**
     * Roles are being eagerly loaded here because
     * they are a fairly small collection of items for this example.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    public boolean hasRole (String roleName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }

        return false;
    }
}
