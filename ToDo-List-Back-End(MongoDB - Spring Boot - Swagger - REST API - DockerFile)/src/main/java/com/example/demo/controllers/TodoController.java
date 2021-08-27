package com.example.demo.controllers;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.TodoCollectionException;
import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@CrossOrigin("*")
@ApiOperation(value = "todos", tags = "ToDo Controller") //Add Response type.
@RequestMapping("todos") //Add requestmaping.
@RestController //Turn object to JSON for browser <-> client result.
public class TodoController {


	@Autowired //Bean takes the TodoService object to inject todoService(right side).
	private TodoService todoService;

	@ApiOperation(value = "Fetch all ToDos", response = Iterable.class) //Describes operation.
	@ApiResponses(value = { //For list multiple api-reponse.
			@ApiResponse(code = 200, message = "SUCCESS", response = Todo.class), //Add possible response.
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = TodoCollectionException.class), //Add possible response.
			@ApiResponse(code = 403, message = "FORBIDDEN", response = TodoCollectionException.class), //Add possible response.
			@ApiResponse(code = 404, message = "NOT FOUND") //Add possible response.
	})
	@GetMapping(value = "") //It makes HTTP Get Requests.
	public ResponseEntity<?> getAllTodo() { //ResponseEntity represent the entire HTTP response. We can code more clear with this.
		List<Todo> todos = todoService.getAllTodos();
		return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Create ToDos by id", response = Todo.class) //Describes operation.
	@PostMapping(value = "") //It makes HTTP Post Request.
	public ResponseEntity<?> createTodo(@RequestBody Todo todos) { //RequestBody turn JSON to a Todo -> todos.
		try {
			todoService.createTodo(todos);
			return new ResponseEntity<Todo>(todos, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@ApiOperation(value = "Fetch ToDos by id", response = Todo.class) //Describes operation.
	@GetMapping(value = "/{id}") //It makes HTTP Get Request. Value with {id} shows end-point will be PathVariable.
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) { //PathVariable takes the id and fetch it into controller.
		try {
			return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Update ToDos by id", response = Todo.class) //Describes operation.
	@PutMapping(value = "/{id}") //It makes HTTP Put Request. Value with {id} shows end-point will be PathVariable.
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Todo todo) { //PathVariable takes the id and fetch it into controller.
		try {
			todoService.updateTodo(id, todo);
			return new ResponseEntity<>("Todo Updated With This ID: " + id, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Delete ToDos by id", response = Todo.class) //Describes operation.
	@DeleteMapping("/{id}") //It makes HTTP Delete Request.
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) { //PathVariable takes the id and fetch it into controller.
		try {
			todoService.deleteTodoById(id);
			return new ResponseEntity<>("Deleted Succesfully! " + id, HttpStatus.OK);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
