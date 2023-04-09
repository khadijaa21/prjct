package com.example.projectsisir.ws.converter;

import com.example.projectsisir.bean.NotificationIS;
import com.example.projectsisir.ws.dto.NotificationIsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component

public class NotificationIsConverter implements Converter<NotificationIS, NotificationIsDto> {
    @Override
    public NotificationIsDto convert(NotificationIS notificationIS) {
        NotificationIsDto dto =new NotificationIsDto();
        dto.setId(notificationIS.getId());
        dto.setAnnee(notificationIS.getAnnee());
        dto.setTrim(notificationIS.getTrim());
        return dto;
    }
}
