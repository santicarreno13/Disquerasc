package com.sena.disquerasc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="generos")
public class Genero {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min=2,max=50)
    @Column(length = 50,nullable=false)
    private String nombre;
    private Boolean estado;
    
    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY, cascade =CascadeType.ALL) 
    private List<Album> album;

    //Constructores
    public Genero() {
        album=new ArrayList<Album>();
    }
    public Genero(Integer id, @NotEmpty @Size(min = 2, max = 50) String nombre, Boolean estado, List<Album> album) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.album = album;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public List<Album> getAlbum() {
        return album;
    }
    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    
}
