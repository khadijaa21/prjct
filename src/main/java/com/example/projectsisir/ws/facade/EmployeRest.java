package com.example.projectsisir.ws.facade;

import com.example.projectsisir.bean.Employe;
import com.example.projectsisir.service.facade.EmployeFacade;
import com.example.projectsisir.ws.converter.EmployeConverter;
import com.example.projectsisir.ws.dto.EmployeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employe")


public class EmployeRest {

    @Autowired
    private EmployeFacade employeFacade;
    @Autowired
    private EmployeConverter employeConverter;


    @GetMapping("/cin/{cin}")
    public EmployeDto findByCin(@PathVariable String cin) {
        Employe employes= employeFacade.findByCin(cin);
        EmployeDto employeDto = employeConverter.toDto(employes);
        return employeDto;
    }
    @GetMapping("/")
    public List<EmployeDto> findAll() {
        List<Employe> employes = employeFacade.findAll();
        List<EmployeDto> employeDtos =employeConverter.toDto(employes);
        return employeDtos;
    }
    @DeleteMapping ("/cin/{cin}")
    public int deleteByCin(@PathVariable String cin) {
        return employeFacade.deleteByCin(cin);
    }
    @GetMapping("/ice/{ice}")
    public EmployeDto findBySocieteIce(@PathVariable String ice) {
        Employe employe = employeFacade.findBySocieteIce(ice);
        EmployeDto employeDto = employeConverter.toDto(employe);
        return employeDto;
    }
    @GetMapping("/salaire/{salaire}")
    public EmployeDto findBySalaire(@PathVariable double salaire) {
        Employe employe = employeFacade.findBySalaire(salaire);
        EmployeDto employeDto = employeConverter.toDto(employe);
        return employeDto;
    }
    @PostMapping("/")
    public int save(@RequestBody Employe employe) {
        return employeFacade.save(employe);
    }
    @GetMapping("/count")
    public int currentEmployeesCount(@PathVariable String ice) {
        return employeFacade.currentEmployesCount(ice);
    }
}

