package com.example.unit_testing.service;

import com.example.unit_testing.dto.StudentDTO;
import com.example.unit_testing.mapper.StudentMapper;
import com.example.unit_testing.model.Student;
import com.example.unit_testing.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;


    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        var student = studentMapper.toStudent(studentDTO);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentDTO(savedStudent);
    }

    public List<StudentDTO> findAllStudents() {
        return studentRepository.findAll()
                .stream().map(studentMapper::toStudentDTO).collect(Collectors.toList());
    }

    public StudentDTO findStudentById(Integer id) {
        return studentRepository.findById(id).map(studentMapper::toStudentDTO).orElse(null);
    }

    public List<StudentDTO> findStudentsByName(String name) {
        return studentRepository.findAllByFirstNameContaining(name).stream().map(studentMapper::toStudentDTO).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }
}
