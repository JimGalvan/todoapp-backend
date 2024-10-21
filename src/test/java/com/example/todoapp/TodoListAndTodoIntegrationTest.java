package com.example.todoapp;

import com.example.todoapp.entities.Todo;
import com.example.todoapp.entities.TodoList;
import com.example.todoapp.repositories.TodoListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListAndTodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        // Clean up database before running each test
        todoListRepository.deleteAll();
    }

    @Test
    public void testCreateTodoListAndThenTodo() throws Exception {
        // Step 1: Create a TodoList
        TodoList todoList = new TodoList();
        todoList.setName("My Tasks");

        String todoListPayload = objectMapper.writeValueAsString(todoList);

        String todoListResponse = mockMvc.perform(post("/todolists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoListPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("My Tasks"))
                .andReturn().getResponse().getContentAsString();

        // Deserialize the response to extract the generated ID
        TodoList createdTodoList = objectMapper.readValue(todoListResponse, TodoList.class);
        String todoListId = createdTodoList.getId().toString();

        // Step 2: Create a Todo associated with the created TodoList
        Todo todo = new Todo();
        todo.setTitle("Complete homework");
        todo.setDescription("Finish math exercises");
        todo.setCompleted(false);
        todo.setList(createdTodoList); // Set the relationship

        String todoPayload = objectMapper.writeValueAsString(todo);

        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Complete homework"))
                .andExpect(jsonPath("$.description").value("Finish math exercises"))
                .andExpect(jsonPath("$.list.id").value(todoListId));
    }
}
