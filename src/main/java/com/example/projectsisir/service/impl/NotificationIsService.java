package com.example.projectsisir.service.impl;

import com.example.projectsisir.bean.NotificationIS;
import com.example.projectsisir.bean.NotificationISDetail;
import com.example.projectsisir.bean.Societe;
import com.example.projectsisir.dao.NotificationISDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationIsService {

    @Autowired
    private NotificationISDao notificationISDao;
    @Autowired
    private NotificationISDetailService notificationISDetailService;
    @Autowired
    private SocieteService societeService;

    public List<NotificationIS> findNotificationISWithoutTaxeIsBySocieteIce(int year, int trimester, String ice) {
        return notificationISDao.findNotificationISWithoutTaxeIsBySocieteIce(year, trimester, ice);
    }

    public int save(NotificationIS notificationIS) {
        // Vérification de l'année et du trimestre
        if (notificationIS.getAnnee() <= 0 || notificationIS.getTrim() <= 0 || notificationIS.getTrim() > 4) {
            return -1; // code d'erreur pour année ou trimestre non valides
        }


        // Enregistrement de la notification IS
        NotificationIS savedNotificationIS = notificationISDao.save(notificationIS);

        // Enregistrement des détails de la notification IS pour toutes les sociétés sans taxe IS pour l'année et le trimestre donnés
        List<Societe> societes = societeService.findSocietesWithoutTaxeIS(notificationIS.getAnnee(), notificationIS.getTrim());
        if (societes != null && !societes.isEmpty()) {
            for (Societe s : societes) {
                NotificationISDetail notificationISDetail = new NotificationISDetail();
                notificationISDetail.setSociete(s);
                notificationISDetail.setNotificationIS(savedNotificationIS);
                notificationISDetailService.save(notificationISDetail);
            }
        }
        return 1; // code de succès
    }
}
