package com.example.demo.service;

import com.example.demo.model.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.repository.TodoRepository;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todo_list_when_find_all_todo_list_given_nothing() throws Exception {
        //given
        Todo savedTodo = todoRepository.save(new Todo(1, "todo-1", true));

        //when then
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(savedTodo.getId()))
                .andExpect(jsonPath("$[0].text").value(savedTodo.getText()))
                .andExpect(jsonPath("$[0].status").value(savedTodo.isStatus()));
    }
}
