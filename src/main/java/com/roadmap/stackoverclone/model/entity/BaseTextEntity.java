package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class BaseTextEntity extends BaseEntity {
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public BaseTextEntity setUser(User user) {
        this.user = user;
        return this;
    }

    public BaseTextEntity setText(String text) {
        this.text = text;
        return this;
    }
}
