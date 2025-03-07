package com.example.demo;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;
    private Student student1, student2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        student1 = new Student("Alice", 20);
        student2 = new Student("Bob", 22);
    }

    /*** Test Get All Students ***/
    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(student1, student2);
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));

        verify(studentService, times(1)).getAllStudents();
    }

    /*** Test Get Student by ID ***/
    @Test
    void testGetStudentById_Success() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(Optional.of(student1));

        mockMvc.perform(get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));

        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    void testGetStudentById_NotFound() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(studentService, times(1)).getStudentById(1L);
    }

    /*** Test Create Student ***/
    @Test
    void testCreateStudent() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(student1);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Alice\", \"age\": 20}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));

        verify(studentService, times(1)).createStudent(any(Student.class));
    }

    /*** Test Update Student ***/
    @Test
    void testUpdateStudent_Success() throws Exception {
        Student updatedStudent = new Student("Alice Updated", 22);

        when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Alice Updated\", \"age\": 22}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice Updated"))
                .andExpect(jsonPath("$.age").value(22));

        verify(studentService, times(1)).updateStudent(eq(1L), any(Student.class));
    }

    @Test
    void testUpdateStudent_NotFound() throws Exception {
        when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(null);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Alice Updated\", \"age\": 22}"))
                .andExpect(status().isNotFound());

        verify(studentService, times(1)).updateStudent(eq(1L), any(Student.class));
    }

    /*** Test Delete Student ***/
    @Test
    void testDeleteStudent_Success() throws Exception {
        when(studentService.deleteStudent(1L)).thenReturn(true);

        mockMvc.perform(delete("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudent(1L);
    }

    @Test
    void testDeleteStudent_NotFound() throws Exception {
        when(studentService.deleteStudent(1L)).thenReturn(false);

        mockMvc.perform(delete("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(studentService, times(1)).deleteStudent(1L);
    }
}
