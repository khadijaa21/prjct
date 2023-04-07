package com.example.projectsisir.service.impl;

import com.example.projectsisir.bean.NotificationISDetail;
import com.example.projectsisir.dao.NotificationIsDetailDao;
import com.example.projectsisir.service.facade.NotificationISDetailFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationISDetailService implements NotificationISDetailFacade {
    public void save(NotificationISDetail notificationISDetail) {
        notificationIsDetailDao.save(notificationISDetail);
    }

    @org.springframework.data.jpa.repository.Query("SELECT nd FROM NotificationISDetail nd WHERE NOT EXISTS "
            + "(SELECT t.notificationISDetails FROM TaxeIS t WHERE t.annee = :year AND t.trimestre = :trimester AND nd.societe = t.societe)")
    public List<NotificationISDetail> findNotificationISDetailsWithoutTaxeIS(int year, int trimester) {
        return notificationIsDetailDao.findNotificationISDetailsWithoutTaxeIS(year, trimester);
    }

    @Query("SELECT nd FROM NotificationISDetail nd WHERE NOT EXISTS "
            + "(SELECT t.notificationISDetails FROM TaxeIS t WHERE t.annee = :year AND t.trimestre = :trimester AND nd.societe = t.societe) AND nd.societe.ice = :ice")
    public List<NotificationISDetail> findNotificationISDetailsWithoutTaxeIsBySocieteIce(int year, int trimester, String ice) {
        return notificationIsDetailDao.findNotificationISDetailsWithoutTaxeIsBySocieteIce(year, trimester, ice);
    }

    public List<NotificationISDetail> findNotificationISDetailsWithoutTaxeISBySocieteIce(String ice, int year, int trimester) {
        return notificationIsDetailDao.findNotificationISDetailsWithoutTaxeIsBySocieteIce(trimester, year, ice);
    }
    @Autowired
    private NotificationIsDetailDao notificationIsDetailDao;

    public NotificationISDetail findByLibelle(String libelle) {
        return notificationIsDetailDao.findByLibelle(libelle);
    }

    public int deleteByLibelle(String libelle) {
        return notificationIsDetailDao.deleteByLibelle(libelle);
    }
}
