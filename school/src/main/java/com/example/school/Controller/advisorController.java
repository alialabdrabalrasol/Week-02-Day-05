package com.example.school.Controller;


import com.example.school.Models.Advisor;
import com.example.school.Models.ResponseApi;
import com.example.school.Service.AdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/advisor")
@RequiredArgsConstructor
public class advisorController {
    private final AdvisorService advisorService;

    @GetMapping
    public ResponseEntity getAdvisors() {
        return ResponseEntity.status(200).body(advisorService.getAdvisors());
    }

    @PostMapping
    public ResponseEntity addAdvisor(@RequestBody @Valid Advisor advisor, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg, 400));
        }
        Boolean isAdvisorAdded = advisorService.addAdvisor(advisor);
        if (!isAdvisorAdded) {
            return ResponseEntity.status(400).body(new ResponseApi("Advisor not added", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Advisor has successfully been added", 201));
    }

    @PutMapping("{adv_id}")
    public ResponseEntity updateAdvisor(@RequestBody @Valid Advisor advisor, @PathVariable String adv_id, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg, 400));
        }
        Boolean isAdvisorUpdated = advisorService.updateAdvisor(advisor, adv_id);
        if (!isAdvisorUpdated) {
            return ResponseEntity.status(400).body(new ResponseApi("Advisor not updated", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Advisor has successfully been updated", 201));

    }

    @DeleteMapping()
    public ResponseEntity deleteAdvisor(@RequestParam String adv_id) {
        Boolean isAdvisorDelete = advisorService.deleteAdvisor(adv_id);
        if (!isAdvisorDelete) {
            return ResponseEntity.status(400).body(new ResponseApi("Advisor not deleted", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Advisor has successfully been deleted", 201));
    }

}
