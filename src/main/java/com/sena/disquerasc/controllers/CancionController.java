package com.sena.disquerasc.controllers;

import java.util.List;

import javax.validation.Valid;

import com.sena.disquerasc.model.Album;
import com.sena.disquerasc.model.Cancion;
import com.sena.disquerasc.service.IAlbumService;
import com.sena.disquerasc.service.ICancionService;

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
@SessionAttributes("cancion")
@RequestMapping("/cancion")
public class CancionController {

    @Autowired
    private ICancionService canciond;

    @Autowired
    private IAlbumService albumd;
    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("cancion", canciond.findAll());
        return "cancion/listar";    
    }

    @GetMapping("/ver-cancion/{id}")
public String verCancion(@PathVariable Integer id,Model m){
Cancion cancion=null;
if(id>0){
cancion=canciond.findOne(id);
}else{
return "redirect:listar";
}
m.addAttribute("cancion",cancion);
m.addAttribute("accion", "Detalle de la cancion");
return "cancion/verc";
}

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
        Cancion cancion=null;
        if(id>0){
            cancion=canciond.findOne(id);
        }else{
            return "redirect:listar";
        }
        List<Album> listaam = albumd.findAll();
        m.addAttribute("am", listaam);
        m.addAttribute("cancion",cancion);
        m.addAttribute("accion", "Actualizar cancion");
        return "cancion/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Cancion cancion=new Cancion();
        List<Album> listaam = albumd.findAll();
        m.addAttribute("am", listaam);
        m.addAttribute("cancion", cancion);
        m.addAttribute("accion", "Agregar Cancion");
        return "cancion/form"; 
    }
    @PostMapping("/add")
    public String add(@Valid Cancion  cancion,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "cancion/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        canciond.save(cancion);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
		
		if(id > 0) {
			canciond.delete(id);
		}
		return "redirect:/cancion/listar";
	}
}

