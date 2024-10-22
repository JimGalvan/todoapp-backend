package com.example.todoapp;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Base64;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateTodo_Success() throws Exception {
        // Step 1: Register and login to get token
        String randomUsername = "testuser_" + UUID.randomUUID().toString();
        String newUserJson = "{\"username\": \"" + randomUsername + "\", \"password\": \"password\"}";
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson))
                .andExpect(status().isOk());


        String credentials = randomUsername + ":password";
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        MvcResult loginResult = mockMvc.perform(post("/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic " + base64Credentials))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = loginResult.getResponse().getContentAsString();
        String token = JsonPath.read(jsonResponse, "$.token");

        // Create a todo
        String todoJson = "{\"title\": \"Test Todo\", \"description\": \"Testing\"}";
        MvcResult response = mockMvc.perform(post("/todos")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isOk())
                .andReturn();

        String todoId = JsonPath.read(response.getResponse().getContentAsString(), "$.id");

        // update the todo
        String updatedTodoJson = "{\"title\": \"Updated Test Todo\", \"description\": \"Updated Testing\"}";
        mockMvc.perform(put(String.format("/todos/%s", todoId))
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTodoJson))
                .andExpect(status().isOk());

        // delete the todo
        mockMvc.perform(delete(String.format("/todos/%s", todoId))
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTodo_Success() throws Exception {
        // Step 1: Register and login to get token
        String newUserJson = "{\"username\": \"testuser\", \"password\": \"password\"}";
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson))
                .andExpect(status().isCreated());

        String loginJson = "{\"username\": \"testuser\", \"password\": \"password\"}";
        MvcResult loginResult = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = loginResult.getResponse().getContentAsString();
        String token = JsonPath.read(jsonResponse, "$.token");

        // Step 2: Create a todo
        String todoJson = "{\"title\": \"Test Todo\", \"description\": \"Testing\"}";
        MvcResult createResult = mockMvc.perform(post("/todos")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoJson))
                .andExpect(status().isCreated())
                .andReturn();

        String createdTodoId = createResult.getResponse().getContentAsString();

        // Step 3: Update the todo
        String updatedTodoJson = "{\"title\": \"Updated Test Todo\", \"description\": \"Updated Testing\"}";
        mockMvc.perform(put("/todos/" + createdTodoId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTodoJson))
                .andExpect(status().isOk());

        // Cleanup: Delete the created user and todos
        mockMvc.perform(delete("/auth/delete/testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
