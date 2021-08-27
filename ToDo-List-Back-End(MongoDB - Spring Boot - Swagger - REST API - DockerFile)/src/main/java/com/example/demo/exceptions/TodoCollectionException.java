package com.example.demo.exceptions;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "ToDo Collection and Error Response Model") //Add additional information.
public class TodoCollectionException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Serial Version Exception

	public TodoCollectionException(String message) { //Constructor
		super(message);
	}

	public static String NotFoundException(String id) {
		return "There is no ToDo with " + id + " <- this id.";
	}

	public static String TodoAlreadyExists() {
		return "There is a ToDo with this name";
		
	}
		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@ApiModelProperty(notes = "Error Code", name = "code", value = "200") //Data of a model property.
	private int code;
	
	@ApiModelProperty(notes = "Status", name = "message", value = "SUCCESS") //Data of a model property.
	private String status;
	
	@ApiModelProperty(notes = "message", name = "message", value = "Invaid field") //Data of a model property.
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	

}
