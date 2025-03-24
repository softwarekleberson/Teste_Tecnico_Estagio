package com.agenda.desafio.src.main.dto.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.agenda.desafio.src.main.domain.entity.cliente.Cliente;
import com.agenda.desafio.src.main.dto.contato.DadosDetalhamentoContato;

	public record DadosDetalhamentoCliente(
        Long id,
        String nome,
        String cpf,
        LocalDate nascimento,
        String endereco,
        List<DadosDetalhamentoContato> contatos
	) {
		
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getDataNascimento() != null ? cliente.getDataNascimento() : null,
            cliente.getEndereco() != null ? cliente.getEndereco().getEndereco() : null,
            cliente.getContatos().stream()
                    .map(DadosDetalhamentoContato::new)
                    .collect(Collectors.toList())
        );
    }
}
