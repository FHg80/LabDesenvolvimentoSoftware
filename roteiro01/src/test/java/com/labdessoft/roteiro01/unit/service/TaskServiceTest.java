package com.labdessoft.roteiro01.unit.service;

import com.labdessoft.roteiro01.dto.DTOCreateDataTask;
import com.labdessoft.roteiro01.dto.DTOCreatePrazoTask;
import com.labdessoft.roteiro01.dto.DTOCreateTask;
import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.entity.TaskPriorityEnum;
import com.labdessoft.roteiro01.entity.TaskTypeEnum;
import com.labdessoft.roteiro01.repository.TaskRepository;
import com.labdessoft.roteiro01.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void testFindAll() {
        
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("Test Task", TaskTypeEnum.LIVRE, TaskPriorityEnum.ALTA));

        
        when(taskRepository.findAll()).thenReturn(taskList);

        
        List<Task> result = taskService.findAll();

        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(taskList.size(), result.size());
    }

    @Test
    void testCreateLivreTask() {

        DTOCreateTask taskDto = new DTOCreateTask();
        taskDto.setDescription("Test Task");
        taskDto.setType(TaskTypeEnum.LIVRE);
        taskDto.setPriority(TaskPriorityEnum.ALTA);

        Task task = new Task("Test Task", TaskTypeEnum.LIVRE, TaskPriorityEnum.ALTA);

        when(taskRepository.save(any(Task.class))).thenReturn(task);

    
        Task result = taskService.create(taskDto);


        assertNotNull(result);
        assertEquals("Test Task", result.getDescription());
        assertEquals(TaskTypeEnum.LIVRE, result.getType());
        assertEquals(TaskPriorityEnum.ALTA, result.getPriority());
    }

    @Test
    void testCreatePrazoTask() {
        
        DTOCreatePrazoTask taskDto = new DTOCreatePrazoTask();
        taskDto.setDescription("Test Task");
        taskDto.setPriority(TaskPriorityEnum.ALTA);
        taskDto.setPlannedDays(5);

        Task task = new Task("Test Task", TaskTypeEnum.PRAZO, TaskPriorityEnum.ALTA, 5);

        
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        
        Task result = taskService.createPrazoTask(taskDto);

        
        assertNotNull(result);
        assertEquals("Test Task", result.getDescription());
        assertEquals(TaskTypeEnum.PRAZO, result.getType());
        assertEquals(TaskPriorityEnum.ALTA, result.getPriority());
        assertEquals(5, result.getPlannedDays());
    }

    @Test
    void testCreateDataTask() {
        
        DTOCreateDataTask taskDto = new DTOCreateDataTask();
        taskDto.setDescription("Test Task");
        taskDto.setPriority(TaskPriorityEnum.ALTA);
        taskDto.setPlannedDate(LocalDate.now().plusDays(3));

        Task task = new Task("Test Task", TaskTypeEnum.DATA, TaskPriorityEnum.ALTA, LocalDate.now().plusDays(3));

        
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        
        Task result = taskService.createDataTask(taskDto);

        
        assertNotNull(result);
        assertEquals("Test Task", result.getDescription());
        assertEquals(TaskTypeEnum.DATA, result.getType());
        assertEquals(TaskPriorityEnum.ALTA, result.getPriority());
        assertEquals(LocalDate.now().plusDays(3), result.getPlannedDate());
    }

    @Test
    void testMarcarTarefaComoConcluida() {
        
        Long taskId = 1L;
        Task task = new Task("Test Task", TaskTypeEnum.LIVRE, TaskPriorityEnum.ALTA);
        task.setId(taskId);
        task.setCompleted(false);

        
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        
        Task result = taskService.marcarTarefaComoConcluida(taskId);

        
        assertNotNull(result);
    }

    @Test
    void testMarcarTarefaComoConcluidaNotFound() {
        
        Long taskId = 1L;

        
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        
        assertThrows(RuntimeException.class, () -> taskService.marcarTarefaComoConcluida(taskId));
    }

    @Test
    void testDelete() {
        
        Long taskId = 1L;

        
        taskService.delete(taskId);

        
        verify(taskRepository, times(1)).deleteById(taskId);
    }
}

*/