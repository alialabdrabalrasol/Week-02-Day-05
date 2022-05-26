package com.example.week2day5.services;

import com.example.week2day5.models.Classes;
import com.example.week2day5.models.Teacher;
import com.example.week2day5.services.classesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class teacherService {
    private ArrayList<Teacher>teacherList=new ArrayList<>();
    private final classesService classesService;

        public ArrayList<Teacher> getTeachers(){
        return teacherList;
    }
    public boolean addTeacher(Teacher teacher){
        return teacherList.add(teacher);
    }
    public Boolean updateTeacher(Teacher teacher,String id){
        Integer curr_tech_idx=findTeacherById(id);
        if(curr_tech_idx==null){
            return false;
        }
        teacherList.set(curr_tech_idx,teacher);
        return true;

    }
    public Boolean deleteTeacher(String id){
        Integer curr_tech_idx=findTeacherById(id);
        if(curr_tech_idx==null){
            return false;
        }
        teacherList.remove((int)curr_tech_idx);
        return true;

    }
    public Integer findTeacherById(String id){
        for (int i = 0; i <teacherList.size() ; i++) {
            if(teacherList.get(i).getId().equals(id)){
                return i;
            }

        }
        return null;
    }

    public Boolean addTeacherToClass(String tech_id,String class_id){
        Teacher curr_teacher=teacherList.get(findTeacherById(tech_id));
        Classes curr_class=classesService.getCourse().get(classesService.findClassById(class_id));
        if(curr_teacher==null |curr_class==null){
            return false;
        }


        return curr_teacher.getClassList().add(curr_class);

    }
    public String getClassTeacher(String class_id){
        Classes curr_class=classesService.getCourse().get(classesService.findClassById(class_id));
        if(curr_class==null){
            return null;
        }
        //search through teachers -- check the class id of each class that the teacher teaches
        for (Teacher curr_tech:teacherList) {

            for(int j=0;j<curr_tech.getClassList().size();j++){
                if(curr_tech.getClassList().get(j).getId().equals(class_id)){
                    return curr_tech.getName();
                }
            }
        }


        return null;
    }


}
