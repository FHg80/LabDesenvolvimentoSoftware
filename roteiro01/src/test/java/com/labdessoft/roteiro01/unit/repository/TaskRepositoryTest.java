package com.labdessoft.roteiro01.unit.repository;

import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** 

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        // Inserir alguns dados de teste antes de cada teste
        Task task1 = new Task("Tarefa 1");
        Task task2 = new Task("Tarefa 2");
        taskRepository.save(task1);
        taskRepository.save(task2);
    }

    @AfterEach
    public void tearDown() {
        // Limpar os dados de teste após cada teste
        taskRepository.deleteAll();
    }

    @Test
    public void testFindAll() {
        // Verifica se duas tarefas foram inseridas corretamente
        assertEquals(2, taskRepository.findAll().size());
    }

    @Test
    public void testFindById() {
        // Verifica se uma tarefa específica pode ser encontrada por ID
        assertTrue(taskRepository.findById(1L).isPresent());
        assertEquals("Tarefa 1", taskRepository.findById(1L).get().getDescription());
    }

    @Test
    public void testSave() {
        // Testa se uma nova tarefa pode ser salva corretamente
        Task newTask = new Task("Nova Tarefa");
        Task savedTask = taskRepository.save(newTask);
        assertTrue(taskRepository.findById(savedTask.getId()).isPresent());
        assertEquals("Nova Tarefa", taskRepository.findById(savedTask.getId()).get().getDescription());
    }

    @Test
    public void testUpdate() {
        // Testa se uma tarefa existente pode ser atualizada corretamente
        Task taskToUpdate = taskRepository.findById(2L).orElse(null);
        if (taskToUpdate != null) {
            taskToUpdate.setDescription("Tarefa 2 Atualizada");
            taskToUpdate.setCompleted(true);
            Task updatedTask = taskRepository.save(taskToUpdate);
            assertEquals("Tarefa 2 Atualizada", updatedTask.getDescription());
        }
    }

    @Test
    public void testDelete() {
        // Testa se uma tarefa pode ser excluída corretamente
        taskRepository.deleteById(1L);
        assertFalse(taskRepository.existsById(1L));
    }
}

*/
