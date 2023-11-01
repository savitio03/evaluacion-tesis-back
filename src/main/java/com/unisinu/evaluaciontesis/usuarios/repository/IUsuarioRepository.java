package com.unisinu.evaluaciontesis.usuarios.repository;

import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import jakarta.inject.Singleton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Singleton
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM EVA_USUARIO u " +
            "WHERE (:nombre IS NULL OR u.EVA_COLUMN_NOMBRE LIKE %:nombre%) " +
            "AND (:numeroCarnet IS NULL OR u.EVA_COLUMN_CODIGOCARNET = :numeroCarnet) " +
            "AND (:programa IS NULL OR u.EVA_COLUMN_PROGRAMAENUM = :programa) " +
            "AND (:numeroIdentificacion IS NULL OR u.EVA_COLUMN_NUMEROIDENTIFICACION = :numeroIdentificacion) " +
            "AND (:tipoIdentificacion IS NULL OR u.EVA_COLUMN_TIPOIDENTIFICACIONENUM = :tipoIdentificacion)",
            nativeQuery = true)
    List<Usuario> findByFilters(
            String nombre,
            String numeroCarnet,
            String programa,
            String numeroIdentificacion,
            String tipoIdentificacion);


}
