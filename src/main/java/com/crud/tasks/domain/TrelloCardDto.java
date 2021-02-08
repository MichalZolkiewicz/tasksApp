package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrelloCardDto {

    private String name;
    private String desc;
    private String pos;
    private String listId;
}
