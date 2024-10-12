package com.example.unit_testing.mapper;

import com.example.unit_testing.dto.StudentDTO;
import com.example.unit_testing.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDTO studentDTO) {
        if (studentDTO == null) {
            throw new NullPointerException("StudentDTO should not be null");
        }
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setDepartment(studentDTO.getDepartment());
        student.setEmail(studentDTO.getEmail());
        return student;
    }

    public StudentDTO toStudentDTO(Student student) {
        if (student == null) {
            throw new NullPointerException("Student should not be null");
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setDepartment(student.getDepartment());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }
}
