package com.ditraacademy.travelagency.core.voyage;
import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.core.destination.DestinationRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VoyageServices {
    
    @Autowired 
    VoyageRepository voyageRepository;
    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<?> createVoyage( Voyage voyage) {
        /*Optional<Destination> destinationOptional= destinationRepository.findById(voyage.getDestination().getId());
        if (!destinationOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("destination not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        voyage.setDestination(destinationOptional.get());*/

        voyage = voyageRepository.save(voyage);
        return new ResponseEntity<>(voyage, HttpStatus.OK);
    }


    public List<Voyage> getVoyages(){
        
        return voyageRepository.findAll();
    }

    public ResponseEntity<?> getOneVoyage(int id){

        Optional<Voyage> voyageOptional = voyageRepository.findById(id);

        if (!voyageOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("voyage not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        
        Voyage voyage = voyageOptional.get();
        return new ResponseEntity<>(voyage, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteVoyage( int id ){

        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        
        if (!voyageOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("voyage not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        voyageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updateVoyage(int id,Voyage updateVoyage) {

        Optional<Voyage> voyageOptional = voyageRepository.findById(id);


        if (!voyageOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("voyage not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }


        Voyage dataBaseVoyage = voyageOptional.get();
        if (updateVoyage.getTitre() != null)

            dataBaseVoyage.setTitre(updateVoyage.getTitre());

        if (updateVoyage.getDescription() != null)

            dataBaseVoyage.setDescription(updateVoyage.getDescription());

        if (updateVoyage.getNbPlaces() != null)

            dataBaseVoyage.setNbPlaces(updateVoyage.getNbPlaces());

        if (updateVoyage.getPrix() != null)

            dataBaseVoyage.setPrix(updateVoyage.getPrix());

        if (updateVoyage.getDate() != null)

            dataBaseVoyage.setDate(updateVoyage.getDate());


        voyageRepository.save(dataBaseVoyage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   /* public ResponseEntity<?> getInterval(Float min, Float max) {
        List<Voyage> voyageList = voyageRepository.findAllByPrixBetween(min,max) ;
        return new ResponseEntity<>(voyageList, HttpStatus.OK);

    }*/

    public ResponseEntity<?> getInterval() {

        List<Voyage> voyageList = voyageRepository.findAllByQuery(200,0) ;


        return new ResponseEntity<>(voyageList, HttpStatus.OK);

    }
}
