package com.example.Parte1Parcial.services;

import java.util.List;

public interface BaseService <E>{
    public List <E> findAll() throws Exception;
    public E findByID(Long id) throws Exception;
    public E save (E entity) throws Exception;
    public E update (Long id, E entity) throws Exception;
    public boolean delete (Long id) throws Exception;
    public boolean mutant (String [] dna) throws Exception;
}
