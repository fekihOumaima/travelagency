package com.ditraacademy.travelagency.core.Chambre.CategorieChambre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategorieController {
    @Autowired
    CategorieServices categorieServices;
    @PostMapping("/CategorieChambre")
    public ResponseEntity<?> createCategorieChambre(@RequestBody CategorieChambre categorieChambre){
        return categorieServices.createCategorieChambre(categorieChambre);
    }

    @GetMapping("/categorieChambre/{id}")
    public ResponseEntity<?> getOneCategorieChambre(@PathVariable int id){
        return categorieServices.getOneCategorieChambre(id);
    }

    @GetMapping("/categorieChambre")
    public List<CategorieChambre> getCategorieChambre(){
        return categorieServices.getCategorieChambre();
    }


    @PutMapping("/CategorieChambre/{id}")
    public ResponseEntity<?> updateCategorieChambre(@PathVariable int id,@RequestBody CategorieChambre updateCategorieChambre) {
        return categorieServices.updateCategorieChambre(id,updateCategorieChambre);
    }

    @DeleteMapping("/CategorieChambre/{id}")
    public ResponseEntity<?> deleteCategorieChambre(@PathVariable int id ){
        return categorieServices.deleteCategorieChambre(id);
    }
}
