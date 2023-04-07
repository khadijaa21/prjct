package com.example.projectsisir.service.facade;


import com.example.projectsisir.bean.Societe;

import java.util.List;

public interface SocieteFacade {

    public int deleteByLibelle(String libelle);

  public   int deleteByIce(String ice);

   public Societe findByIce(String ice);

   public Societe findByEmployeCin(String cin);
   public List<Societe> findByLibelle(String libelle);

    public List<Societe> findAll();

    public int updateSociete(Societe societe);
   public int save(Societe societe);
}
