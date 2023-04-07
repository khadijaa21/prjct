package com.example.projectsisir.dao;

import com.example.projectsisir.bean.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EmployeDao extends JpaRepository<Employe, Long> {

    Employe findByCin(String cin) ;

    int deleteByCin(String cin);


    Employe findBySocieteIce(String ice);

    Employe findBySalaire(double salaire);
}
