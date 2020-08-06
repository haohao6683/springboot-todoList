package com.example.demo.service;

import com.example.demo.model.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> getTodoList();

    Todo addTodo(Todo newTodo);

    Todo updateTodoById(int id, Todo todo);
}
