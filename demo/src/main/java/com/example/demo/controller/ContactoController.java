package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Contacto;
import com.example.demo.service.ContactoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacto")
public class ContactoController {
    @Autowired
    private ContactoService contactoService;

    // 1. Agregar una nueva red social
    @PostMapping("/agregar")
    public ResponseEntity<Contacto> agregarRedSocial(@RequestBody Contacto contacto) {
        Contacto nuevoContacto = contactoService.agregarRedSocial(contacto);
        return new ResponseEntity<>(nuevoContacto, HttpStatus.CREATED);
    }

    // 2. Actualizar una red social existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Contacto> actualizarRedSocial(@PathVariable Long id, @RequestBody Contacto contacto) {
        Contacto contactoActualizado = contactoService.actualizarRedSocial(id, contacto);
        if (contactoActualizado != null) {
            return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3. Eliminar una red social por su ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarRedSocial(@PathVariable Long id) {
        boolean eliminado = contactoService.eliminarRedSocial(id);
        if (eliminado) {
            return new ResponseEntity<>("Red social eliminada exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Red social no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    // 4. Obtener el nombre de la cuenta de una red social por su ID
    @GetMapping("/cuenta/{id}")
    public ResponseEntity<String> obtenerNombreCuentaPorId(@PathVariable Long id) {
        String cuenta = contactoService.obtenerNombreCuentaPorId(id);
        if (cuenta != null) {
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Red social no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    // 5. Obtener todas las redes sociales guardadas
    @GetMapping("/listar")
    public ResponseEntity<List<Contacto>> obtenerTodasLasRedesSociales() {
        List<Contacto> contactos = contactoService.obtenerTodasLasRedesSociales();
        return new ResponseEntity<>(contactos, HttpStatus.OK);
    }
}
