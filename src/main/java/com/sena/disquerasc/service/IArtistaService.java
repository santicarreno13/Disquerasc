package com.sena.disquerasc.service;

import java.util.List;

import com.sena.disquerasc.model.Artista;

public interface IArtistaService {
   
    public List<Artista> findAll();
    public void save (Artista artista);
    public Artista findOne(Integer id);
   public void delete(Integer id); 
}
