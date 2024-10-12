package com.example.unit_testing.mapper;

import com.example.unit_testing.dto.StudentDTO;
import com.example.unit_testing.model.Student;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

   private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDTOToStudent() {
        StudentDTO studentDTO = new StudentDTO(1, "sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com");
        Student student = studentMapper.toStudent(studentDTO);
        assertEquals(studentDTO.getId(), student.getId());
        assertEquals(studentDTO.getFirstName(), student.getFirstName());
        assertEquals(studentDTO.getLastName(), student.getLastName());
        assertEquals(studentDTO.getDepartment(), student.getDepartment());
        assertEquals(studentDTO.getEmail(), student.getEmail());
   }

   @Test
   public void should_throw_null_pointer_exception_if_studentDto_is_null() {
        var exception = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("StudentDTO should not be null", exception.getMessage());
    }

    @Test
    public void should_throw_null_pointer_exception_if_student_is_null() {
        var exception = assertThrows(NullPointerException.class, () -> studentMapper.toStudentDTO(null));
        assertEquals("Student should not be null", exception.getMessage());
    }

   @Test
   public void shouldMapStudentToStudentDTO() {
       Student student = new Student(1, "sai", "dhanukonda", "CSE", "sai.dhanukonda@gmail.com");
       StudentDTO studentDTO = studentMapper.toStudentDTO(student);
       assertEquals(student.getId(), studentDTO.getId());
       assertEquals(student.getFirstName(), studentDTO.getFirstName());
       assertEquals(student.getLastName(), studentDTO.getLastName());
       assertEquals(student.getDepartment(), studentDTO.getDepartment());
       assertEquals(student.getEmail(), studentDTO.getEmail());
   }

}