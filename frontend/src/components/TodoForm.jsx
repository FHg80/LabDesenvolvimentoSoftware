import React, { useState } from "react";
import TaskLivreForm from "./TaskTypeforms/taskLivreForm";
import TaskDataForm from "./TaskTypeforms/taskDataForm";
import TaskPrazoForm from "./TaskTypeforms/taskPrazoForm";
import '../style.css';


function TodoForm({ addTarefa }) {

    const [formType, setFormType] = useState("livre");

    const renderForm = () => {
        switch (formType) {
            case "livre":
                return <div><TaskLivreForm addTarefa={addTarefa} /></div>;
            case "data":
                return <div><TaskDataForm addTarefa={addTarefa} /></div>;
            case "prazo":
                return <div><TaskPrazoForm addTarefa={addTarefa} /></div>;
            default:
                return null;
        }
    };

    return (
        <div className="TodoForm">
            <h1>Aplicação To Do List</h1>

            <h2>Criar uma nova tarefa:</h2>
            
            <div>
                <h4>Selecione o tipo:</h4>
                <button className="btn-typeTask" onClick={() => setFormType("livre")}>Livre</button>
                <button className="btn-typeTask" onClick={() => setFormType("data")}>Data</button>
                <button className="btn-typeTask" onClick={() => setFormType("prazo")}>Prazo</button>
            </div>

            {renderForm()}

        </div>
    );

};

export default TodoForm;