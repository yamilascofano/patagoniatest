package com.example.patagoniatest.feignclients;

import com.example.patagoniatest.model.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "subject-microservice")
@RequestMapping("/subjects")
public interface SubjectFeignClient {

    @PostMapping()
    Subject saveSubject(@RequestBody Subject subject);


    @GetMapping("subjectbystudent/{studentid}")
    public ResponseEntity<?> finByStudentId(@PathVariable("studentid") Long studentId);

}
