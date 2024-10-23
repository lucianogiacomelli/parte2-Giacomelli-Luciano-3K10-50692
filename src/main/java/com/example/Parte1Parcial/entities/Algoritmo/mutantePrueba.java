package com.example.Parte1Parcial.entities.Algoritmo;

import lombok.*;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Getter
@Setter
@NoArgsConstructor
@Component //Se utiliza esta notacion para que sea un singleton

public class mutantePrueba implements Serializable {


    private Set<String> secuenciasVerdaderas = new HashSet<>(); //Utilizo este HashSet ya que no permite almacenar elementos que se añadieron anteriormente, por lo tanto me sirve para poder verificar las sentencias encontradas en el dna

    //Metodo principal
    public boolean esMutante (String[] dna) throws Exception {

        errores(dna);
        secuenciasVerdaderas.clear();
        int n = dna.length;
        int secuencias = 0;




       for (int i = 0 ; i < n ; i++){

           if (RevisionHorizontal(dna, i)) {
               secuencias = secuencias + 1;
           }
           if (RevisionVertical(dna, i)) {
               secuencias = secuencias + 1;
           }
           secuencias = RevisarDiagonales(dna) + secuencias;

       }


        return secuenciasVerdaderas.size() >= 2;
    }



    private boolean RevisionHorizontal(String[] dna, int row) {
        return IntStream.range(0, dna[row].length() - 3)
                .anyMatch(i -> Secuencia(dna[row].charAt(i), dna[row].charAt(i + 1), dna[row].charAt(i + 2), dna[row].charAt(i + 3)));
    }

    private boolean RevisionVertical(String[] dna, int col) {
        return IntStream.range(0, dna.length - 3)
                .anyMatch(i -> Secuencia(dna[i].charAt(col), dna[i + 1].charAt(col), dna[i + 2].charAt(col), dna[i + 3].charAt(col)));
    }

    private int RevisarDiagonales(String[] dna) {
        int n = dna.length;
        int secuenciasEncontradas = 0;
        boolean diagonalPrimaria = IntStream.range(0, n - 3)
                .anyMatch(i -> IntStream.range(0, n - 3)
                        .anyMatch(j -> Secuencia(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))));
        if (diagonalPrimaria){
            secuenciasEncontradas = secuenciasEncontradas + 1;
        }

        boolean diagonalSecundaria = IntStream.range(0, n - 3)
                .anyMatch(i -> IntStream.range(3, n)
                        .anyMatch(j -> Secuencia(dna[i].charAt(j), dna[i + 1].charAt(j - 1), dna[i + 2].charAt(j - 2), dna[i + 3].charAt(j - 3))));
        if (diagonalSecundaria ){

            secuenciasEncontradas = secuenciasEncontradas + 1;
        }
        return secuenciasEncontradas;
    }


    private boolean Secuencia(char a, char b, char c, char d) {
        if( a == b && b == c && c == d){
            String secuencia = ""+a+b+c+d;
            secuenciasVerdaderas.add(secuencia);
            return true;
        } else {
            return false;
        }
    }

    private void errores(String []dna) throws IllegalArgumentException {
        if (dna == null) {
            throw new IllegalArgumentException("El array de ADN es nulo");
        }

        if (dna.length == 0) {
            throw new IllegalArgumentException("El array de ADN está vacío");
        }

        int n = dna.length;

        for (String fila : dna) {
            if (fila == null) {
                throw new IllegalArgumentException("El array contiene filas nulas");
            }
            if (fila.length() != n) {
                throw new IllegalArgumentException("El array de ADN debe ser NxN");
            }
            if (!fila.matches("[ATGC]+")) {
                throw new IllegalArgumentException("El ADN contiene caracteres inválidos. Solo se permiten A, T, G, C");
            }
            if (fila.matches("[0-9]+")) {
                throw new IllegalArgumentException("El array de ADN contiene números en lugar de letras");
            }
        }
    }
}