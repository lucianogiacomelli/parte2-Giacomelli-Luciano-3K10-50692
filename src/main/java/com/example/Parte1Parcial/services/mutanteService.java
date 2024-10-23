package com.example.Parte1Parcial.services;
import com.example.Parte1Parcial.entities.pruebasADN;
import com.example.Parte1Parcial.repositories.mutanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.Parte1Parcial.entities.Algoritmo.mutantePrueba;

import java.util.List;
import java.util.Optional;


@Service
public class mutanteService implements BaseService<pruebasADN> {

    private mutanteRepository mutanteRepository;
    private mutantePrueba mutantePrueba;
    public mutanteService(mutanteRepository mutanteRepository, mutantePrueba mutanteprueba){
        this.mutanteRepository = mutanteRepository;
        this.mutantePrueba = mutanteprueba;
    }



    @Override
    @Transactional
    public List<pruebasADN> findAll() throws Exception {
    try{
        List <pruebasADN> entities = mutanteRepository.findAll();
        return entities;

    } catch (Exception e){
        throw new Exception(e.getMessage());
    }
    }

    @Override
    @Transactional
    public pruebasADN findByID(Long id) throws Exception {
        try{
            Optional<pruebasADN> entityOptional = mutanteRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }




    @Override
    @Transactional
    public pruebasADN save(pruebasADN entity) throws Exception {
        try{
            boolean esMutante = mutantePrueba.esMutante(entity.getPrueba());
            entity.setResultado(esMutante);

            entity = mutanteRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public pruebasADN update(Long id, pruebasADN entity) throws Exception {
        try{
            Optional <pruebasADN> entityOptional = mutanteRepository.findById(id);
            pruebasADN pruebas = entityOptional.get();
            pruebas = mutanteRepository.save(entity);
            return pruebas;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if (mutanteRepository.existsById(id)){
                mutanteRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


@Override
@Transactional
public boolean mutant(String[] dna) throws Exception {
    // No capturar excepciones aquí, solo propaga
    return mutantePrueba.esMutante(dna);
}

}












































/*
    @Override
    @Transactional
    public boolean mutant(String[] dna) throws Exception {
        try{
            return mutantePrueba.esMutante(dna);
        } catch (Exception e){
            System.out.println("Da error");
            throw new Exception(e.getMessage());
        }
    }
*/

/*
    @Override
    @Transactional
    public boolean mutant(String[] dna) throws Exception {
        try {
            System.out.println("ADN recibido: " + Arrays.toString(dna));
            return mutantePrueba.esMutante(dna);
        } catch (Exception e) {
            System.out.println("Error en el método mutant: " + e.getMessage());
            e.printStackTrace(); // Imprime el stacktrace completo para más detalles
            throw new Exception("Error en la lógica de detección del mutante: " + e.getMessage());
        }
    }
*/