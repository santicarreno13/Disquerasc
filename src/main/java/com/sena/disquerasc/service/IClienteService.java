package com.sena.disquerasc.service;

import java.util.List;

import com.sena.disquerasc.model.Cliente;

public interface IClienteService {
    public List<Cliente> findAll();
    public void save (Cliente cliente);
    public Cliente findOne(Integer id);
    public void delete(Integer id);
}
