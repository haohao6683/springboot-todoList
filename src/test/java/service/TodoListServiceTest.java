package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoListServiceTest {
    private final TodoRepository repository = mock(TodoRepository.class);
    private final TodoServiceImpl TodoService = new TodoServiceImpl(repository);

    @Test
    void should_return_to_do_list_when_get() {
        //given
        List<Todo> mockedTodos = new ArrayList<>();
        mockedTodos.add(new Todo(1, "todo-1", true));
        mockedTodos.add(new Todo(1, "todo-1", true));
        when(repository.findAll()).thenReturn(mockedTodos);

        //when
        List<Todo> todos = TodoService.getTodoList();

        //then
        Assertions.assertEquals(2, todos.size());
    }
}
