package com.example.projectsisir.ws.facade;


import com.example.projectsisir.bean.TaxeIR;
import com.example.projectsisir.service.facade.TaxeIRFacade;
import com.example.projectsisir.ws.converter.TaxeIRConverter;
import com.example.projectsisir.ws.dto.ResStatDto;
import com.example.projectsisir.ws.dto.TaxeIRDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/TaxeIR")

public class TaxeIRRest {
    @Autowired
    private TaxeIRFacade taxeIRFacade;

    @Autowired
    private TaxeIRConverter taxeIRConverter;

    @GetMapping("/mois/{mois}/annee/{annee}/ice/{ice}")
    public TaxeIRDto findByMoisAndAnneeAndSocieteIce(@PathVariable int mois, @PathVariable int annee, @PathVariable String ice) {
      TaxeIR taxeIR = taxeIRFacade.findByMoisAndAnneeAndSocieteIce(mois, annee, ice);
        return taxeIRConverter.toDto(taxeIR);    }

   /* @GetMapping("/mois/{mois}/annee/{annee}/ice/{ice}")
    public int calculerSomme(int mois, int annee, String ice) {
        return taxeIRFacade.calculerSomme(mois, annee, ice);
    }*/

    @GetMapping("/")
    public List<TaxeIRDto> findAll() {
        List<TaxeIR> all = taxeIRFacade.findAll();
        return taxeIRConverter.toDto(all);
    }

    @PostMapping("/")
    public int save(@RequestBody TaxeIRDto taxeIRDto) {
        TaxeIR taxeIR =taxeIRConverter.toItem(taxeIRDto);
        return taxeIRFacade.save(taxeIR);
    }

    @GetMapping("/annee/{annee}")
    public List<ResStatDto> calculStatic(@PathVariable int annee) {

        return taxeIRFacade.calculStatic(annee);
    }
    /*@PutMapping("/Taxe/mois/{mois}/annee/{annee}/ice/{ice}")
    public int updateTaxeIR(@RequestBody TaxeIR taxeIR,@PathVariable int mois, @PathVariable int annee, @PathVariable String ice) {
        return taxeIRFacade.updateTaxeIR(taxeIR , mois, annee,ice);
    }*/
    @PutMapping("/Taxe")
    public int updateTaxeIR(@RequestBody TaxeIR taxeIR) {
        return taxeIRFacade.updateTaxeIR(taxeIR);
    }

    @DeleteMapping("/mois/{mois}/annee/{annee}/ice/{ice}")
    public int deleteByMoisAndAnneeAndSocieteIce(@PathVariable int mois,@PathVariable int annee,@PathVariable String ice) {
        return taxeIRFacade.deleteByMoisAndAnneeAndSocieteIce(mois, annee, ice);
    }
}
