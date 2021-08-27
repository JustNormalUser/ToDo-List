package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "ToDo Model") //Add additional information.
@Document(collection = "todo") //Domain Object -> Map a class into a collection inside mongoDB (mongoTemplate @Bean)
public class Todo {

	@ApiModelProperty(notes = "ID for every ToDo", name = "id", required = true) //Add property for control Swagger 
	//specific definitions.
	@Id //Primary key of entity
	private String id;

	@ApiModelProperty(notes = "Title for every ToDo", name = "todos", required = true, value = "What I will do") //Add property for control Swagger 
	//specific definitions.
	@NotNull(message = "todo should not be null") //We say; it has to be filled.
	private String todos;

	@ApiModelProperty(notes = "Description for every ToDo", name = "decription", required = true, value = "Buy Eggs") //Add property for control Swagger 
	//specific definitions.
	@NotNull(message = "description should not be null") //We say; it has to be filled.
	private String description;
	
	@ApiModelProperty(notes = "Checking completed or not for every ToDo", name = "completed", required = true) //Add property for control Swagger 
	//specific definitions.
	@NotNull(message = "completed should not be null") //We say; it has to be filled.
	private Boolean completed;
	
	@ApiModelProperty(notes = "Create time for every ToDo", name = "createdAt", required = true) //Add property for control Swagger 
	//specific definitions.
	private Date createdAt;
	
	@ApiModelProperty(notes = "Upate time for every ToDo", name = "updatedAt", required = false) //Add property for control Swagger 
	//specific definitions.
	private Date updatedAt;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTodos() {
		return todos;
	}

	public void setTodos(String todos) {
		this.todos = todos;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", todos=" + todos + ", description=" + description + ", completed=" + completed
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
