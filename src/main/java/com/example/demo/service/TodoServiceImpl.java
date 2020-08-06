package com.example.demo.service;

import com.example.demo.model.Todo;
import org.springframework.stereotype.Service;
import com.example.demo.repository.TodoRepository;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> getTodoList() {
        return repository.findAll();
    }

    @Override
    public Todo addTodo(Todo newTodo) {
        return repository.save(newTodo);
    }
}
