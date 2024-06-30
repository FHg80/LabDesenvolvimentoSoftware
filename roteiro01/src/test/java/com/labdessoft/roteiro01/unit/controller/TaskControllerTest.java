package com.labdessoft.roteiro01.unit.controller;

import com.labdessoft.roteiro01.controller.TaskController;
import com.labdessoft.roteiro01.dto.DTOCreateTask;
import com.labdessoft.roteiro01.dto.DTOCreateDataTask;
import com.labdessoft.roteiro01.dto.DTOCreatePrazoTask;
import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.entity.TaskPriorityEnum;
import com.labdessoft.roteiro01.entity.TaskTypeEnum;
import com.labdessoft.roteiro01.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        List<Task> taskList = new ArrayList<>();
        when(taskService.findAll()).thenReturn(taskList);

        ResponseEntity<List<Task>> response = taskController.listAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testListAllWithTasks() {
        // Criar algumas tarefas de exemplo
        Task task1 = new Task();
        task1.setId(1L);
        task1.setDescription("Task 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setDescription("Task 2");

        // Criar uma lista e adicionar as tarefas
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        // Simular o comportamento do serviço para retornar as tarefas
        when(taskService.findAll()).thenReturn(taskList);

        // Chamar o método do controlador
        ResponseEntity<List<Task>> response = taskController.listAll();

        // Verificar se o status HTTP retornado é OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar se a lista de tarefas no corpo da resposta não está vazia
        assertNotNull(response.getBody());

        // Verificar se as tarefas na lista correspondem às criadas
        List<Task> returnedTasks = response.getBody();
        assertEquals(2, returnedTasks.size());
        assertEquals(task1, returnedTasks.get(0));
        assertEquals(task2, returnedTasks.get(1));
    }

    @Test
    void testCreate() {
        DTOCreateTask taskDto = new DTOCreateTask();
        Task task = new Task("Description", TaskTypeEnum.LIVRE, TaskPriorityEnum.ALTA);

        when(taskService.create(taskDto)).thenReturn(task);

        ResponseEntity<Task> response = taskController.create(taskDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void testCreatePrazoTask() {
        DTOCreatePrazoTask taskDto = new DTOCreatePrazoTask();
        Task task = new Task("Description", TaskTypeEnum.PRAZO, TaskPriorityEnum.ALTA, 5);

        when(taskService.createPrazoTask(taskDto)).thenReturn(task);

        ResponseEntity<Task> response = taskController.createPrazoTask(taskDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void testCreateDataTask() {
        DTOCreateDataTask taskDto = new DTOCreateDataTask();
        Task task = new Task("Description", TaskTypeEnum.DATA, TaskPriorityEnum.ALTA, LocalDate.now());

        when(taskService.createDataTask(taskDto)).thenReturn(task);

        ResponseEntity<Task> response = taskController.createDataTask(taskDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void testMarcarTarefaComoConcluida() {
        Long id = 1L;
        Task task = new Task("Description", TaskTypeEnum.LIVRE, TaskPriorityEnum.ALTA);

        when(taskService.marcarTarefaComoConcluida(id)).thenReturn(task);

        ResponseEntity<Task> response = taskController.marcarTarefaComoConcluida(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        taskController.delete(id);

        verify(taskService, times(1)).delete(id);
    }
}

*/