package com.fatec.contato.services;

import java.util.stream.Collectors;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.contato.Contato;
import com.fatec.contato.dtos.ContatoRequest;
import com.fatec.contato.dtos.ContatoResponse;
import com.fatec.contato.mappers.ContatoMapper;
import com.fatec.contato.repositories.ContatoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatoService {
    
    @Autowired
    private ContatoRepository contatoRepository;

    public List<ContatoResponse> getContatos() {
        List<Contato> contatos = contatoRepository.findAll();

        return contatos.stream().map(c -> ContatoMapper.toDTO(c)).collect(Collectors.toList());
    }

    public ContatoResponse getContatoById(int id) {
        Contato contato = contatoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contato não cadastrado"));

        return ContatoMapper.toDTO(contato);
    }

    public void deleteContatoById(int id) {
        if (this.contatoRepository.existsById(id)){
            this.contatoRepository.deleteById(id);
        }

        else {
            throw new EntityNotFoundException("Contato não cadastrado");
        }
    }

    public ContatoResponse saveContato(ContatoRequest request) {
        Contato contato = ContatoMapper.toEntity(request);

        return ContatoMapper.toDTO(this.contatoRepository.save(contato));
    }

    public void updateContato(int id, ContatoRequest request) {
        try {
            Contato aux = contatoRepository.getReferenceById(id);
            aux.setName(request.name());
            aux.setTelefone(request.telefone());
            aux.setEmail(request.email());
            aux.setEndereco(request.endereco());
            aux.setCategoria(request.categoria());
            aux.setAniversario(request.aniversario());
            this.contatoRepository.save(aux);
        }
        catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Aluno não cadastrado");
        }
    }

}
