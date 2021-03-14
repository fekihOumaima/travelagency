package com.ditraacademy.travelagency.core.destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinationController {
    @Autowired
    DestinationServices destinationServices;

    @PostMapping("/destination")
    public ResponseEntity<?> createDestination(@RequestBody Destination destination){
        return destinationServices.createDestination(destination);
    }

    @GetMapping("/destination")
    public List<Destination> getUsers(){
        return destinationServices.getDestination();
    }


    @GetMapping("/destination/{id}")
    public ResponseEntity<?> getOneDestination(@PathVariable int id){
        return destinationServices.getOneDestination(id);
    }


    @DeleteMapping("/destination/{id}")
    public ResponseEntity<?> deleteDestination(@PathVariable int id ){
        return destinationServices.deleteDestination(id);
    }


    @PutMapping("/destination/{id}")
    public ResponseEntity<?> updateDestination(@PathVariable int id,@RequestBody Destination updateDestination) {
        return destinationServices.updateDestination(id,updateDestination);
    }
}
