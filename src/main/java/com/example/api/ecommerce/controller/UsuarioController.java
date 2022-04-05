package com.example.api.ecommerce.controller;

import com.example.api.ecommerce.model.Usuario;
import com.example.api.ecommerce.repository.UsuarioRepository;
import com.example.api.ecommerce.request.UsuarioRequest;
import com.example.api.ecommerce.response.UsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;


    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/{email}")
    private UsuarioResponse obtemUsuario(@PathVariable String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com o email fornecido não encontrado"));

        return new UsuarioResponse(usuario);
    }

    @PostMapping
    private ResponseEntity<UsuarioResponse> registraUsuario(@Validated @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRepository.save(usuarioRequest.toUsuario());

        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioResponse(usuario));
    }



}
