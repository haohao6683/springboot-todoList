package com.example.demo.controller;

import com.example.demo.model.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addEmployee(@RequestBody Todo newTodo) {
        return todoService.addTodo(newTodo);
    }

    @PutMapping("/{id}")
    public Todo updateTodoByID(@PathVariable int id, @RequestBody Todo todo) {
        return todoService.updateTodoById(id, todo);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTodoByID(@PathVariable int id) {
        return todoService.deleteTodoByID(id);
    }
}
