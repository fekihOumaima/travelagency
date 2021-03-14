package com.ditraacademy.travelagency.core.destination;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DestinationServices {

    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<?> createDestination(Destination destination) {

        destination = destinationRepository.save(destination);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    public List<Destination> getDestination() {

        return destinationRepository.findAll();
    }

    public ResponseEntity<?> getOneDestination(int id) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);

        if (!destinationOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("destination not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        
        Destination destination = destinationOptional.get();
        return new ResponseEntity<>(destination, HttpStatus.OK);

    }

    public ResponseEntity<?> deleteDestination(int id) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (!destinationOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("destination not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        destinationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updateDestination(int id, Destination updateDestination) {

        Optional<Destination> destinationOptional = destinationRepository.findById(id);

        if (!destinationOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("destination not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Destination dataBaseDestination = destinationOptional.get();

        if (updateDestination.getNom() != null)
            dataBaseDestination.setNom(updateDestination.getNom());

        if (updateDestination.getDescription() != null)
            dataBaseDestination.setDescription(updateDestination.getDescription());

        destinationRepository.save(dataBaseDestination);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
