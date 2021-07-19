package com.example.model;

import com.google.cloud.datastore.Entity;

public class Todo {
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public static Todo getTodoFromEntity(Entity todoEntity) {
		Todo todo = new Todo();
		todo.setText(todoEntity.getString("text"));
		return todo;
	}

}
