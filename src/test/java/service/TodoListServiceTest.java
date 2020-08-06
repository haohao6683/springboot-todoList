package service;

import model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoListServiceTest {
    private final TodoRepository repository = mock(TodoRepository.class);
    private final TodoServiceImpl todoService = new TodoServiceImpl(repository);

    @Test
    void should_return_to_do_list_when_get() {
        //given
        List<Todo> mockedTodos = new ArrayList<>();
        mockedTodos.add(new Todo(1, "todo-1", true));
        mockedTodos.add(new Todo(1, "todo-1", true));
        when(repository.findAll()).thenReturn(mockedTodos);

        //when
        List<Todo> todos = todoService.getTodoList();

        //then
        Assertions.assertEquals(2, todos.size());
    }
}
