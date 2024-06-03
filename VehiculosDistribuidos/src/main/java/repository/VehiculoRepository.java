/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

/**
 *
 * @author adriansobrevela
 */
package VehiculosDistribuidos.repository;

import com.example.vehiculosDistribuidos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    @Query("SELECT AVG(v.potencia) FROM Vehiculo v WHERE v.tipo = :tipo")
    Double findPotenciaMediaByTipo(String tipo);

    @Query("SELECT MAX(v.potencia) FROM Vehiculo v WHERE v.tipo = :tipo")
    Integer findPotenciaMaximaByTipo(String tipo);
}

