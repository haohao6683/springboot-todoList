package service;

import model.Todo;
import repository.TodoRepository;
import java.util.List;

public class TodoServiceImpl implements TodoService{
    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> getTodoList() {
        List<Todo> todos = repository.findAll();
        return todos;
    }
}
