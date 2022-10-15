package com.sena.disquerasc.service;

import java.util.List;

import com.sena.disquerasc.model.Cliente;
import com.sena.disquerasc.model.ICliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    private ICliente cliented;

    @Override
    public List<Cliente> findAll() {
        
        return (List<Cliente>) cliented.findAll();
    }

    @Override
    public void save(Cliente cliente) {
       cliented.save(cliente);        
    }

    @Override
    public Cliente findOne(Integer id) {
        
        return cliented.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        cliented.deleteById(id);
        
    }
 
}
