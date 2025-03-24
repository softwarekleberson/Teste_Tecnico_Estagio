package com.agenda.desafio.src.main.infra.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.desafio.src.main.domain.entity.contato.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
