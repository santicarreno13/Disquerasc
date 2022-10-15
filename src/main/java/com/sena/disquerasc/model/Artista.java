package com.sena.disquerasc.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "artista")
public class Artista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min=2,max=20)
    private String documento;
    @NotEmpty
    @Size(min=2,max=50)
    private String nombre;
    @NotEmpty
    @Size(min=2,max=50)
    private String apellido;
    @NotEmpty
    @Size(min=2,max=50)
    private String nombrea;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fecha;
    
    @NotEmpty
    @Email
    @Column(length=100, nullable=false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_disquera")
    private Disquera disquera;

    private Boolean estado;
    private String foto;

    @OneToMany(mappedBy = "artista", fetch = FetchType.LAZY, cascade =CascadeType.ALL) 
    private List<Album> album;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDocumento tipodoc;

    public Artista() {
        album=new ArrayList<Album>();
    }

    public Artista(Integer id, @NotEmpty @Size(min = 2, max = 20) String documento,
            @NotEmpty @Size(min = 2, max = 50) String nombre, @NotEmpty @Size(min = 2, max = 50) String apellido,
            @NotEmpty @Size(min = 2, max = 50) String nombrea, Date fecha, @NotEmpty @Email String email,
            Disquera disquera, Boolean estado, String foto, List<Album> album, TipoDocumento tipodoc) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombrea = nombrea;
        this.fecha = fecha;
        this.email = email;
        this.disquera = disquera;
        this.estado = estado;
        this.foto = foto;
        this.album = album;
        this.tipodoc = tipodoc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombrea() {
        return nombrea;
    }

    public void setNombrea(String nombrea) {
        this.nombrea = nombrea;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Disquera getDisquera() {
        return disquera;
    }

    public void setDisquera(Disquera disquera) {
        this.disquera = disquera;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public TipoDocumento getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(TipoDocumento tipodoc) {
        this.tipodoc = tipodoc;
    }

  
    
}
