package com.example.projectsisir.ws.facade;


import com.example.projectsisir.bean.TaxeIREmployes;
import com.example.projectsisir.service.facade.TaxeIREmployesFacade;
import com.example.projectsisir.ws.converter.TaxeIREmployesConverter;
import com.example.projectsisir.ws.dto.CriteriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/te")
public class TaxeIREmployesRest {
    @Autowired
    private TaxeIREmployesFacade taxeIREmployesFacade ;
    @Autowired
    private TaxeIREmployesConverter taxeIREmployesConverter;


    @PostMapping("/TaxeIR/criteria")
    public List<TaxeIREmployes> findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(@RequestBody CriteriaDto criteriaDto ) {

       List <TaxeIREmployes> taxeIREmployes = taxeIREmployesFacade.findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(criteriaDto.getMois(), criteriaDto.getAnnee(),criteriaDto.getIce());
        return taxeIREmployes;
    }
    @GetMapping("/lolo")
   public String sqzHello(){
        return "jamilaaaaaaaazlEZETZI";
    }





    @DeleteMapping("taxe/mois/{mois}/annee/{annee}/ice/{ice}")
    public int deleteByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(@PathVariable int mois, @PathVariable int annee,@PathVariable String ice ) {
        return taxeIREmployesFacade.deleteByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(mois, annee,ice);
    }
}
