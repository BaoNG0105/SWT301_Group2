package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student1, student2;

    @BeforeEach
    void setUp() {
        student1 = new Student("Alice", 20);
        student2 = new Student("Bob", 22);
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));

        Optional<Student> foundStudent = studentService.getStudentById(1L);

        assertTrue(foundStudent.isPresent());
        assertEquals("Alice", foundStudent.get().getName());
    }

    @Test
    void testCreateStudent() {
        when(studentRepository.save(student1)).thenReturn(student1);

        Student savedStudent = studentService.createStudent(student1);

        assertNotNull(savedStudent);
        assertEquals("Alice", savedStudent.getName());
    }

    @Test
    void testUpdateStudent_Success() {
        Student updatedStudent = new Student("Alice Updated", 22);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Student result = studentService.updateStudent(1L, updatedStudent);

        assertNotNull(result);
        assertEquals("Alice Updated", result.getName());
        assertEquals(22, result.getAge());
        verify(studentRepository, times(1)).save(student1);
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = studentService.deleteStudent(1L);

        assertTrue(isDeleted);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}
