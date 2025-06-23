package com.ecomarket.springboot.webapp.ecomarket_web.controllers;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Usuario;
import com.ecomarket.springboot.webapp.ecomarket_web.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario", description = "Operaciones CRUD sobre usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioRepository service;

    @Operation(
        summary = "Listar todos los usuarios",
        description = "Obtiene una lista completa de usuarios registrados"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado de usuarios obtenido correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
    )
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = service.findByAll();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(
        summary = "Obtener un usuario por ID",
        description = "Devuelve un usuario específico según el ID proporcionado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Usuario encontrado",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
    )
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = service.findById(id);
        return usuarioOptional.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Crea un nuevo usuario en el sistema"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Usuario creado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
    )
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario creado = service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(
        summary = "Actualizar un usuario existente",
        description = "Modifica los datos de un usuario determinado por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuarioOptional = service.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setCelular(usuarioDetails.getCelular());
            Usuario actualizado = service.save(usuario);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar un usuario",
        description = "Elimina un usuario específico del sistema según su ID"
    )
    @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


