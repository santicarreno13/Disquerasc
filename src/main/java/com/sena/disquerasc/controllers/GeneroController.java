package com.sena.disquerasc.controllers;

import javax.validation.Valid;

import com.sena.disquerasc.model.Genero;
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
@SessionAttributes("genero")
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private IGeneroService generod;
    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("generos", generod.findAll());
        return "genero/listar";    
    }

    @GetMapping("/verc")
    public String verc(Model m){
        Genero gen=new Genero();
        gen.setId(1);
        gen.setNombre("Rock");
        gen.setEstado(false); 
        m.addAttribute("genero", gen);
        return "genero/verc";
    }
    @GetMapping("/ver-genero/{id}")
public String verGenero(@PathVariable Integer id,Model m){
Genero genero=null;
if(id>0){
genero=generod.findOne(id);
}else{
return "redirect:listar";
}
m.addAttribute("genero",genero);
m.addAttribute("accion", "Detalle del genero");
return "genero/verc";
}

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
        Genero genero=null;
        if(id>0){
            genero=generod.findOne(id);
        }else{
            return "redirect:listar";
        }
        m.addAttribute("genero",genero);
        m.addAttribute("accion", "Actualizar Genero");
        return "genero/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Genero genero=new Genero();
        m.addAttribute("genero", genero);
        m.addAttribute("accion", "Agregar Genero");
        return "genero/form"; 
    }

    @PostMapping("/add")
    public String add(@Valid Genero genero,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "genero/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        generod.save(genero);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
		
		if(id > 0) {
			generod.delete(id);
		}
		return "redirect:/genero/listar";
	}

}
