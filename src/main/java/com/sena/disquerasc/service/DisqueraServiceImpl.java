package com.sena.disquerasc.service;

import java.util.List;

import com.sena.disquerasc.model.Disquera;
import com.sena.disquerasc.model.IDisquera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    
public class DisqueraServiceImpl implements IDisqueraService{
    @Autowired
    private IDisquera disquerad;

    @Override
    public List<Disquera> findAll() {
        
        return (List<Disquera>) disquerad.findAll();
    }

    @Override
    public void save(Disquera disquera) {
        disquerad.save(disquera);        
    }

    @Override
    public Disquera findOne(Integer id) {
        
        return disquerad.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        disquerad.deleteById(id);
        
    }
 
}
