import React, { useState } from "react";

function TaskPrazoForm({ addTarefa }) {
  const [descricao, setDescricao] = useState("");
  const [prazo, setPrazo] = useState("");
  const [prioridade, setPrioridade] = useState("AUSENTE");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (descricao && prazo > 0 && prioridade) {
      const novaTarefa = {
        description: descricao,
        priority: prioridade,
        plannedDays: parseInt(prazo, 10),
      };

      const response = await fetch("http://localhost:8080/api/task/create/prazo", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(novaTarefa),
      });

      if (response.ok) {
        setDescricao("");
        setPrazo("");
        setPrioridade("AUSENTE");
        const data = await response.json();
        addTarefa(data);

      } else {
        console.error("Erro ao criar a Tarefa:", response.statusText);
      }
    } else {
      alert("Todos os campos devem ser preenchidos corretamente");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="input-form">
        <p>Descrição da tarefa:</p>
        <input
          type="text"
          placeholder="Descrição"
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
          required
        />
      </div>
      <div className="input-form">
        <p>Meta de dias necessários para conclusão:</p>
        <input
          type="number"
          placeholder="Prazo de conclusão (em dias)"
          value={prazo}
          onChange={(e) => setPrazo(e.target.value)}
          min="1"
          required
        />
      </div>
      <div className="input-form">
        <p>Prioridade da tarefa:</p>
        <select
          value={prioridade}
          onChange={(e) => setPrioridade(e.target.value)}
          required
        >
          <option value="ALTA">Alta</option>
          <option value="MEDIA">Média</option>
          <option value="BAIXA">Baixa</option>
          <option value="AUSENTE">Ausente</option>
        </select>
      </div>
      <button className="button-criar-tarefa" type="submit">
        Criar tarefa
      </button>
    </form>
  );
}

export default TaskPrazoForm;