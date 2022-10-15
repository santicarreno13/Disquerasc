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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min=2,max=50)
    @Column(length = 50,nullable=false)
    private String nombre;
    @NotEmpty
    @Size(min=2,max=50)
    private String apellido;
    @NotEmpty
    @Email
    @Column(length=100, nullable=false)
    private String email;
    @Column(length = 20)
    private String telefono;
    
    private String foto;
    
    private Boolean estado;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Factura> facturas;  
    
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDocumento tipodoc;


    //Constructores
    public Cliente() {
        facturas=new ArrayList<Factura>();
    }

    public Cliente(Integer id, @NotEmpty @Size(min = 2, max = 50) String nombre,
            @NotEmpty @Size(min = 2, max = 50) String apellido, @NotEmpty @Email String email, String telefono,
            Boolean estado, List<Factura> facturas, TipoDocumento tipodoc,String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
        this.facturas = facturas;
        this.tipodoc = tipodoc;
        this.foto = foto;
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


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Boolean getEstado() {
        return estado;
    }


    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    public List<Factura> getFacturas() {
        return facturas;
    }


    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }


    public TipoDocumento getTipodoc() {
        return tipodoc;
    }


    public void setTipodoc(TipoDocumento tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getFoto() {
        return foto;
    }



    public void setFoto(String foto) {
        this.foto = foto;
    }

    
    
}
