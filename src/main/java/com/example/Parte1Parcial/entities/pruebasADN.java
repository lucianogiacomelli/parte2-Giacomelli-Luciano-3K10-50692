package com.example.Parte1Parcial.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class pruebasADN implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;


    private String [] prueba;

    private boolean resultado;


}
