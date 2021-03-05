package com.spring.mysql.test.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mysql.test.api.model.Lancamentos;


public interface LancamentoRepository extends JpaRepository<Lancamentos, Long>{

}
