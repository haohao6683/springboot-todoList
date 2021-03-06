package com.example.demo.service;

import com.example.demo.model.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.repository.TodoRepository;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void should_return_todo_when_add_a_todo() throws Exception {
        //given
        String todoJSON = " {\n" +
                "        \"id\": 2,\n" +
                "        \"text\": \"todo-2\",\n" +
                "        \"status\": true\n" +
                "    }";

        //when then
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON).content(todoJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("todo-2"))
                .andExpect(jsonPath("$.status").value("true"));
    }

    @Test
    void should_return_updated_todo_when_update_todo_given_todo() throws Exception {
        //given
        Todo savedTodo = todoRepository.save(new Todo(1, "todo-1", true));
        String employeeInformation = " {\n" +
                "        \"id\": "+savedTodo.getId()+",\n" +
                "        \"text\": \"I have updated this todo~\",\n" +
                "        \"status\": true\n" +
                "    }";

        //when then
        mockMvc.perform(put("/todos/" + savedTodo.getId())
                .contentType(MediaType.APPLICATION_JSON).content(employeeInformation))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("I have updated this todo~"))
                .andExpect(jsonPath("$.status").value("true"));
    }

    @Test
    void should_return_boolean_when_delete_todo_by_id_given_id() throws Exception {
        //given
        Todo savedTodo = todoRepository.save(new Todo(1, "todo-1", true));

        //when
        mockMvc.perform(delete("/todos/" + savedTodo.getId()))
                .andExpect(status().isOk());

        //then
        mockMvc.perform(get("/todos/" + savedTodo.getId()))
                .andExpect(jsonPath("$[0].id").doesNotHaveJsonPath());
    }
}
