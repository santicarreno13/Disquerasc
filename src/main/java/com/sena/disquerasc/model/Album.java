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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min=2,max=70)
    @Column(length = 50,nullable=false)
    private String nombre;
    private String publicacion;
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_artista")
    private Artista artista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_genero")
    private Genero genero;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade =CascadeType.ALL) 
    private List<Cancion> cancion;
    
    public Album() {
        cancion=new ArrayList<Cancion>();
    }
    public Album(Integer id, @NotEmpty @Size(min = 2, max = 70) String nombre, String publicacion, Boolean estado,
            Artista artista, Genero genero, List<Cancion> cancion) {
        this.id = id;
        this.nombre = nombre;
        this.publicacion = publicacion;
        this.estado = estado;
        this.artista = artista;
        this.genero = genero;
        this.cancion = cancion;
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
    public String getPublicacion() {
        return publicacion;
    }
    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Artista getArtista() {
        return artista;
    }
    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public List<Cancion> getCancion() {
        return cancion;
    }
    public void setCancion(List<Cancion> cancion) {
        this.cancion = cancion;
    }
    
}
