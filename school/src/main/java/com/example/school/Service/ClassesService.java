package com.example.school.Service;


import com.example.school.Models.Classes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClassesService {
    private ArrayList<Classes> classList=new ArrayList<>();

    public ArrayList<Classes> getCourse(){

        return classList;
    }
    public boolean addClass(Classes course){
        return classList.add(course);
    }
    public Boolean updateClass(Classes course,String id){
        Integer curr_class_idx=findClassById(id);
        if(curr_class_idx==null){
            return false;
        }
        classList.set(curr_class_idx,course);
        return true;

    }
    public Boolean deleteClass(String id){
        Integer curr_class_idx=findClassById(id);
        if(curr_class_idx==null){
            return false;
        }
        classList.remove((int)curr_class_idx);
        return true;

    }
    public Integer findClassById(String id){
        for (int i = 0; i <classList.size() ; i++) {
            if(classList.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }


}
