package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Component
@SpringBootTest
class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void testMapToBoard() {

        TrelloListDto trelloListDto = new TrelloListDto();
        trelloListDto.setId("one");
        trelloListDto.setName("first");
        trelloListDto.setClosed(false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);


        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        trelloBoardDto.setId("two");
        trelloBoardDto.setName("second");
        trelloBoardDto.setLists(trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);

        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloBoardDtos);

        assertEquals("two", trelloBoards.get(0).getId());
        assertEquals("second", trelloBoards.get(0).getName());
    }

    @Test
    void testMapToBoardDto() {
        TrelloList trelloList = new TrelloList("one", "first", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("two", "second", trelloLists);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardDto(trelloBoards);

        assertEquals("two", trelloBoardDtos.get(0).getId());
        assertEquals("second", trelloBoardDtos.get(0).getName());
    }

    @Test
    void testMapToList() {
        TrelloListDto trelloListDto = new TrelloListDto("one", "first", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);

        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        assertEquals("one", trelloLists.get(0).getId());
        assertEquals("first", trelloLists.get(0).getName());
        assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    void testMapToListDto() {
        TrelloList trelloList = new TrelloList("one", "first", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        assertEquals("one", trelloListDtos.get(0).getId());
        assertEquals("first", trelloListDtos.get(0).getName());
        assertFalse(trelloListDtos.get(0).isClosed());
    }

    @Test
    void testMapToCard() {
        TrelloCard trelloCard = new TrelloCard("one", "card", "top", "1", "test");

        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        assertEquals("one", trelloCardDto.getName());
        assertEquals("card", trelloCardDto.getDesc());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void testMapToCardDto() {
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        trelloCardDto.setName("one");
        trelloCardDto.setDesc("card");
        trelloCardDto.setPos("top");
        trelloCardDto.setListId("1");
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        TrelloCard expectedTrelloCard = new TrelloCard("one", "card", "top", "1", "test");

        assertEquals(expectedTrelloCard, trelloCard);
    }
}
