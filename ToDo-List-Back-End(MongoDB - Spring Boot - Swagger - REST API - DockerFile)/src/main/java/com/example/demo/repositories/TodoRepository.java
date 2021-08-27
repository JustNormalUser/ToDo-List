package com.example.demo.repositories;

import com.example.demo.model.*;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository // Persistence API, Provides CRUD to DB to Front-end
public interface TodoRepository extends MongoRepository<Todo, String> { // Extend MongoDB interface -> model class name
																		// and data-type property

	@Query("{'todo': ?0}") // Query method for finding the todo which is try to find existing todo.
	// Also Query methods find from databse and declared on the repository
	// interface. Parameter index to 0 which is ?0.
	// Only return JSON.
	Optional<Todo> findByTodos(String todos);
}
