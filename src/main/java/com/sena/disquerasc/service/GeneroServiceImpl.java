package com.sena.disquerasc.service;

import java.util.List;

import com.sena.disquerasc.model.Genero;
import com.sena.disquerasc.model.IGenero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements IGeneroService{
    @Autowired
    private IGenero generod;

    @Override
    public List<Genero> findAll() {
        
        return (List<Genero>) generod.findAll();
    }

    @Override
    public void save(Genero genero) {
       generod.save(genero);        
    }

    @Override
    public Genero findOne(Integer id) {
        
        return generod.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        generod.deleteById(id);
        
    }
 
}
