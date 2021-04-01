package com.crud.tasks.trello.validator;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;

import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {

    @Test
    void testValidateCard() {

        Logger logger = (Logger) LoggerFactory.getLogger(TrelloValidator.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        logger.addAppender(listAppender);

        TrelloCard trelloCard = new TrelloCard("test", "test", "test", "test");
        TrelloValidator trelloValidator = new TrelloValidator();
        trelloValidator.validateCard(trelloCard);

        List<ILoggingEvent> logsList = listAppender.list;
        String msgInfo = String.valueOf(logsList.get(0));

        assertEquals("[INFO] Someone is testing my application!", msgInfo);
    }

    @Test
    void testValidateTrelloBoard () {
        Logger logger = (Logger) LoggerFactory.getLogger(TrelloValidator.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        logger.addAppender(listAppender);

        TrelloList trelloList = new TrelloList("10", "test", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("1", "test", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();

        trelloBoards.add(trelloBoard);

        TrelloValidator trelloValidator = new TrelloValidator();
        trelloValidator.validateTrelloBoard(trelloBoards);

        List<ILoggingEvent> logsInfo = listAppender.list;
        Integer listSize = logsInfo.size();
        String firstInfo = String.valueOf(logsInfo.get(0));
        String secondInfo = String.valueOf(logsInfo.get(1));

        assertEquals("[INFO] Starting filtering boards...", firstInfo);
        assertEquals("[INFO] Boards have been filtered. Current list size: 0", secondInfo);
        assertEquals(2, listSize);
    }

}