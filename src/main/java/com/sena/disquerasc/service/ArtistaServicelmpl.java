package com.sena.disquerasc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.disquerasc.model.Artista;
import com.sena.disquerasc.model.IArtista;

@Service
public class ArtistaServicelmpl implements IArtistaService {
    @Autowired
    private IArtista artistad;
    
    @Override
    public List<Artista> findAll() {   
        return (List<Artista>) artistad.findAll();
    }
    @Override
    public void save(Artista artista) {
       artistad.save(artista);        
    }
    @Override
    public Artista findOne(Integer id) {    
        return artistad.findById(id).orElse(null);
    }
    @Override
    public void delete(Integer id) {
        artistad.deleteById(id);
    }    
}
