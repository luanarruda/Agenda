package com.fatec.contato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.contato.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}