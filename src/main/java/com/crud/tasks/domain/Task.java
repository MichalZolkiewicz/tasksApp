package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
