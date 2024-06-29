package com.fatec.contato.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ContatoRequest(@NotNull(message = "Nome não pode ser nulo") 
String name,
@NotNull(message = "Telefone não pode ser nulo")
String telefone,
@NotNull(message = "Email não pode ser nulo")
String email,
@NotNull(message = "Endereço não pode ser nulo")
String endereco,
String categoria,
LocalDate aniversario){
    
}