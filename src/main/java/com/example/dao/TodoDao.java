package com.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.Todo;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

public class TodoDao implements Dao<Todo> {
	
	private static TodoDao todoDao;
	
	private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();;
	
	public static TodoDao getInstance() {
		if(todoDao == null) 
			todoDao = new TodoDao();
		return todoDao;
	}

	@Override
	public Optional<Todo> get(long key) {
		return null;
	}
	
	public List<Todo> getAllByParam(boolean isDeleted){
		List<Todo> todoList = new ArrayList<>();
		

		Query<Entity> query = Query.newEntityQueryBuilder()
				.setKind("Todo")
				.setFilter(PropertyFilter.eq("isDeleted", isDeleted))
				.build();

		QueryResults<Entity> todos = datastore.run(query);

		while (todos.hasNext()) {
			todoList.add(Todo.getTodoFromEntity(todos.next()));
		}
		
		return todoList;
	}

	@Override
	public List<Todo> getAll() {
		return null;
	}

	@Override
	public void create(Todo todo) {
		
		KeyFactory keyFactory = datastore.newKeyFactory().setKind("Todo");
		Key todoKey = datastore.allocateId(keyFactory.newKey());
		
		Entity todoEntity = Entity.newBuilder(todoKey)
				.set("text", todo.getText())
				.set("isDeleted", false)
				.build();
		datastore.put(todoEntity);
	}

	@Override
	public void update(Todo t, String[] params) {
		
	}

	@Override
	public void delete(Todo t) {
		
	}

}
