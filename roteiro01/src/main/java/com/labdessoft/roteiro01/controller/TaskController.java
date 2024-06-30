package com.labdessoft.roteiro01.controller;

import com.labdessoft.roteiro01.dto.DTOCreateTask;
import com.labdessoft.roteiro01.dto.DTOCreateDataTask;
import com.labdessoft.roteiro01.dto.DTOCreatePrazoTask;
import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    @Operation(summary = "Lista todas as tarefas da lista de tarefas")
    public ResponseEntity<List<Task>> listAll() {
        
        try {
            List<Task> taskList = new ArrayList<Task>();
            taskService.findAll().forEach(taskList::add);
            if (taskList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/livre")
    @Operation(summary = "Cria uma nova tarefa do Tipo Livre")
    public ResponseEntity<Task> create(@RequestBody DTOCreateTask taskDto) {
        try {
            Task taskCriada = taskService.create(taskDto.getDescription());
            return new ResponseEntity<>(taskCriada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/prazo")
    @Operation(summary = "Cria um prazo para uma nova tarefa")
    public ResponseEntity<Task> createPrazoTask(@RequestBody DTOCreatePrazoTask taskDto) {
        try {
            Task taskCriada = taskService.createPrazoTask(taskDto);
            return new ResponseEntity<>(taskCriada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create/data")
    @Operation(summary = "Cria uma data para uma nova tarefa")
    public ResponseEntity<Task> createDataTask(@RequestBody DTOCreateDataTask taskDto) {
        try {
            Task taskCriada = taskService.createDataTask(taskDto);
            return new ResponseEntity<>(taskCriada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Marca a tarefa com o ID que foi passado, como concluída")
    public ResponseEntity<Task> marcarTarefaComoConcluida(@PathVariable Long id) {
        try {
            Task updatedTask = taskService.marcarTarefaComoConcluida(id);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta a tarefa com o ID que foi informado")
    public void delete(@PathVariable Long id) {
        try {
            taskService.delete(id);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
