package com.example.Bench.Management.Project.Service.Impl;

import com.example.Bench.Management.Project.Model.Dto;
import com.example.Bench.Management.Project.Model.EmpDetails;
import com.example.Bench.Management.Project.Model.Location;
import com.example.Bench.Management.Project.Model.Skill;
import com.example.Bench.Management.Project.Repository.EmpDetailsRepo;
import com.example.Bench.Management.Project.Service.EmpDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmpDetailsService {
    @Autowired
    private EmpDetailsRepo empDetailsRepo;

    public  EmployeeService(EmpDetailsRepo empDetailsRepo){
        super();
        this.empDetailsRepo=empDetailsRepo;
    }
    @Override
    public EmpDetails save(EmpDetails empDetails) {
        return empDetailsRepo.save(empDetails);
    }

    @Override
    public List<EmpDetails> getData() {
        return empDetailsRepo.findAll();
    }

    @Override
    public long getActiveEmployees() {
        return empDetailsRepo.getAllActiveEmployees();
    }

    @Override
    public long getInactiveEmployees() {
        return empDetailsRepo.getAllInActiveEmployees();
    }

    @Override
    public long getAllEmployees() {
        return empDetailsRepo.getAllEmployees();
    }

    @Override
    public String updateCompanyStatus(Long employeeId) {
        EmpDetails empDetails=empDetailsRepo.findById(employeeId).get();
        if(empDetails.getActive()==true){
            empDetails.setActive(false);
        }
        else
            empDetails.setActive(true);
      empDetailsRepo.save(empDetails);
        return "Update";
    }

    @Override
    public EmpDetails getEmployeeById(Long employeeId) {
        EmpDetails empDetails=null;
        if(employeeId!=null) {
           empDetails = empDetailsRepo.findById(employeeId).get();
            return empDetails;
        }
        else return empDetails;
    }


    @Override
    public List<Dto> getAllDto() {

        return empDetailsRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private Dto convertEntityToDto(EmpDetails empDetails){
        Dto dto=new Dto();
        dto.setEmployeeId(empDetails.getId());
        dto.setEmployeeName(empDetails.getName());
        dto.setExperience(empDetails.getWorkExp());
        dto.setJava(empDetails.getSkill().getJava());
        dto.setPython(empDetails.getSkill().getPython());
        dto.setReact(empDetails.getSkill().getReact());
        dto.setAngular(empDetails.getSkill().getAngular());
        dto.setHtml(empDetails.getSkill().getHtml());
        dto.setCss(empDetails.getSkill().getCss());
        dto.setJavascript(empDetails.getSkill().getJavascript());
        dto.setSpringboot(empDetails.getSkill().getSpringboot());
        dto.setLocation(empDetails.getEmpLocation());
        dto.setBenchStatus(empDetails.getBenchStatus());
        return dto;
    }


}
