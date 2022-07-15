package com.example.patagoniatest.feignclients;

import com.example.patagoniatest.model.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "subject-microservice")
@RequestMapping("/subjects")
public interface StudentFeignStudent {

    @PostMapping()
    Subject saveSubject(@RequestBody Subject subject);

}
