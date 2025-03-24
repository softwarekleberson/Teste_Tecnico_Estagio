package com.agenda.desafio.src.main.infra.persistencia;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.desafio.src.main.domain.entity.cliente.Cliente;
import com.agenda.desafio.src.main.dto.cliente.DadosDetalhamentoCliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Optional<DadosDetalhamentoCliente> findByCpf(String cpf);
	Page<Cliente> findByNome(Pageable pageable, String nome);

}
