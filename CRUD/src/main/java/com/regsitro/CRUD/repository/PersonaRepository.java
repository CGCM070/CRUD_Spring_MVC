package com.regsitro.CRUD.repository;

import com.regsitro.CRUD.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository  extends JpaRepository<Persona, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot (String email, Long id);
}
