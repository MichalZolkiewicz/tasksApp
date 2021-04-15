package com.crud.tasks.domain;

import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    void testShouldGetAllTasks() throws Exception {
        Task task = new Task();
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        TaskDto taskDto = new TaskDto(10L, "Test", "test task");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        mockMvc
            .perform(MockMvcRequestBuilders
                    .get("/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(10)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Test")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test task")));
    }

    @Test
    void testShouldGetTask() throws Exception {
        Long id = 10L;
        Optional<Task> task = Optional.of(new Task());
        TaskDto taskDto = new TaskDto(10L, "Test", "test task");

        when(dbService.getTask(id)).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        mockMvc
            .perform(MockMvcRequestBuilders
                    .get("/v1/tasks/{taskId}", "10")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(10)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test task")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto(10L,"test1", "content1");
        Task task = new Task(10L,"test1", "content1");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(10)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test1")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content1")));
    }

    @Test
    void testShouldUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto(10L,"test1", "content1");
        Task task = new Task(10L, "test1", "content1");
        Task savedTask = new Task(10L, "test1", "content1");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(savedTask);
        when(taskMapper.mapToTaskDto(savedTask)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mockMvc
            .perform(MockMvcRequestBuilders
                    .put("/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(10)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content1")));
    }

    @Test
    void testShouldDeleteTask() throws Exception {
        Task task = new Task(10L,"test1", "content1");
        when(dbService.saveTask(task)).thenReturn(task);

        mockMvc
            .perform(MockMvcRequestBuilders
                    .delete("/v1/tasks/{taskId}", "10")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}