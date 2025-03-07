# Todo List Application

This project is a Todo List application built using **React** and **Vite**. It leverages modern web development practices to provide a fast and responsive user experience. The application supports Hot Module Replacement (HMR) for seamless development and includes ESLint for code quality.

## Technologies Used

- **React**: A JavaScript library for building user interfaces, allowing for the creation of reusable UI components.
- **Vite**: A build tool that provides a fast development environment and optimized production builds.
- **Ant Design**: A design system that provides a set of high-quality React components for building rich user interfaces.
- **Lucide Icons**: A collection of open-source icons for use in the application.

## Features

- **Add Tasks**: Users can add new tasks to their todo list. Input validation ensures that tasks cannot be empty or start with a space.
- **Delete Tasks**: Users can remove tasks from the list.
- **Toggle Task Completion**: Users can mark tasks as completed or not, visually indicating their status.
- **Edit Tasks**: Users can edit existing tasks. Input validation is also applied during editing to ensure task integrity.
- **Responsive Design**: The application is designed to be responsive, providing a good user experience on both desktop and mobile devices.

## Key Implementation Details

1. **State Management**: 
   - Uses React's `useState` hook.
   - Maintains todos array, new todo input, and edit states.

2. **UI Components**: 
   - Uses Ant Design for alerts.
   - Uses Lucide React for icons.
   - Implements a clean, modern design with Tailwind CSS.

3. **Testing Support**: 
   - Includes `data-testid` attributes for easy testing.
   - Separable components for unit testing.
   - Clear state management for testing state changes.

## Testing Scope

Here's what students can test:
1. **Component Rendering**: 
   - Initial render of the todo list.
   - Rendering of todo items.
   - Presence of input field and buttons.

2. **User Interactions**: 
   - Adding new todos.
   - Marking todos as complete.
   - Editing todos.
   - Deleting todos.
   - Input validation.

3. **State Management**: 
   - Todo list updates.
   - Edit mode state.
   - Alert state.

4. **Edge Cases**: 
   - Empty input handling.
   - Editing validation.
   - Multiple todos management.

## Conclusion

This Todo List application serves as a practical example of using React with Vite, showcasing essential features for task management while adhering to modern development standards.

## Reference

Video demo for the todo list application: https://youtu.be/kfz6csltDX4?si=S4rY_-4fnGzYjX9R
