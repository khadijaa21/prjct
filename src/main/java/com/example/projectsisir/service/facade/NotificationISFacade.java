package com.example.projectsisir.service.facade;

import com.example.projectsisir.bean.NotificationIS;

import java.util.List;

public interface NotificationISFacade {
    List<NotificationIS> findByTaxeISAnneeAndTaxeISTrimestre(int annee, float trimestre);

    NotificationIS save(NotificationIS notificationIS);

    List<NotificationIS> findAll();

    NotificationIS findById(Long id);

    int deleteById(Long id);
}
