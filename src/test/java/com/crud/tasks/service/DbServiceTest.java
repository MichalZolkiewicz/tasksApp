package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    void testSaveTask() {
        Task task = new Task("name", "description");

        dbService.saveTask(task);
        Long id = task.getId();

        assertNotEquals(0, id);

        dbService.deleteTask(id);
    }

    @Test
    void testGetTask() {
        Task task = new Task("name", "description");

        dbService.saveTask(task);
        Long id = task.getId();

        Optional<Task> taskOptional = dbService.getTask(id);

        assertTrue(taskOptional.isPresent());

        dbService.deleteTask(id);
    }

    @Test
    void testGetAllTasks() {
        Task task = new Task("name", "description");
        dbService.saveTask(task);
        Long id = task.getId();

        List<Task> taskList = dbService.getAllTasks();

        assertEquals(7, taskList.size());

        dbService.deleteTask(id);
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("name", "description");

        dbService.saveTask(task);
        Long id = task.getId();
        dbService.deleteTask(id);

        Optional<Task> taskOptional = dbService.getTask(id);

        assertTrue(taskOptional.isEmpty());
    }
}