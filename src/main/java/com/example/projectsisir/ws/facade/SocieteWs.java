package com.example.projectsisir.ws.facade;

import com.example.projectsisir.bean.Societe;
import com.example.projectsisir.service.facade.SocieteFacade;
import com.example.projectsisir.ws.converter.SocieteConverter;
import com.example.projectsisir.ws.dto.SocieteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Societe")
public class SocieteWs {
    @Autowired
    SocieteFacade societeFacade;
    @Autowired
    private SocieteConverter societeConverter;

    @GetMapping("/")
    public List<SocieteDto> findAll() {
        List<Societe> all = societeFacade.findAll();
        return societeConverter.toDto(all);
    }
    @GetMapping("/ice/{ice}")
    public SocieteDto findByIce(@PathVariable String ice) {
        Societe byIce = societeFacade.findByIce(ice);
        return societeConverter.toDto(byIce);
    }
    @GetMapping("/cin/{cin}")
    public SocieteDto findByEmployeCin(@PathVariable String cin) {
        Societe byCin = societeFacade.findByEmployeCin(cin);
        return societeConverter.toDto(byCin);
    }

    @PostMapping("/")
    public int save(@RequestBody SocieteDto societeDto) {
        Societe societe =societeConverter.toItem(societeDto);
        return societeFacade.save(societe);
    }
    @PutMapping("/")
    public int updateSociete(@RequestBody SocieteDto societeDto) {
        Societe update = societeConverter.toItem(societeDto);
        return societeFacade.updateSociete(update);
    }
    @GetMapping("/libelle/{libelle}")
    public List<SocieteDto> findByLibelle(@PathVariable String libelle) {
        List<Societe> byLibelle = societeFacade.findByLibelle(libelle);
        return societeConverter.toDto(byLibelle);
    }
    @DeleteMapping("/ice/{ice}")
    public int deleteByIce(@PathVariable String ice) {
        return societeFacade.deleteByIce(ice);
    }
    @DeleteMapping("/Libelle/{Libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return societeFacade.deleteByLibelle(libelle);
    }
}