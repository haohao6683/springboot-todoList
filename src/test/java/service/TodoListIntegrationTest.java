package service;

import model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoListIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void should_return_todo_list_when_find_all_todo_list_given_nothing() throws Exception {
        //given
        List<Todo> mockedTodos = new ArrayList<>();
        mockedTodos.add(new Todo(1, "todo-1", true));
        mockedTodos.add(new Todo(1, "todo-1", true));
        when(todoRepository.findAll()).thenReturn(mockedTodos);

        //when then
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(mockedTodos.get(0).getId()))
                .andExpect(jsonPath("$[0].text").value(mockedTodos.get(0).getText()))
                .andExpect(jsonPath("$[0].status").value(mockedTodos.get(0).isStatus()));
    }
}
