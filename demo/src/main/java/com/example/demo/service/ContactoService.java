package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Contacto;
import com.example.demo.repositories.ContactoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {
    @Autowired
    private ContactoRepository contactoRepository;

    // 1. Agregar una red social que le pertenece a una persona
    public Contacto agregarRedSocial(Contacto contacto) {
        return contactoRepository.save(contacto);  // Guardar la red social en la base de datos
    }

    // 2. Actualizar una red social existente
    public Contacto actualizarRedSocial(Long idContacto, Contacto detallesContacto) {
        Optional<Contacto> contactoExistente = contactoRepository.findById(idContacto);

        if (contactoExistente.isPresent()) {
            Contacto contactoActualizado = contactoExistente.get();
            contactoActualizado.setRedSocial(detallesContacto.getRedSocial());
            contactoActualizado.setCuenta(detallesContacto.getCuenta());  // Actualizar la cuenta de la red social
            return contactoRepository.save(contactoActualizado);  // Guardar cambios
        }
        return null;  // O lanzar una excepción personalizada si prefieres
    }

    // 3. Eliminar una red social
    public boolean eliminarRedSocial(Long idContacto) {
        Optional<Contacto> contacto = contactoRepository.findById(idContacto);
        if (contacto.isPresent()) {
            contactoRepository.delete(contacto.get());
            return true;
        }
        return false;
    }

    // 4. Traer el nombre de la cuenta de una red social por su ID
    public String obtenerNombreCuentaPorId(Long idContacto) {
        Optional<Contacto> contacto = contactoRepository.findById(idContacto);
        return contacto.map(Contacto::getCuenta).orElse(null);  // Retorna el nombre de la cuenta si existe
    }

    // 5. Obtener todas las redes sociales que tiene guardadas
    public List<Contacto> obtenerTodasLasRedesSociales() {
        return contactoRepository.findAll();  // Retorna todas las redes sociales guardadas
    }
}
