package com.example.week2day5.services;

import com.example.week2day5.models.Advisor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class advisorService {
    private ArrayList<Advisor>advisorList=new ArrayList<>();
    public ArrayList<Advisor> getAdvisors(){
        return advisorList;
    }
    public boolean addAdvisor(Advisor advisor){
        return advisorList.add(advisor);
    }
    public Boolean updateAdvisor(Advisor advisor,String id){
        Integer curr_adv_idx=findAdvisorById(id);
        if(curr_adv_idx==null){
            return false;
        }
        advisorList.set(curr_adv_idx,advisor);
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
