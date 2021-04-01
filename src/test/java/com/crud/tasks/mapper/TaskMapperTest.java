package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        TaskDto taskDto = new TaskDto(20L, "Task one", "testing");

        Task task = taskMapper.mapToTask(taskDto);

        assertThat(task.getId()).isEqualTo(20L);
        assertThat(task.getTitle()).isEqualTo("Task one");
        assertThat(task.getContent()).isEqualTo("testing");
    }

    @Test
    void testMapToTaskDto() {
        Task task = new Task(20L, "Task one", "testing");

        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        assertThat(taskDto.getId()).isEqualTo(20);
        assertThat(taskDto.getTitle()).isEqualTo("Task one");
        assertThat(taskDto.getContent()).isEqualTo("testing");
    }

    @Test
    void testMapToTaskDtoList() {
        Task task = new Task(20L, "Task one", "testing");
        List<Task> taskList = new ArrayList<>();

        taskList.add(task);

        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        assertThat(taskDtoList.size()).isEqualTo(taskList.size());
        assertThat(taskDtoList.get(0).getId()).isEqualTo(20);
    }

}