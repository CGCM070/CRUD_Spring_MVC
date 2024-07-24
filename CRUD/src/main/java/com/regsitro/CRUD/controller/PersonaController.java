package com.regsitro.CRUD.controller;

import com.regsitro.CRUD.model.Persona;
import com.regsitro.CRUD.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping()
    public String list(Model model) {
        model.addAttribute("personas", personaService.listAll());
        return "persona-list";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("persona", new Persona());
        return "persona-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Persona persona, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("persona", persona);
            return "persona-form";
        }
        //  Verificar si es una actualización y el correo ya existe y pertenece a la misma persona
        if (persona.getId() != null && personaService.existsByEmailExceptId(persona.getEmail(), persona.getId())) {
            model.addAttribute("persona", persona);
            model.addAttribute("errorMessage", "Ya existe una persona con este email ingresado");
            return "persona-form";

        } else if (persona.getId() == null && personaService.ExistsByEmail(persona.getEmail())) {
            model.addAttribute("persona", persona);
            model.addAttribute("errorMessage", "Ya existe una persona con este email ingresado");
            return "persona-form";
        }
        personaService.save(persona);
        return "redirect:/personas";
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioActualizar(@PathVariable long id, Model model) {
        Persona persona = personaService.get(id);
        model.addAttribute("persona", persona);
        return "persona-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable long id) {
        personaService.delete(id);
        return "redirect:/personas";
    }
}
