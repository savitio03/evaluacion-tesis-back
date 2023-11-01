package com.unisinu.evaluaciontesis.tesis.repository;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.tesis.models.entidades.Tesis;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import jakarta.inject.Singleton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Singleton
@Repository
public interface ITesisRepository extends JpaRepository<Tesis, Long> {

    List<Tesis> findAllByEstudiante(Usuario estudiante);

    List<Tesis> findAllByEvaluador(Usuario evaluador);

    List<Tesis> findAllByProgramaEnum(ProgramaEnum programaEnum);
}
