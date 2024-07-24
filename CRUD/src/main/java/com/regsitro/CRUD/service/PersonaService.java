package com.regsitro.CRUD.service;

import com.regsitro.CRUD.model.Persona;
import com.regsitro.CRUD.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;


    public List<Persona> listAll() {
        return personaRepository.findAll();
    }

    public  boolean ExistsByEmail(String email) {
        return personaRepository.existsByEmail(email);
    }
    public boolean existsByEmailExceptId(String email, Long id){
        return personaRepository.existsByEmailAndIdNot(email,id);
    }


    public void save(Persona persona) {
        Persona existe = personaRepository.findById(persona.getId()).orElse(null);
        personaRepository.save(persona);
    }

    public Persona get(long id) {
        return personaRepository.findById(id).orElse(null);
    }

    public void delete(long id) {
        personaRepository.deleteById(id);
    }


}
