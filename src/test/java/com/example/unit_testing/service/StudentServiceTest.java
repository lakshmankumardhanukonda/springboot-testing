package com.example.unit_testing.service;

import com.example.unit_testing.dto.StudentDTO;
import com.example.unit_testing.mapper.StudentMapper;
import com.example.unit_testing.model.Student;
import com.example.unit_testing.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_student() {
        StudentDTO studentDTO = new StudentDTO("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com");
        Student student = new Student("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com");
        Student savedStudent = new Student("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com");
        savedStudent.setId(1);

        Mockito.when(studentMapper.toStudent(studentDTO)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentDTO(savedStudent)).thenReturn(new StudentDTO("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com"));

        StudentDTO responseDTO = studentService.saveStudent(studentDTO);

        assertEquals(studentDTO.getFirstName(), responseDTO.getFirstName());
        assertEquals(studentDTO.getLastName(), responseDTO.getLastName());
        assertEquals(studentDTO.getEmail(), responseDTO.getEmail());
        assertEquals(studentDTO.getDepartment(), responseDTO.getDepartment());

        Mockito.verify(studentMapper, Mockito.times(1)).toStudent(studentDTO);
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentDTO(savedStudent);
    }

    @Test
    public void should_return_all_students() {
        List<Student> students = new ArrayList<>();
        Student student = new Student("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com");
        students.add(student);

        Mockito.when(studentRepository.findAll()).thenReturn(students);
        Mockito.when(studentMapper.toStudentDTO(Mockito.any(Student.class))).thenReturn(new StudentDTO("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com"));

        List<StudentDTO> studentDTOS = studentService.findAllStudents();

        Assertions.assertEquals(students.size(),studentDTOS.size());
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {
        Integer studentId = 1;
        Student student = new Student("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com");

        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentDTO(Mockito.any(Student.class))).thenReturn(new StudentDTO("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com"));

        StudentDTO actual = studentService.findStudentById(studentId);

        assertEquals(student.getFirstName(), actual.getFirstName());
        assertEquals(student.getLastName(), actual.getLastName());
        assertEquals(student.getEmail(), actual.getEmail());
        assertEquals(student.getDepartment(), actual.getDepartment());

        Mockito.verify(studentRepository, Mockito.times(1)).findById(studentId);
    }

    @Test
    public void should_find_student_by_name() {
        String studentName = "sai";
        List<Student> students = new ArrayList<>();
        Student student = new Student("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com");
        students.add(student);

        Mockito.when(studentRepository.findAllByFirstNameContaining(studentName)).thenReturn(students);
        Mockito.when(studentMapper.toStudentDTO(Mockito.any(Student.class))).thenReturn(new StudentDTO("sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com"));

        List<StudentDTO> responseDTO = studentService.findStudentsByName(studentName);

        Assertions.assertEquals(students.size(),responseDTO.size());
        Mockito.verify(studentRepository, Mockito.times(1)).findAllByFirstNameContaining(studentName);
    }
}