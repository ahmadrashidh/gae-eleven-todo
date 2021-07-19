package com.example.appengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.TodoDao;
import com.example.model.Todo;
import com.example.utility.JsonUtility;
import com.google.gson.Gson;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/todos")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isDeleted = Boolean.getBoolean(request.getParameter("isDeleted"));
		
		System.out.println(isDeleted);
		
		TodoDao todoDao = new TodoDao();
		List<Todo> todoList = todoDao.getAllByParam(isDeleted);
		
		for(Todo todo : todoList)
			System.out.println(todo.getText());
		

		Gson gson = new Gson();
		String jsonPayload = gson.toJson(todoList);
		
		response.getWriter().write(jsonPayload);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String payload;
		while ((payload = reader.readLine()) != null) {
			buffer.append(payload);
		}

		String jsonStr = buffer.toString();

		com.example.utility.JsonUtility<Todo> jsonUtil = new JsonUtility<>(Todo.class);
		Todo todo = jsonUtil.convertToModel(jsonStr);
		
		System.out.println("Todo:" + todo.getText());
		
		TodoDao todoDao = new TodoDao();
		todoDao.create(todo);
		
	}

}
