package com.ditraacademy.travelagency.core.Chambre.Chambre;

import com.ditraacademy.travelagency.core.Chambre.CategorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.Chambre.CategorieChambre.CategorieChambreRepository;
import com.ditraacademy.travelagency.core.Chambre.TypeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.Chambre.TypeChambre.TypeChambreRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class ChambreServices {
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    CategorieChambreRepository categorieChambreRepository;
    @Autowired
    TypeChambreRepository typeChambreRepository;
   

    public ResponseEntity<?> createChambre(Chambre chambre)  {

        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findById(chambre.getCategorie().getId());
        if (!categorieChambreOptional.isPresent()){
            return new ResponseEntity<>(new ErrorResponseModel("categorie not found"),HttpStatus.BAD_REQUEST);
        }

        Optional<TypeChambre> typeChambreOptional = typeChambreRepository.findById(chambre.getType().getId());

        if (!typeChambreOptional.isPresent())
                return new ResponseEntity<>(new ErrorResponseModel("type not found"), HttpStatus.BAD_REQUEST);

        Optional<Chambre> chambreOptional = chambreRepository.findByCategorieAndType(categorieChambreOptional.get(),typeChambreOptional.get());

        if(chambreOptional.isPresent()) {
            return new ResponseEntity<>(new ErrorResponseModel("chambre exist"), HttpStatus.BAD_REQUEST);
        }

        chambre = chambreRepository.save(chambre);
        //try {
        // chambre = chambreRepository.save(chambre);}
        //catch(Exception exception){
        // return new ReponseEntity<>(new ErrorResponseModel(exception.getMessage()), HttpStatus.BAD_REQUEST);
        //}
        return new ResponseEntity<>(chambre, HttpStatus.OK);

    }

    public List<Chambre> getChambre(){

        return chambreRepository.findAll();
    }

    public ResponseEntity<?> getOneChambre(int id){

        Optional<Chambre> chambreOptional = chambreRepository.findById(id);

        if (!chambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("chambre not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Chambre chambre = chambreOptional.get();
        return new ResponseEntity<>(chambre, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteChambre( int id ){

        Optional<Chambre> chambreOptional = chambreRepository.findById(id);

        if (!chambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("chambre not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        chambreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<?> updateChambre(int id,Chambre updateChambre) {

        Optional<Chambre> chambreOptional = chambreRepository.findById(id);


        if (!chambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("chambre not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        chambreRepository.save(updateChambre);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

