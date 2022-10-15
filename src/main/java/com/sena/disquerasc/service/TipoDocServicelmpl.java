package com.sena.disquerasc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.disquerasc.model.ITipoDocumento;
import com.sena.disquerasc.model.TipoDocumento;
@Service
public class TipoDocServicelmpl implements ITipoDocService {
  
    @Autowired
    private ITipoDocumento tipodoc;
    @Override
    public List<TipoDocumento> findAll() {
    return (List<TipoDocumento>) tipodoc.findAll();
    }
}
