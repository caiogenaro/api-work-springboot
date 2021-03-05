package com.spring.mysql.test.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mysql.test.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {	

}
