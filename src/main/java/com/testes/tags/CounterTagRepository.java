package com.testes.tags;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounterTagRepository extends JpaRepository<CounterTagEntity, Long> {

    List<CounterTagEntity> findByUrl(String url);
    
}
