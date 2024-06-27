package com.fatec.contato.dtos;

import java.time.LocalDate;

public record ContatoRequest(String name, String telefone, String email, String endereco, String categoria, LocalDate aniversario){
    
}