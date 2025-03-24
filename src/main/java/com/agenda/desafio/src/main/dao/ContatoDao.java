package com.agenda.desafio.src.main.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.agenda.desafio.src.main.domain.entity.contato.Contato;
import com.agenda.desafio.src.main.domain.entity.contato.RepositorioContato;
import com.agenda.desafio.src.main.dto.cliente.DadosDetalhamentoCliente;
import com.agenda.desafio.src.main.dto.contato.DadosDetalhamentoContato;
import com.agenda.desafio.src.main.dto.contato.DadosUpateContato;
import com.agenda.desafio.src.main.infra.persistencia.ContatoRepository;

import jakarta.transaction.Transactional;

@Service
public class ContatoDao implements RepositorioContato{

	private final ContatoRepository repository;
	
	public ContatoDao(ContatoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Contato salvar(Contato contato) {
		return repository.save(contato);
	}

	@Transactional
	@Override
	public Contato alterar(Long id, DadosUpateContato dados) {
		Contato contato = repository.findById(id)
		.orElseThrow(() 
		-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		boolean atualizado = false;
		
		if(dados.valor() != null && !dados.valor().equals(contato.getValor())) {
			contato.setValor(dados.valor());
			atualizado = true;
		}
		
		if(dados.observacao() != null && !dados.observacao().equals(contato.getObservacao())) {
			contato.setObservacao(dados.observacao());
			atualizado = true;
		}
		
		return atualizado ? repository.save(contato) : contato;
	}

	@Transactional
	@Override
	public void deletar(Long id) {
		Contato contato = repository.findById(id)
	    .orElseThrow(() 
		-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		repository.delete(contato);
	}

	@Override
	public Page<DadosDetalhamentoContato> listar(Pageable pageable) {
		return repository.findAll(pageable)
			   .map(DadosDetalhamentoContato::new);
	}

	@Override
	public DadosDetalhamentoContato buscarPeloId(Long id) {
		return repository.findById(id)
				   .map(DadosDetalhamentoContato::new)
				   .orElseThrow(() 
				   -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
