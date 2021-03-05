package com.spring.mysql.test.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mysql.test.api.event.RecursoCriadoEvent;
import com.spring.mysql.test.api.model.Lancamentos;
import com.spring.mysql.test.api.repository.LancamentoRepository;

@RestController
@RequestMapping(value="/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Lancamentos> listar(){
		return lancamentoRepository.findAll();
	}
	
	@PostMapping	
	public ResponseEntity<Lancamentos> Criar(@Valid @RequestBody Lancamentos lancamentos, HttpServletResponse response) {
		Lancamentos lancamentoSalvo = lancamentoRepository.save(lancamentos);			
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);		
	}
	
	@GetMapping("/{id}")
	public Lancamentos buscarPeloCodigo(@PathVariable Long id) {
		return lancamentoRepository.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		lancamentoRepository.deleteById(id);
	}
	

}
