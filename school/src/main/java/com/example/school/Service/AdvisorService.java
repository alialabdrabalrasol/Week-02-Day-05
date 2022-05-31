package com.example.school.Service;

import com.example.school.Models.Advisor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdvisorService {
    private ArrayList<Advisor>advisorList=new ArrayList<>();
    public ArrayList<Advisor> getAdvisors(){
        return advisorList;
    }
    public boolean addAdvisor(Advisor advisor){
        return advisorList.add(advisor);
    }
    public Boolean updateAdvisor(Advisor advisor,String id){
        Integer Addv=findAdvisorById(id);
        if(Addv==null){
            return false;
        }
        advisorList.set(Addv,advisor);
        return true;

    }
    public Boolean deleteAdvisor(String id){
        Integer curr_stu_idx=findAdvisorById(id);
        if(curr_stu_idx==null){
            return false;
        }
        advisorList.remove((int)curr_stu_idx);
        return true;

    }
    public Integer findAdvisorById(String id){
        for (int i = 0; i <advisorList.size() ; i++) {
            if(advisorList.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }

}
