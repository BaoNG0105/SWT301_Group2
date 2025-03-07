import React, { useState, useEffect } from 'react';
import { Edit, Trash } from 'lucide-react';
import { Alert } from 'antd';

const Item = ({ task, index, deleteTask, toggleTask, updateTask }) => {
  const [editingText, setEditingText] = useState(task.text);
  const [editingTask, setEditingTask] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    setEditingText(task.text);
  }, [task.text]);

  const handleSaveTask = () => {
    if (editingText.trim() === '' || editingText.startsWith(' ')) {
      setError('Todo cannot be empty or start with a space');
      return;
    }
    updateTask(index, editingText);
    setEditingTask(false);
    setError('');
  };

  return (
    <li className="flex items-center justify-between bg-gray-100 p-4 rounded shadow">
      <div className="flex items-center">
        <input
          type="checkbox"
          className="mr-2"
          checked={task.completed}
          onChange={() => toggleTask(index)}
          data-testid={`toggle-${index}`}
        />
        {editingTask ? (
          <input
            type="text"
            className="border-1 border-gray-300 p-2 pr-25 mr-2 flex-1 rounded"
            value={editingText}
            onChange={(e) => setEditingText(e.target.value)}
            onKeyPress={(e) => {
              if (e.key === 'Enter') {
                handleSaveTask();
              }
            }}
            data-testid={`edit-input-${index}`}
          />
        ) : (
          <span className={`flex-1 text-lg ${task.completed ? 'line-through text-gray-500' : ''}`}>
            {task.text}
          </span>
        )}
      </div>
      <div className="flex items-center">
        {editingTask ? (
          <>
            <button 
              className="bg-green-500 text-white p-2 mr-2 rounded hover:bg-green-600" 
              onClick={handleSaveTask}
              data-testid={`save-button-${index}`}
            >
              Save
            </button>
            {error && <Alert message={error} type="error" showIcon className="mt-2" />}
          </>
        ) : (
          <button 
            className="bg-yellow-500 text-white p-2 mr-2 rounded hover:bg-yellow-600" 
            onClick={() => setEditingTask(true)}
            data-testid={`edit-button-${index}`}
          >
            <Edit className="h-5 w-5" />
          </button>
        )}
        <button 
          className="bg-red-500 text-white p-2 rounded hover:bg-red-600" 
          onClick={() => deleteTask(index)}
          data-testid={`delete-button-${index}`}
        >
          <Trash className="h-5 w-5" />
        </button>
      </div>
    </li>
  );
};

export default Item;
