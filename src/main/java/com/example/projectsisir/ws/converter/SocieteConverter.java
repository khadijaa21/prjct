package com.example.projectsisir.ws.converter;

import com.example.projectsisir.bean.Societe;
import com.example.projectsisir.ws.dto.SocieteDto;
import org.springframework.stereotype.Component;
@Component
public class SocieteConverter extends AbstractConverter<Societe, SocieteDto> {

        public SocieteDto toDto(Societe item){
            SocieteDto dto= null;
            if (item != null) {
                dto= new SocieteDto();
                dto.setLibelle(dto.getLibelle());
                dto.setIce(dto.getIce());
                dto.setDescription(dto.getDescription());
                dto.setEmploye(dto.getEmploye());

            }

            return dto;
        } public Societe toItem(SocieteDto dto){
            Societe item= null;
            if (dto != null) {
                item= new Societe();
                item.setLibelle(item.getLibelle());
                item.setIce(item.getIce());
                item.setDescription(item.getDescription());
                item.setEmploye(item.getEmploye());

            }

            return item;
        }
}

