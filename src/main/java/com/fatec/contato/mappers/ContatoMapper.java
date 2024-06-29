package com.fatec.contato.mappers;

import com.fatec.contato.Contato;
import com.fatec.contato.dtos.ContatoRequest;
import com.fatec.contato.dtos.ContatoResponse;

public class ContatoMapper {
    public static Contato toEntity(ContatoRequest request) {
        Contato contato = new Contato();

        contato.setName(request.name());
        contato.setTelefone(request.telefone());
        contato.setEmail(request.email());
        contato.setEndereco(request.endereco());
        contato.setCategoria(request.categoria());
        contato.setAniversario(request.aniversario());
        return contato;
    }

    public static ContatoResponse toDTO(Contato contato){
        return new ContatoResponse(contato.getId(),
        contato.getName(), contato.getTelefone(),contato.getEmail(),contato.getEndereco(), contato.getCategoria(), contato.getAniversario());
    }
}
