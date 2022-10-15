package com.sena.disquerasc.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.validation.Valid;

import com.sena.disquerasc.model.Artista;
import com.sena.disquerasc.model.Disquera;
import com.sena.disquerasc.model.TipoDocumento;
import com.sena.disquerasc.service.IArtistaService;
import com.sena.disquerasc.service.IDisqueraService;
import com.sena.disquerasc.service.ITipoDocService;
import com.sena.disquerasc.service.IUploadFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("artista")
@RequestMapping("/artista")
public class ArtistaController {
    @Autowired
    private IArtistaService artistad;

    @Autowired
    private ITipoDocService tipodoc;

    @Autowired
    private IDisqueraService disquerad;
    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value="/uploads/{filename:.+}")    
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }
    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("artista", artistad.findAll());
        return "artista/listar";    
    }

    @GetMapping("/ver-artista/{id}")
public String verArtista(@PathVariable Integer id,Model m){
Artista artista=null;
if(id>0){
artista=artistad.findOne(id);
}else{
return "redirect:listar";
}
m.addAttribute("artista",artista);
m.addAttribute("accion", "Detalle del artista");
return "artista/verc";
}

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id,Model m){
        Artista artista=null;
        if(id>0){
            artista=artistad.findOne(id);
        }else{
            return "redirect:listar";
        }
        List<TipoDocumento>listatd=tipodoc.findAll();
        List<Disquera>listads=disquerad.findAll();
        m.addAttribute("ds", listads);
        m.addAttribute("td", listatd);
        m.addAttribute("artista",artista);
        m.addAttribute("accion", "Actualizar artista");
        return "artista/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Artista artista=new Artista();
        List<TipoDocumento>listatd=tipodoc.findAll();
        List<Disquera>listads=disquerad.findAll();
        m.addAttribute("ds", listads);
        m.addAttribute("td", listatd);
        m.addAttribute("artista", artista);
        m.addAttribute("accion", "Agregar artista");
        return "artista/form"; 
    }
    @PostMapping("/add")
    public String add(@Valid Artista  artista,BindingResult res, Model m, @RequestParam("file") MultipartFile foto,
    SessionStatus status){
        if(res.hasErrors()){
            return "artista/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";  cancion album*/
        if (!foto.isEmpty()) {
            if (artista.getId() != null && artista.getId() > 0 && artista.getFoto() != null
                    && artista.getFoto().length() > 0) {
                // Si hay un archivo previo se elimina
                uploadFileService.delete(artista.getFoto());
            }
            // Si no hay archivos definimos un nombre único con el método copy
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Guardamos el archivo con el nombre nuevo en el objeto cliente
            artista.setFoto(uniqueFilename);

        }
        artistad.save(artista);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
		
		if(id > 0) {
			artistad.delete(id);
            if (id > 0) {
                //Buscamos el registro a eliminar
                Artista artista = artistad.findOne(id);
                //Eliminamos el registro
                artistad.delete(id);
                //Verificamos si tiene un archivo asociado y lo eliminamos del directorio
                if (uploadFileService.delete(artista.getFoto())) {
                    
                }
    
            }
		}
		return "redirect:/artista/listar";
	}
    
    
}
