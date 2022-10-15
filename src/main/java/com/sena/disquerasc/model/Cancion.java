package com.sena.disquerasc.model;

 import java.util.Date;

 import javax.persistence.Column;
 import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
 import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

 @Entity
 @Table(name = "cancion")
 public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min=2,max=50)
    @Column(length = 50,nullable=false)
    private String nombre;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fecha;

    @NotEmpty
    @Column(length=100, nullable=false)
    private String duracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_album")
    private Album album;
    private Boolean estado;

    public Cancion() {
    }

    public Cancion(Integer id, @NotEmpty @Size(min = 2, max = 50) String nombre, Date fecha, @NotEmpty String duracion,
            Album album, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.album = album;
        this.estado = estado;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
    
 }
   