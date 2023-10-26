package com.unisinu.evaluaciontesis.usuarios.repository;

import jakarta.inject.Singleton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;


@Singleton
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
