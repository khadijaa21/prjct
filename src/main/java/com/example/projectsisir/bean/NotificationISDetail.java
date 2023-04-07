package com.example.projectsisir.bean;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class NotificationISDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    private NotificationIS notificationIS;

    @ManyToOne
    private Societe societe;


    @ManyToOne
    private TaxeIS taxeIS;

}