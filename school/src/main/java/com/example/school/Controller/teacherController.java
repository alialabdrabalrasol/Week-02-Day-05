package com.example.school.Controller;


import com.example.school.Models.ResponseApi;
import com.example.school.Models.Teacher;

import com.example.school.Service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class teacherController {
    private final TeacherService teacherService;
    @GetMapping
    public ResponseEntity getTeachers() {
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PostMapping
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg, 400));
        }
        Boolean isTeacherAdded = teacherService.addTeacher(teacher);
        if (!isTeacherAdded) {
            return ResponseEntity.status(400).body(new ResponseApi("Teacher not added", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Teacher has successfully been added", 201));
    }

    @PutMapping("{tech_id}")
    public ResponseEntity updateTeacher(@RequestBody @Valid Teacher teacher, @PathVariable String adv_id, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg, 400));
        }
        Boolean isTeacherUpdated = teacherService.updateTeacher(teacher, adv_id);
        if (!isTeacherUpdated) {
            return ResponseEntity.status(400).body(new ResponseApi("Teacher not updated", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Teacher has successfully been updated", 201));

    }

    @DeleteMapping()
    public ResponseEntity deleteTeacher(@RequestParam String adv_id) {
        Boolean isTeacherDelete = teacherService.deleteTeacher(adv_id);
        if (!isTeacherDelete) {
            return ResponseEntity.status(400).body(new ResponseApi("Teacher not deleted", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Teacher has successfully been deleted", 201));
    }
    @PutMapping("class/add")
    public ResponseEntity addTeacherToClass(@RequestParam String tech_id,@RequestParam String class_id){
        Boolean isTeacherAdded=teacherService.addTeacherToClass(tech_id,class_id);
        if(!isTeacherAdded){
            return ResponseEntity.status(400).body(new ResponseApi("Teacher not deleted", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Teacher has successfully been added to the class", 201));

    }
    @GetMapping("class")
    public ResponseEntity getClassTeacher(String class_id){
        String target_teacher= teacherService.getClassTeacher(class_id);
        if(target_teacher==null){
            return ResponseEntity.status(400).body(new ResponseApi("Teacher not deleted", 400));

        }
        return ResponseEntity.status(201).body(target_teacher);

    }
}
