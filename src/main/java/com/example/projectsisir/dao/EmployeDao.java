package com.example.projectsisir.dao;

import com.example.projectsisir.bean.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EmployeDao extends JpaRepository<Employe, Long> {

    Employe findByCin(String cin) ;

    int deleteByCin(String cin);


    Employe findBySocieteIce(String ice);

    Employe findBySalaire(double salaire);
    @Query("select count(e) from Employe e where e.societe.ice =: ice")
    int currentEmployesCount(@Param("ice") String ice);
}
