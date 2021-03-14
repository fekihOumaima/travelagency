package com.ditraacademy.travelagency.core.Chambre.TypeChambre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TypeController {
    @Autowired
    TypeServices typeChambreServices;

    @PostMapping("/type")
    public ResponseEntity<?> createTypeChambre(@RequestBody TypeChambre typeChambre){
        return typeChambreServices.createTypeChambre(typeChambre);
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<?> getTypeChambre(@PathVariable int id){
        return typeChambreServices.getTypeChambre(id);
    }


    @PutMapping("/type/{id}")
    public ResponseEntity<?> updateTypeChamre(@PathVariable int id,@RequestBody TypeChambre updateTypeChambre) {
        return typeChambreServices.updateTypeChambre(id,updateTypeChambre);
    }

    @DeleteMapping("/type/{id}")
    public ResponseEntity<?> deleteTypeChambre(@PathVariable int id ){
        return typeChambreServices.deleteTypeChambre(id);
    }
}
