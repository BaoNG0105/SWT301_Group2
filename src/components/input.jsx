import React, { useState } from 'react';
import { Alert } from 'antd';

const Input = ({ addTask }) => {
  const [newTask, setNewTask] = useState('');
  const [error, setError] = useState('');

  const handleAddTask = () => {
    if (newTask.trim() === '' || newTask.startsWith(' ')) {
      setError('Todo cannot be empty or start with a space');
      return;
    }
    addTask(newTask);
    setError('');
    setNewTask('');
  };

  return (
    <div className="mb-4 flex flex-col">
      <div className="flex">
        <input
          type="text"
          className="border-1 border-gray-300 p-2 flex-1 rounded-l mr-2"
          placeholder="Add new todo..."
          value={newTask}
          onChange={(e) => setNewTask(e.target.value)}
          onKeyPress={(e) => {
            if (e.key === 'Enter') {
              handleAddTask();
            }
          }}
          data-testid="todo-input"
        />
        <button 
          className="bg-blue-500 text-white p-2 rounded hover:bg-blue-600" 
          onClick={handleAddTask}
          data-testid="add-button"
        >
          + Add
        </button>
      </div>
      {error && <Alert message={error} type="error" showIcon className="mt-2" />}
    </div>
  );
};

export default Input;
