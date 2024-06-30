package com.labdessoft.roteiro01.dto;

import com.labdessoft.roteiro01.entity.TaskPriorityEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOCreatePrazoTask {
    
    @NotBlank(message = "A descrição da tarefa é obrigatória")
    @Size(min = 10, message = "A descrição da tarefa deve possuir pelo menos 10 caracteres")
    private String description;
    
    @NotNull(message = "A prioridade da tarefa é obrigatória, ou seja, não pode ser nula")
    private TaskPriorityEnum priority;
    
    @Positive(message = "O prazo previsto de conclusão deve ser um número positivo")
    private Integer plannedDays;
}
