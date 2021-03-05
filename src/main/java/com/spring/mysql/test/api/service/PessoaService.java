package com.spring.mysql.test.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.spring.mysql.test.api.model.Pessoa;
import com.spring.mysql.test.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloID(id);		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");		
		return pessoaRepository.save(pessoaSalva);
	}


	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {		
		Pessoa pessoaSalva = buscarPessoaPeloID(id);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
		
		
	}

	private Pessoa buscarPessoaPeloID(Long id) {
		Pessoa pessoaSalva = pessoaRepository.findById(id).get();		
		if(pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
