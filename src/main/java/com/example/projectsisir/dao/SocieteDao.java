package com.example.projectsisir.dao;

import com.example.projectsisir.bean.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocieteDao extends JpaRepository<Societe, Long> {

    int deleteByLibelle(String libelle);

    int deleteByIce(String ice);

    Societe findByIce(String ice);
    Societe findByEmployeCin(String cin);


    List<Societe> findByLibelle(String libelle);


    @Query("SELECT s FROM Societe s WHERE NOT EXISTS "
            + "(SELECT t.societe FROM TaxeIS t WHERE t.annee = :year AND t.trimestre = :trimester AND t.societe = s)")
    List<Societe> findSocietesWithoutTaxeIS(@Param("year") int year, @Param("trimester") int trimester);
}