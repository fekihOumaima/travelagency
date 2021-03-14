package com.ditraacademy.travelagency.core.Chambre.CategorieChambre;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServices {

    @Autowired
    CategorieChambreRepository categorieChambreRepository;



    public  ResponseEntity<?> createCategorieChambre(CategorieChambre categorieChambre) {
        categorieChambre = categorieChambreRepository.save(categorieChambre);
        return new ResponseEntity<>(categorieChambre, HttpStatus.OK);
    }

    public List<CategorieChambre> getCategorieChambre(){

        return categorieChambreRepository.findAll();
    }

    public  ResponseEntity<?> getOneCategorieChambre(int id){

        Optional<CategorieChambre> categoriechambreOptional = categorieChambreRepository.findById(id);

        if (!categoriechambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("categorie chambre not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        CategorieChambre categorieChambre = categoriechambreOptional.get();
        return new ResponseEntity<>(categorieChambre, HttpStatus.OK);
    }



    public  ResponseEntity<?> updateCategorieChambre(int id, CategorieChambre updateCategorieChambre){

        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findById(id);

        if (!categorieChambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("categorie chambre not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        categorieChambreRepository.save(updateCategorieChambre);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public  ResponseEntity<?> deleteCategorieChambre(int id){

        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findById(id);

        if (!categorieChambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("chambre categorie not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        categorieChambreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
