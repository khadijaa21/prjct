package com.example.projectsisir.ws.converter;


import com.example.projectsisir.bean.TaxeIREmployes;
import com.example.projectsisir.ws.dto.TaxeIREmployesDto;
import org.springframework.stereotype.Component;

@Component
public class TaxeIREmployesConverter  extends AbstractConverter<TaxeIREmployes, TaxeIREmployesDto> {
    public TaxeIREmployesDto toDto (TaxeIREmployes item) {
        TaxeIREmployesDto dto = null;
        if (item != null) {
            dto = new TaxeIREmployesDto();
            dto.setSalaireBrute(item.getSalaireBrute());
            dto.setId(item.getId());
            dto.setSalaireNet(item.getSalaireNet());
            dto.setTauxTaxeIR(item.getTauxTaxeIR());
            dto.setMontantIR(item.getMontantIR());
            dto.setTaxeIR(item.getTaxeIR());

        }
        return dto;
    }
    public TaxeIREmployes toItem (TaxeIREmployesDto dto) {
        TaxeIREmployes item = null;
        if (dto != null) {
            item =new TaxeIREmployes();

            item.setSalaireBrute(dto.getSalaireBrute());
            item.setId(dto.getId());
            item.setTauxTaxeIR(dto.getTauxTaxeIR());
            item.setSalaireNet(dto.getSalaireNet());
            item.setMontantIR(dto.getMontantIR());
            item.setTaxeIR(dto.getTaxeIR());


        }
        return item;
    }


}

