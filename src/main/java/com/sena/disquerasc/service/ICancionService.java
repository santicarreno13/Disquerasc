package com.sena.disquerasc.service;

import java.util.List;

import com.sena.disquerasc.model.Cancion;

public interface ICancionService {
    public List<Cancion> findAll();
    public void save (Cancion cancion);
    public Cancion findOne(Integer id);
    public void delete(Integer id); 
}
