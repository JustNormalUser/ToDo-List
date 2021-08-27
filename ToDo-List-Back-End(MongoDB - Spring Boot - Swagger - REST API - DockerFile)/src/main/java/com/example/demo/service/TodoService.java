package com.example.demo.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.example.demo.exceptions.TodoCollectionException;
import com.example.demo.model.Todo;

public interface TodoService {

	public void createTodo(Todo todo) throws ConstraintViolationException, TodoCollectionException; // Causes and
																									// Solutions for
																									// Hibernate and
																									// JPA.

	public List<Todo> getAllTodos();

	public Todo getSingleTodo(String id) throws TodoCollectionException;

	public void updateTodo(String id, Todo todo) throws TodoCollectionException;

	public void deleteTodoById(String id) throws TodoCollectionException;

}
