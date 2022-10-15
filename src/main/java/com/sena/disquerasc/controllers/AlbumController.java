package com.sena.disquerasc.controllers;


import java.util.List;

import javax.validation.Valid;

import com.sena.disquerasc.model.Album;
import com.sena.disquerasc.model.Artista;
import com.sena.disquerasc.model.Genero;
import com.sena.disquerasc.service.IAlbumService;
import com.sena.disquerasc.service.IArtistaService;
import com.sena.disquerasc.service.IGeneroService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;



@Controller
@SessionAttributes("album")
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private IAlbumService albumd;

    @Autowired
    private IArtistaService artistad;

    @Autowired
    private IGeneroService generod;
    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("album", albumd.findAll());
        return "album/listar";    
    }

    @GetMapping("/ver-album/{id}")
public String verAlbum(@PathVariable Integer id,Model m){
Album album=null;
if(id>0){
album=albumd.findOne(id);
}else{
return "redirect:listar";
}
m.addAttribute("album",album);
m.addAttribute("accion", "Detalle del album");
return "album/verc";
}

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
        Album album=null;
        if(id>0){
            album=albumd.findOne(id);
        }else{
            return "redirect:listar";
        }
        List<Artista>listaar=artistad.findAll();
        List<Genero>listag=generod.findAll();
        m.addAttribute("ar", listaar);
        m.addAttribute("gn", listag);
        m.addAttribute("album",album);
        m.addAttribute("accion", "Actualizar album");
        return "album/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Album album=new Album();
        List<Artista>listaar=artistad.findAll();
        List<Genero>listag=generod.findAll();
        m.addAttribute("ar", listaar);
        m.addAttribute("gn", listag);
        m.addAttribute("album", album);
        m.addAttribute("accion", "Agregar Album");
        return "album/form"; 
    }
    @PostMapping("/add")
    public String add(@Valid Album  album,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "album/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";  cancion*/
        albumd.save(album);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
		
		if(id > 0) {
			albumd.delete(id);
		}
		return "redirect:/album/listar";
	}
}

