package service;

import model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import repository.TodoRepository;

import static org.hamcrest.Matchers.hasSize;
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
        Todo todo = new Todo(1, "todo-1", true);
        Todo save = todoRepository.save(todo);

        //when then
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(save.getId()))
                .andExpect(jsonPath("$[0].text").value(save.getText()))
                .andExpect(jsonPath("$[0].status").value(save.isStatus()));

    }
}
