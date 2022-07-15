package com.example.patagoniatest.controller;

import com.example.patagoniatest.entity.Student;
import com.example.patagoniatest.model.Subject;
import com.example.patagoniatest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class StudentController {

    @Autowired
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getClients() {
        return studentService.getStudents();
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable Long id) throws Exception {
        return studentService.findById(id);
    }

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/putStudent/{id}")
    public void putClient(@PathVariable Long id, @RequestBody Student student) throws IllegalStateException {
        studentService.update(id, student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


    @PostMapping("/addSubject/{studentId}")
    public Subject saveStudent(@PathVariable("studentId") Long studentId, @RequestBody Subject subject) throws Exception {
        return studentService.saveSubject(studentId, subject);
    }
}
