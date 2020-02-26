package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class BaseTextEntity extends BaseEntity {
    @Column(name = "text")
    private String text;

    public BaseTextEntity setText(String text) {
        this.text = text;
        return this;
    }
}
