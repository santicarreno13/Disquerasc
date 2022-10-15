package com.sena.disquerasc.controllers;

import java.util.List;

import javax.validation.Valid;

import com.sena.disquerasc.model.Cliente;
import com.sena.disquerasc.model.TipoDocumento;
import com.sena.disquerasc.service.IClienteService;
import com.sena.disquerasc.service.ITipoDocService;

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
@SessionAttributes("cliente")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IClienteService cliented;

    @Autowired
    private ITipoDocService tipodoc;    

    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("clientes", cliented.findAll());
        return "cliente/listar";    
    }

    @GetMapping("/ver-cliente/{id}")
public String verCliente(@PathVariable Integer id,Model m){
Cliente cliente=null;
if(id>0){
cliente=cliented.findOne(id);
}else{
return "redirect:listar";
}
m.addAttribute("cliente",cliente);
m.addAttribute("accion", "Detalle cliente");
return "cliente/verc";
}

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
    Cliente cliente=null;
    if(id>0){
    cliente=cliented.findOne(id);
    }else{
    return "redirect:listar";
    }
    List<TipoDocumento> listatd = tipodoc.findAll();
    m.addAttribute("cliente",cliente);
    m.addAttribute("tiposdoc", listatd);
    m.addAttribute("accion", "Actualizar Cliente");
    return "cliente/form";
    }

    @GetMapping("/form")
    public String form(Model m){
    Cliente cliente=new Cliente();
    List<TipoDocumento> listatd = tipodoc.findAll();
    m.addAttribute("cliente", cliente);
    m.addAttribute("tiposdoc", listatd);
    m.addAttribute("accion", "Agregar Cliente");
    return "cliente/form";
    }
    @PostMapping("/add")
    public String add(@Valid Cliente cliente,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "cliente/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        cliented.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

		if(id > 0) {
			cliented.delete(id);
		}
		return "redirect:/cliente/listar";
	}
}
