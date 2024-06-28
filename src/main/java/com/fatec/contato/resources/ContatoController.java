package com.fatec.contato.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.contato.Contato;
import com.fatec.contato.dtos.ContatoRequest;
import com.fatec.contato.dtos.ContatoResponse;
import com.fatec.contato.services.ContatoService;

@RestController
@RequestMapping
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<ContatoResponse> getContatos(){
        return contatoService.getContatos();
    }

    @GetMapping("{id}")
    public ContatoResponse getContatoById(@PathVariable int id){
        return contatoService.getContatoById(id);
    }

    @DeleteMapping
    public void deleteContatoById(@PathVariable int id){
        this.contatoService.deleteContatoById(id);
    }

    @PostMapping
    public ContatoResponse saveContato(@RequestBody ContatoRequest contato){
        return this.contatoService.saveContato(contato);
    }

    @PutMapping("{id}")
    public void updateContato(@PathVariable int id, @RequestBody ContatoRequest contato) {
        this.contatoService.updateContato(id, contato);
    }

}
