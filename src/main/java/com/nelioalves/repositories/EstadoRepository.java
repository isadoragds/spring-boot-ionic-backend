package com.nelioalves.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
