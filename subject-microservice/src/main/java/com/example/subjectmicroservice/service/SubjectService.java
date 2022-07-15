package com.example.subjectmicroservice.service;

import com.example.entity.Subject;
import com.example.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new IllegalStateException("No existe materia "));
        subjectRepository.delete(subject);
    }

    @Transactional
    public void update(Long id, Subject subject) throws IllegalStateException {
        Subject subject1 = subjectRepository.findById(id).orElseThrow(() -> new IllegalStateException("No existe materia "));
        subject1.setId(id);
        subject1.setAmount(subject.getAmount());
        subject1.setType(subject.getType());
        subject1.setStudentId(subject.getStudentId());
        subjectRepository.save(subject1);
    }

    public Subject findById(Long id) throws Exception {
        return subjectRepository.findById(id).orElseThrow(() -> new Exception("No existe " + id));
    }
}
