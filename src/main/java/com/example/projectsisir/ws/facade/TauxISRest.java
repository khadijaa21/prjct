package com.example.projectsisir.ws.facade;

import com.example.projectsisir.bean.TauxTaxeIS;
import com.example.projectsisir.service.facade.TauxTaxeISFacade;
import com.example.projectsisir.ws.converter.TauxIsConverter;
import com.example.projectsisir.ws.dto.TauxTaxeIsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tauxIs")
public class TauxISRest {
    @Autowired
    TauxTaxeISFacade tauxTaxeISFacade;
    @Autowired
    TauxIsConverter tauxIsConverter;
    @DeleteMapping("/")
    public int delete(TauxTaxeIS tauxTaxeIS) {
        return tauxTaxeISFacade.delete(tauxTaxeIS);
    }

    @GetMapping("/dateDebut/{dateDebut}/dateFin/{dateFin}")
    public TauxTaxeIsDto findByDateApplicationDebutAndDateApplicationFin(@PathVariable Date dateDebut, @PathVariable Date dateFin) {
        TauxTaxeIS tauxTaxeIS = tauxTaxeISFacade.findByDateApplicationDebutAndDateApplicationFin(dateDebut, dateFin);
        TauxTaxeIsDto tauxTaxeIsDto = tauxIsConverter.toDto(tauxTaxeIS);
        return tauxTaxeIsDto;
    }
    @PutMapping("/")
    public int update(@RequestBody TauxTaxeIS tauxTaxeIS) {
        return tauxTaxeISFacade.update(tauxTaxeIS);
    }

    @DeleteMapping("/dateDebut/{dateDebut}/dateFin/{dateFin}")
    public int deleteByDateApplicationDebutAndDateApplicationFin(@PathVariable Date dateDebut,@PathVariable Date dateFin) {
        return tauxTaxeISFacade.deleteByDateApplicationDebutAndDateApplicationFin(dateDebut, dateFin);
    }

    @GetMapping("/resultatAvantImpotBetweenMinAndMax/{resultatAvantImpot}")
    public TauxTaxeIsDto findByResultatAvantImpotBetweenMinAndMax(@PathVariable double resultatAvantImpot) {
        TauxTaxeIS tauxTaxeIS = tauxTaxeISFacade.findByResultatAvantImpot(resultatAvantImpot);
        TauxTaxeIsDto tauxTaxeIsDto = tauxIsConverter.toDto(tauxTaxeIS);
        return tauxTaxeIsDto;
    }
    @PostMapping("/")
    public int save(@RequestBody TauxTaxeIS tauxTaxeIS) {
        return tauxTaxeISFacade.save(tauxTaxeIS);
    }
    @GetMapping("/")
    public List<TauxTaxeIS> findAll() {
        return tauxTaxeISFacade.findAll();
    }
}
