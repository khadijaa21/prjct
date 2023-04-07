package com.example.projectsisir.ws.facade;

import com.example.projectsisir.bean.FactureGagne;
import com.example.projectsisir.service.facade.FactureGagneFacade;
import com.example.projectsisir.ws.converter.FactureGagneConverter;
import com.example.projectsisir.ws.dto.FactureGagneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/FactureGagne")
public class FactureGagneRest {

    @Autowired
    private FactureGagneFacade factureGagneFacade;
    @Autowired
    FactureGagneConverter factureGagneConverter;


    @PostMapping("/")
    public int save(@RequestBody FactureGagne factureGagne) {
        return factureGagneFacade.save(factureGagne);
    }

    @GetMapping("/code/{code}")
    public FactureGagneDto findByCode(@PathVariable String code) {
        FactureGagne ByCode = factureGagneFacade.findByCode(code);
        FactureGagneDto factureGagneDto = factureGagneConverter.toDto(ByCode);
        return factureGagneDto;
    }
    @GetMapping("/id/{id}")
    public FactureGagneDto findById(@PathVariable Long id) {
        FactureGagne ById =factureGagneFacade.findById(id);
        FactureGagneDto factureGagneDto = factureGagneConverter.toDto(ById);
        return factureGagneDto;
    }
    @GetMapping("/")
    public List<FactureGagneDto> findAll() {
        List<FactureGagne> Byall = factureGagneFacade.findAll();
        List<FactureGagneDto> factureGagneDtos = factureGagneConverter.toDto(Byall);
        return factureGagneDtos;
    }

    @PutMapping("/code/{code}")
    public int updateByCode(@RequestBody FactureGagne factureGagne) {
        return factureGagneFacade.updateByCode(factureGagne);
    }



    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return factureGagneFacade.deleteById(id);
    }


    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return factureGagneFacade.deleteByCode(code);
    }
}
