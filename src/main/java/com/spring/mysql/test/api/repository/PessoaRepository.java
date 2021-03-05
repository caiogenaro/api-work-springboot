package com.spring.mysql.test.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mysql.test.api.model.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
