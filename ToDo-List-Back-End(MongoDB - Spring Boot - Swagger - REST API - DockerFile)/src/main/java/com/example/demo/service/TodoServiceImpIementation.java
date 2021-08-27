package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Todo;
import com.example.demo.repositories.TodoRepository;
import com.example.demo.exceptions.*;

@Service // Service Layer
public class TodoServiceImpIementation implements TodoService {

	@Autowired // Inject object(TodoRepository) dependency. Get properties in XML.
	private TodoRepository todoRepo;

	@Override
	public void createTodo(Todo todo) throws ConstraintViolationException, TodoCollectionException {
		Optional<Todo> todoOptional = todoRepo.findByTodos(todo.getTodos()); // Optional takes todo
		if (todoOptional.isPresent()) { // If todo already exist throw exception.
			throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
		} else { // else create new todo
			todo.setId(null); //Change ID to null because of it has to be null for MongoDB implement id.
			todo.setTodos(todo.getTodos().isEmpty() ? "Empty Title" : todo.getTodos()); //If title is empty it adds auto title.
			todo.setDescription(todo.getDescription().isEmpty() ? "Empty Description" : todo.getDescription()); // If description is empty it adds auto description.
			todo.setCompleted(todo.getCompleted() == true ? false : todo.getCompleted()); //If user marks CompletedBox while creating it. It turns it to unmark.
			todo.setUpdatedAt(null); //Surety for create a update time while creating todo.
			todo.setCreatedAt(new Date(System.currentTimeMillis())); //Adds the time to todo.
			todoRepo.save(todo); //It saves the todo to db.
		}
	}

	@Override
	public List<Todo> getAllTodos() { // function to get all todos.
		List<Todo> todo = todoRepo.findAll();
		if (todo.size() > 0) { // if todo is not empty return todo
			return todo;
		} else { // return new
			return new ArrayList<Todo>();
		}
	}

	@Override
	public Todo getSingleTodo(String id) throws TodoCollectionException { // Get single todo function.
		Optional<Todo> optionalTodo = todoRepo.findById(id);
		if (!optionalTodo.isPresent()) { // if the wanted id does not exist throw exception
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		} else { // else exist return it
			return optionalTodo.get();
		}
	}

	@Override
	public void updateTodo(String id, Todo todo) throws TodoCollectionException { // Update todo function.
		Optional<Todo> todoWithId = todoRepo.findById(id); // Optional for finding id with path-variable.
		Optional<Todo> todoWithSameName = todoRepo.findByTodos(todo.getTodos()); // Optional for check all todos in
																					// query.

		if (todoWithId.isPresent()) {

			if (todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)) { // Checking are there any
																								// same todo.
				// İf todo optional is present and there are a todo with same id then function
				// gives a collection.
				throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
			}

			Todo todoToUpdate = todoWithId.get(); // Saving changes to database.

			todoToUpdate.setTodos(todo.getTodos());
			todoToUpdate.setDescription(todo.getDescription());
			todoToUpdate.setCompleted(todo.getCompleted());
			todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todoToUpdate);

		} else {
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id)); // Exception for there are
																								// matching id in
																								// database.
		}
	}

	@Override
	public void deleteTodoById(String id) throws TodoCollectionException { // Delete function.

		Optional<Todo> todoOptional = todoRepo.findById(id); // Optional for finding id with path-variable.
		if (!todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id)); // Sending id to
																								// CollectionException
																								// class function.
		} else {
			todoRepo.deleteById(id); // Delete.
		}
	}
}
