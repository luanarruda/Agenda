package com.fatec.contato.dtos;

import java.time.LocalDate;

public record ContatoResponse( int id, String name, String telefone, String email, String endereco, String categoria, LocalDate aniversario) {

}
