package com.sena.disquerasc.controllers;

import javax.validation.Valid;

import com.sena.disquerasc.model.Disquera;
import com.sena.disquerasc.service.IDisqueraService;

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
@SessionAttributes("disquera")
@RequestMapping("/disquera")
public class DisqueraController {

    @Autowired
    private IDisqueraService disquerad;
    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("disquera", disquerad.findAll());
        return "disquera/listar";    
    }

    @GetMapping("/ver-disquera/{id}")
    public String verDisquera(@PathVariable Integer id,Model m){
    Disquera disquera=null;
    if(id>0){
    disquera=disquerad.findOne(id);
    }else{
    return "redirect:listar";
    }
    m.addAttribute("disquera",disquera);
    m.addAttribute("accion", "Detalle disquera");
    return "disquera/verc";
    }

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
        Disquera disquera=null;
        if(id>0){
            disquera=disquerad.findOne(id);
        }else{
            return "redirect:listar";
        }
        m.addAttribute("disquera",disquera);
        m.addAttribute("accion", "Actualizar Disquera");
        return "disquera/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Disquera disquera=new Disquera();
        m.addAttribute("disquera", disquera);
        m.addAttribute("accion", "Agregar Disquera");
        return "disquera/form"; 
    }

    @PostMapping("/add")
    public String add(@Valid Disquera disquera,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "disquera/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        disquerad.save(disquera);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
		
		if(id > 0) {
			disquerad.delete(id);
		}
		return "redirect:/disquera/listar";
	}
}
