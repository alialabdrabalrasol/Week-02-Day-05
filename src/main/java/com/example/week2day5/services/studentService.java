package com.example.week2day5.services;

import com.example.week2day5.models.Advisor;
import com.example.week2day5.models.Classes;
import com.example.week2day5.models.Student;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class studentService {
    private ArrayList<Student>studentList=new ArrayList<>();
    private final classesService classesService;
    private final advisorService advisorService;
    public ArrayList<Student> getStudents(){
        return studentList;
    }
    public boolean addStudent(Student student){
        return studentList.add(student);
    }
    public Boolean updateStudent(Student student,String id){
        Integer curr_stu_idx=findStudentById(id);
        if(curr_stu_idx==null){
            return false;
        }
        studentList.set(curr_stu_idx,student);
        return true;

    }
    public Boolean deleteStudent(String id){
        Integer curr_stu_idx=findStudentById(id);
        System.out.println(curr_stu_idx);
        if(curr_stu_idx==null){
            return false;
        }
        studentList.remove((int)curr_stu_idx);
        return true;

    }
    public Integer findStudentById(String id){
        for (int i = 0; i <studentList.size() ; i++) {
            if(studentList.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    public Boolean addStudentToClass(String stu_id,String class_id){
        Student curr_stu=studentList.get(findStudentById(stu_id));
        Classes curr_class=classesService.getCourse().get(classesService.findClassById(class_id));
        if(curr_stu==null |curr_class==null){
            return false;
        }

        return curr_stu.getClassList().add(curr_class);
    }
    public Boolean changeMajor(String stu_id,String new_major){
        Student curr_stu=studentList.get(findStudentById(stu_id));
        if(curr_stu==null){
            return false;
        }
        curr_stu.setMajor(new_major);
        curr_stu.setClassList(new ArrayList<>());
        return true;
    }
    public ArrayList<Student> getClassStudents(String class_id){
        Classes target_class=classesService.getCourse().get(classesService.findClassById(class_id));
        if(target_class==null){
            return null;
        }
       ArrayList<Student>filtered_students=new ArrayList<>();
        for (Student curr_stu:studentList) {
            for (Classes curr_class:curr_stu.getClassList()
                 ) {
                if(target_class.getId().equals(curr_class.getId())){
                    filtered_students.add(curr_stu);
                }

            }
        }



        return filtered_students;
    }

    public ArrayList<Student> getAdvisorStudents(String adv_id){
        Advisor curr_adv=advisorService.getAdvisors().get(advisorService.findAdvisorById(adv_id));
       if(curr_adv==null){
           return null;
       }
        ArrayList<Student>filtered_students=new ArrayList<>();
        for (Student curr_stu:studentList) {
           if(curr_stu.getAdvisorName().equals(curr_adv.getName())){
               filtered_students.add(curr_stu);
           }
        }
    return filtered_students;
    }

}
