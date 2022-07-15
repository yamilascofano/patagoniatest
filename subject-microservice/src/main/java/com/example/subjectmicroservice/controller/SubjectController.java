package com.example.subjectmicroservice.controller;

import com.example.entity.Subject;
import com.example.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/list")
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Subject findById(@PathVariable Long id) throws Exception {
        return subjectService.findById(id);
    }

    @PostMapping()
    public Subject saveSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @DeleteMapping("/deleteSubject/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }

    @PutMapping("/putSubject/{id}")
    public void putSubject(@PathVariable Long id, @RequestBody Subject subject) throws IllegalStateException {
        subjectService.update(id, subject);
    }
}
