package com.example.projectsisir.dao;

import com.example.projectsisir.bean.NotificationIS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationISDao extends JpaRepository<NotificationIS, Long> {

    NotificationIS findByTrimAndAnnee(int trim, int annee);

    int deleteByTrimAndAnnee(int trim, int annee);

    @Query("SELECT nd.notificationIS FROM NotificationISDetail nd WHERE NOT EXISTS "
            + "(SELECT t.notificationISDetails FROM TaxeIS t WHERE t.annee = :year AND t.trimestre = :trimester AND nd.societe = t.societe) AND nd.societe.ice = :ice")
    List<NotificationIS> findNotificationISWithoutTaxeIsBySocieteIce(@Param("year") int year, @Param("trimester") int trimester, @Param("ice") String ice);

}
