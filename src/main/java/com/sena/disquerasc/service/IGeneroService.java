package com.sena.disquerasc.service;

import java.util.List;

import javax.validation.Valid;

import com.sena.disquerasc.model.Genero;

public interface IGeneroService {
    public List<Genero> findAll();
    public void save (@Valid Genero genero);
    public Genero findOne(Integer id);
    public void delete(Integer id);
}
