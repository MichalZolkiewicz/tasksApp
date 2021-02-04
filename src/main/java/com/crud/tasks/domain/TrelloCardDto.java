package com.crud.tasks.domain;

import lombok.Data;

@Data
public class TrelloCardDto {

    private String name;
    private String desc;
    private String pos;
    private String listId;
}
