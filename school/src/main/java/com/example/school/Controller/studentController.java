package com.example.school.Controller;



import com.example.school.Models.ResponseApi;
import com.example.school.Models.Student;
import com.example.school.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class studentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg, 400));
        }
        Boolean isStudentAdded = studentService.addStudent(student);
        if (!isStudentAdded) {
            return ResponseEntity.status(400).body(new ResponseApi("Student not added", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Student has successfully been added", 201));
    }

    @PutMapping("{stu_id}")
    public ResponseEntity updateStudent(@RequestBody @Valid Student student, @PathVariable String stu_id, Errors error) {
        if (error.hasFieldErrors()) {
            String err_msg = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg, 400));
        }
        Boolean isStudentUpdated = studentService.updateStudent(student, stu_id);
        if (!isStudentUpdated) {
            return ResponseEntity.status(400).body(new ResponseApi("Student not updated", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Student has successfully been updated", 201));

    }

    @DeleteMapping()
    public ResponseEntity deleteStudent(@RequestParam String stu_id) {
        Boolean isStudentDelete = studentService.deleteStudent(stu_id);
        if (!isStudentDelete) {
            return ResponseEntity.status(400).body(new ResponseApi("Student not found", 400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Student has successfully been deleted", 201));
    }
    @PutMapping("class/add")
    public ResponseEntity addStudentToClass(@RequestParam String stu_id,@RequestParam String class_id){
        Boolean isStudentAdded=studentService.addStudentToClass(stu_id,class_id);
        if(!isStudentAdded){
            return ResponseEntity.status(400).body(new ResponseApi("Student not added", 400));
        }
        return  ResponseEntity.status(201).body(new ResponseApi("Student has successfully been added", 201));
    }
    @PutMapping("major/change")
    public ResponseEntity changeMajor(@RequestParam String stu_id,@RequestParam String new_major ){
    boolean isMajorChange= studentService.changeMajor(stu_id,new_major);

        if(!isMajorChange){
            return ResponseEntity.status(400).body(new ResponseApi("Major not changed", 400));
        }
        return  ResponseEntity.status(201).body(new ResponseApi("Major has successfully been added", 201));
    }
    @GetMapping("class/students")
   public ResponseEntity getClassStudents(@RequestParam String class_id){
        ArrayList<Student>filtered_stulist=studentService.getClassStudents(class_id);
        if(filtered_stulist==null){
            return ResponseEntity.status(400).body(new ResponseApi("Major not change", 400));
        }
        return ResponseEntity.status(200).body(filtered_stulist);
    }
    @GetMapping("advisor/students")
    public ResponseEntity getAdvisorStudents(@RequestParam String adv_id){
        ArrayList<Student> filtered_stulist=studentService.getAdvisorStudents(adv_id);
        if(filtered_stulist==null){
            return ResponseEntity.status(400).body(new ResponseApi("No advisor found", 400));
        }
        return ResponseEntity.status(200).body(filtered_stulist);
    }
}