import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import '../style.css';

export const TodoList = ({ tarefas, setTarefas }) => {
  const excluirTarefa = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/task/${id}`, {
        method: "DELETE",
      });
      if (response.ok) {
        console.log("Tarefa deletada com sucesso!");
        setTarefas(tarefas.filter((tarefa) => tarefa.id !== id));
      } else {
        console.error("Erro ao deletar a tarefa:", response.statusText);
      }
    } catch (error) {
      console.error("Erro ao deletar a tarefa:", error);
    }
  };

  return (
    <div className="tasksList">
      <h2>Lista de Tarefas</h2>
      <ul>
        {tarefas.map((tarefa) => (
          <li key={tarefa.id} className="tarefa-item margin-fix ${tarefa.completed ? 'completed' : ''}">
            <span >{tarefa.description}</span>
            <button className="btn-excluir" onClick={() => excluirTarefa(tarefa.id)}>
              <FontAwesomeIcon icon={faTrash} />
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};