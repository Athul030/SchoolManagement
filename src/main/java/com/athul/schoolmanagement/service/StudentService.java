package com.athul.schoolmanagement.service;

import com.athul.schoolmanagement.dto.StudentDTO;
import com.athul.schoolmanagement.exceptions.ResourceNotFoundException;
import com.athul.schoolmanagement.model.Student;
import com.athul.schoolmanagement.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final ModelMapper mapper;


    public StudentDTO addStudent(StudentDTO studentDTO){
        Student studentDataReceived = mapper.map(studentDTO, Student.class);
        Student newStudent = studentRepo.save(studentDataReceived);
        return mapper.map(newStudent, StudentDTO.class);
    }

    public StudentDTO getStudentByID(Long id) {
        Student getStudent =  studentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(id));
        return mapper.map(getStudent, StudentDTO.class);
    }

    public List<StudentDTO> getStudentByName(String name) {
        List<Student> studentsByName = studentRepo.findByNameContaining(name);
        return studentsByName.stream().map(x->mapper.map(x, StudentDTO.class)).toList();
    }

    public List<StudentDTO> getStudentByClass(String className) {
        List<Student> studentsByClassName = studentRepo.findByClassNameContaining(className);
        return studentsByClassName.stream().map(x->mapper.map(x, StudentDTO.class)).toList();
    }

    public StudentDTO updateStudentDetails(Long id, StudentDTO studentDTO) {
        Student existingStudent  = studentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(id));

        //dont change values if null values are present in respective field
        if (studentDTO.getName() != null) {
            existingStudent.setName(studentDTO.getName());
        }
        if (studentDTO.getDateOfBirth() != null) {
            existingStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        }
        if (studentDTO.getJoiningDate() != null) {
            existingStudent.setJoiningDate(studentDTO.getJoiningDate());
        }
        if (studentDTO.getClassName() != null) {
            existingStudent.setClassName(studentDTO.getClassName());
        }

        Student updatedStudent = studentRepo.save(existingStudent);
        return mapper.map(updatedStudent, StudentDTO.class);

    }
}
