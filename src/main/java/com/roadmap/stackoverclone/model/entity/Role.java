package com.roadmap.stackoverclone.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="roles")
@Getter
@Setter
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;
}