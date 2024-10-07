package com.example.Parte1Parcial.repositories;

import com.example.Parte1Parcial.entities.pruebasADN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mutanteRepository extends JpaRepository<pruebasADN,Long> {

}
