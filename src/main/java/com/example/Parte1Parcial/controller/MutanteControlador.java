package com.example.Parte1Parcial.controller;


import com.example.Parte1Parcial.entities.pruebasADN;
import com.example.Parte1Parcial.services.mutanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/mutantes")
public class MutanteControlador {
    private mutanteService mutanteService;

    public MutanteControlador(mutanteService mutanteService){
        this.mutanteService = mutanteService;
    }
    //Métodos para crear, buscar, actualizar y borrar
    @GetMapping("")
    public ResponseEntity <?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.findAll());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.findByID(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }




    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody pruebasADN pruebas){
        try{
            pruebasADN pruebaGuardada = mutanteService.save(pruebas);

            if (pruebaGuardada.isResultado()){
                System.out.println("Es un mutante.");
                return ResponseEntity.status(HttpStatus.OK).body(pruebaGuardada);

            } else {

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(pruebaGuardada);

            }


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody pruebasADN pruebas){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mutanteService.update(id, pruebas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mutanteService.delete(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    //Ejercicio Solicitado

    @PostMapping("/mutant/")
    public ResponseEntity<?> verificarMutante(@RequestBody pruebasADN pruebasADN){
        try{

            boolean esMutante = mutanteService.mutant(pruebasADN.getPrueba());
            if (esMutante){
                return ResponseEntity.status(HttpStatus.OK).body("Es mutante");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
            }
        } catch (IllegalArgumentException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}
