
package com.sena.disquerasc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.disquerasc.model.Cancion;
import com.sena.disquerasc.model.ICancion;

@Service
public class CancionServiceImpl implements ICancionService{
    @Autowired
    private ICancion canciond;
    
    @Override
    public List<Cancion> findAll() {   
        return (List<Cancion>) canciond.findAll();
    }
    @Override
    public void save(Cancion cancion) {
       canciond.save(cancion);        
    }
    @Override
    public Cancion findOne(Integer id) {    
        return canciond.findById(id).orElse(null);
    }
    @Override
    public void delete(Integer id) {
        canciond.deleteById(id);
    }
}
