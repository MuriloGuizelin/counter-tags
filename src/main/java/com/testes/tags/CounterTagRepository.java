package com.testes.tags;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterTagRepository extends JpaRepository<CounterTagEntity, String> {
}
