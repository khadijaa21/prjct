package com.example.projectsisir.ws.facade;

import com.example.projectsisir.bean.FacturePerte;
import com.example.projectsisir.service.facade.FacturePerteFacade;
import com.example.projectsisir.ws.converter.FacturePerteConverter;
import com.example.projectsisir.ws.dto.FacturePerteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Factureperte")
public class FacturePerteRest {
    @Autowired
    private FacturePerteFacade facturePerteFacade;
    @Autowired
    private FacturePerteConverter facturePerteConverter;

    @GetMapping("/")
    public List<FacturePerteDto> findAll() {
        List<FacturePerte> all = facturePerteFacade.findAll();
        List<FacturePerteDto> facturePerteDtos = facturePerteConverter.toDto(all);
        return facturePerteDtos;
    }

    @GetMapping("/code/{code}")
    public FacturePerteDto findByCode(@PathVariable String code) {
        FacturePerte ByCode = facturePerteFacade.findByCode(code);
        FacturePerteDto facturePerteDto = facturePerteConverter.toDto(ByCode);
        return facturePerteDto;
    }

    @PostMapping("/")
    public int save(@RequestBody FacturePerte facturePerte) {
        return facturePerteFacade.save(facturePerte);
    }

    @PutMapping("/")
    public int updateByCode(@RequestBody FacturePerte facturePerte) {
        return facturePerteFacade.updateByCode(facturePerte);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return facturePerteFacade.deleteByCode(code);
    }
}
