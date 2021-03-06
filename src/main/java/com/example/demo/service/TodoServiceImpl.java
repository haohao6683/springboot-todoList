package com.example.demo.service;

import com.example.demo.model.Todo;
import org.springframework.stereotype.Service;
import com.example.demo.repository.TodoRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> getTodoList() {
        List<Todo> todoList = repository.findAll();
        Collections.reverse(todoList);
        return todoList;
    }

    @Override
    public Todo findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Todo addTodo(Todo newTodo) {
        return repository.save(newTodo);
    }

    @Override
    public Todo updateTodoById(int id, Todo todo) {
        return repository.save(todo);
    }

    @Override
    public boolean deleteTodoByID(Integer id) {
        if(this.findById(id) == null){
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
