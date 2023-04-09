package com.example.projectsisir.ws.dto;
import com.example.projectsisir.bean.NotificationIS;
import com.example.projectsisir.bean.Societe;
import com.example.projectsisir.bean.TaxeIS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationISDetailDto {
    private long id;
    private NotificationIS notificationIS;
    private Societe societe;
    private TaxeIS taxeIS;
}
