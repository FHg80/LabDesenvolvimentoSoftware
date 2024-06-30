import React, { useState, useEffect }  from "react";
import TodoForm from "./TodoForm";
import { TodoList } from "./TodoList";

function TodoWrapper() {
  const [tarefas, setTarefas] = useState([]);

  useEffect(() => {
    listarTarefas();
  }, []);

  const listarTarefas = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/task");
      if (response.ok) {
        const data = await response.json();
        setTarefas(data);
      } else {
        console.error("Erro ao buscar as tarefas:", response.statusText);
      }
    } catch (error) {
      console.error("Erro ao buscar as tarefas:", error);
    }
  };

  const addTarefa = (novaTarefa) => {
    setTarefas([...tarefas, novaTarefa]);
  };

  return (
    <div className="app-container">
      <TodoForm addTarefa={addTarefa} />
      <TodoList tarefas={tarefas} setTarefas={setTarefas} />
    </div>
  );
}

export default TodoWrapper;