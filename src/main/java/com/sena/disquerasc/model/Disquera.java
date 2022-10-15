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
@Table(name="disqueras")
public class Disquera {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min=2,max=50)
    @Column(length = 50,nullable=false)
    private String nit;
    @NotEmpty
    @Size(min=2,max=50)
    @Column(length = 50,nullable=false)
    private String nombre;
    @Column(length = 20)
    private String telefono;
    @NotEmpty
    @Size(min=2,max=50)
    @Column(length = 50,nullable=false)
    private String direccion;
    private Boolean estado;
    @OneToMany(mappedBy = "disquera", fetch = FetchType.LAZY, cascade =CascadeType.ALL) 
    private List<Artista> artista;
    //Constructores
    public Disquera() {
        artista=new ArrayList<Artista>();
    }
    public Disquera(Integer id, @NotEmpty @Size(min = 2, max = 50) String nit,
            @NotEmpty @Size(min = 2, max = 50) String nombre, String telefono,
            @NotEmpty @Size(min = 2, max = 50) String direccion, Boolean estado, List<Artista> artista) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
        this.artista = artista;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public List<Artista> getArtista() {
        return artista;
    }
    public void setArtista(List<Artista> artista) {
        this.artista = artista;
    }

    
}
