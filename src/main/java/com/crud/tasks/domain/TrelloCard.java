package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloCard {
    private String name1;
    private String description;
    private String pos;
    private String listId;
}
