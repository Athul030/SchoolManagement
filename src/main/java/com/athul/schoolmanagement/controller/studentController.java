package com.athul.schoolmanagement.controller;

import com.athul.schoolmanagement.dto.StudentDTO;
import com.athul.schoolmanagement.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class studentController {

    private final StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        StudentDTO savedStudent = studentService.addStudent(studentDTO);
        //studentDTO is returned if need any data of stored student
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);

    }

    //considering the id, name or class is coming as a search word
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<?> searchStudents(@PathVariable String searchTerm) {
        try {
            Long id = Long.parseLong(searchTerm);
            StudentDTO studentDTO = studentService.getStudentByID(id);
            return ResponseEntity.ok(studentDTO);
        } catch (NumberFormatException ex) {
            List<StudentDTO> studentsByName = studentService.getStudentByName(searchTerm);
            //return with students with name
            if (!studentsByName.isEmpty()) {
                return ResponseEntity.ok(studentsByName);
            }
            List<StudentDTO> studentsByClass = studentService.getStudentByClass(searchTerm);
            if (!studentsByClass.isEmpty()){
                return ResponseEntity.ok(studentsByClass);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No students found with the given search term");

        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@Valid @PathVariable Long id, @RequestBody StudentDTO studentDTO, BindingResult result){

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        StudentDTO updatedStudent = studentService.updateStudentDetails(id,studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }


}
