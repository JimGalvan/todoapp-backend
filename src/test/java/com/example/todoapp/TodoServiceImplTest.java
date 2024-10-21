package com.example.todoapp;

import com.example.todoapp.entities.Todo;
import com.example.todoapp.repositories.TodoRepository;
import com.example.todoapp.services.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    public TodoServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo();
        todo.setTitle("Sample Todo");

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo createdTodo = todoService.createTodo(todo);
        assertNotNull(createdTodo);
        assertEquals("Sample Todo", createdTodo.getTitle());
    }

    @Test
    public void testGetTodoById() {
        UUID id = UUID.randomUUID();
        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle("Sample Todo");

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));

        Todo fetchedTodo = todoService.getTodoById(id);
        assertNotNull(fetchedTodo);
        assertEquals(id, fetchedTodo.getId());
    }

    @Test
    public void testDeleteTodo() {
        UUID id = UUID.randomUUID();

        doNothing().when(todoRepository).deleteById(id);

        todoService.deleteTodoById(id);
        verify(todoRepository, times(1)).deleteById(id);
    }
}
