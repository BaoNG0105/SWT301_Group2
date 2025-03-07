import './App.css';
import { useState } from 'react';
import Input from './components/input';
import Item from './components/item';

function App() {
  const [tasks, setTasks] = useState([]);

  const addTask = (newTask) => {
    setTasks([...tasks, { text: newTask, completed: false }]);
  };

  const deleteTask = (index) => {
    const newTasks = tasks.filter((_, i) => i !== index);
    setTasks(newTasks);
  };

  const toggleTask = (index) => {
    const newTasks = tasks.map((task, i) => 
      i === index ? { ...task, completed: !task.completed } : task
    );
    setTasks(newTasks);
  };

  const updateTask = (index, newText) => {
    const newTasks = tasks.map((task, i) => 
      i === index ? { ...task, text: newText } : task
    );
    setTasks(newTasks);
  };

  return (
    <div className="container mx-auto p-6 max-w-lg bg-white shadow-lg rounded-lg">
      <h1 className="text-3xl font-bold mb-6 text-center">Todo List</h1>

      <Input addTask={addTask} />

      <ul className="space-y-2">
        {tasks.map((task, index) => (
          <Item 
            key={index} 
            task={task} 
            index={index} 
            deleteTask={deleteTask} 
            toggleTask={toggleTask} 
            updateTask={updateTask}
          />
        ))}
      </ul>
    </div>
  );
}

export default App;