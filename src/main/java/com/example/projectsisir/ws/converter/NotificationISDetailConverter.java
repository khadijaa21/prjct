package com.example.projectsisir.ws.converter;

import com.example.projectsisir.bean.NotificationISDetail;
import com.example.projectsisir.ws.dto.NotificationISDetailDto;
import org.springframework.stereotype.Component;
@Component
public class NotificationISDetailConverter {
    public NotificationISDetailDto toDto(NotificationISDetail notificationISDetail){
        NotificationISDetailDto dto=new NotificationISDetailDto();
        dto.setId(notificationISDetail.getId());
        dto.setNotificationIS(notificationISDetail.getNotificationIS());
        dto.setSociete(notificationISDetail.getSociete());
        dto.setTaxeIS(notificationISDetail.getTaxeIS());
        return dto;
    }
    public NotificationISDetail toEntity(NotificationISDetailDto dto) {
        NotificationISDetail notificationISDetail = new NotificationISDetail();
        notificationISDetail.setId(dto.getId());
        notificationISDetail.setNotificationIS(dto.getNotificationIS());
        notificationISDetail.setSociete(dto.getSociete());
        notificationISDetail.setTaxeIS(dto.getTaxeIS());
        return notificationISDetail;
    }
}

