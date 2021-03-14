package com.ditraacademy.travelagency.core.voyage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class VoyageController {

    @Autowired
    VoyageServices voyageServices;

    @PostMapping("/voyage")
    public ResponseEntity<?> createVoyage(@RequestBody Voyage voyage){
        return voyageServices.createVoyage(voyage);
    }

//    @GetMapping("/voyage")
//    public List<Voyage> getVoyages(){
//        return voyageServices.getVoyages();
//    }


    @GetMapping("/voyage/{id}")
    public ResponseEntity<?> getOneVoyage(@PathVariable int id){
        return voyageServices.getOneVoyage(id);
    }


    @DeleteMapping("/voyage/{id}")
    public ResponseEntity<?> deleteVoyage(@PathVariable int id ){
        return voyageServices.deleteVoyage(id);
    }


    @PutMapping("/voyage/{id}")
    public ResponseEntity<?> updateVoyage(@PathVariable int id,@RequestBody Voyage updateVoyage) {
        return voyageServices.updateVoyage(id,updateVoyage);
    }

   /* @GetMapping("/voyage/byPrice")
    public ResponseEntity<?> getInterval(@RequestParam Float min,@RequestParam Float max){
        return voyageServices.getInterval(min,max);
    }*/

   @GetMapping("/voyage")
    public ResponseEntity<?> getInterval(){
        return voyageServices.getInterval();
    }

}
