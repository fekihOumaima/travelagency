package com.ditraacademy.travelagency.core.Chambre.Chambre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ChambreController {
    @Autowired
    ChambreServices chambreServices;

    @PostMapping("/chambre")
    public ResponseEntity<?> createChambre(@RequestBody Chambre chambre){
        return chambreServices.createChambre(chambre);
    }

    @GetMapping("/chambre")
    public List<Chambre> getChambre(){
        return chambreServices.getChambre();
    }

    @GetMapping("/chambre/{id}")
    public ResponseEntity<?> getOneChambre(@PathVariable int id){
         return chambreServices.getOneChambre(id);
    }


    @PutMapping("/chambre/{id}")
    public ResponseEntity<?> updateChambre(@PathVariable int id,@RequestBody Chambre updateChambre) {
        return chambreServices.updateChambre(id,updateChambre);
    }

    @DeleteMapping("/chambre/{id}")
    public ResponseEntity<?> deleteChambre(@PathVariable int id ){
        return chambreServices.deleteChambre(id);
    }
}
