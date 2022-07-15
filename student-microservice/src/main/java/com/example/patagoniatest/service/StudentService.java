package com.example.patagoniatest.service;

import com.example.patagoniatest.entity.Student;
import com.example.patagoniatest.feignclients.SubjectFeignClient;
import com.example.patagoniatest.model.Subject;
import com.example.patagoniatest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectFeignClient subjectFeignClient;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("No existe estudiante "));
        studentRepository.delete(student);
    }

    @Transactional
    public void update(Long id, Student student) throws IllegalStateException {
        Student student1 = new Student();
        student1.setId(id);
        if (student1 == null)
            throw new IllegalStateException("El estudiante no existe");
        if (!student1.getIncome().equals(student.getIncome()))
            student1.setIncome(student.getIncome());
        if (!student1.getFullName().equals(student.getFullName()))
            student1.setFullName(student.getFullName());
        studentRepository.save(student1);

    }

    public Student findById(Long id) throws Exception {
        return studentRepository.findById(id).orElseThrow(() -> new Exception("No existe " + id));
    }


    public Subject saveSubject(Long studentId, Subject subject) throws Exception {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new Exception("No existe estudiante " + studentId));
        if (student != null)
            subject.setStudentId(studentId);
        return subjectFeignClient.saveSubject(subject);
    }
    public List<Subject> getSubjects(Long studentId){
        return (List<Subject>) subjectFeignClient.finByStudentId(studentId).getBody();
    }
}