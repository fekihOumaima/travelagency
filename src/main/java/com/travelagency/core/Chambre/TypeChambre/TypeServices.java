package com.ditraacademy.travelagency.core.Chambre.TypeChambre;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeServices {
    @Autowired
    TypeChambreRepository typeChambreRepository;

    public ResponseEntity<?> createTypeChambre(TypeChambre typeChambre)  {
        typeChambre = typeChambreRepository.save(typeChambre);
        return new ResponseEntity<>(typeChambre, HttpStatus.OK);
    }

    public ResponseEntity<?> getTypeChambre(int id){

        Optional<TypeChambre> typeChambreOptional = typeChambreRepository.findById(id);

        if (!typeChambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("type chambre not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        TypeChambre typeChambre = typeChambreOptional.get();
        return new ResponseEntity<>(typeChambre, HttpStatus.OK);
    }

    public  ResponseEntity<?> updateTypeChambre(int id, TypeChambre updateTypeChambre){

        Optional<TypeChambre> typeChambreOptional = typeChambreRepository.findById(id);

        if (!typeChambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("type chambre not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        typeChambreRepository.save(updateTypeChambre);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public  ResponseEntity<?> deleteTypeChambre(int id){

        Optional<TypeChambre> typeChambreOptional = typeChambreRepository.findById(id);

        if (!typeChambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("type chambre not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        typeChambreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
