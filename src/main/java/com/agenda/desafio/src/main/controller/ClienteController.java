package com.agenda.desafio.src.main.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.agenda.desafio.src.main.dto.cliente.DadosCadastroCliente;
import com.agenda.desafio.src.main.dto.cliente.DadosDetalhamentoCliente;
import com.agenda.desafio.src.main.dto.cliente.DadosPesquisa;
import com.agenda.desafio.src.main.dto.cliente.DadosUpateCliente;
import com.agenda.desafio.src.main.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	private final ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
		var dto = service.criar(dados);
		var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping("/buscar")
	public Page<DadosDetalhamentoCliente> buscarPorParametro(@RequestBody @Valid DadosPesquisa dados, Pageable pageable) {
	    if (dados.parametro().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
	        DadosDetalhamentoCliente cliente = service.buscarPorCpf(dados.parametro());
	        return new PageImpl<>(List.of(cliente)); 
	    }

	    return service.buscarPorNome(pageable, dados.parametro());
	}

	
	@GetMapping
	public Page<DadosDetalhamentoCliente> listarClientes(Pageable pageable) {
		Page<DadosDetalhamentoCliente> clientes = service.listarTodos(pageable);
		return clientes;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id) {
		DadosDetalhamentoCliente cliente = service.buscarPorId(id);
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosUpateCliente dados) {
		DadosDetalhamentoCliente update = service.update(dados, id);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar (@PathVariable Long id){
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
