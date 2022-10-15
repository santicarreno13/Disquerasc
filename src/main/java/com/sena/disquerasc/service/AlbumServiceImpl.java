package com.sena.disquerasc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.disquerasc.model.Album;
import com.sena.disquerasc.model.IAlbum;

@Service
public class AlbumServiceImpl implements IAlbumService {
    @Autowired
    private IAlbum albumd;
    
    @Override
    public List<Album> findAll() {   
        return (List<Album>) albumd.findAll();
    }
    @Override
    public void save(Album album) {
       albumd.save(album);        
    }
    @Override
    public Album findOne(Integer id) {    
        return albumd.findById(id).orElse(null);
    }
    @Override
    public void delete(Integer id) {
        albumd.deleteById(id);
    } 
}
