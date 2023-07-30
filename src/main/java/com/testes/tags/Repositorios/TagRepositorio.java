package com.testes.tags.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testes.tags.Entidades.TagEntidade;

public interface TagRepositorio extends JpaRepository<TagEntidade, String>{
    
}
