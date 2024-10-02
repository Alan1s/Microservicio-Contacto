package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Contacto;
import org.springframework.stereotype.Repository;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
