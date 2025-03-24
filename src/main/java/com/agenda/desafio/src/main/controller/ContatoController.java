package com.agenda.desafio.src.main.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agenda.desafio.src.main.dto.contato.DadosCadastroContato;
import com.agenda.desafio.src.main.dto.contato.DadosDetalhamentoContato;
import com.agenda.desafio.src.main.dto.contato.DadosUpateContato;
import com.agenda.desafio.src.main.service.ContatoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("contatos")
@CrossOrigin(origins = "*")
public class ContatoController {

	private final ContatoService service;
	
	public ContatoController(ContatoService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroContato dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criar(dados);
		var uri = uriBuilder.path("/contato/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping("/{id}")
	public DadosDetalhamentoContato encontrarPeloId(@PathVariable Long id) {
		DadosDetalhamentoContato contato = service.encontrarPeloId(id);
		return contato;
	}
	
	@GetMapping
	public Page<DadosDetalhamentoContato> listarContatos(Pageable pageable) {
		Page<DadosDetalhamentoContato> clientes = service.listarTodos(pageable);
		return clientes;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosUpateContato dados) {
		DadosDetalhamentoContato update = service.update(dados, id);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar (@PathVariable Long id){
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
