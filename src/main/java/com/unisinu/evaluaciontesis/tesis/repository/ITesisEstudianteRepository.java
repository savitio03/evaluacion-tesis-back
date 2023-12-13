package com.unisinu.evaluaciontesis.tesis.repository;

import com.unisinu.evaluaciontesis.tesis.models.entidades.Tesis;
import com.unisinu.evaluaciontesis.tesis.models.entidades.TesisEstudiante;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import jakarta.inject.Singleton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Singleton
@Repository
public interface ITesisEstudianteRepository extends JpaRepository<TesisEstudiante, Long> {

    List<TesisEstudiante> findAllByUsuarioDTOEstudiante(Usuario estudiante);

    List<TesisEstudiante> findAllByTesisDTOEstudiante(Tesis tesis);
    List<TesisEstudiante> findAllByTesisDTOEstudianteIn(List<Tesis> listaTesis);
}
